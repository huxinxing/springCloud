package com.ml.service_zuul.domain.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import vip.dcpay.helpbuy.domain.dto.HelpPayAccountInfoDto;
import vip.dcpay.helpbuy.infrastrucyure.repository.HelpPayAccountInfoRepository;
import vip.dcpay.helpbuy.util.JwtUtil;
import vip.dcpay.vo.basic.Result;


public class MyRealm extends AuthorizingRealm {

    @Autowired
    HelpPayAccountInfoRepository helpPayAccountInfoRepository;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        AccountInfoEntity accountInfo = (AccountInfoEntity)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        List<AccountRoleEntity> accountRoleEntityList = accountRoleRepository.findByUserId(accountInfo.getUserId());
//        List<String> roleStr = new ArrayList<>();
//        if (CollectionUtils.isEmpty(accountRoleEntityList)){
//            return simpleAuthorizationInfo;
//        }else {
//            List<Integer> roleList = new ArrayList<>();
//
//            for (AccountRoleEntity accountRoleEntity : accountRoleEntityList){
//                roleList.add(accountRoleEntity.getRoleId());
//            }
//            List<RoleEntity> roleEntityList = roleRepository.findByRoleIdIn(roleList);
//            if (!CollectionUtils.isEmpty(roleEntityList)){
//                for (int i = 0 ;i < roleEntityList.size(); i++){
//                    roleStr.add(roleEntityList.get(i).getRoleName());
//                }
//            }
//        }
//        simpleAuthorizationInfo.addRoles(roleStr);

        return  simpleAuthorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比

        String account = JwtUtil.getUsername(token);
        if (StringUtils.isEmpty(account)) {
            throw new AuthenticationException("token无效");
        }

        Result<HelpPayAccountInfoDto> helpPayAccountInfoDtoResult = helpPayAccountInfoRepository.findByAccount(account);
        if (!helpPayAccountInfoDtoResult.getSuccess()){
            throw new AuthenticationException("用户不存在!");
        }
        HelpPayAccountInfoDto helpPayAccountInfoDto = helpPayAccountInfoDtoResult.getData();
        if (!JwtUtil.verify(token, account,helpPayAccountInfoDto.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        if (ObjectUtils.isEmpty(helpPayAccountInfoDto.getIsLogin())){
            throw new AuthenticationException("获取用户会话失败");
        }

        if (helpPayAccountInfoDto.getIsLogin() != 1){
            throw new AuthenticationException("获取用户会话失败");
        }

        return new SimpleAuthenticationInfo(helpPayAccountInfoDto, token, "my_realm");

    }
}
