package com.zhifan.address_tag.domain.dto.resultDto.account;

import lombok.Data;

import java.util.List;

@Data
public class AccountAddMsg {

    private Integer userId;

    private String userName;

    private String password = "123456";

    private String email;

    private Integer Status;

    private List<Integer> roleList;

}
