package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "al_organization_info", type = "al_organization_info_type",shards = 1,replicas = 1)
public class OrganizationInfoEntity {

    @Id
    private Integer orgId;

    private String orgNameCh;

    private String orgNameEn;

    private Integer orgType;

    private String webSite;

    private Long orgFoundingTime;

    private Integer isBusiness;

    private String phone;

    private String emial;

    private Long createTime;

    private Long modifyTime;

}
