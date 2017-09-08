package com.mangocore.biz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by notreami on 17/6/24.
 */
@Slf4j
@Service
public class SimpleService {
//    @Resource
//    private SimpleDao simpleDao;
//
//    public List<SimpleDomain> querySimpleDomainByAll() {
//        return simpleDao.selectSimpleDomainByAll();
//    }
//
//    public CommonResult getStatus() {
//        SysStatus sysStatus = new SysStatus();
//
//        sysStatus.setEngineering(SysUtils.getProjectName());
//
//        ZonedDateTime zonedDateTime = ZonedDateTime.now();
//        ZoneId zoneId = zonedDateTime.getZone();
//        sysStatus.setDatetime(zoneId.getDisplayName(TextStyle.FULL, Locale.ROOT)
//                + "(" + zoneId.getDisplayName(TextStyle.SHORT, Locale.ROOT) + ")  " + zonedDateTime);
//
//        sysStatus.setJavaVersion(System.getProperty("java.version"));
//
//        return CommonResult.get(CommonResultEnum.OK, sysStatus);
//    }
}
