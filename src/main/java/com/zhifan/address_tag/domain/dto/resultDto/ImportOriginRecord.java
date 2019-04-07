package com.zhifan.address_tag.domain.dto.resultDto;

import lombok.Data;

@Data
public class ImportOriginRecord {

    private Integer orId;

    private Long uploadTime = System.currentTimeMillis();

    private String dataSource = "BitcoinTalk";

    private Integer num = 100;

    private String coinType = "BTC";

    private Long gatherTime = System.currentTimeMillis();

    private String uploadUser = "dan";

    private String examinStatus = "已通过";

    private String examinUser = "dan";

    private Long examinTime = System.currentTimeMillis();

    private String examinNote = "nihao";

    private Long createTime = System.currentTimeMillis();

}
