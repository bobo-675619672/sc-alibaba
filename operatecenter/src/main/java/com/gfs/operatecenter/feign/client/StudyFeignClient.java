package com.gfs.operatecenter.feign.client;

import com.gfs.common.bean.ResponseMsg;
import com.gfs.operatecenter.feign.fallback.StudyFeignClientFallbackFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.feign.client
 * @ClassName StudyFeignClient
 * @description
 * @date created in 2020-05-18 15:50
 * @modified by
 */
@FeignClient(value = "studycenter", fallbackFactory = StudyFeignClientFallbackFactory.class)
public interface StudyFeignClient {

    /**
     * 查询学生是否有未完成的作业
     * @param studentId
     * @return
     */
    @GetMapping("/pc/task/queryNotDoneCount")
    ResponseMsg<Integer> queryNotDoneCount(@RequestParam("studentId") Long studentId);

    /**
     * 查询学生完成作业份数
     * @param studentId
     * @return
     */
    @GetMapping("/pc/task/queryTaskCount")
    ResponseMsg<Integer> queryTaskCount(@RequestParam("studentId") Long studentId);
}
