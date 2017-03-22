package com.cd.wzjkj.canyi.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.Paramss;
import com.cd.wzjkj.canyi.entity.User;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.cd.wzjkj.canyi.tools.MD5;
import com.cd.wzjkj.canyi.tools.SmsSender;
import com.cd.wzjkj.canyi.tools.Tools;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzheng on 2016/12/21.
 */
public class ZhucheActivity extends AutoLayoutActivity {
    private ImageView back_iv;
    private ImageView iv;
    private EditText et_username;
    private TextView btn;
    private TextView btn_send;
    private EditText et_send;
    private EditText et_psw;
    private LinearLayout layout_psw;
    private LinearLayout layout_send;
    private LinearLayout layout_username;
    private boolean flag = true;
    private int second;
    private boolean flag_layout = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (second > 0) {
                    btn_send.setText(second + "s");
                    second--;
                } else {
                    btn_send.setText(getResources().getString(R.string.send_agin));
                }
            } else if (msg.what == 2) {
                if (second == 0) {
                    second = 6;
                    handler.postDelayed(r3, 0);
                    flag_layout = false;
                    setview();
                }
            } else if (msg.what == 3) {
                JSONObject obj = (JSONObject) msg.obj;
                try {
                    if (obj.getInt("statusCode") == 1) {
                        User u = new User();
                        u.setMobile(obj.getJSONArray("data").getJSONObject(0).getString("Mobile"));
                        u.setUserid(obj.getJSONArray("data").getJSONObject(0).getString("Id"));
                        Httptools.user = u;
                        Tools.addressdb.deleteAll(User.class);
                        Tools.userdb.save(u);
                        Httptools.makeText(ZhucheActivity.this, "注册成功", false);
                        finish();
                    } else {
                        Httptools.makeText(ZhucheActivity.this, "验证失败", false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private Runnable r3;
    private SmsSender smsSender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuche);
        getview();
        setview();
        setlistener();
    }

    private void getview() {
        back_iv = (ImageView) findViewById(R.id.back_iv);
        et_username = (EditText) findViewById(R.id.et_username);
        et_send = (EditText) findViewById(R.id.et_send);
        et_psw = (EditText) findViewById(R.id.et_psw);
        btn_send = (TextView) findViewById(R.id.btn_send);
        btn = (TextView) findViewById(R.id.btn);
        iv = (ImageView) findViewById(R.id.iv);
        layout_psw = (LinearLayout) findViewById(R.id.layout_psw);
        layout_send = (LinearLayout) findViewById(R.id.layout_send);
        layout_username = (LinearLayout) findViewById(R.id.layout_username);
        r3 = new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情，这里再次调用此Runnable对象，以实现每两秒实现一次的定时器操作
                handler.sendEmptyMessage(1);
                handler.postDelayed(this, 1000);
            }
        };
    }

    private void setview() {
        btn_send.setText(getResources().getString(R.string.send_agin));
        if (flag_layout) {
            btn.setText(getResources().getString(R.string.next));
            back_iv.setImageResource(R.mipmap.close);
            layout_psw.setVisibility(View.GONE);
            layout_send.setVisibility(View.GONE);
            layout_username.setVisibility(View.VISIBLE);
        } else {
            btn.setText(getResources().getString(R.string.zhuche));
            back_iv.setImageResource(R.mipmap.back_white);
            layout_psw.setVisibility(View.VISIBLE);
            layout_send.setVisibility(View.VISIBLE);
            layout_username.setVisibility(View.GONE);
        }
    }

    private void setlistener() {
        findViewById(R.id.layoutiv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    iv.setImageResource(R.mipmap.psw_close);
                    et_psw.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    flag = false;
                } else {
                    iv.setImageResource(R.mipmap.psw_see);
                    et_psw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    flag = true;
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag_layout) {
                    if (!Tools.getViewNull(et_username)) {
                        ArrayList<Paramss> paramsses = new ArrayList<Paramss>();
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("NotaCode", "86");
                        map.put("Phone", et_username.getText().toString());
                        paramsses.add(new Paramss(map, 2, "/Sender/SenderSms"));
                        Httptools.sendpost1(ZhucheActivity.this, paramsses, handler, true);
                    } else {
                        Httptools.makeText(ZhucheActivity.this, et_username.getHint().toString(), false);
                    }
                    //发送验证并倒计时
                } else {
                    String str = "";
                    if (Tools.getViewNull(et_psw)) {
                        str = et_psw.getHint().toString();
                    } else if (Tools.getViewNull(et_send)) {
                        str = et_send.getHint().toString();
                    }
                    if ("".equals(str)) {
                        ArrayList<Paramss> paramsses = new ArrayList<Paramss>();
                        Map<String, String> map = new HashMap<String, String>();
                        map.put("phone", et_username.getText().toString());
                        map.put("validNumber", et_send.getText().toString());
                        map.put("password", MD5.GetMD5Code(et_psw.getText().toString()));
                        paramsses.add(new Paramss(map, 3, "/Sender/ValSmsCode"));
                        Httptools.sendpost1(ZhucheActivity.this, paramsses, handler, true);
                    }else {
                        Httptools.makeText(ZhucheActivity.this, str, false);
                    }
                }
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (second == 0) {
                    handler.removeCallbacks(r3);
                    //发送验证并倒计时
                    ArrayList<Paramss> paramsses = new ArrayList<Paramss>();
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("NotaCode", "86");
                    map.put("Phone", et_username.getText().toString());
                    paramsses.add(new Paramss(map, 2, "/Sender/SenderSm"));
                    Httptools.sendpost1(ZhucheActivity.this, paramsses, handler, true);
                } else {
                    return;
                }
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag_layout) {
                    finish();
                } else {
                    flag_layout = true;
                    second = 0;
                    handler.removeCallbacks(r3);
                    setview();
                }
            }
        });
    }


}
