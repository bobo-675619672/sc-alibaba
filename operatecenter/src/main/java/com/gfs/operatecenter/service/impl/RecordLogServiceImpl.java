package com.gfs.operatecenter.service.impl;

import com.gfs.operatecenter.dao.InterfaceRecordMapper;
import com.gfs.operatecenter.dao.PointsRecordMapper;
import com.gfs.operatecenter.entity.table.InterfaceRecord;
import com.gfs.operatecenter.entity.table.PointsRecord;
import com.gfs.operatecenter.entity.table.VoiceTestLog;
import com.gfs.operatecenter.entity.vo.dictdata.InterfaceRecordVo;
import com.gfs.operatecenter.entity.vo.dictdata.PointsRecordVo;
import com.gfs.operatecenter.service.RecordLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lcs
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service.impl
 * @ClassName RecordLogServiceImpl
 * @description  首页模块管理
 * @date created in 2020-05-13 14:47
 * @modified by
 */
@Slf4j
@Service
@Transactional
public class RecordLogServiceImpl implements RecordLogService {

    @Autowired
    PointsRecordMapper pointsRecordMapper;

    @Autowired
    InterfaceRecordMapper interfaceRecordMapper;

    @Override
    public int addPointsRecordLog(PointsRecordVo pointsRecordVo) {
        PointsRecord pointsRecord = new PointsRecord();
        BeanUtils.copyProperties(pointsRecordVo, pointsRecord);
        return pointsRecordMapper.insert(pointsRecord);
    }

    @Override
    public int addInterfaceRecordLog(InterfaceRecordVo interfaceRecordVo) {
        InterfaceRecord interfaceRecord = new InterfaceRecord();
        BeanUtils.copyProperties(interfaceRecordVo, interfaceRecord);
        return interfaceRecordMapper.insert(interfaceRecord);
    }
}
