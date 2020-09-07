package com.gfs.operatecenter.feign.client;

import com.gfs.common.bean.ResponseMsg;
import com.gfs.operatecenter.entity.vo.feign.AppVideoVoResponse;
import com.gfs.operatecenter.entity.vo.feign.H5ArticleBookPageVo;
import com.gfs.operatecenter.feign.fallback.ContentFeignClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.feign.client
 * @ClassName ContentFeignClient
 * @description
 * @date created in 2020-07-13 15:10
 * @modified by
 */
@FeignClient(value = "contentcenter", fallbackFactory = ContentFeignClientFallbackFactory.class)
public interface ContentFeignClient {
    /**
     * 随机推荐篇章
     * @return
     */
    @GetMapping("pc/h5 /ArticleBook/relevantRecommend")
     ResponseMsg<List<H5ArticleBookPageVo>> articleBookRelevantRecommend();

    /**
     * 随机推荐配音视频
     * @return
     */
    @GetMapping("pc/dubbing/relevantRecommend")
     ResponseMsg<List<AppVideoVoResponse>> dubbingRelevantRecommend();
}
