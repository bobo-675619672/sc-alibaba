package com.dw.starter.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class SmsService {

    public String host;

    public String key;

    public void sendMsg(String message) {
        log.info("发送短信! -> Host: {}, Key: {}, Message: {}", host, key, message);
    }

}
