package com.gfs.operatecenter.service;

import com.gfs.operatecenter.entity.vo.VoiceTestLogVoRequest;

/**
 * @author hugaohui
 * @ProjectName gaofenshuo-parent
 * @Package com.gaofenshuo.service.systemmanager.data
 * @ClassName VoiceTestLogService
 * @description 语音评测日志
 * @date created in 2019-02-25 15:04
 * @modified by
 */
public interface VoiceTestLogService {
    /**
     * 添加语音评测日志
     * @param voiceTestLogVoRequest
     * @return
     */
  int addVoiceTestLog(VoiceTestLogVoRequest voiceTestLogVoRequest);
}
