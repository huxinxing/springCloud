package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "al_role", type = "al_role_type",shards = 1,replicas = 1)
public class RoleEntity {

    @Id
    private Integer roleId;

    private String roleName;

    private Integer roleStatul;

    private Long createTime;

    private Long modifyTime;

}
