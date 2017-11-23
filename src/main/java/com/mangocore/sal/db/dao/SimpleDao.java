package com.mangocore.sal.db.dao;

import com.mangocore.sal.db.domain.SimpleDomain;
import com.mangocore.sal.db.mapper.SimpleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by notreami on 17/6/24.
 */
@Slf4j
@Component
public class SimpleDao {
    @Resource
    private SimpleMapper simpleMapper;

    public List<SimpleDomain> selectSimpleDomainByAll() {
        return simpleMapper.selectSimpleDomainByAll();
    }

    public Date selectSysDate(){
        return simpleMapper.selectSysDate();
    }
}
