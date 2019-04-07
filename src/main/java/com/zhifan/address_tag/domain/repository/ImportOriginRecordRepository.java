package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.ImportOriginRecordEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ImportOriginRecordRepository extends ElasticsearchRepository<ImportOriginRecordEntity,Integer> {
}
