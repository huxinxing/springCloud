package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.ImportOriginMsgEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ImportOriginMsgRepository extends ElasticsearchRepository<ImportOriginMsgEntity,Integer> {
}
