package com.cloudinteractive.auth.rest;

import com.cloudinteractive.account.model.request.AuthLoginRequest;
import com.cloudinteractive.auth.model.JwtAuthentication;
import com.cloudinteractive.auth.model.User;
import com.cloudinteractive.auth.model.response.JwtAuthenticationResponse;
import com.cloudinteractive.auth.security.JwtTokenUtil;
import com.cloudinteractive.auth.service.AuthService;
import com.cloudinteractive.core.controller.BaseRestController;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = {"Auth API"})
public class AuthRestController extends BaseRestController {
    @Value("${jwt.expiration}")
    private Long expiration;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthService authService;

    @ApiOperation(("login"))
    @ApiImplicitParam(dataType = "AuthLoginRequest", dataTypeClass = AuthLoginRequest.class, name = "requestBdy", value = "requestBdy", required = true, paramType = "body")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):失敗"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody AuthLoginRequest requestBdy) throws Exception {
        String account = requestBdy.getAccount();
        String password = requestBdy.getPassword();

        User user = authService.login(account, password);
        if (user == null || !user.getEnabled()) return ResponseEntity.ok(JwtAuthenticationResponse.builder().result_code("9999").build());

        String access_token = jwtTokenUtil.generateToken(account);

        JwtAuthentication jwt = JwtAuthentication.builder()
                .access_token(access_token).build();

        JwtAuthenticationResponse res = JwtAuthenticationResponse.builder()
                .result_code("0000").result_data(jwt).build();
        return ResponseEntity.ok(res);
    }
}
