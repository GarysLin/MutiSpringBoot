package com.cloudinteractive.auth.service.impl;

import com.cloudinteractive.auth.security.JwtUserFactory;
import com.cloudinteractive.auth.model.Authority;
import com.cloudinteractive.auth.model.AuthorityName;
import com.cloudinteractive.auth.model.User;
import com.cloudinteractive.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 這邊做取得設定User資料及Authorities
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority(AuthorityName.ROLE_USER));
        User user = new User(username, new BCryptPasswordEncoder().encode(username), authorities);

//        if (!username.equals(username)) {
//            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
//        } else {
//            return JwtUserFactory.create(user);
//        }
        return JwtUserFactory.create(user);
    }
}
