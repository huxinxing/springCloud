package com.zhifan.address_tag.domain.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "al_import_origin_record", type = "al_import_origin_record_type",shards = 1,replicas = 1)
public class ImportOriginRecordEntity {

    @Id
    private Integer orId;

    private Long uploadTime;

    private String dataSource;

    private Integer num;

    private String coinType;

    private Long gatherTime;

    private Integer uploadUserId;

    private Integer examinStatus;

    private Integer examinUserId;

    private Long examinTime;

    private String examinNote;

    private Integer status;

    private Long createTime;

}
