package com.zhifan.address_tag.domain.dto.resultDto;

import lombok.Data;

import java.util.List;

@Data
public class ImportOriginMsg {

    private Integer numId;

    private String address = "1DjwFCkbkdzuWFc6VWz86ZKYFfxeJy4Ff9";

    private List<String> tagList;

    private String sourceName = "Bitcointalk";

    private String sourceUrl = "https://www.baidu.com/";

}
