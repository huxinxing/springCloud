package com.zhifan.address_tag.shiro;

import com.zhifan.address_tag.domain.entity.AccountInfoEntity;
import com.zhifan.address_tag.domain.entity.AccountRoleEntity;
import com.zhifan.address_tag.domain.entity.RoleEntity;
import com.zhifan.address_tag.domain.repository.AccountInfoRepository;
import com.zhifan.address_tag.domain.repository.AccountRoleRepository;
import com.zhifan.address_tag.domain.repository.RoleRepository;
import com.zhifan.address_tag.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AccountInfoRepository accountInfoRepository;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccountInfoEntity accountInfo = (AccountInfoEntity)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<AccountRoleEntity> accountRoleEntityList = accountRoleRepository.findByUserId(accountInfo.getUserId());
        List<String> roleStr = new ArrayList<>();
        simpleAuthorizationInfo.addRole("admin");
        if (CollectionUtils.isEmpty(accountRoleEntityList)){
            return simpleAuthorizationInfo;
        }else {
            List<Integer> roleList = new ArrayList<>();

            for (AccountRoleEntity accountRoleEntity : accountRoleEntityList){
                roleList.add(accountRoleEntity.getRoleId());
            }
            List<RoleEntity> roleEntityList = roleRepository.findByRoleIdIn(roleList);
            if (!CollectionUtils.isEmpty(roleEntityList)){
                for (int i = 0 ;i < roleEntityList.size(); i++){
                    roleStr.add(roleEntityList.get(i).getRoleName());
                }
            }
        }
        simpleAuthorizationInfo.addRoles(roleStr);

        return simpleAuthorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比

        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token无效");
        }

        AccountInfoEntity accountInfo = accountInfoRepository.findByUserName(username);
        if (ObjectUtils.isEmpty(accountInfo)) {
            throw new AuthenticationException("用户不存在!");
        }

        if (!JwtUtil.verify(token, username, accountInfo.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(accountInfo, token, "my_realm");

    }
}
