package com.zhifan.address_tag.controller;

import com.zhifan.address_tag.domain.dto.parmaryDto.AddSeedAddressParamary;
import com.zhifan.address_tag.domain.dto.parmaryDto.SeedExaminParamary;
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
@RequestMapping("/al/seed")
public class SeedAddressController {

    @ApiOperation("种子地址的搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "查询地址", required = true, dataType = "string" , paramType = "query"),
            @ApiImplicitParam(name = "type", value = "1：全部来源 2：待审核地址 3：种子地址列表", required = true, dataType = "string" , paramType = "query")
    })
    @RequestMapping(value = "/seedSearch",method = POST)
    public ResponseResult seedSearch(
            @RequestParam(name = "address") String address,
            @RequestParam(name = "type") Integer type
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("地址查询异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("种子地址列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coinType", value = "币种", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "1：全部来源 2：待审核地址 3：种子地址列表", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/seedList",method = POST)
    public ResponseResult seedList(
            @RequestParam(name = "coinType") String coinType,
            @RequestParam(value = "type") Integer type
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("列表展示异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("查看标注地址详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "地址Hash", required = true, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/seedDetail",method = POST)
    public ResponseResult seedDetail(
            @RequestParam(name = "address") String address
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("地址详情异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("编辑种子列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "地址Hash", required = true, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/seedEdit",method = POST)
    public ResponseResult seedEdit(
            @RequestParam(name = "address") String address
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("地址编辑异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("删除标注地址详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "地址Hash", required = true, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/seedDelete",method = POST)
    public ResponseResult seedDelete(
            @RequestParam(name = "address") String address
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("删除地址列表",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("添加种子地址")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/addSeedAddress",method = POST)
    public ResponseResult addSeedAddress(
            @RequestBody AddSeedAddressParamary addSeedAddressParamary
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("添加种子地址",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("种子地址审核")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/seedExamin",method = POST)
    public ResponseResult seedExamin(
            @RequestBody SeedExaminParamary seedExaminParamary
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("种子地址审核",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }


}
