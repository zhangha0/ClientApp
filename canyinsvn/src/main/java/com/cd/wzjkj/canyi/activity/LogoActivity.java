package com.cd.wzjkj.canyi.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.Paramss;
import com.cd.wzjkj.canyi.entity.User;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.cd.wzjkj.canyi.tools.MD5;
import com.cd.wzjkj.canyi.tools.Tools;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzheng on 2016/12/20.
 */
public class LogoActivity extends AutoLayoutActivity {
    private EditText et1;
    private EditText et2;
    private boolean flag = true;
    private ImageView iv;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                JSONObject obj = (JSONObject) msg.obj;
                try {
                    if(obj.getInt("statusCode")==1) {
                        User u = new User();
                        u.setMobile(obj.getJSONArray("data").getJSONObject(0).getString("Mobile"));
                        u.setUserid(obj.getJSONArray("data").getJSONObject(0).getString("Id"));
                        Httptools.user = u;
                        Tools.addressdb.deleteAll(User.class);
                        Tools.userdb.save(u);
                        Httptools.makeText(LogoActivity.this,"登录成功",false);
                        finish();
                    }else{
                        Httptools.makeText(LogoActivity.this,"登录失败",false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        iv = (ImageView) findViewById(R.id.iv);
        setlistener();
    }

    private void setlistener() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.layoutiv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    iv.setImageResource(R.mipmap.psw_close);
                    et2.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    flag = false;
                } else {
                    iv.setImageResource(R.mipmap.psw_see);
                    et2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    flag = true;
                }
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Httptools.makeText(LogoActivity.this, "帐号==" + et1.getText().toString() + "---密码==" + et2.getText().toString(), true);
                String str = "";
                if(Tools.getViewNull(et1)){
                    str = et1.getHint().toString();
                }else if(Tools.getViewNull(et2)){
                    str = et2.getHint().toString();
                }
                if("".equals(str)) {
                    ArrayList<Paramss> paramsses = new ArrayList<Paramss>();
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("mobile", et1.getText().toString());
                    map.put("password", MD5.GetMD5Code(et2.getText().toString()));
                    paramsses.add(new Paramss(map, 1, "/Customer/Login"));
                    Httptools.sendpost1(LogoActivity.this, paramsses, handler, true);
                }else{
                    Httptools.makeText(LogoActivity.this,str,false);
                }
            }
        });
    }
}
