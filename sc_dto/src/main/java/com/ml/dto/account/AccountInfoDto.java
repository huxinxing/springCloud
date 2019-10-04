package com.ml.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoDto implements Serializable {

    private String userName;

    private String userNick;

    private String password;

    private String phone;

    private String email;

    private String gaKey;

}
