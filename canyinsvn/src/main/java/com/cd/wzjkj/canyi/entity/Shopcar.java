package com.cd.wzjkj.canyi.entity;

import java.io.Serializable;

/**
 * Created by liuzheng on 2016/12/15.
 */

public class Shopcar implements Serializable{
    private int id;
    private String userid;
    private int spid;
    private int count;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
