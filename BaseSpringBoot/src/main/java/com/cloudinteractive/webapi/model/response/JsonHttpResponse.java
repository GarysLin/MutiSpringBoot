package com.cloudinteractive.webapi.model.response;

import com.alibaba.fastjson.JSONObject;
import com.cloudinteractive.core.model.BaseHttpResponse;
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
    private JSONObject resultData;
}



