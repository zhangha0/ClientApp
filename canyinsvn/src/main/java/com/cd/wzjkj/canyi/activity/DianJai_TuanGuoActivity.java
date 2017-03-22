package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.TuanGouLVAdapter;
import com.cd.wzjkj.canyi.entity.Paramss;
import com.cd.wzjkj.canyi.entity.TuanGou_DainJia;
import com.cd.wzjkj.canyi.entity.TuanGuo2;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.cd.wzjkj.canyi.tools.Tools;
import com.cd.wzjkj.canyi.view.SelfListView;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by liuzheng on 2016/11/23.
 */

public class DianJai_TuanGuoActivity extends AutoLayoutActivity {
    private ImageView icon;
    private ImageView icon_bg;
    private TextView title;
    private TextView time;
    private TextView address;
    private TextView goodcomment;
    private TextView midlecomment;
    private TextView badcomment;
    private TextView piccomment;
    private TextView comment;
    private TextView point;
    private ImageView[] xinxins;
    private ImageView xinxinhalf;
    private TextView tuanguo_count;
    private TextView information;
    private TuanGou_DainJia dianJia_TuanGuo;
    private ArrayList<TuanGuo2> tuanguos;
    private SelfListView listview;
    private int all;
    private int pull;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                JSONObject obj = (JSONObject) msg.obj;
                Log.e("zhang: ", "hand1111" + obj.toString());
                try {
                    obj = obj.getJSONObject("data");
                    dianJia_TuanGuo = new Gson().fromJson(obj.toString(), TuanGou_DainJia.class);
                    sendnum--;
                    setview();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (msg.what == 2) {
                JSONObject obj = (JSONObject) msg.obj;
                Log.e("zhang: ", "hand2222" + obj.toString());
                try {
                    JSONArray ary = obj.getJSONArray("data").getJSONObject(0).getJSONArray("List");
                    all = obj.getJSONArray("data").getJSONObject(0).getInt("PageCount");
                    if (pageIndex == 1 || pull == 1) {
                        tuanguos = new ArrayList<>();
                    }
                    for (int i = 0; i < ary.length(); i++) {
                        JSONObject obj1 = ary.getJSONObject(i);
                        TuanGuo2 tg = new TuanGuo2();
                        tg.setTgid(obj1.getString("Id"));
                        tg.setShopId(obj1.getString("ShopId"));
                        tg.setTitle(obj1.getString("Title_" + getResources().getString(R.string.country_symbol)));
                        tg.setIntroduce(obj1.getString("Introduce_" + getResources().getString(R.string.country_symbol)));
                        tg.setBlackprice(obj1.getDouble("Price"));
                        tg.setRedprice(obj1.getDouble("Discountprice"));
                        tg.setBackGroundImg(obj1.getString("ImageUrl"));
                        tg.setId(obj1.getInt("FoodTypeId"));
                        tg.setIngredientsIconArray(obj1.getString("IngredientsIconArray"));
                        tuanguos.add(tg);
                    }
                    sendnum--;
                    setview();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private int sendnum;
    private int pageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianjia_tuanguo);
        getview();
        getdata();
//        setview();
        setListener();
    }

    private void getview() {
        icon = (ImageView) findViewById(R.id.icon);
        icon_bg = (ImageView) findViewById(R.id.icon_bg);
        title = (TextView) findViewById(R.id.title);
        time = (TextView) findViewById(R.id.time);
        tuanguo_count = (TextView) findViewById(R.id.tuanguo_count);
        information = (TextView) findViewById(R.id.information);
        address = (TextView) findViewById(R.id.address);
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
        listview = (SelfListView) findViewById(R.id.listview1);
    }

    private void getdata() {
        String shopid = getIntent().getStringExtra("data");
        ArrayList<Paramss> paramsses = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("shopId", shopid);
        paramsses.add(new Paramss(map, 1, "/Shop/GetShopInfo"));
        map.put("pageIndex", "" + pageIndex);
        map.put("pageSize", "" + 10);
        paramsses.add(new Paramss(map, 2, "/Shop/GroupFoodList"));
        sendnum = paramsses.size();
        Httptools.sendpost1(this, paramsses, handler, true);
    }

    private void setview() {
        if (sendnum == 0) {
            String backUrl=dianJia_TuanGuo.getBackgroundImageUrl();
            String logeUrl=dianJia_TuanGuo.getLogoImageUrl();
            if(backUrl!=null){
                backUrl=(backUrl.split(","))[0];
                logeUrl=(logeUrl.split(","))[0];
            }
            BitmapUtils bu = new BitmapUtils(this);
            bu.display(icon, Httptools.Http2 + logeUrl);
            BitmapUtils bu2 = new BitmapUtils(this);
            bu2.display(icon_bg, Httptools.Http2 + backUrl);
            Log.e("zhang: ","backUrl="+Httptools.Http2 + backUrl );
            Log.e("zhang: ","logeUrl="+Httptools.Http2 + logeUrl );
//            Glide.with(this).load(Httptools.Http2 + backUrl).into(icon_bg);
//            x.image().bind(icon,Httptools.Http2 + dianJia_TuanGuo.getLogoImageUrl());
//            x.image().bind(icon_bg, Httptools.Http2 +backUrl);
            title.setText(dianJia_TuanGuo.getShopName());
            time.setText(getResources().getString(R.string.open_data) + ":" + dianJia_TuanGuo.getStartTime1().substring(0, 5) + "-" + dianJia_TuanGuo.getEndTime1().substring(0, 5) + "    " + dianJia_TuanGuo.getStartTime2().substring(0, 5) + "-" + dianJia_TuanGuo.getEndTime2().substring(0, 5));
            tuanguo_count.setText(getResources().getString(R.string.monetuangou));
            address.setText(dianJia_TuanGuo.getAddrees());
            ListAdapter adapter = new TuanGouLVAdapter(this, tuanguos);
            listview.setAdapter(adapter);
            information.setText(dianJia_TuanGuo.getRemark());
            point.setText("" + dianJia_TuanGuo.getPoint());
            Tools.setXingji(dianJia_TuanGuo.getPoint(), xinxins, xinxinhalf);
//        comment.setText(getResources().getString(R.string.comment)+"("+(dianJia_TuanGuo.getGoodComments().size()+dianJia_TuanGuo.getMidleComments().size()+dianJia_TuanGuo.getBadComments().size())+") >");
//        goodcomment.setText(getResources().getString(R.string.goodcomment)+dianJia_TuanGuo.getGoodComments().size());
//        midlecomment.setText(getResources().getString(R.string.midlecomment)+dianJia_TuanGuo.getMidleComments().size());
//        badcomment.setText(getResources().getString(R.string.badcomment)+dianJia_TuanGuo.getBadComments().size());
//        piccomment.setText(getResources().getString(R.string.piccomment)+dianJia_TuanGuo.getPicComments().size());
        }
    }

    private void setListener() {
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.shouchang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Httptools.makeText1(DianJai_TuanGuoActivity.this, new Throwable().fillInStackTrace());
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DianJai_TuanGuoActivity.this, TuanGuoXXActivity.class);
                intent.putExtra("data_dianjia", dianJia_TuanGuo);
//                intent.putExtra("data",dianJia_TuanGuo.getTuanGuos().get(position));
                intent.putExtra("data", tuanguos);
                intent.putExtra("num", position);
                startActivity(intent);
            }
        });
    }


}
