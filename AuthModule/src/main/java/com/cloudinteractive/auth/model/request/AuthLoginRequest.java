package com.cloudinteractive.account.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthLoginRequest {
    @ApiModelProperty(example = "Apple", value = "帳號")
    private String account;
    @ApiModelProperty(example = "Aa12345", value = "密碼")
    private String password;
}
