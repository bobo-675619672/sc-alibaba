package com.gfs.operatecenter.dao.DictData;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gfs.operatecenter.entity.table.DictData;
import com.gfs.operatecenter.entity.table.HomeModule;
import com.gfs.operatecenter.entity.vo.dictdata.DictDataVoResponse;
import com.gfs.operatecenter.entity.vo.homemodule.HomeModuleVo;
import com.gfs.operatecenter.entity.vo.homemodule.HomePageVoRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.dao.HomeModule
 * @ClassName HomeModuleMapper
 * @description
 * @date created in 2020-05-13 16:09
 * @modified by
 */
public interface DictDataMapper extends BaseMapper<DictData> {


    /**
     * 查询某个字典组的所有字典值
     * @param groupId
     * @return
     */
    List<DictDataVoResponse> queryByGroupId(@Param("groupId") String groupId);

}
