package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.OrderAdapter;
import com.cd.wzjkj.canyi.entity.CaiPing;
import com.cd.wzjkj.canyi.entity.Order;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/8.
 */

public class OrderActivity extends AutoLayoutActivity {
    private TextView title;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private ListView listview;
    private ArrayList<Order> orders;
    private int rbnum;
    private OrderAdapter adapter;

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
        title.setText(getResources().getString(R.string.my_order));
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
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
            o.setCaiPings(caiPings);
            orders.add(o);
        }
    }

    private void setview() {
        adapter = new OrderAdapter(this, orders);
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


    public void onclick(int type,int position) {
        switch (type){
            case 2:
                Intent intent = new Intent(OrderActivity.this,Input_CommentActivity.class);
                intent.putExtra("data",orders.get(position));
                startActivity(intent);
                break;
        }
    }
}
