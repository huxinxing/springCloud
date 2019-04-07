package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "al_human_info", type = "al_human_info_type",shards = 1,replicas = 1)
public class HumanInfoEntity {

    @Id
    private Integer humanId;

    private String humanTag;

    private String  humanName;

    private String Source;

    private Integer old;

    private Integer sex;

    private Integer state;

    private Integer city;

    private String emial;

    private String phone;

    private String webSite;

    private Long createTime;

    private Long modifyTime;

}
