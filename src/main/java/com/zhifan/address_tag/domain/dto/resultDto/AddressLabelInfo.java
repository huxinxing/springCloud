package com.zhifan.address_tag.domain.dto.resultDto;

import lombok.Data;

@Data
public class AddressLabelInfo {

    private String address;

    private Long walletId;

    private Integer entityId;

    private Long seedWalletId;

    private String algorithmModel;

    private String algorithmNum;

    private Integer examinStatus;

    private Integer examinUserId;

    private Long examinTime;

    private String examinNote;

    private Long createTime;

    private Long modifyTime;

}
