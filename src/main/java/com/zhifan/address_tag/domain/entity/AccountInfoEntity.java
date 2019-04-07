package com.zhifan.address_tag.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.util.StringUtils;

@Data
@Document(indexName = "al_account_info", type = "al_account_info_type",shards = 1,replicas = 1)
public class AccountInfoEntity {

    public static Integer ACCOUNT_STATUS_ZERO = 0; //停用状态

    public static Integer ACCOUNT_STATUS_ONE = 1; //可用状态

    @Id
    private Integer userId;

    private String userName = "-";

    private String salt = "-";

    private String password = "-";

    private String phone = "-";

    private String email = "-";

    private Integer status;

    private String googleCode;

    private Long createTime = 0L;

    private Long modifyTime = 0L;

    public boolean isBindGoogleCode(){
        if (StringUtils.isEmpty(googleCode)){
            return true;
        }else {
            return false;
        }
    }

}
