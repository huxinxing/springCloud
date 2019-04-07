package com.zhifan.address_tag.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OriginImportSeedSchedule {

    /**
     *
     */
    @Scheduled(fixedDelay = 1000)
    public void originImportSeed(){

    }

}
