package com.ml.service_account.controller;

import com.alibaba.fastjson.JSONObject;
import com.ml.dto.account.AccountInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
@RequestMapping("/service/account")
public class AccountController {

    @PostMapping(value = "/register")
    public String register(String userName){
        log.info("你好" + JSONObject.toJSONString(userName));
        return "nihao";
    }

}
