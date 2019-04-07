package com.zhifan.address_tag.domain.dto.resultDto;

import lombok.Data;

@Data
public class LabelErrorMsg {

    private Integer id;

    private String errorType;

    private String errorContent;

    private Integer handleStatus;

    private Integer handleUserId;

    private Long handleTime;

    private String handleNote;

    private Long createTime;

    private Long modifyTime;

}
