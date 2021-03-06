package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.CommentAdapter;
import com.cd.wzjkj.canyi.adapter.TextViewLVAdapter;
import com.cd.wzjkj.canyi.adapter.WaiMai_CaiPing_Adapter;
import com.cd.wzjkj.canyi.entity.CaiPing;
import com.cd.wzjkj.canyi.entity.DianJia_WaiMai;
import com.cd.wzjkj.canyi.entity.SelfComment;
import com.cd.wzjkj.canyi.entity.Shopcar2;
import com.cd.wzjkj.canyi.entity.TuanGuo;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.cd.wzjkj.canyi.tools.Tools;
import com.cd.wzjkj.canyi.view.MyHoveringScrollView;
import com.cd.wzjkj.canyi.view.SelfListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.exception.DbException;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzheng on 2016/11/23.
 */

public class DianJai_WaiMaiActivity extends AutoLayoutActivity implements Plus_Minus_ChooseActivity {
    private ImageView icon;
    private ImageView icon_bg;
    private TextView title;
    private TextView tv1;
    private TextView gonggao;
    private TextView price_all;
    private TextView emsmoney;
    private TextView opentime;
    private TextView address;
    private TextView tophone;
    private TextView goodcomment;
    private TextView midlecomment;
    private TextView badcomment;
    private TextView piccomment;
    private TextView comment;
    private TextView point;
    private ImageView[] xinxins;
    private ImageView xinxinhalf;
    private DianJia_WaiMai dianJia_waiMai;
    private SelfListView listview1;
    private SelfListView listview_first;
    private SelfListView listview_second;
    private SelfListView listview_third;
    private SelfListView listview_tianping;
    private SelfListView listview_drink;
    private SelfListView listview_comment;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private LinearLayout linearLayout3;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton r2b1;
    private RadioButton r2b2;
    private int choose;
    private int tcchoose;
    private int count;
    private double money;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Bitmap bitmap = (Bitmap) msg.obj;
                if (bitmap == null) {
                    Log.e("TAG", "nu8ll");
                } else {
                    icon_bg.setImageBitmap(Tools.blurBitmap(bitmap, DianJai_WaiMaiActivity.this));
                }
            }
        }
    };
    private WaiMai_CaiPing_Adapter wmcpa;
    private WaiMai_CaiPing_Adapter wmcpa1;
    private WaiMai_CaiPing_Adapter wmcpa2;
    private WaiMai_CaiPing_Adapter wmcpa3;
    private TextViewLVAdapter adapter2;
    private TextViewLVAdapter adapter3;
    private List<Shopcar2> shopcar2s;
    private MyHoveringScrollView view_hover;
    private boolean isems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianjia_waimai);
        getview();
        getdata();
        setview();
        setListener();
    }

    private void getview() {
        view_hover = (MyHoveringScrollView) findViewById(R.id.view_hover);
        view_hover.setTopView(R.id.top);
        icon = (ImageView) findViewById(R.id.icon);
        icon_bg = (ImageView) findViewById(R.id.icon_bg);
        title = (TextView) findViewById(R.id.title);
        tv1 = (TextView) findViewById(R.id.tv1);
        gonggao = (TextView) findViewById(R.id.gonggao);
        price_all = (TextView) findViewById(R.id.price_all);
        emsmoney = (TextView) findViewById(R.id.emsmoney);
        opentime = (TextView) findViewById(R.id.opentime);
        tophone = (TextView) findViewById(R.id.tophone);
        address = (TextView) findViewById(R.id.address);
        listview1 = (SelfListView) findViewById(R.id.listview1);
        listview_first = (SelfListView) findViewById(R.id.listview_first);
        listview_second = (SelfListView) findViewById(R.id.listview_second);
        listview_third = (SelfListView) findViewById(R.id.listview_third);
        listview_tianping = (SelfListView) findViewById(R.id.listview_tianping);
        listview_drink = (SelfListView) findViewById(R.id.listview_drink);
        listview_comment = (SelfListView) findViewById(R.id.listview_comment);
        linearLayout1 = (LinearLayout) findViewById(R.id.layout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.layout2);
        linearLayout3 = (LinearLayout) findViewById(R.id.layout3);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        r2b1 = (RadioButton) findViewById(R.id.r2b1);
        r2b2 = (RadioButton) findViewById(R.id.r2b2);

        point = (TextView) findViewById(R.id.point);
        comment = (TextView) findViewById(R.id.comment);
        goodcomment = (TextView) findViewById(R.id.good_comment);
        midlecomment = (TextView) findViewById(R.id.midle_comment);
        badcomment = (TextView) findViewById(R.id.bad_comment);
        piccomment = (TextView) findViewById(R.id.pic_comment);
        xinxins = new ImageView[]{
                (ImageView) findViewById(R.id.xinxin1),
                (ImageView) findViewById(R.id.xinxin2),
                (ImageView) findViewById(R.id.xinxin3),
                (ImageView) findViewById(R.id.xinxin4),
                (ImageView) findViewById(R.id.xinxin5)};
        xinxinhalf = (ImageView) findViewById(R.id.xinxinhalf);
        rb1.setChecked(true);
        r2b1.setChecked(true);
    }

    private void getdata() {
        dianJia_waiMai = (DianJia_WaiMai) getIntent().getSerializableExtra("data");
        ArrayList<CaiPing> caiPings = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            CaiPing cp = new CaiPing();
            cp.setId(i);
            caiPings.add(cp);
        }
        dianJia_waiMai.setCaiPings(caiPings);
        ArrayList<TuanGuo> tuanGuos = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
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
                cp1.setPrice(1+j);
                cp2.setPrice(2+j);
                cp3.setPrice(3+j);
                cp4.setPrice(4+j);
                cp5.setPrice(5+j);
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
            tg.setId(i);
            tuanGuos.add(i,tg);
        }
        ArrayList<SelfComment> selfComments = new ArrayList<>();
        selfComments.add(new SelfComment());
        selfComments.add(new SelfComment());
        selfComments.add(new SelfComment());
        dianJia_waiMai.setGoodComments(selfComments);
        dianJia_waiMai.setMidleComments(selfComments);
        dianJia_waiMai.setBadComments(selfComments);
        dianJia_waiMai.setPicComments(selfComments);
        dianJia_waiMai.setTuanGuos(tuanGuos);
    }

    private void setview() {
        BitmapUtils bu = new BitmapUtils(this);
        bu.display(icon, dianJia_waiMai.getIcon());
        title.setText(dianJia_waiMai.getTitle());
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = Tools.getBitmap(dianJia_waiMai.getIcon());
                Message msg = new Message();
                msg.what = 0;
                msg.obj = bitmap;
                handler.sendMessage(msg);
            }
        }).start();
        gonggao.setText(dianJia_waiMai.getGonggao());
        tv1.setText("€" + dianJia_waiMai.getStartseld() + getResources().getString(R.string.startseld) + "  " + getResources().getString(R.string.emsmoney) + "€" + dianJia_waiMai.getEmsmoney() + "  €" + dianJia_waiMai.getNoems() + getResources().getString(R.string.noems));
        opentime.setText(dianJia_waiMai.getOpentime());
        address.setText(dianJia_waiMai.getAddrees());
        tophone.setText(dianJia_waiMai.getPhone());

        Tools.setXingji(dianJia_waiMai.getPoint(), xinxins, xinxinhalf);
        comment.setText(getResources().getString(R.string.comment) + "(" + (dianJia_waiMai.getGoodComments().size() + dianJia_waiMai.getMidleComments().size() + dianJia_waiMai.getBadComments().size()) + ") >");
        goodcomment.setText(getResources().getString(R.string.goodcomment) + dianJia_waiMai.getGoodComments().size());
        midlecomment.setText(getResources().getString(R.string.midlecomment) + dianJia_waiMai.getMidleComments().size());
        badcomment.setText(getResources().getString(R.string.badcomment) + dianJia_waiMai.getBadComments().size());
        piccomment.setText(getResources().getString(R.string.piccomment) + dianJia_waiMai.getPicComments().size());
        point.setText("" + dianJia_waiMai.getPoint());
        setviewlist();
    }

    private void setviewlist() {
        try {
            Tools.getCountByCaiPings(dianJia_waiMai.getCaiPings());
        } catch (DbException e) {
            Log.e("TAG","b==="+e.toString());
            e.printStackTrace();
        }
        setMoneyAndCount();


        if (choose == 0) {
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.GONE);
            wmcpa = new WaiMai_CaiPing_Adapter(this, dianJia_waiMai.getCaiPings());
            listview1.setAdapter(wmcpa);
        } else if (choose == 1) {
            linearLayout2.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout3.setVisibility(View.GONE);
            TuanGuo tg = dianJia_waiMai.getTuanGuos().get(tcchoose);
            wmcpa1 = new WaiMai_CaiPing_Adapter(this, tg.getFirst_cp(), 1);
            wmcpa2 = new WaiMai_CaiPing_Adapter(this, tg.getSecond_cp(), 2);
            wmcpa3 = new WaiMai_CaiPing_Adapter(this, tg.getThird_cp(), 3);
            adapter2 = new TextViewLVAdapter(this, tg.getTianping_cp(), 1);
            adapter3 = new TextViewLVAdapter(this, tg.getDrinks_cp(), 2);
            listview_first.setAdapter(wmcpa1);
            listview_second.setAdapter(wmcpa2);
            listview_third.setAdapter(wmcpa3);
            listview_tianping.setAdapter(adapter2);
            listview_drink.setAdapter(adapter3);
        } else if (choose == 2) {
            CommentAdapter adapter_comment = new CommentAdapter(this, dianJia_waiMai.getBadComments());
            listview_comment.setAdapter(adapter_comment);
            linearLayout3.setVisibility(View.VISIBLE);
            linearLayout1.setVisibility(View.GONE);
            linearLayout2.setVisibility(View.GONE);
        }
    }

    private void setListener() {
        findViewById(R.id.btn_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dianJia_waiMai.getTuanGuos().get(tcchoose).getchoosed()!=5){
                    Httptools.makeText(DianJai_WaiMaiActivity.this,"请选择完",false);
                    return;
                }else{
                    Log.e("TAG",dianJia_waiMai.getTuanGuos().get(tcchoose).getchoosed()+"===num");
                    tnClick();
                }
            }
        });
        findViewById(R.id.btn_topay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DianJai_WaiMaiActivity.this,PayOrderActivity.class);
                intent.putExtra("data",dianJia_waiMai);
                intent.putExtra("isems",isems);
                intent.putExtra("num",1);
                startActivity(intent);
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.shouchang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Httptools.makeText1(DianJai_WaiMaiActivity.this, new Throwable().fillInStackTrace());
            }
        });
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choose == 0) {
                    return;
                }
                choose = 0;
                setviewlist();
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choose == 1) {
                    return;
                }
                choose = 1;
                setviewlist();
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (choose == 2) {
                    return;
                }
                choose = 2;
                setviewlist();

            }
        });
        r2b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isems) {
                    return;
                }
                isems = true;

            }
        });
        r2b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isems) {
                    return;
                }
                isems = !true;
            }


        });
    }

    public void minus(int i) {
        if(Httptools.user==null){
            Httptools.makeText(this,"未登录",false);
            return;
        }
        Tools.minus(dianJia_waiMai.getCaiPings().get(i));
        setMoneyAndCount();
    }

    private void setMoneyAndCount() {
        count=0;
        money=0;
        for(int i=0;i<dianJia_waiMai.getCaiPings().size();i++){
            CaiPing c = dianJia_waiMai.getCaiPings().get(i);
            count+=c.getCount();
            money +=c.getCount()*c.getPrice();
        }
        try {
            shopcar2s = Tools.getCountByTuanGou(dianJia_waiMai.getTuanGuos());
        } catch (DbException e) {
            e.printStackTrace();
        }
        for(int i=0;i<shopcar2s.size();i++){
            Shopcar2 sc2 = shopcar2s.get(i);
            count += sc2.getCount();
            for(int j=0;j<dianJia_waiMai.getTuanGuos().size();j++){
                TuanGuo t = dianJia_waiMai.getTuanGuos().get(j);
                if(t.getId()==sc2.getSpid()){
                    money += t.getFirst_cp().get(sc2.getFirst()).getPrice()*sc2.getCount();
                    money += t.getSecond_cp().get(sc2.getSecond()).getPrice()*sc2.getCount();
                    money += t.getThird_cp().get(sc2.getThird()).getPrice()*sc2.getCount();
                    money += t.getTianping_cp().get(sc2.getTianping()).getPrice()*sc2.getCount();
                    money += t.getDrinks_cp().get(sc2.getDrink()).getPrice()*sc2.getCount();
                }
            }
        }
        price_all.setText(money + "");
        String str = money < dianJia_waiMai.getNoems() ? getResources().getString(R.string.emsmoney) + dianJia_waiMai.getEmsmoney() : getResources().getString(R.string.noems);
        emsmoney.setText(str);
    }

    public void plus(int i) {
        if(Httptools.user==null){
            Httptools.makeText(this,"未登录",false);
            return;
        }
        Tools.plus(dianJia_waiMai.getCaiPings().get(i));
        setMoneyAndCount();
    }
    public void tnClick(){
        Tools.plus(dianJia_waiMai.getTuanGuos().get(tcchoose));
        setMoneyAndCount();
    }

    public void choose(int i, int position) {
        List<CaiPing> caiPings = null;
        BaseAdapter wmcpa = null;
        switch (i) {
            case 1:
                caiPings = dianJia_waiMai.getTuanGuos().get(tcchoose).getFirst_cp();
                wmcpa = wmcpa1;
                break;
            case 2:
                caiPings = dianJia_waiMai.getTuanGuos().get(tcchoose).getSecond_cp();
                wmcpa = wmcpa2;
                break;
            case 3:
                caiPings = dianJia_waiMai.getTuanGuos().get(tcchoose).getThird_cp();
                wmcpa = wmcpa3;
                break;
            case 4:
                caiPings = dianJia_waiMai.getTuanGuos().get(tcchoose).getTianping_cp();
                wmcpa = adapter2;
                break;
            case 5:
                caiPings = dianJia_waiMai.getTuanGuos().get(tcchoose).getDrinks_cp();
                wmcpa = adapter3;
                break;
        }
        for (int j = 0; j < caiPings.size(); j++) {
            caiPings.get(j).setChoosed(false);
        }
        caiPings.get(position).setChoosed(true);
        wmcpa.notifyDataSetChanged();
    }
}
