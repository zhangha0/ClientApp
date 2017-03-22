package com.cd.wzjkj.canyi.entity;

/**
 * Created by Administrator on 2016/8/5,0005.
 */
public class User {
    private int id;
    private String userid;
    private String Name;
    private String Email;
    private int ProvinceId;
    private String RegisterDate;
    private String Mobile;
    private boolean haspsw;
    private boolean showmoney;




    public boolean isShowmoney() {
        return showmoney;
    }
    public void setShowmoney(boolean showmoney) {
        this.showmoney = showmoney;
    }
    public boolean isHaspsw() {
        return haspsw;
    }
    public void setHaspsw(boolean haspsw) {
        this.haspsw = haspsw;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String loginName) {
        Name = loginName;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setMobile(String mobile) {
        Mobile = mobile;
    }
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userId) {
        this.userid = userId;
    }
    public String getMobile() {
        return Mobile;
    }
    public int getProvinceId() {
        return ProvinceId;
    }
    public String getRegisterDate() {
        return RegisterDate;
    }
    public void setProvinceId(int provinceId) {
        ProvinceId = provinceId;
    }
    public void setRegisterDate(String registerDate) {
        RegisterDate = registerDate;
    }
}

