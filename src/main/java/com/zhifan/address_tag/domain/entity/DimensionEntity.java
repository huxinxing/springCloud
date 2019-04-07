package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "al_address_dimension_info", type = "al_address_dimension_info_type",shards = 1,replicas = 1)
public class DimensionEntity {

    @Id
    private Integer dimensionId;

    private String tagDim;

    private String expand;

    private Long createTime;

}
