package com.gfs.operatecenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gfs.operatecenter.entity.table.DictData;
import com.gfs.operatecenter.entity.vo.dictdata.DictDataVoResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service
 * @ClassName DictDataService
 * @description 字典
 * @date created in 2020-05-14 14:36
 * @modified by
 */
public interface DictDataService extends IService<DictData> {
    /**
     * 查询字典项
     * @param dictId
     * @param groupId
     * @return
     */
    DictDataVoResponse queryDictData(String dictId,String groupId);

    /**
     * 查询某个字典组的所有字典值
     * @param groupId
     * @return
     */
    List<DictDataVoResponse>queryByGroupId(String groupId);

    /**
     * 获取多个字典组的所有字典值
     * @param list
     * @return
     */
    Map<String,List<DictDataVoResponse>> queryByGroupIds(List<String> list);



}
