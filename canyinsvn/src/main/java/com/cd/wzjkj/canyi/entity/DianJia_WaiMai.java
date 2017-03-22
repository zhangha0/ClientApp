package com.cd.wzjkj.canyi.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */
public class DianJia_WaiMai extends DianJia{
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
//    //评价
//    private double point;
//    //营业时间
//    private String opentime;
//    //简介
//    private String information;
//    //月售
//    private int seldbymonth;
//    //起送金
//    private double startseld;
//    //配送费
//    private double emsmoney;
//    //公告
//    private String gonggao;
//    //免配送
//    private double noems;
//    //分类
//    private ArrayList<WaiMai_class> waiMai_classes;
//    //单点套品
//    private ArrayList<CaiPing> caiPings;
//    //套餐
//    private ArrayList<TuanGuo> tuanGuos;
//    //电话
//    private String phone;
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
    private String Title = "外卖店名";
    //地址
    private String addrees = "成都市环球中心N3-814";
    //经度
    private double longitud;
    //纬度
    private double latitud;
    //评价
    private double point = 3.9;
    //营业时间
    private String opentime="8:30-21:00";
    //简介
    private String information = "简介简介简介简介简介简介简介简介简介简介简介简介";
    //月售
    private int seldbymonth = 302;
    //起送金
    private double startseld = 8;
    //配送费
    private double emsmoney = 2;
    //免配送
    private double noems = 40;
    //公告
    private String gonggao = "公告公告公告公告公告公告公告公告公告公告公告公告";
    //单点套品
    private List<CaiPing> caiPings;
    //套餐
    private List<TuanGuo> tuanGuos;

    private String phone="028-85201314";
    //好评
    private ArrayList<SelfComment> goodComments;
    //中评
    private ArrayList<SelfComment> midleComments;
    //差评
    private ArrayList<SelfComment> badComments;
    //图评
    private ArrayList<SelfComment> picComments;


    public ArrayList<SelfComment> getPicComments() {
        return picComments;
    }

    public void setPicComments(ArrayList<SelfComment> picComments) {
        this.picComments = picComments;
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CaiPing> getCaiPings() {
        return caiPings;
    }

    public void setCaiPings(List<CaiPing> caiPings) {
        this.caiPings = caiPings;
    }

    public List<TuanGuo> getTuanGuos() {
        return tuanGuos;
    }

    public void setTuanGuos(List<TuanGuo> tuanGuos) {
        this.tuanGuos = tuanGuos;
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

    public int getSeldbymonth() {
        return seldbymonth;
    }

    public void setSeldbymonth(int seldbymonth) {
        this.seldbymonth = seldbymonth;
    }

    public double getStartseld() {
        return startseld;
    }

    public void setStartseld(double startseld) {
        this.startseld = startseld;
    }

    public double getEmsmoney() {
        return emsmoney;
    }

    public void setEmsmoney(double emsmoney) {
        this.emsmoney = emsmoney;
    }

    public String getGonggao() {
        return gonggao;
    }

    public void setGonggao(String gonggao) {
        this.gonggao = gonggao;
    }

    public double getNoems() {
        return noems;
    }

    public void setNoems(double noems) {
        this.noems = noems;
    }

}
