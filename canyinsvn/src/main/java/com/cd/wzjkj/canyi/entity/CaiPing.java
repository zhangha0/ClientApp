package com.cd.wzjkj.canyi.entity;

import java.io.Serializable;

public class CaiPing implements Serializable {
    private int Id;
//    private String title;
//    private String backGroundImg;
//    private String beiZhu;
    private String title2;
    private String title ="菜品";
    private String backGroundImg="http://img3.imgtn.bdimg.com/it/u=370903378,405657647&fm=23&gp=0.jpg";
    private String beiZhu = "备注";
    private String[] peiLiaos = new String[]{"http://img3.imgtn.bdimg.com/it/u=370903378,405657647&fm=23&gp=0.jpg","http://img3.imgtn.bdimg.com/it/u=370903378,405657647&fm=23&gp=0.jpg"};
    private double price = 3.5;
    private String cpId;
    private int count;
    private boolean choosed;
    private double redprice;
    private int FoodTypeId;

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public int getFoodTypeId() {
        return FoodTypeId;
    }

    public void setFoodTypeId(int foodTypeId) {
        FoodTypeId = foodTypeId;
    }

    public String getCpId() {
        return cpId;
    }

    public void setCpId(String cpId) {
        this.cpId = cpId;
    }

    public double getRedprice() {
        return redprice;
    }

    public void setRedprice(double redprice) {
        this.redprice = redprice;
    }

    public boolean isChoosed() {
        return choosed;
    }

    public void setChoosed(boolean choosed) {
        this.choosed = choosed;
    }

    public String getBaiZhu() {
        return beiZhu;
    }

    public void setBaiZhu(String baiZhu) {
        beiZhu = baiZhu;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

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

    public String getBeiZhu() {
        return beiZhu;
    }

    public void setBeiZhu(String beiZhu) {
        this.beiZhu = beiZhu;
    }

    public String[] getPeiLiaos() {
        return peiLiaos;
    }

    public void setPeiLiaos(String[] peiLiaos) {
        this.peiLiaos = peiLiaos;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
