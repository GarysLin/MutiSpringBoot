package com.cloudinteractive.account.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestPostMethodRequest {
    @ApiModelProperty(example = "Apple", value = "使用者")
    private String user;
}
