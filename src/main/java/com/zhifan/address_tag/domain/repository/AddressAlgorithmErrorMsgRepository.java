package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.AddressAlgorithmErrorMsgEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AddressAlgorithmErrorMsgRepository extends ElasticsearchRepository<AddressAlgorithmErrorMsgEntity,Integer> {
}
