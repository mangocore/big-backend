package com.mangocore.data.mapper;


import com.mangocore.data.domain.SimpleDomain;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by notreami on 17/6/24.
 */
@Repository
public interface SimpleMapper {

    List<SimpleDomain> selectSimpleDomainByAll();

    @Select("SELECT SYSDATE() FROM DUAL")
    Date selectSysDate();
}
