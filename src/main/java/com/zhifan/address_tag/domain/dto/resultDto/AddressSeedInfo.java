package com.zhifan.address_tag.domain.dto.resultDto;

import lombok.Data;

import java.util.List;

@Data
public class AddressSeedInfo {

    private String address;

    private List<Integer> extranalTag;

    private List<Integer> trueTag;

    private String coin;

    private List<String> sourceNameList;

    private Integer walletType;

    private Integer examinStatus;

    private Integer examinUserId;

    private Long examinTime;

    private String examinNote;

    private Integer status;

    private Long createTime;

}
