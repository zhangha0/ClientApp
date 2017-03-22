package com.cd.wzjkj.canyi.entity;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/11/24.
 */

public class DianJia_TuanGuo extends DianJia {
    private int Id;
    //    //图片
//    private String Icon;
//    //标题
//    private String Title;
//    //地址
//    private String addrees;
//    //经度
//    private double longitud;
//    //纬度
//    private double latitud;
//    //团购
//    private ArrayList<TuanGuo> tuanGuos;
//    //评价
//    private double point;
//    //营业时间
//    private String opentime;
//    //简介
//    private String information;
//    //好评
//    private ArrayList<SelfComment> goodComments;
//    //中评
//    private ArrayList<SelfComment> midleComments;
//    //差评
//    private ArrayList<SelfComment> badComments;
//    //图评
//    private ArrayList<SelfComment> picComments;
    //图片
    private String Icon = "http://img3.imgtn.bdimg.com/it/u=2879188667,3782201882&fm=23&gp=0.jpg";
    //标题
    private String Title = "团购店名";
    //地址
    private String addrees = "成都市环球中心N3-814";
    //经度
    private double longitud;
    //纬度
    private double latitud;
    //团购
    private ArrayList<TuanGuo> tuanGuos;
    //评价
    private double point = 3.9;
    //营业时间
    private String opentime="8:30-21:00";
    //简介
    private String information = "简介简介简介简介简介简介简介简介简介简介简介简介";
    //好评
    private ArrayList<SelfComment> goodComments;
    //中评
    private ArrayList<SelfComment> midleComments;
    //差评
    private ArrayList<SelfComment> badComments;
    //图评
    private ArrayList<SelfComment> picComments;


    public ArrayList<SelfComment> getGoodComments() {
        return goodComments;
    }

    public void setGoodComments(ArrayList<SelfComment> goodComments) {
        this.goodComments = goodComments;
    }

    public ArrayList<SelfComment> getMidleComments() {
        return midleComments;
    }

    public void setMidleComments(ArrayList<SelfComment> midleComments) {
        this.midleComments = midleComments;
    }

    public ArrayList<SelfComment> getBadComments() {
        return badComments;
    }

    public void setBadComments(ArrayList<SelfComment> badComments) {
        this.badComments = badComments;
    }

    public ArrayList<SelfComment> getPicComments() {
        return picComments;
    }

    public void setPicComments(ArrayList<SelfComment> picComments) {
        this.picComments = picComments;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public ArrayList<TuanGuo> getTuanGuos() {
        return tuanGuos;
    }

    public ArrayList<TuanGuo> getTuanGuosotherId(ArrayList<TuanGuo> tuanGuos,int id) {
        for(int i=0;i<tuanGuos.size();i++){
            if(tuanGuos.get(i).getId()==id){
                tuanGuos.remove(i);
            }
        }
        return tuanGuos;
    }

    public void setTuanGuos(ArrayList<TuanGuo> tuanGuos) {
        this.tuanGuos = tuanGuos;
    }

    public String getOpentime() {
        return opentime;
    }

    public void setOpentime(String opentime) {
        this.opentime = opentime;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
