package com.cloudinteractive.auth.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class JwtAuthentication {
    @ApiModelProperty(example = "", value = "access_token")
    private String access_token;
    @ApiModelProperty(example = "", value = "refresh_token")
    private String refresh_token;
    @Builder.Default
    @ApiModelProperty(example = "", value = "token_type")
    private String token_type = "JWT";
    @ApiModelProperty(example = "", value = "expires_in")
    private long expires_in;
    @Builder.Default
    @ApiModelProperty(example = "", value = "id")
    private String scope = "none";
}
