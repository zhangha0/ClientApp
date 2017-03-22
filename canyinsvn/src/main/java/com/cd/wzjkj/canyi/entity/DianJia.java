package com.cd.wzjkj.canyi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/26.
 */
public class DianJia implements Serializable{
    private String Icon = "http://img3.imgtn.bdimg.com/it/u=2879188667,3782201882&fm=23&gp=0.jpg";
    //标题
    private String Title = "团购店名";
    //地址
    private String addrees = "成都市环球中心N3-814";
    //经度
    private double longitud;
    //纬度
    private double latitud;
    //评价
    private double point = 3.9;
    //人均
    private double price = 23.9;


    private String ShopId;
    private String Mobile;
    private String LogoImageUrl;
    private String CIF;
    private String CIFImageUrl;
    private String StartTime1;
    private String StartTime2;
    private String EndTime1;
    private String EndTime2;
    private int LikeNumber;
    private double StartTakeawayMoney;
    private double Humanfold;
    private double Freight;
    private double NoFreightMoney;
    private int DeliveryTime;
    private int TakeawayRange;
    private String EmployeeId;
    private int ProvinceId;
    private int CityId;
    private String ST;
    private double lat;
    private double lng;
    private String ZipCode;
    private String BackgroundImageUrl;
    private String Email;
    private double Score;
    private int AreaId;
    private int ShopTypeId;
    private boolean Week1;
    private boolean Week2;
    private boolean Week3;
    private boolean Week4;
    private boolean Week5;
    private boolean Week6;
    private boolean Week7;
    private int BDId;
    private String SpecialtyFoodId;
    private String ShopName;
    private String Remark;

    public String getShopId() {
        return ShopId;
    }

    public void setShopId(String shopId) {
        ShopId = shopId;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getLogoImageUrl() {
        return LogoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        LogoImageUrl = logoImageUrl;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public String getCIFImageUrl() {
        return CIFImageUrl;
    }

    public void setCIFImageUrl(String CIFImageUrl) {
        this.CIFImageUrl = CIFImageUrl;
    }

    public String getStartTime1() {
        return StartTime1;
    }

    public void setStartTime1(String startTime1) {
        StartTime1 = startTime1;
    }

    public String getStartTime2() {
        return StartTime2;
    }

    public void setStartTime2(String startTime2) {
        StartTime2 = startTime2;
    }

    public String getEndTime1() {
        return EndTime1;
    }

    public void setEndTime1(String endTime1) {
        EndTime1 = endTime1;
    }

    public String getEndTime2() {
        return EndTime2;
    }

    public void setEndTime2(String endTime2) {
        EndTime2 = endTime2;
    }

    public int getLikeNumber() {
        return LikeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        LikeNumber = likeNumber;
    }

    public double getStartTakeawayMoney() {
        return StartTakeawayMoney;
    }

    public void setStartTakeawayMoney(int startTakeawayMoney) {
        StartTakeawayMoney = startTakeawayMoney;
    }

    public double getHumanfold() {
        return Humanfold;
    }

    public void setHumanfold(int humanfold) {
        Humanfold = humanfold;
    }

    public double getFreight() {
        return Freight;
    }

    public void setFreight(int freight) {
        Freight = freight;
    }

    public double getNoFreightMoney() {
        return NoFreightMoney;
    }

    public void setNoFreightMoney(int noFreightMoney) {
        NoFreightMoney = noFreightMoney;
    }

    public int getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        DeliveryTime = deliveryTime;
    }

    public int getTakeawayRange() {
        return TakeawayRange;
    }

    public void setTakeawayRange(int takeawayRange) {
        TakeawayRange = takeawayRange;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public int getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(int provinceId) {
        ProvinceId = provinceId;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getBackgroundImageUrl() {
        return BackgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        BackgroundImageUrl = backgroundImageUrl;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public double getScore() {
        return Score;
    }

    public void setScore(double score) {
        Score = score;
    }

    public int getAreaId() {
        return AreaId;
    }

    public void setAreaId(int areaId) {
        AreaId = areaId;
    }

    public int getShopTypeId() {
        return ShopTypeId;
    }

    public void setShopTypeId(int shopTypeId) {
        ShopTypeId = shopTypeId;
    }

    public boolean isWeek1() {
        return Week1;
    }

    public void setWeek1(boolean week1) {
        Week1 = week1;
    }

    public boolean isWeek2() {
        return Week2;
    }

    public void setWeek2(boolean week2) {
        Week2 = week2;
    }

    public boolean isWeek3() {
        return Week3;
    }

    public void setWeek3(boolean week3) {
        Week3 = week3;
    }

    public boolean isWeek4() {
        return Week4;
    }

    public void setWeek4(boolean week4) {
        Week4 = week4;
    }

    public boolean isWeek5() {
        return Week5;
    }

    public void setWeek5(boolean week5) {
        Week5 = week5;
    }

    public boolean isWeek6() {
        return Week6;
    }

    public void setWeek6(boolean week6) {
        Week6 = week6;
    }

    public boolean isWeek7() {
        return Week7;
    }

    public void setWeek7(boolean week7) {
        Week7 = week7;
    }

    public int getBDId() {
        return BDId;
    }

    public void setBDId(int BDId) {
        this.BDId = BDId;
    }

    public String getSpecialtyFoodId() {
        return SpecialtyFoodId;
    }

    public void setSpecialtyFoodId(String specialtyFoodId) {
        SpecialtyFoodId = specialtyFoodId;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAddrees() {
        return addrees;
    }

    public void setAddrees(String addrees) {
        this.addrees = addrees;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
