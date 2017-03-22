package com.cd.wzjkj.canyi.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuzheng on 2016/11/24.
 */
public class TuanGuo implements Serializable {
    private int id;
    private String tgid;
    private String shopId;
    private long data;
    //标题
//    private String Title;
    //图片
//    private String BackGroundImg;
    //价格
//    private double redprice;
    //价格
//    private double blackprice;
    //菜品
//    private List<CaiPing> first_cp;
//    private List<CaiPing> second_cp;
//    private List<CaiPing> third_cp;
    //甜品
//    private String[] tianpings;
    //饮料
//    private String[] drinks;
    //使用日期
//    private String usedata;
    //使用时间
//    private String usetime;
    //已收藏
//    private int goods;
    //已卖
//    private int seld;

    private String IngredientsIconArray;

    private String Introduce;
    private String Title = "title";
    private String BackGroundImg = "http://img.redocn.com/sheying/20150304/diezishangpiaoliangderou_3973232.jpg";
    private double redprice = 998;
    private double blackprice = 1340.36;
    private List<CaiPing> first_cp;
    private List<CaiPing> second_cp;
    private List<CaiPing> third_cp;
    private List<CaiPing> tianping_cp;
    private List<CaiPing> drinks_cp;
    private String usedata = "2016.11.01-2017.03.30";
    private String usetime = "08:30-21:00";
    private int goods = 329;
    private int seld = 489;
    private int count;

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

    public List<CaiPing> getFirst_cp() {
        return first_cp;
    }

    public void setFirst_cp(List<CaiPing> first_cp) {
        this.first_cp = first_cp;
    }

    public List<CaiPing> getSecond_cp() {
        return second_cp;
    }

    public void setSecond_cp(List<CaiPing> second_cp) {
        this.second_cp = second_cp;
    }

    public List<CaiPing> getThird_cp() {
        return third_cp;
    }

    public void setThird_cp(List<CaiPing> third_cp) {
        this.third_cp = third_cp;
    }

    public String[] getPeiLiaos() {
        return peiLiaos;
    }

    public void setPeiLiaos(String[] peiLiaos) {
        this.peiLiaos = peiLiaos;
    }

    public List<CaiPing> getTianping_cp() {
        return tianping_cp;
    }

    public void setTianping_cp(List<CaiPing> tianping_cp) {
        this.tianping_cp = tianping_cp;
    }

    public List<CaiPing> getDrinks_cp() {
        return drinks_cp;
    }

    public void setDrinks_cp(List<CaiPing> drinks_cp) {
        this.drinks_cp = drinks_cp;
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

    public int getchoosed() {
        int num = 0;
        num = getischoosed(num, first_cp);
        num = getischoosed(num, second_cp);
        num = getischoosed(num, third_cp);
        num = getischoosed(num, tianping_cp);
        num = getischoosed(num, drinks_cp);
        return num;
    }

    private int getischoosed(int num, List<CaiPing> caiPings) {
        for (int i = 0; i < caiPings.size(); i++) {
            if (caiPings.get(i).isChoosed()) {
                num += 1;
            }
        }
        return num;
    }
    public double getprice2(){
        double d = 0;
        for(int i=0;i<first_cp.size();i++){
            if(first_cp.get(i).isChoosed()){
                d += first_cp.get(i).getPrice();
            }
        }
        for(int i=0;i<second_cp.size();i++){
            if(second_cp.get(i).isChoosed()){
                d += second_cp.get(i).getPrice();
            }
        }
        for(int i=0;i<third_cp.size();i++){
            if(third_cp.get(i).isChoosed()){
                d += third_cp.get(i).getPrice();
            }
        }
        for(int i=0;i<tianping_cp.size();i++){
            if(tianping_cp.get(i).isChoosed()){
                d += tianping_cp.get(i).getPrice();
            }
        }
        for(int i=0;i<drinks_cp.size();i++){
            if(drinks_cp.get(i).isChoosed()){
                d += drinks_cp.get(i).getPrice();
            }
        }
        return d;
    }
    public String togson(Gson gson){
        return gson.toJson(this);
    }

}
