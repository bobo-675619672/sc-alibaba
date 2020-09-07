package com.gfs.operatecenter.controller;

import com.gfs.common.base.BaseController;
import com.gfs.common.bean.ResponseMsg;
import com.gfs.operatecenter.entity.vo.dictdata.DictDataVoResponse;
import com.gfs.operatecenter.service.DictDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.controller
 * @ClassName DictDataController
 * @description 字典
 * @date created in 2020-05-14 13:55
 * @modified by
 */
@Api(tags = "字典")
@RestController
@RequestMapping("pc/dictData")
public class DictDataController extends BaseController {
    @Autowired
    DictDataService dictDataService;

    @GetMapping("/queryDictData")
    @ApiOperation(value = "查询字典项")
    public ResponseMsg<DictDataVoResponse> queryDictData( @RequestParam("groupId") String groupId, @RequestParam("dictId") String dictId) {
        return success(dictDataService.queryDictData(dictId, groupId));
    }

    @GetMapping("/queryDictDatas")
    @ApiOperation(value = "查询某个字典组的所有字典值")
    public ResponseMsg<List<DictDataVoResponse>> queryDictDatas(@RequestParam("groupId") String groupId) {
        return success(dictDataService.queryByGroupId(groupId));
    }

    @PostMapping("/queryByGroupIds")
    @ApiOperation(value = "获取多个字典组的所有字典值")
    public ResponseMsg<Map<String,List<DictDataVoResponse>>> queryByGroupIds(@RequestBody List<String> list) {
        return success(dictDataService.queryByGroupIds(list));
    }

}
