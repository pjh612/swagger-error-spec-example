package com.example.exampleapi.controller;

import com.example.commonswagger.annotation.ApiErrorCodeExample;
import com.example.commonswagger.annotation.ExceptionCodeExample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @ApiErrorCodeExample(
            examples = {
                    @ExceptionCodeExample(title = "비밀번호 틀릴 때", codes = {"C-001"}),
                    @ExceptionCodeExample(title = "입력한 아이디가 존재하지 않을 때", codes = {"C-002"})}
    )
    @GetMapping("/sample")
    public String sample(@RequestBody SampleRequest request) {
        return request.toString();
    }
}
