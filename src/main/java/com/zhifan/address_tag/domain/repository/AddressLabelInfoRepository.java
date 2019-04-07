package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.AddressLabelInfoEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AddressLabelInfoRepository extends ElasticsearchRepository<AddressLabelInfoEntity,String> {
}
