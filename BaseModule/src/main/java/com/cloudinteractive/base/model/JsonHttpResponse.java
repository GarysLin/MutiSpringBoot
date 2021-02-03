package com.cloudinteractive.base.model;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JsonHttpResponse extends BaseHttpResponse {
    @ApiModelProperty(example = "{}", value = "回傳內容")
    private JSONObject result_data;
}



