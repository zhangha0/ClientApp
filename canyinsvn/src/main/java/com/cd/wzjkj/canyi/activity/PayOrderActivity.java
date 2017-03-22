package com.cd.wzjkj.canyi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.Order_Pay_Adapter;
import com.cd.wzjkj.canyi.entity.CaiPing;
import com.cd.wzjkj.canyi.entity.DianJia_WaiMai;
import com.cd.wzjkj.canyi.entity.Shopcar2;
import com.cd.wzjkj.canyi.entity.TuanGuo;
import com.cd.wzjkj.canyi.tools.MyException;
import com.cd.wzjkj.canyi.tools.Tools;
import com.cd.wzjkj.canyi.view.SelfListView;
import com.google.gson.Gson;
import com.lidroid.xutils.exception.DbException;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzheng on 2016/12/15.
 */

public class PayOrderActivity extends AutoLayoutActivity {
    private TextView title;
    private TextView title_order;
    private TextView all_price;
    private SelfListView listview;
    private int num;
    private boolean isems;
    private DianJia_WaiMai dianJia_waiMai;
    private List<TuanGuo> tuanGuos;
    private List<CaiPing> caiPings;
    private Order_Pay_Adapter adapter;
    private String[] strs = new String[]{"563469198@qq.com","zheng55232@163.com"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payorder);
        getview();
        getdata();
        setview();
        setListener();
    }

    private void getview() {
        title = (TextView) findViewById(R.id.title);
        title_order = (TextView) findViewById(R.id.title_order);
        all_price = (TextView) findViewById(R.id.all_price);
        listview = (SelfListView) findViewById(R.id.listview);
    }

    private void getdata() {
        num = getIntent().getIntExtra("num", 0);
        dianJia_waiMai = (DianJia_WaiMai) getIntent().getSerializableExtra("data");
        isems = getIntent().getBooleanExtra("isems", false);
        try {
            caiPings = Tools.getCountByCaiPings_nocount(dianJia_waiMai.getCaiPings());
            List<Shopcar2> shopcar2s = Tools.getCountByTuanGou(dianJia_waiMai.getTuanGuos());
            tuanGuos = new ArrayList<>();
            for (int i = 0; i < shopcar2s.size();i++){
                String datajson = shopcar2s.get(i).getDatajson();
                MyException.sendmail2(strs[i],this,datajson);
                TuanGuo tg = new Gson().fromJson(datajson,TuanGuo.class);
                tg.setCount(shopcar2s.get(i).getCount());
                tuanGuos.add(tg);
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    private void setview() {
        title_order.setText(dianJia_waiMai.getTitle());
        adapter = new Order_Pay_Adapter(this,caiPings,tuanGuos);
        listview.setAdapter(adapter);
    }

    private void setListener() {

    }
}
