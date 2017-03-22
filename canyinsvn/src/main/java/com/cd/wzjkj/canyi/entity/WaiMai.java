package com.cd.wzjkj.canyi.entity;

import java.io.Serializable;

/**
 * Created by liuzheng on 2016/11/28.
 */

public class WaiMai implements Serializable {
    private String title = "菜品";
    private String backGroundImg = "http://img3.imgtn.bdimg.com/it/u=370903378,405657647&fm=23&gp=0.jpg";

    private double price = 25;
    private int saldmoney = 62;
    private int goods = 20;
    private int count = 0;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackGroundImg() {
        return backGroundImg;
    }

    public void setBackGroundImg(String backGroundImg) {
        this.backGroundImg = backGroundImg;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSaldmoney() {
        return saldmoney;
    }

    public void setSaldmoney(int saldmoney) {
        this.saldmoney = saldmoney;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
