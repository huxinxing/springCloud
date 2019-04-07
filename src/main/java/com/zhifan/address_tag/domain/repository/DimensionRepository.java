package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.DimensionEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DimensionRepository extends ElasticsearchRepository<DimensionEntity,Integer> {
}
