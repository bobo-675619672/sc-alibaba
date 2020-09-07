package com.gfs.operatecenter.feign.fallback;

import com.gfs.common.bean.ResponseMsg;
import com.gfs.common.enums.ResultEnum;
import com.gfs.operatecenter.entity.vo.feign.IsVipVo;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import com.gfs.operatecenter.feign.client.MallFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 商城Feign熔断处理Factory
 * @author liaodewen
 */
@Slf4j
@Component
public class MallFeignClientFallbackFactory implements FallbackFactory<MallFeignClient> {

    @Override
    public MallFeignClient create(Throwable throwable) {
        log.error("商城熔断: {}", throwable.getMessage());
        return new MallFeignClient() {
            @Override
            public ResponseMsg<Integer> countExperienceByUserId(Long userId) {
                throw new GfsRuntimeException(ResultEnum.MALL_DISCONNECT);
            }

            @Override
            public ResponseMsg<IsVipVo> queryVipDetails(Long userId) {
                throw new GfsRuntimeException(ResultEnum.MALL_DISCONNECT);
            }
        };
    }

}
