package com.zhifan.address_tag.controller;

import com.zhifan.address_tag.domain.dto.resultDto.ResponseResult;
import com.zhifan.address_tag.domain.enums.ResponseStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
@RequestMapping("/al/tag")
public class TagExaminController {

    @ApiOperation("算法标注的搜索接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "address", value = "查询地址", required = true, dataType = "string" , paramType = "query"),
    })
    @RequestMapping(value = "/examinSearch",method = POST)
    public ResponseResult examinSearch(
            @RequestParam(name = "address") String address
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("算法标注的搜索接口异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("标注审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coinType", value = "币种", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "examinStatus", value = "0：未审核 1：未通过 2：已通过 4：全部", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/exminList",method = POST)
    public ResponseResult exminList(
            @RequestParam(name = "coinType") String coinType,
            @RequestParam(value = "examinStatus") Integer examinStatus
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("标注审核列表",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("标注审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "examinNote", value = "审核备注", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "examinStatus", value = "0：未审核 1：未通过 2：已通过 4：全部", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "examinNote", value = "审核备注", required = true, dataType = "string", paramType = "query")
    })
    @RequestMapping(value = "/exmin",method = POST)
    public ResponseResult exmin(
            @RequestParam(name = "address") String address,
            @RequestParam(name = "examinStatus") Integer examinStatus,
            @RequestParam(name = "examinNote") String examinNote
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("标注审核",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }


    @ApiOperation("错误提示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "coinType", value = "币种", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "handleStatus", value = "处理状态0：已处理 1：未处理", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value = "/examinError",method = POST)
    public ResponseResult examinError(
            @RequestParam(value = "coinType") String coinType,
            @RequestParam(name = "handleStatus") Integer handleStatus
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("错误提示异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

}
