package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.OrderWaiMaiAdapter;
import com.cd.wzjkj.canyi.entity.CaiPing;
import com.cd.wzjkj.canyi.entity.Order;
import com.cd.wzjkj.canyi.entity.TuanGuo;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/8.
 */

public class OrderWaiMaiActivity extends AutoLayoutActivity {
    private TextView title;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private ListView listview;
    private ArrayList<Order> orders;
    private int rbnum;
    private OrderWaiMaiAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        getview();
        getdata();
        setview();
        setListener();
    }

    private void getview() {
        title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.waimai_order));
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        rb1.setText(getResources().getString(R.string.will_pay));
        rb2.setText(getResources().getString(R.string.making));
        rb3.setText(getResources().getString(R.string.will_comment));
        rb4.setVisibility(View.GONE);
        listview = (ListView) findViewById(R.id.listview);
        rbnum = getIntent().getIntExtra("data", 0);
    }

    private void getdata() {
        switch (rbnum) {
            case 0:
                rb1.setChecked(true);
                break;
            case 1:
                rb2.setChecked(true);
                break;
            case 2:
                rb3.setChecked(true);
                break;
            case 3:
                rb4.setChecked(true);
                break;
        }
        orders = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Order o = new Order();
            o.setType(rbnum);
            ArrayList<CaiPing> caiPings = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                CaiPing c = new CaiPing();
                caiPings.add(c);
            }
            ArrayList<TuanGuo> tuanGuos = new ArrayList<>();
            for (int m = 0; m < 8; m++) {
                TuanGuo tg = new TuanGuo();
                ArrayList<CaiPing> caiPings1 = new ArrayList<>();
                ArrayList<CaiPing> caiPings2 = new ArrayList<>();
                ArrayList<CaiPing> caiPings3 = new ArrayList<>();
                ArrayList<CaiPing> caiPings4 = new ArrayList<>();
                ArrayList<CaiPing> caiPings5 = new ArrayList<>();
                for (int j = 0; j < 3; j++) {
                    CaiPing cp1 = new CaiPing();
                    CaiPing cp2 = new CaiPing();
                    CaiPing cp3 = new CaiPing();
                    CaiPing cp4 = new CaiPing();
                    CaiPing cp5 = new CaiPing();
                    caiPings1.add(cp1);
                    caiPings2.add(cp2);
                    caiPings3.add(cp3);
                    caiPings4.add(cp4);
                    caiPings5.add(cp5);
                }
                tg.setFirst_cp(caiPings1);
                tg.setSecond_cp(caiPings2);
                tg.setThird_cp(caiPings3);
                tg.setTianping_cp(caiPings4);
                tg.setDrinks_cp(caiPings5);
                tg.getFirst_cp().get(0).setChoosed(true);
                tg.getSecond_cp().get(1).setChoosed(true);
                tg.getThird_cp().get(2).setChoosed(true);
                tg.getTianping_cp().get(1).setChoosed(true);
                tg.getDrinks_cp().get(0).setChoosed(true);
                tuanGuos.add(tg);
            }
            o.setTuanGuos(tuanGuos);
            o.setCaiPings(caiPings);
            orders.add(o);
        }
    }

    private void setview() {
        adapter = new OrderWaiMaiAdapter(this, orders);
        listview.setAdapter(adapter);
    }

    private void setListener() {
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbnum == 0) {
                    return;
                }
                rbnum = 0;
                getdata();
                setview();
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbnum == 1) {
                    return;
                }
                rbnum = 1;
                getdata();
                setview();
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbnum == 2) {
                    return;
                }
                rbnum = 2;
                getdata();
                setview();
            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbnum == 3) {
                    return;
                }
                rbnum = 3;
                getdata();
                setview();
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void onclick(int type, int position) {
        switch (type) {
            case 2:
                Intent intent = new Intent(OrderWaiMaiActivity.this, Input_CommentActivity.class);
                intent.putExtra("data", orders.get(position));
                startActivity(intent);
                break;
        }
    }
}
