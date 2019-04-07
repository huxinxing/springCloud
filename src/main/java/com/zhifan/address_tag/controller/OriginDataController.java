package com.zhifan.address_tag.controller;

import com.zhifan.address_tag.domain.dto.resultDto.ResponseResult;
import com.zhifan.address_tag.domain.enums.ResponseStatus;
import com.zhifan.address_tag.service.OriginDataService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
@RequestMapping("/al/origin")
public class OriginDataController {

    @Autowired
    OriginDataService originDataService;

    @ApiOperation("源数据导入")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataSource", value = "导入数据来源", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "coinType", value = "币种", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "dataSourceExecl", value = "导入数据源execl文件", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/importDataSource",method = POST)
    public ResponseResult importDataSource(
            @RequestParam(name = "dataSource") String dataSource,
            @RequestParam(name = "coinType") String coinType,
            @RequestParam(value = "dataSourceExecl") MultipartFile dataSourceExecl
    ){
        try{
            originDataService.importDataSource(dataSource,coinType,dataSourceExecl);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("源数据导入成功",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("源数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页数量", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "coinType", value = "币种", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "examinType", value = "0：未审核 1：未通过 2：已通过", required = true, dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "/originList",method = POST)
    public ResponseResult originList(
            @RequestParam(name = "pageNum") Integer pageNum,
            @RequestParam(name = "pageSize") Integer pageSize,
            @RequestParam(name = "coinType") String coinType,
            @RequestParam(name = "examinType") Integer examinType
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue(),originDataService.originList(pageNum,pageSize,coinType,examinType));
        }catch (Exception e){
            log.error("源数据列表",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("源数据详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orId", value = "源数据记录Id", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/originDetail",method = POST)
    public ResponseResult originDetail(
            @RequestParam(name = "orId") Integer orId
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue(),originDataService.originDetail(orId));
        }catch (Exception e){
            log.error("源数据详情",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("源数据审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orId", value = "源数据记录Id", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "examinType", value = "0：未审核 1：未通过 2：已通过", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "examinNote", value = "审核备注", required = true, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/originExamin",method = POST)
    public ResponseResult originExamin(
            @RequestParam(name = "orId") Integer orId,
            @RequestParam(name = "examinType") Integer examinType,
            @RequestParam(name = "examinNote",required = false) String examinNote
    ){
        try{
            originDataService.originExamin(orId,examinType,examinNote);
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("源数据审核",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }




}
