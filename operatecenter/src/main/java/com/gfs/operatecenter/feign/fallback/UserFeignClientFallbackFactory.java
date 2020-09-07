package com.gfs.operatecenter.feign.fallback;

import com.gfs.common.bean.ResponseMsg;
import com.gfs.common.enums.ResultEnum;
import com.gfs.operatecenter.entity.vo.feign.StudentInfoResponseVo;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import com.gfs.operatecenter.feign.client.UserFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户中心Feign熔断处理Factory
 * @author liaodewen
 */
@Slf4j
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable throwable) {
        log.error("用户中心熔断: {}", throwable.getMessage());
        return new UserFeignClient() {


            @Override
            public ResponseMsg<StudentInfoResponseVo> info() {
                throw new GfsRuntimeException(ResultEnum.USERCENTER_DISCONNECT);
            }

            @Override
            public ResponseMsg<String> checkToken() {
                throw new GfsRuntimeException(ResultEnum.USERCENTER_DISCONNECT);
            }
        };
    }

}
