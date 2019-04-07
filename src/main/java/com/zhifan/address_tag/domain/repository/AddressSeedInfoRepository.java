package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.AddressSeedInfoEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AddressSeedInfoRepository extends ElasticsearchRepository<AddressSeedInfoEntity,String> {
}
