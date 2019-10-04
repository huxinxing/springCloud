package com.ml.service_account.infrastrucyure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "config")
public class ScAccountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "system_key")
    private Integer systemKey;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_nick")
    private String userNick;

    @Column(name = "salt")
    private String salt;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "useing")
    private Integer useing;

    @Column(name = "ga_key")
    private String gaKey;

    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "create_time")
    private Date createTime;

}
