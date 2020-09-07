package com.gfs.operatecenter.feign.fallback;

import com.gfs.common.bean.ResponseMsg;
import com.gfs.common.enums.ResultEnum;
import com.gfs.operatecenter.entity.vo.feign.AppVideoVoResponse;
import com.gfs.operatecenter.entity.vo.feign.H5ArticleBookPageVo;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import com.gfs.operatecenter.feign.client.ContentFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hugaohui
 * @ProjectName gfsCloud
 * @Package com.gfs.operatecenter.feign.fallback
 * @ClassName ContentFeignClientFallbackFactory
 * @description 内容中心Feign熔断处理Factory
 * @date created in 2020-07-13 15:11
 * @modified by
 */
@Slf4j
@Component
public class ContentFeignClientFallbackFactory implements FallbackFactory<ContentFeignClient> {
    @Override
    public ContentFeignClient create(Throwable throwable) {
        log.error("内容中心熔断: {}", throwable.getMessage());
        return new ContentFeignClient() {
            @Override
            public ResponseMsg<List<H5ArticleBookPageVo>> articleBookRelevantRecommend(){
                throw new GfsRuntimeException(ResultEnum.CONTENTCENTER_DISCONNECT);
            }
            @Override
            public ResponseMsg<List<AppVideoVoResponse>> dubbingRelevantRecommend(){
                throw new GfsRuntimeException(ResultEnum.CONTENTCENTER_DISCONNECT);
            }
        };
    }
}
