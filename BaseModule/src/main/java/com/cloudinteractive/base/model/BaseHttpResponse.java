package com.cloudinteractive.base.model;

import com.cloudinteractive.base.utils.DateTimeUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.context.i18n.LocaleContextHolder;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class BaseHttpResponse {
    @Builder.Default
    @ApiModelProperty(example = "0000", value = "回傳代碼")
    private String result_code = "9999";
    @Builder.Default
    @ApiModelProperty(example = "test", value = "回傳訊息")
    private String result_message = "";
    @Builder.Default
    @ApiModelProperty(example = "2020-08-07 14:03:07.961", value = "回傳時間")
    private String response_time =  DateTimeUtil.getNowStringMs();
    @Builder.Default
    @ApiModelProperty(example = "", value = "回傳代碼2")
    private String sub_result_code = "";
    @Builder.Default
    @ApiModelProperty(example = "", value = "回傳訊息2")
    private String sub_result_message = "";
    @Builder.Default
    @ApiModelProperty(example = "", value = "備註")
    private String info = "";
    @Builder.Default
    @ApiModelProperty(example = "", value = "jwt token")
    private String token = "";
//    @Builder.Default
//    @ApiModelProperty(example = "zh-tw", value = "語系")
//    private String lang = LocaleContextHolder.getLocale().toString();
}
