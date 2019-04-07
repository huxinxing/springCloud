package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "al_address_label_info", type = "al_address_label_info_type",shards = 1,replicas = 1)
public class AddressLabelInfoEntity {

    @Id
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
