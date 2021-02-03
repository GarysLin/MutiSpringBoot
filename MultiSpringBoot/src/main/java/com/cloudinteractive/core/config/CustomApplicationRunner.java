package com.cloudinteractive.core.config;

import com.auth0.jwk.SigningKeyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws SigningKeyNotFoundException {
        // 專案啟動時須執行的動作
    }

}
