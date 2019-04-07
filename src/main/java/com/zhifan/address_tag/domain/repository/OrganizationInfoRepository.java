package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.OrganizationInfoEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface OrganizationInfoRepository extends ElasticsearchRepository<OrganizationInfoEntity,Integer> {
}
