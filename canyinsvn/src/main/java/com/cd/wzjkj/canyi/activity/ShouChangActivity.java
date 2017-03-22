package com.cd.wzjkj.canyi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.ShangChang_LV_Adapter;
import com.cd.wzjkj.canyi.entity.DianJia;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/8.
 */

public class ShouChangActivity extends AutoLayoutActivity{
    private TextView title;
    private ListView listview;
    private ArrayList<DianJia> shangJias;
    private ShangChang_LV_Adapter adapter;

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
        title.setText(getResources().getString(R.string.my_shouchang));
        listview = (ListView) findViewById(R.id.listview);
        findViewById(R.id.rblayout).setVisibility(View.GONE);
    }

    private void getdata() {
        shangJias = new ArrayList<>();
        for(int i=0;i<5;i++){
            DianJia o = new DianJia();
            shangJias.add(o);
        }
    }

    private void setview() {
        adapter = new ShangChang_LV_Adapter(this,shangJias);
        listview.setAdapter(adapter);
    }

    private void setListener() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
