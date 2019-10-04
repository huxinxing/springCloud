package com.ml.application_helloworld.controller;

import com.ml.application_helloworld.service.HelloWorldService;
import com.ml.vo.basic.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ml")
public class HelloWorldController {

    @Autowired
    HelloWorldService helloWorldService;

    @RequestMapping(value = "/helloWorld",method = RequestMethod.GET)
    public Result<String> helloworld(){
        return Result.success(0,"请求成功",helloWorldService.sayHello());
    }

}
