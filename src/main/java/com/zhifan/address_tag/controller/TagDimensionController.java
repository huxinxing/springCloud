package com.zhifan.address_tag.controller;

import com.zhifan.address_tag.domain.dto.parmaryDto.DimensionParamary;
import com.zhifan.address_tag.domain.dto.resultDto.ResponseResult;
import com.zhifan.address_tag.domain.enums.ResponseStatus;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@RestController
@RequestMapping("/al/tag")
public class TagDimensionController {

    @ApiOperation("地址标注多维度列表")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "dimensionList",method = POST)
    public ResponseResult dimensionList(
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("地址标注多维度列表异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

    @ApiOperation("添加地址标注多维度")
    @ApiImplicitParams({

    })
    @RequestMapping(value = "dimensionAdd",method = POST)
    public ResponseResult dimensionAdd(
            @RequestBody DimensionParamary dimensionParamary
    ){
        try{
            return new ResponseResult(ResponseStatus.SUCCESS.getKey(),ResponseStatus.SUCCESS.getValue());
        }catch (Exception e){
            log.error("添加地址标注多维度异常",e);
            return new ResponseResult(ResponseStatus.Default.getKey(),e.getMessage());
        }
    }

}
