package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.HumanInfoEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HumanInfoRepository extends ElasticsearchRepository<HumanInfoEntity,Integer> {
}
