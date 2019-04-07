package com.zhifan.address_tag.service;

import com.github.pagehelper.PageInfo;
import com.zhifan.address_tag.domain.dto.resultDto.account.AccountAddMsg;
import com.zhifan.address_tag.domain.dto.resultDto.account.AccountMsg;
import com.zhifan.address_tag.domain.entity.AccountInfoEntity;
import com.zhifan.address_tag.domain.entity.AccountRoleEntity;
import com.zhifan.address_tag.domain.entity.RoleEntity;
import com.zhifan.address_tag.domain.repository.AccountInfoRepository;
import com.zhifan.address_tag.domain.repository.AccountRoleRepository;
import com.zhifan.address_tag.domain.repository.RoleRepository;
import com.zhifan.address_tag.shiro.JwtToken;
import com.zhifan.address_tag.util.GoogleAuthenticatorUtil;
import com.zhifan.address_tag.util.JwtUtil;
import com.zhifan.address_tag.util.PasswordUtil;
import com.zhifan.address_tag.util.RandomUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service
public class AccountService {

    @Autowired
    AccountInfoRepository accountInfoRepository;

    @Autowired
    PrimaeyKeyService primaeyKeyService;

    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    /**
     * 注册接口
     *
     * @param loginName
     * @param loginPass
     */
    public Map<String, Object> register(String loginName, String loginPass) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        AccountInfoEntity accountInfo = accountInfoRepository.findByUserName(loginName);
        if (!ObjectUtils.isEmpty(accountInfo)) {
            throw new Exception("用户名已存在");
        } else {
            accountInfo = new AccountInfoEntity();
            accountInfo.setUserId(primaeyKeyService.AccountInfoUserId());
        }
        accountInfo.setSalt(RandomUnit.generateRandom(6, RandomUnit.RANDOM_TWO));
        accountInfo.setPassword(PasswordUtil.MD5Salt(loginPass, accountInfo.getSalt()));
        accountInfo.setUserName(loginName);
        accountInfo.setEmail(loginName);
        accountInfo.setModifyTime(System.currentTimeMillis());
        accountInfo.setCreateTime(System.currentTimeMillis());
        accountInfoRepository.save(accountInfo);
        resultMap.put("dictate", accountInfo.isBindGoogleCode());
        resultMap.put("userid", accountInfo.getUserId());
        return resultMap;
    }

    /**
     * 获取google验证码私钥
     */
    public Map<String, Object> googleGain() {
        Map<String, Object> resultMap = new HashMap<>();
        String googleKey = GoogleAuthenticatorUtil.generateSecretKey();
        String url = "otpauth://totp/zhifan?secret=" + googleKey;
        resultMap.put("googleCode",googleKey);
        return resultMap;
    }

    /**
     * 绑定Google验证码私钥
     */
    public void googleBind(Integer userId,String googleSecretKey) throws Exception {
        AccountInfoEntity accountInfoEntity = accountInfoRepository.findByUserId(userId);
        if (ObjectUtils.isEmpty(accountInfoEntity)){
            throw new Exception("用户不存在");
        }
        accountInfoEntity.setGoogleCode(googleSecretKey);
        accountInfoRepository.save(accountInfoEntity);
    }

    /**
     * 验证google验证吗
     */
    public void googleVerify(Long googleCode,Integer userId) throws Exception {
        AccountInfoEntity accountInfoEntity = accountInfoRepository.findByUserId(userId);
        if (ObjectUtils.isEmpty(accountInfoEntity)){
            throw new Exception("用户不存在");
        }

        if (StringUtils.isEmpty(accountInfoEntity.getGoogleCode())){
            throw new Exception("用户未绑定google验证吗");
        }

        if (!GoogleAuthenticatorUtil.check_code(googleCode,System.currentTimeMillis(),accountInfoEntity.getGoogleCode())){
            throw new Exception("验证码验证失败");
        }
    }


    /**
     * 登录接口
     */
    public String login(String loginName, String loginPass) throws Exception {

        AccountInfoEntity accountInfo = accountInfoRepository.findByUserName(loginName);
        if (ObjectUtils.isEmpty(accountInfo)) {
            throw new Exception("用户不存在，请先注册");
        }

        if (accountInfo.getStatus() == 0){
            throw new Exception("用户已停用");
        }

        if (accountInfo.getPassword().equals(PasswordUtil.MD5Salt(loginPass, accountInfo.getSalt()))) {
            String token = JwtUtil.sign(loginName, PasswordUtil.MD5Salt(loginPass, accountInfo.getSalt()));
            SecurityUtils.getSubject().login(new JwtToken(token));
            //AccountInfoEntity accountInfoEntity = (AccountInfoEntity) SecurityUtils.getSubject().getPrincipal();
            return token;
        } else {
            throw new Exception("密码错误");
        }

    }

    /**
     * 用户退出接口
     */
    public void loginOut() throws Exception {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }


    /**
     * 搜索用户
     */
    public PageInfo accountSearch(String msg,Integer pageNum,Integer pageSize){
        //获取需要展示的用户信息
        Page<AccountInfoEntity> page = accountInfoRepository.findByUserNameLike(msg, PageRequest.of(pageNum,pageSize,new Sort(Sort.Direction.DESC,"createTime")));
        if (ObjectUtils.isEmpty(page) || CollectionUtils.isEmpty(page.getContent())){
            return null;
        }
        return accountMsgs(page);
    }

    /**
     * 获取用户信息列表
     * @return
     */
    public PageInfo accountList(Integer pageNum,Integer pageSize){
        //获取需要展示的用户信息
        Page<AccountInfoEntity> page = accountInfoRepository.findAll(PageRequest.of(pageNum,pageSize,new Sort(Sort.Direction.DESC,"createTime")));
        if (ObjectUtils.isEmpty(page) || CollectionUtils.isEmpty(page.getContent())){
            return null;
        }
        return accountMsgs(page);
    }


    public PageInfo accountMsgs(Page<AccountInfoEntity> page){
        PageInfo pageInfo = new PageInfo();
        List<AccountInfoEntity> accountInfoEntities = page.getContent();
        //获取角色Id和角色名的映射关系
        HashMap<Integer,String> roleAndNameMap = new HashMap<>();
        List<Integer> userIdList = new ArrayList<>();
        for (AccountInfoEntity accountInfoEntity : accountInfoEntities){
            userIdList.add(accountInfoEntity.getUserId());
        }
        List<AccountRoleEntity> accountRoleEntityList = accountRoleRepository.findByUserIdIn(userIdList);
        Set<Integer> roleSet = new TreeSet<>();
        for (AccountRoleEntity accountRoleEntity : accountRoleEntityList){
            roleSet.add(accountRoleEntity.getRoleId());
        }
        List<Integer> roleList = new ArrayList<>(roleSet);
        List<RoleEntity> roleEntityList = roleRepository.findByRoleIdIn(roleList);
        for (RoleEntity roleEntity : roleEntityList){
            roleAndNameMap.put(roleEntity.getRoleId(),roleEntity.getRoleName());
        }
        //构建用户角色映射关系
        Map<Integer,List<String>> userRoleMap = new HashMap<>();
        for (AccountRoleEntity accountRoleEntity : accountRoleEntityList){
            String roleName = roleAndNameMap.get(accountRoleEntity.getRoleId());
            if (ObjectUtils.isEmpty(userRoleMap.get(accountRoleEntity.getUserId()))){
                List<String> roleListTmp = new ArrayList<>();
                roleListTmp.add(roleName);
                userRoleMap.put(accountRoleEntity.getUserId(),roleListTmp);
            }else {
                List<String> roleListTmp = userRoleMap.get(accountRoleEntity.getUserId());
                roleListTmp.add(roleName);
                userRoleMap.put(accountRoleEntity.getUserId(),roleListTmp);
            }
        }
        //构造返回list
        List<AccountMsg> accountMsgList = new ArrayList<>();
        for (AccountInfoEntity accountInfoEntity : accountInfoEntities){
            AccountMsg accountMsg = new AccountMsg();
            accountMsg.setUserId(accountInfoEntity.getUserId());
            accountMsg.setEmail(accountInfoEntity.getEmail());
            accountMsg.setUserName(accountInfoEntity.getUserName());
            accountMsg.setRoleList(userRoleMap.get(accountInfoEntity.getUserId()));
            accountMsg.setStatus(accountInfoEntity.getStatus());
        }

        pageInfo.setTotal(page.getTotalElements());
        pageInfo.setList(accountMsgList);
        return pageInfo;
    }

    /**
     * 编辑或者添加用户
     * 角色编辑暂时不写
     */
    public void accountAddOrEidt(AccountAddMsg accountAddMsg) throws Exception {
        //保存用户信息
        AccountInfoEntity  accountInfoEntity = null;
        Set<Integer> roleSet = new TreeSet<>();
        Integer userId = null;
        if (ObjectUtils.isEmpty(accountAddMsg.getUserId())){
            //添加用户
            accountInfoEntity = new AccountInfoEntity();
            accountInfoEntity.setUserId(primaeyKeyService.AccountInfoUserId());
            accountInfoEntity.setCreateTime(System.currentTimeMillis());
            accountInfoEntity.setSalt(RandomUnit.generateRandom(6, RandomUnit.RANDOM_TWO));
            accountInfoEntity.setStatus(AccountInfoEntity.ACCOUNT_STATUS_ONE);
            accountInfoEntity.setPassword(PasswordUtil.MD5Salt(accountAddMsg.getPassword(), accountInfoEntity.getSalt()));

            //保存用户角色
            List<AccountRoleEntity> accountRoleEntityList = new ArrayList<>();
            List<Integer> roleList = accountAddMsg.getRoleList();
            if (!CollectionUtils.isEmpty(roleList)){
                for (Integer roleId : roleList){
                    AccountRoleEntity accountRoleEntity = new AccountRoleEntity();
                    accountRoleEntity.setId(primaeyKeyService.AccountRoleId());
                    accountRoleEntity.setRoleId(roleId);
                    accountRoleEntity.setUserId(accountInfoEntity.getUserId());
                    accountRoleEntity.setCreateTime(System.currentTimeMillis());
                    accountInfoEntity.setModifyTime(System.currentTimeMillis());
                    accountRoleEntityList.add(accountRoleEntity);
                }
            }
            if (!CollectionUtils.isEmpty(accountRoleEntityList)){
                accountRoleRepository.saveAll(accountRoleEntityList);
            }

        }else {
            //编辑用户
            userId = accountAddMsg.getUserId();
            accountInfoEntity = accountInfoRepository.findByUserId(userId);
            List<AccountRoleEntity> accountRoleEntityList = accountRoleRepository.findByUserId(userId);

            if (ObjectUtils.isEmpty(accountInfoEntity)){
                throw new Exception("编辑用户不存在");
            }
        }
        accountInfoEntity.setEmail(ObjectUtils.isEmpty(accountAddMsg.getEmail())?accountInfoEntity.getEmail():accountAddMsg.getEmail());
        accountInfoEntity.setUserName(ObjectUtils.isEmpty(accountAddMsg.getUserName())?accountInfoEntity.getUserName():accountAddMsg.getUserName());
        accountInfoEntity.setModifyTime(System.currentTimeMillis());
        accountInfoRepository.save(accountInfoEntity);

    }

    /**
     * 停用用户
     */
    public void accountCease(Integer userId) throws Exception {
        AccountInfoEntity accountInfoEntity = accountInfoRepository.findByUserId(userId);
        if (ObjectUtils.isEmpty(accountInfoEntity)){
            throw new Exception("用户信息不存在");
        }

        if (accountInfoEntity.getStatus() == AccountInfoEntity.ACCOUNT_STATUS_ZERO){
            throw new Exception("用户已停用");
        }

        accountInfoEntity.setStatus(AccountInfoEntity.ACCOUNT_STATUS_ZERO);
        accountInfoRepository.save(accountInfoEntity);
    }


}
