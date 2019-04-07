package com.zhifan.address_tag;

import com.zhifan.address_tag.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AddressTagApplication{

    public static void main(String[] args) {
        SpringApplication.run(AddressTagApplication.class, args);
    }


}
