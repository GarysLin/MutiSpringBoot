package com.cloudinteractive.webapi.rest;

import com.alibaba.fastjson.JSONObject;
import com.cloudinteractive.core.controller.BaseRestController;
import com.cloudinteractive.webapi.model.request.TestPostMethodRequest;
import com.cloudinteractive.webapi.model.response.JsonHttpResponse;
import com.cloudinteractive.webapi.model.response.StringHttpResponse;
import com.cloudinteractive.webapi.utils.LocaleUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/test")
@Api(tags = {"測試用API"})
public class TestRestController extends BaseRestController {

    @ApiOperation(("GET範例"))
    @RequestMapping(path = "/test01", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<StringHttpResponse> testGetMethod(
            @ApiParam(value = "語系", example = "") @RequestParam(required = false) String lang) throws Exception {

        StringHttpResponse res = StringHttpResponse.builder()
                .resultCode("0000").resultData(LocaleUtil.get("language")).build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @ApiOperation(("POST範例"))
    @RequestMapping(path = "/test02", method = RequestMethod.POST)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<JsonHttpResponse> testPostMethod(
            @Valid @RequestBody TestPostMethodRequest requestBdy) throws Exception {
        JSONObject params = new JSONObject();
        params.put("language", LocaleUtil.get("language"));
        params.put("Data", requestBdy);
        params.put("sessionId", request.getSession().getId());

        JsonHttpResponse res = JsonHttpResponse.builder()
                .resultCode("0000").resultData(params).build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
