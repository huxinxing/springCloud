package com.ml.service_helloworld.controller;

import com.ml.vo.basic.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ml")
public class HelloWorldController {

    @RequestMapping(value = "/helloWorld",method = RequestMethod.GET)
    public Result<String> helloworld(){
        return Result.success(0,"请求成功","hello_world");
    }

}
