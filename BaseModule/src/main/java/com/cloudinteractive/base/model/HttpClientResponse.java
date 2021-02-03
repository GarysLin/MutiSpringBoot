package com.cloudinteractive.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HttpClientResponse<T> {
    @ApiModelProperty(example = "200", value = "回傳代碼")
    private int statusCode;
    @ApiModelProperty(example = "", value = "回傳資料")
    private T response;

    public HttpClientResponse(int statusCode, T response) {
        this.statusCode = statusCode;
        this.response = response;
    }
}
