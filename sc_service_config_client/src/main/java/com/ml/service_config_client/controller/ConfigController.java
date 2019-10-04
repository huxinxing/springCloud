package com.ml.service_config_client.controller;

import com.alibaba.fastjson.JSONObject;
import com.ml.service_config_client.domain.model.Config;
import com.ml.service_config_client.domain.repository.ConfigDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RefreshScope
//注：springCloud 配置文件自动新 post请求为 /actuator/refresh
@RequestMapping("/ml")
public class ConfigController {

    @Value("${from}")
    private String fromValues;

    @Autowired
    ConfigDao configDao;

    @RequestMapping("/config")
    public String configFrom(){

        Config config = configDao.findByConfigId(1);

        log.info(JSONObject.toJSONString(config));
        return fromValues;

    }

}
