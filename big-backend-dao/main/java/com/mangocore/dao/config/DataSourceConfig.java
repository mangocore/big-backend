package com.mangocore.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Properties;

/**
 * Created by notreami on 17/5/8.
 */
public class DataSourceConfig {
    /**
     * Druid数据源统一配置
     *
     * @param name
     * @param driverClassName
     * @param jdbcUrl
     * @param userName
     * @param password
     * @return
     */
    public static DruidDataSource getDruidDataSource(String name, String driverClassName, String jdbcUrl, String userName, String password) throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName(name);//数据源名
        druidDataSource.setDriverClassName(driverClassName);//driver的类名，默认从jdbc url中自动探测
        druidDataSource.setUrl(jdbcUrl);//指定JDBC URL
        druidDataSource.setUsername(userName);//数据库用户名
        druidDataSource.setPassword(password);//数据库密码

        druidDataSource.setDefaultAutoCommit(false);//自动提交
        druidDataSource.setInitialSize(5);//初始化时建立物理连接的个数
        druidDataSource.setMaxActive(80);//最大连接池数量 缺省:8
        druidDataSource.setMinIdle(5);//最小连接池数量
        druidDataSource.setMaxWait(6000);//获取连接时最大等待时间，单位毫秒
        druidDataSource.setPoolPreparedStatements(false);//PSCache
        druidDataSource.setMaxOpenPreparedStatements(-1);//20
        druidDataSource.setValidationQuery("SELECT SYSDATE() FROM DUAL");//用来检测连接是否有效的sql，要求是一个查询语句
        druidDataSource.setTestOnBorrow(false);//申请连接时执行validationQuery检测连接是否有效
        druidDataSource.setTestOnReturn(false);//归还连接时执行validationQuery检测连接是否有效
        druidDataSource.setTestWhileIdle(true);//空闲时连接检测
        druidDataSource.setTimeBetweenEvictionRunsMillis(3600000);//配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(3600000);//配置一个连接在池中最小生存的时间，单位是毫秒
        druidDataSource.setConnectionInitSqls(Collections.singletonList("set names utf8mb4;"));//如果需要支持utf8mb4编码的话打开
        druidDataSource.setFilters("stat,wall,slf4j");//监控统计
        druidDataSource.setConnectionProperties(
                "druid.stat.slowSqlMillis=3000;druid.stat.logSlowSql=true;config.decrypt=false;" +
                "druid.stat.mergeSql=true;druid.logType=slf4j;" +
                        "druid.filters=mergeStat;druid.useGlobalDataSourceStat=true");//慢SQL标准
        druidDataSource.setRemoveAbandoned(true);//打开removeAbandoned功能(对性能会有一些影响，建议怀疑存在泄漏之后再打开)
        druidDataSource.setRemoveAbandonedTimeout(1800);//超过30分钟开始关闭空闲连接
        druidDataSource.setLogAbandoned(true);//将当前关闭动作记录到日志
        druidDataSource.setQueryTimeout(30);//查询超时时间
        druidDataSource.setTransactionQueryTimeout(30);//事务查询超时时间
        druidDataSource.setLoginTimeout(6);//登录超时时间
        return druidDataSource;
    }

    /**
     * 分页插件
     * 分页说明:http://git.oschina.net/free/Mybatis_PageHelper/blob/master/wikis/HowToUse.markdown
     *
     * @return
     */
    public static PageInterceptor getPageHelper() {
        Properties properties = new Properties();
        //连接的数据库设置,4.0.0以后版本可以不设置该参数
        properties.setProperty("helperDialect", "mysql");
        //默认为false
        //设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
        // 和startPage中的pageNum效果一样,可以用页码和页面大小两个参数进行分页
        properties.setProperty("offsetAsPageNum", "true");
        //默认为false
        //设置为true时，使用RowBounds分页会进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果
        //（相当于没有执行分页查询，但是返回结果仍然是Page类型）
        properties.setProperty("pageSizeZero", "true");
        //分页参数合理化，默认false禁用
        //启用合理化时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        //禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
        properties.setProperty("reasonable", "true");
        //为了支持startPage(Object params)方法
        // 增加了一个`params`参数来配置参数映射，用于从Map或ServletRequest中取值
        //可以配置pageNum,pageSize,count,pageSizeZero,reasonable,orderBy,不配置映射的用默认值
        //不理解该含义的前提下，不要随便复制该配置
//        properties.setProperty("params", "pageNum=pageHelperStart;pageSize=pageHelperRows;");
        //支持通过Mapper接口参数来传递分页参数
        properties.setProperty("supportMethodsArguments", "false");
        //设置为 true 时，允许在运行时根据多数据源自动识别对应方言的分页
        properties.setProperty("autoRuntimeDialect", "true");
        properties.setProperty("closeConn", "true");

        PageInterceptor pageInterceptor = new PageInterceptor();

        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
