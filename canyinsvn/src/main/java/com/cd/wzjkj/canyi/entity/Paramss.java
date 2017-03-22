package com.cd.wzjkj.canyi.entity;

/**
 * Created by Administrator on 2016/8/5,0005.
 */

import java.util.Map;

public class Paramss {

    private Map<String, String> map;
    private int num;
    private String url;

    public Paramss() {
        super();
    }

    public Paramss(Map<String, String> map, int num, String url) {
        this.map = map;
        this.num = num;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }


}
