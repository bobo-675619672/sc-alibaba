package com.gfs.operatecenter.service;

import com.gfs.operatecenter.entity.vo.dictdata.InterfaceRecordVo;
import com.gfs.operatecenter.entity.vo.dictdata.PointsRecordVo;
import com.gfs.operatecenter.entity.vo.qiniu.QiniuVoRequest;
import com.gfs.operatecenter.entity.vo.qiniu.QiniuVoResponse;

import java.io.IOException;
import java.util.Set;

/**
 * 日志记录
 * @author lcs
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service.impl
 * @ClassName PointRecordService
 * @description
 * @date created in 2020-08-19 14:01
 * @modified by
 */
public interface RecordLogService {
    /**
     * 埋点日志
     * @return
     */
    int addPointsRecordLog(PointsRecordVo pointsRecordVo);

    /**
     * 错误、超时访问日志
     * @return
     */
    int addInterfaceRecordLog(InterfaceRecordVo interfaceRecordVo);
}
