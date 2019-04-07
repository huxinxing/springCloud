package com.zhifan.address_tag.domain.repository;

import com.zhifan.address_tag.domain.entity.AccountInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AccountInfoRepository  extends ElasticsearchRepository<AccountInfoEntity,Integer> {

    AccountInfoEntity findByUserName(String username);

    AccountInfoEntity findByUserId(Integer userId);

    Page<AccountInfoEntity> findByUserNameLike(String msg, PageRequest pageRequest);

}
