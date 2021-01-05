package com.cloudinteractive.core.model;

import com.cloudinteractive.webapi.utils.DateTimeUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.context.i18n.LocaleContextHolder;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseHttpResponse {
    @Builder.Default
    @ApiModelProperty(example = "0000", value = "回傳代碼")
    private String resultCode = "9999";
    @Builder.Default
    @ApiModelProperty(example = "test", value = "回傳訊息")
    private String resultMessage = "";
    @Builder.Default
    @ApiModelProperty(example = "2020-08-07 14:03:07.961", value = "回傳時間")
    private String responseDatetime =  DateTimeUtil.getNowStringMs();
    @Builder.Default
    @ApiModelProperty(example = "", value = "回傳代碼2")
    private String subResultCode = "";
    @Builder.Default
    @ApiModelProperty(example = "", value = "回傳訊息2")
    private String subResultMessage = "";
    @Builder.Default
    @ApiModelProperty(example = "", value = "備註")
    private String info = "";
    @Builder.Default
    @ApiModelProperty(example = "zh-tw", value = "語系")
    private String lang = LocaleContextHolder.getLocale().toString();
}
