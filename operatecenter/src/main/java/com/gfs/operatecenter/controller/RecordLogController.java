package com.gfs.operatecenter.controller;

import com.gfs.common.base.BaseController;
import com.gfs.common.bean.ResponseMsg;
import com.gfs.operatecenter.entity.vo.dictdata.InterfaceRecordVo;
import com.gfs.operatecenter.entity.vo.dictdata.PointsRecordVo;
import com.gfs.operatecenter.service.RecordLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author lanchangsheng
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.controller
 * @ClassName PointRecordController
 * @description 日志记录
 * @date created in 2020-05-14 13:55
 * @modified by
 */
@Api(tags = "日志记录")
@RestController
@RequestMapping("pc/recordlog")
public class RecordLogController extends BaseController {
    @Autowired
    private RecordLogService pointRecordService;

    @ApiOperation(value = "添加埋点日志")
    @PostMapping("/addPointsRecord")
    public ResponseMsg funtion(@RequestBody @Valid PointsRecordVo pointsRecordVo){
        return success(pointRecordService.addPointsRecordLog(pointsRecordVo));
    }

    @ApiOperation(value = "添加错误，超时 访问日志")
    @PostMapping("/addInterfaceRecordLog")
    public ResponseMsg funtion(@RequestBody @Valid InterfaceRecordVo interfaceRecordVo){
        return success(pointRecordService.addInterfaceRecordLog(interfaceRecordVo));
    }

}
