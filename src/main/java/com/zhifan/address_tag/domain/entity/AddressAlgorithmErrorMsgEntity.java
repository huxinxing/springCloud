package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "al_address_algorithm_error_msg", type = "al_address_algorithm_error_msg_type",shards = 1,replicas = 1)
public class AddressAlgorithmErrorMsgEntity {

    @Id
    private Integer id;

    private String errorType;

    private String errorContent;

    private Integer handleStatus;

    private Integer handleUserId;

    private Long handleTime;

    private String handleNote;

    private Long createTime;

    private Long modifyTime;

}
