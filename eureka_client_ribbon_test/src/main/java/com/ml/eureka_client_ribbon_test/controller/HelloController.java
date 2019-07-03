package com.ml.eureka_client_ribbon_test.controller;

import com.ml.eureka_client_ribbon_test.domain.dto.resultDto.ResponseResult;
import com.ml.eureka_client_ribbon_test.domain.enums.ResponseStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ml")
@Api("注册服务中心，测试输出 Hello")
public class HelloController {

    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "ribbon test")
    @RequestMapping(value = "/ribbon")
    public ResponseResult test(){
        try{
            restTemplate.getForObject("http://eureka-hello/ml/hello",String.class);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

}
