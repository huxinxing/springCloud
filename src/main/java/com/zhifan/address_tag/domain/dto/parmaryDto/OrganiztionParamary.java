package com.zhifan.address_tag.domain.dto.parmaryDto;

import lombok.Data;

@Data
public class OrganiztionParamary {

    private Integer orgId;

    private String orgNameCh;

    private String orgNameEn;

    private Integer orgType;

    private String webSite;

    private Long orgFoundingTime;

    private Integer isBusiness;

    private String phone;

    private String emial;

    private Long createTime;

}
