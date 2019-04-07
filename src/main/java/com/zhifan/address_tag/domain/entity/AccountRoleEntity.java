package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "al_account_role", type = "al_account_role_type",shards = 1,replicas = 1)
public class AccountRoleEntity {

    @Id
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Long createTime;

    private Long modifyTime;

}
