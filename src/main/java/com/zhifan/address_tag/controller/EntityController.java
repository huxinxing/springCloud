package com.zhifan.address_tag.controller;

import com.zhifan.address_tag.domain.dto.parmaryDto.HumanParamary;
import com.zhifan.address_tag.domain.dto.parmaryDto.OrganiztionParamary;
import com.zhifan.address_tag.domain.dto.resultDto.ResponseResult;
import com.zhifan.address_tag.domain.enums.ResponseStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
@RequestMapping("/al/entity")
public class EntityController {

    @ApiOperation("实体搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchMsgs", value = "查询条件", required = true, dataType = "string" , paramType = "query"),
            @ApiImplicitParam(name = "type", value = "查询类型 1：机构实体 2:个人管理", required = true, dataType = "string" , paramType = "query")
    })
    @RequestMapping(value = "/entitySearch",method = POST)
    public ResponseResult entitySearch(
            @RequestParam(name = "address") String address
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("实体搜索接口异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("机构列表")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/orgList",method = POST)
    public ResponseResult orgList(
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("机构列表异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("添加机构")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "addOrg",method = POST)
    public ResponseResult addOrg(
            @RequestBody OrganiztionParamary organiztionParamary
            ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("添加机构异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("编辑机构")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "editOrg",method = POST)
    public ResponseResult editOrg(
            @RequestBody OrganiztionParamary organiztionParamary
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("编辑机构异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("删除机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构Id", required = true, dataType = "string" , paramType = "query")
    })
    @RequestMapping(value = "deleteOrg",method = POST)
    public ResponseResult deleteOrg(
            @RequestParam(name = "orgId") Integer orgId
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("删除机构异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }


    @ApiOperation("个人列表")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "humanList",method = POST)
    public ResponseResult humanList(
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("个人列表表异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("添加个人")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "addHuman",method = POST)
    public ResponseResult addHuman(
            @RequestBody HumanParamary humanParamary
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("添加个人异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("编辑个人")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "editHuman",method = POST)
    public ResponseResult editHuman(
            @RequestBody HumanParamary humanParamary
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("编辑个人异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("删除机构")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "humanId", value = "个人Id", required = true, dataType = "string" , paramType = "query")
    })
    @RequestMapping(value = "deleteHuman",method = POST)
    public ResponseResult deleteHuman(
            @RequestParam(name = "humanId") Integer humanId
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("删除个人异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }


}
