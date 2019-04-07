package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.AccountRoleEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AccountRoleRepository extends ElasticsearchRepository<AccountRoleEntity,Integer> {

    List<AccountRoleEntity> findByUserId(Integer userId);

    List<AccountRoleEntity> findByUserIdIn(List<Integer> userIdList);

}
