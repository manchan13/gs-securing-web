package com.example.securingweb.entity;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ログインユーザのユーザ名、パスワードを格納するためのEntity
 *
 */
@Data
@Entity
@Table(name = "userTbl")
public class LoginUser {

    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

}