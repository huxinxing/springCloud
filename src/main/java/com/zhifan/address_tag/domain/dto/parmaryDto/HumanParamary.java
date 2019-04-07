package com.zhifan.address_tag.domain.dto.parmaryDto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class HumanParamary {

    private Integer humanId;

    private String humanTag;

    private String  humanName;

    private String Source;

    private Integer old;

    private Integer sex;

    private Integer state;

    private Integer city;

    private String emial;

    private String phone;

    private String webSite;

    private Long createTime;

}
