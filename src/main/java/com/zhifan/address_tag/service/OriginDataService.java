package com.zhifan.address_tag.service;

import com.github.pagehelper.PageInfo;
import com.zhifan.address_tag.domain.dto.resultDto.ImportOriginMsg;
import com.zhifan.address_tag.domain.dto.resultDto.ImportOriginRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class OriginDataService {

    /**
     * 导入源数据
     */
    public void importDataSource(String dataSource,String coinType,MultipartFile dataSourceExecl){

        return;
    }

    /**
     * 源数据列表
     */
    public PageInfo originList(Integer pageNum,Integer pageSize,String coinType,Integer examinType ){
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotal(100);
        List<ImportOriginRecord> importOriginRecords = new ArrayList<>();
        for (int i = 0 ; i < 100; i++){
            ImportOriginRecord importOriginRecord = new ImportOriginRecord();
            importOriginRecord.setOrId(i);
            importOriginRecords.add(importOriginRecord);
        }
        pageInfo.setList(importOriginRecords);
        return pageInfo;
    }

    /**
     * 源数据详情
     */
    public List<ImportOriginMsg> originDetail(Integer orId){
        List<ImportOriginMsg> importOriginMsgList = new ArrayList<>();
        for (int i = 0; i < 10; i ++){
            ImportOriginMsg importOriginMsg = new ImportOriginMsg();
            List<String> tagList = new ArrayList<>();
            tagList.add("Huobi");
            importOriginMsg.setTagList(tagList);
            importOriginMsgList.add(importOriginMsg);
        }
        return importOriginMsgList;
    }

    /**
     * 源数据审核
     */
    public void originExamin(Integer orId,Integer examinType,String examinNote){
       return;
    }


}
