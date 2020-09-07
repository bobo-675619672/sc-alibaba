package com.gfs.operatecenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gfs.common.constant.RedisConstant;
import com.gfs.operatecenter.dao.DictData.DictDataMapper;
import com.gfs.operatecenter.entity.table.DictData;
import com.gfs.operatecenter.entity.vo.dictdata.DictDataVoResponse;
import com.gfs.operatecenter.service.DictDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.service.impl
 * @ClassName DictDataServiceImpl
 * @description 字典
 * @date created in 2020-05-14 14:43
 * @modified by
 */
@Slf4j
@Service
@Transactional
public class DictDataServiceImpl  extends ServiceImpl<DictDataMapper, DictData> implements DictDataService {
    @Autowired
    private DictDataMapper dictDataMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 查询字典项
     * @param dictId
     * @param groupId
     * @return
     */
    @Override
    public DictDataVoResponse queryDictData(String dictId, String groupId) {
        Map<String, Object> map = new HashMap<>();
        map.put("groupId", groupId);
        // 根据字典组id查询出所有的字典项
        List<DictDataVoResponse> dictDatas = queryByGroupId(groupId);
        if (dictDatas.size() == 0) {
            log.info("字典组: {} 不存在", groupId);
            return null;
        }
        // 遍历判断前端传来dictId是否属于该字典列表
        for (DictDataVoResponse dictData : dictDatas) {
            if (dictId.equals(dictData.getDictId())) {
                return dictData;
            }
        }
        log.info("字典项: {} 不存在", dictId);
        return null;
    }

    /**
     * 查询某个字典组的所有字典值
     * @param groupId
     * @return
     */
    @Override
    public List<DictDataVoResponse> queryByGroupId(String groupId) {
        String key = RedisConstant.DICTDATA_KEY + groupId;
        // 缓存存在
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if (null != hasKey && hasKey) {
            // 获取缓存的json字符串的list
            String value = stringRedisTemplate.opsForValue().get(key);
            // 将json转化list
            List<DictDataVoResponse> list = JSONObject.parseArray(value, DictDataVoResponse.class);
            return list;
        }
        List<DictDataVoResponse> list = dictDataMapper.queryByGroupId(groupId);
        if (list != null && list.size() > 0) {
            String value = JSON.toJSONString(list);
            // 放入缓存，并设置缓存时间为7天
            stringRedisTemplate.opsForValue().set(key, value, RedisConstant.VALID_TIME, TimeUnit.SECONDS);
        }
        return list;
    }

    /**
     * 获取多个字典组的所有字典值
     * @param list
     * @return
     */
    @Override
    public Map<String, List<DictDataVoResponse>> queryByGroupIds(List<String> list) {
        Map<String, List<DictDataVoResponse>> mapRes = new HashMap<String, List<DictDataVoResponse>>();
        for (String item : list) {
            List<DictDataVoResponse> res = new ArrayList<DictDataVoResponse>();
            res = queryByGroupId(item);
            mapRes.put(item,res);
        }
        return mapRes;
    }


}
