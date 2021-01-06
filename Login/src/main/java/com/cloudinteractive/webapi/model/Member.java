package com.cloudinteractive.webapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class Member {
    @ApiModelProperty(example = "A123456789", value = "id")
    private String id;
    @ApiModelProperty(example = "大A", value = "姓名")
    private String name;
}
