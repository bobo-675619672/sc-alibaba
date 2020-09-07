package com.gfs.operatecenter.controller;

import com.gfs.common.base.BaseController;
import com.gfs.common.bean.ResponseMsg;
import com.gfs.operatecenter.entity.vo.VoiceTestLogVoRequest;
import com.gfs.operatecenter.service.VoiceTestLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhangjie
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.controller
 * @ClassName VoiceTestLogController
 * @description
 * @date created in 2020-07-21 14:11
 * @modified by
 */
@Api(tags = "七牛")
@RestController
@RequestMapping("/pc/voiceTestLog")
public class VoiceTestLogController extends BaseController {

    @Autowired
    private VoiceTestLogService voiceTestLogService;

    @ApiOperation(value = "添加语音评测日志")
    @PostMapping("/addVoiceTestLog")
    public ResponseMsg funtion(@RequestBody @Valid VoiceTestLogVoRequest voiceTestLogVoRequest){
        return success(voiceTestLogService.addVoiceTestLog(voiceTestLogVoRequest));
    }
}
