package com.cd.wzjkj.canyi.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuzheng on 2016/11/24.
 */
public class TuanGuo2 implements Serializable {
    private int id;
    private String tgid;
    private String shopId;
    private long data;
    private List<List<CaiPing>> caipingses;

    private String IngredientsIconArray;

    private String Introduce;
    private String Title = "title";
    private String BackGroundImg = "http://img.redocn.com/sheying/20150304/diezishangpiaoliangderou_3973232.jpg";
    private double redprice = 998;
    private double blackprice = 1340.36;

    private String usedata = "2016.11.01-2017.03.30";
    private String usetime = "08:30-21:00";
    private int goods = 329;
    private int seld = 489;
    private int count;


    public List<List<CaiPing>> getCaipingses() {
        return caipingses;
    }

    public void setCaipingses(List<List<CaiPing>> caipingses) {
        this.caipingses = caipingses;
    }

    public String getIngredientsIconArray() {
        return IngredientsIconArray;
    }

    public void setIngredientsIconArray(String ingredientsIconArray) {
        IngredientsIconArray = ingredientsIconArray;
    }

    public String getTgid() {
        return tgid;
    }

    public void setTgid(String tgid) {
        this.tgid = tgid;
    }

    public String getIntroduce() {
        return Introduce;
    }

    public void setIntroduce(String introduce) {
        Introduce = introduce;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private String[] peiLiaos = new String[]{"http://img.redocn.com/sheying/20150304/diezishangpiaoliangderou_3973232.jpg", "http://img.redocn.com/sheying/20150304/diezishangpiaoliangderou_3973232.jpg", "http://img.redocn.com/sheying/20150304/diezishangpiaoliangderou_3973232.jpg"};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBackGroundImg() {
        return BackGroundImg;
    }

    public void setBackGroundImg(String backGroundImg) {
        BackGroundImg = backGroundImg;
    }

    public double getRedprice() {
        return redprice;
    }

    public void setRedprice(double redprice) {
        this.redprice = redprice;
    }

    public double getBlackprice() {
        return blackprice;
    }

    public void setBlackprice(double blackprice) {
        this.blackprice = blackprice;
    }


    public String[] getPeiLiaos() {
        return peiLiaos;
    }

    public void setPeiLiaos(String[] peiLiaos) {
        this.peiLiaos = peiLiaos;
    }

    public String getUsedata() {
        return usedata;
    }

    public void setUsedata(String usedata) {
        this.usedata = usedata;
    }

    public String getUsetime() {
        return usetime;
    }

    public void setUsetime(String usetime) {
        this.usetime = usetime;
    }

    public int getGoods() {
        return goods;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public int getSeld() {
        return seld;
    }

    public void setSeld(int seld) {
        this.seld = seld;
    }


    private int getischoosed(int num, List<CaiPing> caiPings) {
        for (int i = 0; i < caiPings.size(); i++) {
            if (caiPings.get(i).isChoosed()) {
                num += 1;
            }
        }
        return num;
    }
    public String togson(Gson gson){
        return gson.toJson(this);
    }

}
