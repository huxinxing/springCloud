package com.ml.eureka_client.controller;

import com.ml.eureka_client.domain.dto.resultDto.ResponseResult;
import com.ml.eureka_client.domain.enums.ResponseStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ml")
@Api("注册服务中心，测试输出 Hello")
public class HelloController {

    @ApiOperation(value = "hello")
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public ResponseResult test(){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue(),"hello");
        }catch (Exception e){
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

}
