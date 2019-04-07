package com.zhifan.address_tag.domain.dto.resultDto.account;

import lombok.Data;

import java.util.List;

@Data
public class AccountMsg {

    private Integer userId;

    private String email;

    private String userName;

    private List<String> roleList;

    private Integer status;

}
