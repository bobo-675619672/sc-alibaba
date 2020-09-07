package com.gfs.operatecenter.controller;

import com.gfs.common.base.BaseController;
import com.gfs.common.bean.ResponseMsg;
import com.gfs.operatecenter.entity.vo.qiniu.QiniuVoRequest;
import com.gfs.operatecenter.entity.vo.qiniu.QiniuVoResponse;
import com.gfs.operatecenter.service.QiniuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.controller
 * @ClassName QiniuController
 * @description
 * @date created in 2020-06-05 13:52
 * @modified by
 */
@Api(tags = "七牛")
@RestController
@RequestMapping("/pc/qiniu")
public class QiniuController extends BaseController {
    @Autowired
    private QiniuService qiniuService;

    @ApiOperation(value = "获取七牛上传的token")
    @GetMapping("/queryUploadToken")
    public ResponseMsg<QiniuVoResponse> getToken() {
        return success(this.qiniuService.clientUploadToken());
    }


    @ApiOperation(value = "保存七牛临时上传文件名")
    @PostMapping("/saveTempFile")
    public ResponseMsg<Set<String>> saveTempFile(
            @Valid @RequestBody QiniuVoRequest qiNiu) {
        return success(this.qiniuService.saveTempFile(qiNiu));
    }

    @ApiOperation(value = "删除七牛云云空间储的文件")
    @GetMapping("/deleteFileByName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "删除文件名", required = false, example = "http://xx.gfs100.cn/test.jpg")})
    public ResponseMsg<String> deleteFileByName(@RequestParam("fileName") String fileName) {
        return success(this.qiniuService.deleteFileByName(fileName));
    }

    @ApiOperation(value = "删除七牛未使用的文件")
    @PostMapping("/deleteUnUseFiles")
    public ResponseMsg<String> deleteUnUseFiles(
            @Valid @RequestBody QiniuVoRequest qiNiu) {
        return success(this.qiniuService.deleteUnUseFiles(qiNiu));
    }

}
