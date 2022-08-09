package com.example.ass_j5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "account")

public class Account {
    @Id
    @Column(name = "id")
    private String id;
    @NotNull
    @NotBlank(message = "khong de trong email")
    @Size(min =3, max = 300)
    @Email(regexp = "\\w+@\\w+(\\.\\w+){1,2}")
    @Column(name = "email")
    private String email;
    @NotNull
    @NotBlank(message = "khong de trong pass")
    @Size(min = 3, max = 300)
    @Column(name = "pass")
    private String pass;

    @Column(name = "admin")
    private Integer admin;
    @Column(name = "sell")
    private Integer sell;
    @Column(name = "updelete")
    private Integer updelete;

    public Integer getUpdelete() {
        return updelete;
    }

    public void setUpdelete(Integer updelete) {
        this.updelete = updelete;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Integer getSell() {
        return sell;
    }

    public void setSell(Integer sell) {
        this.sell = sell;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", admin=" + admin +
                ", sell=" + sell +
                ", updelete=" + updelete +
                '}';
    }
}