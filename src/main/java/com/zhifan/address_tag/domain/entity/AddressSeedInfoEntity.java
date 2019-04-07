package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Document(indexName = "al_address_seed_info", type = "al_address_seed_info_type",shards = 1,replicas = 1)
public class AddressSeedInfoEntity {

    @Id
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
