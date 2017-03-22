package com.cd.wzjkj.canyi.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.DianJia_WaiMai;
import com.cd.wzjkj.canyi.entity.User;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.cd.wzjkj.canyi.tools.Tools;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.zhy.autolayout.AutoLayoutActivity;

import static com.cd.wzjkj.canyi.tools.Tools.userdb;


@SuppressLint("NewApi")
public class MainActivity extends AutoLayoutActivity implements OnClickListener {
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    public static boolean isForeground = false;

    private ImageView[] bt_menu = new ImageView[8];
    private int[] bt_menu_id = {R.id.radio1, R.id.radio2, R.id.radio3,
            R.id.radio4, R.id.radio5, R.id.radio6, R.id.radio7,
            R.id.radio8};

    private FragmentOne home_F;
    private FragmentTwo tao_F;
    private FragmentThree discover_F;
    private FragmentFour cart_F;
    private RelativeLayout pause_btn;
    private RelativeLayout play_btn;
    private FrameLayout layout;
    private long exitTime = 0;
    private RequestQueue mQueue;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;
    private LinearLayout layout5;
    private LinearLayout layout6;
    private LinearLayout layout7;
    private LinearLayout layout8;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueue = Volley.newRequestQueue(this);
        Tools.addressdb = DbUtils.create(this, "address");
        Tools.shopdb = DbUtils.create(this, "shop");
        userdb = DbUtils.create(this, "user");
        try {
            Httptools.user = Tools.userdb.findFirst(User.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        initView();
    }

    private void initView() {
        for (int i = 0; i < bt_menu.length; i++) {
            bt_menu[i] = (ImageView) findViewById(bt_menu_id[i]);
        }

        if (home_F == null) {
            home_F = new FragmentOne();
            home_F.setmQueue(mQueue);
            addFragment(home_F);
            showFragment(home_F);
        } else {
            showFragment(home_F);
        }
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout) findViewById(R.id.layout3);
        layout4 = (LinearLayout) findViewById(R.id.layout4);
        layout5 = (LinearLayout) findViewById(R.id.layout5);
        layout6 = (LinearLayout) findViewById(R.id.layout6);
        layout7 = (LinearLayout) findViewById(R.id.layout7);
        layout8 = (LinearLayout) findViewById(R.id.layout8);
        setviewon(0);
        setListener();
    }

    private void setListener() {
        findViewById(R.id.radiol1).setOnClickListener(this);
        findViewById(R.id.radiol2).setOnClickListener(this);
        findViewById(R.id.radiol3).setOnClickListener(this);
        findViewById(R.id.radiol4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radiol1:
                if (home_F == null) {

                    home_F = new FragmentOne();
                    addFragment(home_F);
                    showFragment(home_F);
                } else {
                    if (home_F.isHidden()) {
                        showFragment(home_F);
                    }
                }
                setviewon(0);
                break;
            case R.id.radiol2:
                if (tao_F == null) {
                    tao_F = new FragmentTwo();
                    if (!tao_F.isHidden()) {
                        addFragment(tao_F);
                        showFragment(tao_F);
                    }
                } else {
                    if (tao_F.isHidden()) {
                        showFragment(tao_F);
                    }
                }
                setviewon(1);
//                Intent intent = new Intent(this, WaiMaiActivity.class);
//                startActivity(intent);
                break;
            case R.id.radiol3:
//                if (discover_F == null) {
//                    discover_F = new FragmentThree();
//                    if (!discover_F.isHidden()) {
//                        addFragment(discover_F);
//                        showFragment(discover_F);
//                    }
//                } else {
//                    if (discover_F.isHidden()) {
//                        showFragment(discover_F);
//                    }
//                }
//                setviewon(2);
                Intent intent1 = new Intent(this, DianJai_DianCanActivity.class);
                DianJia_WaiMai sj = new DianJia_WaiMai();
                intent1.putExtra("data", sj);
                startActivity(intent1);
                break;
            case R.id.radiol4:
                if (cart_F == null) {

                    cart_F = new FragmentFour();
                    if (!cart_F.isHidden()) {
                        addFragment(cart_F);
                        showFragment(cart_F);
                    }
                } else {
                    if (cart_F.isHidden()) {
                        showFragment(cart_F);
                    }
                }
                setviewon(3);
                break;
        }


    }

    private void setviewon(int j) {
        layout1.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout5.setVisibility(View.GONE);
        layout7.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
        layout4.setVisibility(View.VISIBLE);
        layout6.setVisibility(View.VISIBLE);
        layout8.setVisibility(View.VISIBLE);
        switch (j) {
            case 0:
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                break;
            case 1:
                layout3.setVisibility(View.VISIBLE);
                layout4.setVisibility(View.GONE);
                break;
            case 2:
                layout5.setVisibility(View.VISIBLE);
                layout6.setVisibility(View.GONE);
                break;
            case 3:
                layout7.setVisibility(View.VISIBLE);
                layout8.setVisibility(View.GONE);
                break;
        }
    }


    public void addFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager()
                .beginTransaction();
        ft.add(R.id.show_layout, fragment);
        ft.commit();
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager()
                .beginTransaction();
        ft.setCustomAnimations(R.anim.cu_push_right_in, R.anim.cu_push_left_out);
        if (home_F != null) {
            ft.hide(home_F);
        }
        if (tao_F != null) {
            ft.hide(tao_F);
        }
        if (discover_F != null) {
            ft.hide(discover_F);
        }
        if (cart_F != null) {
            ft.hide(cart_F);
        }

        ft.show(fragment);
        ft.commitAllowingStateLoss();

    }


    public void showbottom(boolean isvi) {
        if (isvi) {
            findViewById(R.id.div).setVisibility(View.VISIBLE);
            findViewById(R.id.radioGroup).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.div).setVisibility(View.GONE);
            findViewById(R.id.radioGroup).setVisibility(View.GONE);
        }
    }

    public void show() {
        layout.setVisibility(View.VISIBLE);
    }

    public void play() {
        play_btn.setVisibility(View.GONE);
        pause_btn.setVisibility(View.VISIBLE);
        play_btn.setClickable(false);
        pause_btn.setClickable(true);
    }

    public void pause() {
        pause_btn.setVisibility(View.GONE);
        play_btn.setVisibility(View.VISIBLE);
        pause_btn.setClickable(false);
        play_btn.setClickable(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void finish() {
        if (exit()) {
            super.finish();
        }
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = true;
        super.onPause();
    }


    public boolean exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), getString(R.string.outapp),
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }


}
