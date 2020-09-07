package com.gfs.operatecenter.service.impl;

import com.gfs.common.enums.ResultEnum;
import com.gfs.operatecenter.dao.VoiceTestLogMapper;
import com.gfs.operatecenter.entity.table.VoiceTestLog;
import com.gfs.operatecenter.entity.vo.VoiceTestLogVoRequest;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import com.gfs.operatecenter.service.VoiceTestLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hugaohui
 * @ProjectName gaofenshuo-parent
 * @Package com.gaofenshuo.service.systemmanager.data.impl
 * @ClassName VoiceTestLogServiceImpl
 * @description
 * @date created in 2019-02-25 15:28
 * @modified by
 */
@Service
public class VoiceTestLogServiceImpl implements VoiceTestLogService {

    @Autowired
    VoiceTestLogMapper voiceTestLogMapper;

    @Override
    public int addVoiceTestLog(VoiceTestLogVoRequest voiceTestLogVoRequest) {
        try {
            VoiceTestLog voiceTestLog = new VoiceTestLog();
            BeanUtils.copyProperties(voiceTestLogVoRequest, voiceTestLog);
            return voiceTestLogMapper.insert(voiceTestLog);
        } catch (Exception e) {
            throw new GfsRuntimeException(e, ResultEnum.SQL_INSERT_ERROR, "新增语音评测日志失败");
        }
    }
}
