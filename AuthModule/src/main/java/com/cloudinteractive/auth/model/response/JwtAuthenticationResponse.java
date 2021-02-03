package com.cloudinteractive.auth.model.response;

import com.cloudinteractive.auth.model.JwtAuthentication;
import com.cloudinteractive.base.model.BaseHttpResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JwtAuthenticationResponse extends BaseHttpResponse {
    @ApiModelProperty(example = "", value = "回傳內容")
    private JwtAuthentication result_data;
}
