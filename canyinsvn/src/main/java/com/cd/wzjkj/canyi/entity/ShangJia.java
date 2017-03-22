package com.cd.wzjkj.canyi.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class ShangJia implements Serializable{
	//唯一标识
    private int Id;
    //公司名
	private String Title;
    //介绍
	private String Remark;
    //背景图片（多张用逗号隔开）
	private String BackGroundImg;
    //Logo
    private String LogoImg;
    //地址
    private String Address;
    //电话
    private String Phone;
    //人均价
    private double Average;
    //中午开始时间
    private String StartTimeOne;
    //中午结束时间
    private String EndTimeOne;
    //晚上开始时间
    private String StartTimeTwo;
    //晚上结束时间
    private String EndTimeTwo;
    //查看次数
    private int Shows;
    //起送价
    private double StartPrice;
    //配送费
    private double Freight;
    //配送时间
    private int Time;
    //总体评分
    private double Evaluate;
    //口味评分
    private double Flavor;
    //服务评分
    private double Service;
    //注册时间
    private String AddTime;
    //店名
    private String ShopName;
    //店铺类型
    private String BusType;
    //邮箱
    private String Email;
    //省份
    private String Province;
    //城市
    private String City;
    //税号
    private String CIFNum;
    //税图单张
    private String CIFImg;
    //邮编
    private String ZipCode;
    //特色菜
    private ArrayList<CaiPing> caiPings;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getBackGroundImg() {
        if(BackGroundImg==null||"null".equals(BackGroundImg))
            return "";
        return BackGroundImg;
    }

    public void setBackGroundImg(String backGroundImg) {
        BackGroundImg = backGroundImg;
    }

    public String getLogoImg() {
        return LogoImg;
    }

    public void setLogoImg(String logoImg) {
        LogoImg = logoImg;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public double getAverage() {
        return Average;
    }

    public void setAverage(double average) {
        Average = average;
    }

    public String getStartTimeOne() {
        return StartTimeOne;
    }

    public void setStartTimeOne(String startTimeOne) {
        StartTimeOne = startTimeOne;
    }

    public String getEndTimeOne() {
        return EndTimeOne;
    }

    public void setEndTimeOne(String endTimeOne) {
        EndTimeOne = endTimeOne;
    }

    public String getStartTimeTwo() {
        return StartTimeTwo;
    }

    public void setStartTimeTwo(String startTimeTwo) {
        StartTimeTwo = startTimeTwo;
    }

    public String getEndTimeTwo() {
        return EndTimeTwo;
    }

    public void setEndTimeTwo(String endTimeTwo) {
        EndTimeTwo = endTimeTwo;
    }

    public int getShows() {
        return Shows;
    }

    public void setShows(int shows) {
        Shows = shows;
    }

    public double getStartPrice() {
        return StartPrice;
    }

    public void setStartPrice(double startPrice) {
        StartPrice = startPrice;
    }

    public double getFreight() {
        return Freight;
    }

    public void setFreight(double freight) {
        Freight = freight;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public double getEvaluate() {
        return Evaluate;
    }

    public void setEvaluate(double evaluate) {
        Evaluate = evaluate;
    }

    public double getFlavor() {
        return Flavor;
    }

    public void setFlavor(double flavor) {
        Flavor = flavor;
    }

    public double getService() {
        return Service;
    }

    public void setService(double service) {
        Service = service;
    }

    public String getAddTime() {
        return AddTime;
    }

    public void setAddTime(String addTime) {
        AddTime = addTime;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getBusType() {
        return BusType;
    }

    public void setBusType(String busType) {
        BusType = busType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCIFNum() {
        return CIFNum;
    }

    public void setCIFNum(String CIFNum) {
        this.CIFNum = CIFNum;
    }

    public String getCIFImg() {
        return CIFImg;
    }

    public void setCIFImg(String CIFImg) {
        this.CIFImg = CIFImg;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public ArrayList<CaiPing> getCaiPings() {
        return caiPings;
    }

    public void setCaiPings(ArrayList<CaiPing> caiPings) {
        this.caiPings = caiPings;
    }
}
