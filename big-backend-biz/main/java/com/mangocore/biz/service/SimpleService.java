package com.mangocore.biz.service;

import com.mangocore.data.dao.SimpleDao;
import com.mangocore.data.domain.SimpleDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by notreami on 17/6/24.
 */
@Slf4j
@Service
public class SimpleService {
    @Resource
    private SimpleDao simpleDao;

    public List<SimpleDomain> selectSimpleDomainByAll() {
        return simpleDao.selectSimpleDomainByAll();
    }

    public Date selectSysDate() {
        return simpleDao.selectSysDate();
    }
}
