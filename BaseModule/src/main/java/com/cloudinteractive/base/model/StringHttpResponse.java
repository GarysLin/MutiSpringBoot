package com.cloudinteractive.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StringHttpResponse extends BaseHttpResponse {
    @ApiModelProperty(example = "", value = "回傳內容")
    private String result_data;
}
