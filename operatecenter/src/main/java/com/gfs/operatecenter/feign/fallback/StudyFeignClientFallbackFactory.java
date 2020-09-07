package com.gfs.operatecenter.feign.fallback;

import com.gfs.common.bean.ResponseMsg;
import com.gfs.common.enums.ResultEnum;
import com.gfs.operatecenter.exception.GfsRuntimeException;
import com.gfs.operatecenter.feign.client.StudyFeignClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 学习中心Feign熔断处理Factory
 * @author liaodewen
 */
@Slf4j
@Component
public class StudyFeignClientFallbackFactory implements FallbackFactory<StudyFeignClient> {

    @Override
    public StudyFeignClient create(Throwable throwable) {
        log.error("学习中心熔断: {}", throwable.getMessage());
        return new StudyFeignClient() {

            @Override
            public ResponseMsg<Integer> queryNotDoneCount(Long studentId) {
                throw new GfsRuntimeException(ResultEnum.STUDYCENTER_DISCONNECT);
            }
            @Override
            public ResponseMsg<Integer> queryTaskCount(Long studentId) {
                throw new GfsRuntimeException(ResultEnum.STUDYCENTER_DISCONNECT);
            }
        };
    }

}
