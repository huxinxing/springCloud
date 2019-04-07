package com.zhifan.address_tag.service;

import com.zhifan.address_tag.domain.entity.AccountInfoEntity;
import com.zhifan.address_tag.domain.entity.AccountRoleEntity;
import com.zhifan.address_tag.domain.entity.RoleEntity;
import com.zhifan.address_tag.domain.repository.AccountInfoRepository;
import com.zhifan.address_tag.domain.repository.AccountRoleRepository;
import com.zhifan.address_tag.domain.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 生成组件服务
 */
@Slf4j
@Service
public class PrimaeyKeyService {

    @Autowired
    AccountInfoRepository accountInfoRepository;

    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    /**
     * 生成用户表主键Id
     */
    public Integer AccountInfoUserId() {
        if (accountInfoRepository.count() != 0){
            List<AccountInfoEntity> accountInfoEntityList = accountInfoRepository.findAll(PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "userName"))).getContent();
            if (CollectionUtils.isEmpty(accountInfoEntityList)) {
                return 1000;
            } else {
                return (accountInfoEntityList.get(0).getUserId() + 1);
            }
        }else {
            return 1000;
        }
    }

    /**
     * 生成用户角色表主键Id
     */
    public Integer AccountRoleId(){
        if (accountRoleRepository.count() != 0){
            List<AccountRoleEntity> accountRoleEntityList = accountRoleRepository.findAll(PageRequest.of(0,1,new Sort(Sort.Direction.DESC,"createTime"))).getContent();
            if (CollectionUtils.isEmpty(accountRoleEntityList)){
                return 1000;
            }else {
                return (accountRoleEntityList.get(0).getId() + 1);
            }
        }else {
            return 1000;
        }
    }

    /**
     * 生成角色表主键Id
     */
    public Integer RoleId(){
        if (roleRepository.count() != 0){
            List<RoleEntity> roleEntityList = roleRepository.findAll(PageRequest.of(0,1,new Sort(Sort.Direction.DESC,"createTime"))).getContent();
            if (CollectionUtils.isEmpty(roleEntityList)){
                return 1000;
            }else {
                return (roleEntityList.get(0).getRoleId() + 1);
            }
        }else {
            return 1000;
        }
    }

}
