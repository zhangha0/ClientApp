package com.cd.wzjkj.canyi.entity;

import java.io.Serializable;

/**
 * Created by liuzheng on 2016/12/9.
 */

public class AddressSelf implements Serializable{
    private int id;
    private int userid;
    private String name;
    private String phone;
    private String shen_city_region;
    private boolean isdefult;
    private String address;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isdefult() {
        return isdefult;
    }

    public void setIsdefult(boolean isdefult) {
        this.isdefult = isdefult;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShen_city_region() {
        return shen_city_region;
    }

    public void setShen_city_region(String shen_city_region) {
        this.shen_city_region = shen_city_region;
    }
}
