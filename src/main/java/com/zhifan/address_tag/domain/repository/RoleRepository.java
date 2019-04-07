package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.RoleEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RoleRepository extends ElasticsearchRepository<RoleEntity,Integer> {

    List<RoleEntity> findByRoleIdIn(List<Integer> roleList);

}
