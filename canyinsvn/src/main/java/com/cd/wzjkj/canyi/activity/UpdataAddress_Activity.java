package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.AddressSelf;
import com.cd.wzjkj.canyi.tools.Tools;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by liuzheng on 2016/12/9.
 */
public class UpdataAddress_Activity extends AutoLayoutActivity {
    private TextView title;
    private TextView right_tv2;
    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private ImageView iv1;
    private AddressSelf addressSelf;
    private boolean isdefult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_updata);
        getview();
        getdata();
        setview();
        setListener();
    }

    private void getview() {
        title = (TextView) findViewById(R.id.title);
        title.setText(getResources().getString(R.string.addaddress));
        right_tv2 = (TextView) findViewById(R.id.right_tv2);
        right_tv2.setText(getResources().getString(R.string.save));
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        iv1 = (ImageView) findViewById(R.id.iv1);
    }

    private void getdata() {
        addressSelf = (AddressSelf) getIntent().getSerializableExtra("data");
    }

    private void setview() {
        if(addressSelf==null){
            return;
        }else{
            et1.setText(addressSelf.getName());
            et2.setText(addressSelf.getPhone());
            et3.setText(addressSelf.getShen_city_region());
            et4.setText(addressSelf.getAddress());
            if(addressSelf.isdefult()){
                iv1.setImageResource(R.drawable.choose_yes_tc);
                isdefult = true;
            }else{
                isdefult = false;
                iv1.setImageResource(R.drawable.choose_no_tc);
            }
        }

    }

    private void setListener() {
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isdefult){
                    isdefult = false;
                    iv1.setImageResource(R.drawable.choose_no_tc);
                }else{
                    iv1.setImageResource(R.drawable.choose_yes_tc);
                    isdefult = true;
                }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.right2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddressSelf addressSelf = new AddressSelf();
                addressSelf.setAddress(et4.getText().toString());
                addressSelf.setName(et1.getText().toString());
                addressSelf.setPhone(et2.getText().toString());
                addressSelf.setShen_city_region(et3.getText().toString());
                addressSelf.setIsdefult(isdefult);
                if(UpdataAddress_Activity.this.addressSelf !=null){
                    try {
                        addressSelf.setId(UpdataAddress_Activity.this.addressSelf.getId());
                        Tools.addressdb.update(addressSelf, WhereBuilder.b("id","=",""+UpdataAddress_Activity.this.addressSelf.getId()));
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        Tools.addressdb.save(addressSelf);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("data",addressSelf);
                setResult(9,intent);
                finish();
            }
        });
        findViewById(R.id.tomap_choose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et3.setText("四川省成都市武候区");
            }
        });
    }
}
