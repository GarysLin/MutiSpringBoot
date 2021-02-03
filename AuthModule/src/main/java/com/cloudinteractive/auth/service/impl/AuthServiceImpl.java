package com.cloudinteractive.auth.service.impl;

import com.cloudinteractive.auth.model.JwtAuthentication;
import com.cloudinteractive.auth.model.User;
import com.cloudinteractive.auth.security.JwtTokenUtil;
import com.cloudinteractive.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService {
    Logger logger = LoggerFactory.getLogger(AuthService.class);



    @Override
    public User login(String account, String password) {
        if (!"Apple".equals(account) || !"Aa12345".equals(password)) return null;

        return new User(account, password, null);
    }
}
