package com.cloudinteractive.webapi.rest;

import com.cloudinteractive.core.controller.BaseRestController;
import com.cloudinteractive.webapi.model.Member;
import com.cloudinteractive.webapi.model.response.AllMemberHttpResponse;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/member")
@Api(tags = {"Member API"})
public class MemberRestController extends BaseRestController {

    @ApiOperation(("取得所有會員資料"))
    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "resultCode(0000):成功\nresultCode(9999):未預期錯誤"),
            @ApiResponse(code = 500, message = "未預期錯誤")})
    public ResponseEntity<AllMemberHttpResponse> allMember() throws Exception {

        List<Member> memberList = new ArrayList<>();
        memberList.add(Member.builder().id("A123456789").name("大Ａ").build());
        memberList.add(Member.builder().id("A000000001").name("小Ｂ").build());

        AllMemberHttpResponse res = AllMemberHttpResponse.builder()
                .resultCode("0000").resultData(memberList).build();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
