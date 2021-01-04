package com.cloudinteractive.webapi.model.response;
import com.cloudinteractive.webapi.utils.DateTimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@ApiModel
@SuperBuilder
@NoArgsConstructor
public class DefaultHttpResponse<T> {
    @Builder.Default
    @ApiModelProperty(example = "0000", value = "回傳代碼")
    private String resultCode = "9999";
    @Builder.Default
    @ApiModelProperty(example = "test", value = "回傳訊息")
    private String resultMessage = "";
    @ApiModelProperty(example = "{code: 1}", value = "回傳內容")
    private T resultData;
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
    private String lang = "";
    @Builder.Default
    @ApiModelProperty(example = "", value = "token")
    private String token = "";
}



