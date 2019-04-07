package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Document(indexName = "al_import_origin_msg", type = "al_import_origin_msg_type",shards = 1,replicas = 1)
public class ImportOriginMsgEntity {

    @Id
    private Integer omId;

    private Integer orId;

    private String address;

    private List<String> tagList;

    private String coin;

    private String sourceName;

    private String sourceUrl;

    private Long uploadTime;

}
