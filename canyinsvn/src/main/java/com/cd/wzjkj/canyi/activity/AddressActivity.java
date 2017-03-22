package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.AddressAdapter;
import com.cd.wzjkj.canyi.entity.AddressSelf;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.cd.wzjkj.canyi.tools.Tools;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liuzheng on 2016/12/9.
 */

public class AddressActivity extends AutoLayoutActivity {
    private TextView title;
    private TextView title_2;
    private ListView listview;
    private AddressAdapter adapter;
    private int type;
    private List<AddressSelf> addressSelfs;
    private int num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        getview();
        getdata();
        setview();
        setListener();
    }

    private void getdata() {
        try {
            addressSelfs = Tools.addressdb.findAll(Selector.from(AddressSelf.class).where(WhereBuilder.b("userid", "=", Httptools.user.getUserid())));
            if(addressSelfs==null){
                addressSelfs = new ArrayList<>();
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void setview() {
        adapter = new AddressAdapter(this,addressSelfs);
        listview.setAdapter(adapter);
    }

    private void setListener() {
        findViewById(R.id.layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this,UpdataAddress_Activity.class);
                startActivityForResult(intent,8);
            }
        });
        findViewById(R.id.right2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this,UpdataAddress_Activity.class);
                startActivityForResult(intent,8);
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getview() {
        title = (TextView) findViewById(R.id.title);
        title_2 = (TextView) findViewById(R.id.right_tv2);
        listview = (ListView) findViewById(R.id.listview);
        type = getIntent().getIntExtra("data", 0);
        title_2.setText(getResources().getString(R.string.add));
        if (type == 0) {
            title.setText(getResources().getString(R.string.choose_address));
            findViewById(R.id.layout1).setVisibility(View.GONE);
            findViewById(R.id.right2).setVisibility(View.VISIBLE);
        } else {
            title.setText(getResources().getString(R.string.my_address));
            findViewById(R.id.layout1).setVisibility(View.VISIBLE);
            findViewById(R.id.right2).setVisibility(View.GONE);
        }
    }
    public void delete(int position){
        try {
            Tools.addressdb.delete(AddressSelf.class,WhereBuilder.b("id","=",""+addressSelfs.get(position).getId()).and("userid","=",Httptools.user.getUserid()));
            addressSelfs.remove(position);
            adapter.notifyDataSetChanged();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    public void updata(int position){
        Intent intent = new Intent(this,UpdataAddress_Activity.class);
        num = position;
        intent.putExtra("data",addressSelfs.get(position));
        startActivityForResult(intent,7);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==9&&requestCode==7){
            AddressSelf a = (AddressSelf) data.getSerializableExtra("data");
            addressSelfs.set(num,a);
        }else if(resultCode==9&&requestCode==8){
            AddressSelf a = (AddressSelf) data.getSerializableExtra("data");
            addressSelfs.add(a);
        }
        adapter.notifyDataSetChanged();
    }
}
