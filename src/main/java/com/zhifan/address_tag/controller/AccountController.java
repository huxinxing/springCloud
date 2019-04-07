package com.zhifan.address_tag.controller;

import com.zhifan.address_tag.domain.dto.resultDto.ResponseResult;
import com.zhifan.address_tag.domain.dto.resultDto.account.AccountAddMsg;
import com.zhifan.address_tag.domain.enums.ResponseStatus;
import com.zhifan.address_tag.service.AccountService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
@RequestMapping("/al/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @ApiOperation("用户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "loginPass", value = "密码", required = true, dataType = "String", paramType = "query")

    })
    @RequestMapping(value = "/register",method = GET)
    public ResponseResult register(
            @RequestParam(name = "loginName") String loginName,
            @RequestParam(name = "loginPass") String loginPass
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue(),accountService.register(loginName,loginPass));
        }catch (Exception e){
            log.error("注册异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("获取Google验证码")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/gainGoogle",method = GET)
    public ResponseResult gainGoogle(
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue(),accountService.googleGain());
        }catch (Exception e){
            log.error("获取GoogleCode异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("绑定Google验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "googleKey", value = "google验证码私钥", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/bindGoogle",method = GET)
    public ResponseResult bindGoogle(
            @RequestParam(name = "googleKey") String googleKey,
            @RequestParam(name = "userId") Integer userId
    ){
        try{
            accountService.googleBind(userId,googleKey);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("绑定GoogleCode异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("验证Google验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "googleCode", value = "google验证吗", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/verifyGoogle",method = GET)
    public ResponseResult verifyGoogle(
            @RequestParam(name = "googleCode") Long googleCode,
            @RequestParam(name = "userId") Integer userId
    ){
        try{
            accountService.googleVerify(googleCode,userId);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("验证GoogleCode异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "loginPass", value = "密码", required = true, dataType = "String", paramType = "query")

    })
    @RequestMapping(value = "/login",method = POST)
    public ResponseResult login(
            @RequestParam(name = "loginName") String loginName,
            @RequestParam(name = "loginPass") String loginPass
    ){
        try{
            String token = accountService.login(loginName,loginPass);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue(),token);
        }catch (Exception e){
            log.error("登录异常",e);
            e.printStackTrace();
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("用户退出")
    @RequestMapping(value = "/loginOut",method = POST)
    public ResponseResult loginOut(
    ){
        try{
            accountService.loginOut();
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),"注销成功");
        }catch (Exception e){
            log.error("退出失败",e);
            e.printStackTrace();
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("搜索用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchMsgs", value = "查询条件", required = true, dataType = "string" , paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "string" , paramType = "query"),
            @ApiImplicitParam(name = "PageSize", value = "每页数量", required = true, dataType = "string" , paramType = "query")
    })
    @RequestMapping(value = "/accountSearch",method = POST)
    public ResponseResult accountSearch(
            @RequestParam(name = "searchMsgs") String searchMsgs,
            @RequestParam(name = "pageNum") Integer pageNum,
            @RequestParam(name = "PageSize") Integer pageSize

    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue(),accountService.accountSearch(searchMsgs,pageNum,pageSize));
        }catch (Exception e){
            log.error("搜索用户异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "string" , paramType = "query"),
            @ApiImplicitParam(name = "PageSize", value = "每页数量", required = true, dataType = "string" , paramType = "query")
    })
    @RequestMapping(value = "/accountList",method = POST)
    public ResponseResult accountList(
            @RequestParam(name = "pageNum") Integer pageNum,
            @RequestParam(name = "PageSize") Integer pageSize
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue(),accountService.accountList(pageNum,pageSize));
        }catch (Exception e){
            log.error("用户列表异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("添加用户")
    @ApiImplicitParams({

    })
    @RequestMapping(value = "/accountAdd",method = POST)
    public ResponseResult accountAdd(
            @RequestBody AccountAddMsg accountAddMsg
            ){
        try{
            if (!ObjectUtils.isEmpty(accountAddMsg.getUserId())){
                throw new Exception("添加用户信息错误");
            }
            accountService.accountAddOrEidt(accountAddMsg);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("添加用户异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("编辑用户")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/accountEdit",method = POST)
    public ResponseResult accountEdit(
            @RequestBody AccountAddMsg accountAddMsg
    ){
        try{
            if (ObjectUtils.isEmpty(accountAddMsg.getUserId())){
                throw new Exception("编辑信息错误");
            }
            accountService.accountAddOrEidt(accountAddMsg);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("编辑用户异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("停止用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户名", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/accountCease",method = POST)
    public ResponseResult accountCease(
            @RequestParam(name = "userId") Integer userId
    ){
        try{
            accountService.accountCease(userId);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("停止用户异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("重置用户GA")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "googleKey", value = "google验证码私钥", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户Id", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/resettingGA",method = POST)
    public ResponseResult resettingGA(
            @RequestParam(name = "googleKey") String googleKey,
            @RequestParam(name = "userId") Integer userId
    ){
        try{
            accountService.googleBind(userId,googleKey);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("重置用户GA异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }


    @RequiresRoles(value={"admin","test"},logical = Logical.OR)
    @ApiOperation("测试数据")
    @RequestMapping(value = "/getMessage",method = POST)
    public ResponseResult getMessage(

    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue(),"成功");
        }catch (Exception e){
            log.error("获取数据失败",e);
            e.printStackTrace();
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

}
