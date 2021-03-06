package com.mangocore.data.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mangocore.data.database.domain.SimpleDomain;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Mybatis配置,设置dao所在的package路径,关联注解在dao类上的Annotation名字
 * Created by notreami on 16/8/15.
 */
@Configuration
@MapperScan(sqlSessionTemplateRef = "sqlSessionTemplate", basePackages = "com.mangocore.data.database.mapper")
public class MybatisConfig {
    //数据库连接相关的参数：
    @Value("${custom.datasource.name}")
    private String name;
    @Value("${custom.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${custom.datasource.jdbc-url}")
    private String jdbcUrl;
    @Value("${custom.datasource.username}")
    private String userName;
    @Value("${custom.datasource.password}")
    private String password;

    @Bean(name = "dataSource", destroyMethod = "close")
    public DruidDataSource dataSource() throws SQLException {
        return DataSourceConfig.getDruidDataSource(name, driverClassName, jdbcUrl, userName, password);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //分页插件
        bean.setPlugins(new Interceptor[]{DataSourceConfig.getPageHelper()});
        //Mybatis配置
        bean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));//mybatis配置文件的位置
        //指定mapper xml目录
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));//mapping位置
        bean.setTypeAliasesPackage(SimpleDomain.class.getPackage().getName());//扫描别名扫描包路径
        return bean.getObject();
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "dataSourceTransactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
