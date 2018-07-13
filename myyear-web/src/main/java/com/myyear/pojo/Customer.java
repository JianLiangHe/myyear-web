package com.myyear.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Customer {
    private Long id;

    private Date gmt_create;

    private Date gmt_modifiy;

    private Integer is_delete;

    private Integer status;

    private String account_number;

    private String password;

    private String user_name;

    private Integer sex;

    private Integer age;

    private String email;

    private Date birthady;

    private String photo;

    private String city;

    private BigDecimal perfect_ratio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getGmt_modifiy() {
        return gmt_modifiy;
    }

    public void setGmt_modifiy(Date gmt_modifiy) {
        this.gmt_modifiy = gmt_modifiy;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number == null ? null : account_number.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name == null ? null : user_name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthady() {
        return birthady;
    }

    public void setBirthady(Date birthady) {
        this.birthady = birthady;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public BigDecimal getPerfect_ratio() {
        return perfect_ratio;
    }

    public void setPerfect_ratio(BigDecimal perfect_ratio) {
        this.perfect_ratio = perfect_ratio;
    }
}