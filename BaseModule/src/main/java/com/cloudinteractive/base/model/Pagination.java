package com.cloudinteractive.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pagination {
    @ApiModelProperty(example = "", value = "目前在第幾頁")
    private String current_page;
    @ApiModelProperty(example = "", value = "總頁數")
    private String last_page;
    @ApiModelProperty(example = "", value = "本頁資料是第幾筆開始")
    private String from;
    @ApiModelProperty(example = "", value = "本頁資料是第幾筆結束")
    private String to;
    @ApiModelProperty(example = "", value = "總筆數")
    private String total;
}
