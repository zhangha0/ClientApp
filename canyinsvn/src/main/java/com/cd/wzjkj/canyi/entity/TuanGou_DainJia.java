package com.cd.wzjkj.canyi.entity;

import java.io.Serializable;

/**
 * Created by liuzheng on 2017/1/3.
 */

public class TuanGou_DainJia extends DianJia implements Serializable{
    private double DiscountPrice;
    private double Distance;
    private String FoodTitle;
    private double Price;
    private String AreaName;
    private int GroupOrderCount;
    private int TakeawayOrderCount;
    private String FoodId;
    private String FoodImageUrl;


    public int getGroupOrderCount() {
        return GroupOrderCount;
    }

    public void setGroupOrderCount(int groupOrderCount) {
        GroupOrderCount = groupOrderCount;
    }

    public int getTakeawayOrderCount() {
        return TakeawayOrderCount;
    }

    public void setTakeawayOrderCount(int takeawayOrderCount) {
        TakeawayOrderCount = takeawayOrderCount;
    }

    public String getFoodImageUrl() {
        return FoodImageUrl;
    }

    public void setFoodImageUrl(String foodImageUrl) {
        FoodImageUrl = foodImageUrl;
    }

    public double getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        DiscountPrice = discountPrice;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }

    public String getFoodTitle() {
        return FoodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        FoodTitle = foodTitle;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getFoodId() {
        return FoodId;
    }

    public void setFoodId(String foodId) {
        FoodId = foodId;
    }
}
