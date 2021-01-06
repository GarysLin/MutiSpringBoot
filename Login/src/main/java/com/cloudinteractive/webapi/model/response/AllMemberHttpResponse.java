package com.cloudinteractive.webapi.model.response;

import com.cloudinteractive.core.model.BaseHttpResponse;
import com.cloudinteractive.webapi.model.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AllMemberHttpResponse extends BaseHttpResponse {
    @ApiModelProperty(example = "", value = "回傳內容")
    private List<Member> resultData;
}
