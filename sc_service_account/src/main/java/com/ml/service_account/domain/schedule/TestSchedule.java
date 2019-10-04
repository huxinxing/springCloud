//package com.ml.service_account.domain.schedule;
//
//import com.ml.dto.account.AccountInfoDto;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class TestSchedule {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    @Scheduled(fixedDelay = 100)
//    public void test(){
//
//        AccountInfoDto accountInfoDto = AccountInfoDto.builder()
//                .userName("huxinxing")
//                .phone("13776646273")
//                .password("xing886372")
//                .build();
//
//        HttpHeaders headers = new HttpHeaders();
//
//        HttpEntity entity = new HttpEntity(accountInfoDto,headers);
//
//        String s = restTemplate.postForObject("http://service-account/service/account/register",entity,String.class);
//
//        log.info("nihao" + s);
//
//    }
//
//}
