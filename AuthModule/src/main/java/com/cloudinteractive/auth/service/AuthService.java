package com.cloudinteractive.auth.service;

import com.cloudinteractive.auth.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    User login(String account, String password);

}
