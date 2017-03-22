package com.cd.wzjkj.canyi.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/8.
 */

public class Order implements Serializable{
    private int type;
    private String title = "名字";
    private String orderId ="2016345789";
    private String name = "店名";
    private String order_data = "2016-08-30";
    private String last_user_data = "2016-10-30";
    private int count = 3;
    private double price = 5.5;
    private String icon = "http://img3.imgtn.bdimg.com/it/u=370903378,405657647&fm=23&gp=0.jpg";
    private ArrayList<CaiPing> caiPings;
    private ArrayList<TuanGuo> tuanGuos;

    public ArrayList<TuanGuo> getTuanGuos() {
        return tuanGuos;
    }

    public void setTuanGuos(ArrayList<TuanGuo> tuanGuos) {
        this.tuanGuos = tuanGuos;
    }

    public ArrayList<CaiPing> getCaiPings() {
        return caiPings;
    }

    public void setCaiPings(ArrayList<CaiPing> caiPings) {
        this.caiPings = caiPings;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder_data() {
        return order_data;
    }

    public void setOrder_data(String order_data) {
        this.order_data = order_data;
    }

    public String getLast_user_data() {
        return last_user_data;
    }

    public void setLast_user_data(String last_user_data) {
        this.last_user_data = last_user_data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
