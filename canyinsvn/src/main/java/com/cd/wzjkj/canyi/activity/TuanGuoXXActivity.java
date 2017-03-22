package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.TuanGouLVAdapter;
import com.cd.wzjkj.canyi.adapter.TuanGuo_Cai_Adapter;
import com.cd.wzjkj.canyi.entity.CaiPing;
import com.cd.wzjkj.canyi.entity.Paramss;
import com.cd.wzjkj.canyi.entity.TuanGou_DainJia;
import com.cd.wzjkj.canyi.entity.TuanGuo2;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.lidroid.xutils.BitmapUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by liuzheng on 2016/11/23.
 */
public class TuanGuoXXActivity extends AutoLayoutActivity {

    private ImageView icon;
    private TextView title;
    private TextView address;
    private TextView information;
    private TextView title_tuanguo;
    private ListView listview1;
    private ListView listview5;
    private TextView price_red;
    private TextView price_black;
    private TextView count;
    private ArrayList<TuanGuo2> tuanGuos;
    private TuanGou_DainJia dianJia_TuanGuo;
    private TuanGuo2 tuanguo;
    private int num;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                JSONObject obj = (JSONObject) msg.obj;
                try {
                    JSONObject obj1 = obj.getJSONObject("data").getJSONObject("GroupFoodInfo");
                    JSONArray ary = obj.getJSONObject("data").getJSONArray("List");
                    tuanguo = new TuanGuo2();
                    tuanguo.setTgid(obj1.getString("Id"));
                    tuanguo.setShopId(obj1.getString("ShopId"));
                    tuanguo.setTitle(obj1.getString("Title_"+getResources().getString(R.string.country_symbol)));
                    tuanguo.setIntroduce(obj1.getString("Introduce_"+getResources().getString(R.string.country_symbol)));
                    tuanguo.setBlackprice(obj1.getDouble("Price"));
                    tuanguo.setRedprice(obj1.getDouble("Discountprice"));
                    tuanguo.setBackGroundImg(obj1.getString("ImageUrl"));
                    tuanguo.setIngredientsIconArray(obj1.getString("IngredientsIconArray"));
                    ArrayList<List<CaiPing>> cpss = new ArrayList<>();
                    for(int i=0;i<ary.length();i++){
                        ArrayList<CaiPing> caiPings = new ArrayList<>();
                        JSONArray ary1 = ary.getJSONObject(i).getJSONArray("Foods");
                        for(int j=0;j<ary1.length();j++){
                            JSONObject obj2 = ary1.getJSONObject(j);
                            CaiPing cp = new CaiPing();
                            cp.setTitle2(ary.getJSONObject(i).getString("Title_"+getResources().getString(R.string.country_symbol)));
                            cp.setCpId(obj2.getString("FoodId"));
                            cp.setTitle(obj2.getString("Title_"+getResources().getString(R.string.country_symbol)));
                            cp.setRedprice(obj2.getDouble("Discountprice"));
                            cp.setPrice(obj2.getDouble("Price"));
                            cp.setBackGroundImg(obj2.getString("ImageUrl"));
                            cp.setPeiLiaos(obj2.getString("IngredientsIconArray").split(","));
                            cp.setFoodTypeId(obj2.getInt("FoodTypeId"));
                            caiPings.add(cp);
                        }
                        cpss.add(caiPings);
                    }
                    tuanguo.setCaipingses(cpss);
                    setview();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuanguoxx);
        getview();
        getdata();
//        setview();
        setListener();
    }

    private void getview() {
        icon = (ImageView) findViewById(R.id.icon);
        title = (TextView) findViewById(R.id.title);
        title_tuanguo = (TextView) findViewById(R.id.title_tuanguo);
        address = (TextView) findViewById(R.id.address);
        information = (TextView) findViewById(R.id.information);
        listview1 = (ListView) findViewById(R.id.listview1);
        listview5 = (ListView) findViewById(R.id.listview5);
        price_red = (TextView) findViewById(R.id.price_red);
        price_black = (TextView) findViewById(R.id.price_black);
        count = (TextView) findViewById(R.id.count);
        count.setText("1");
    }

    private void getdata() {
        dianJia_TuanGuo = (TuanGou_DainJia) getIntent().getSerializableExtra("data_dianjia");
        num = getIntent().getIntExtra("num", -1);
//        tuanGuos = dianJia_TuanGuo.getTuanGuos();
        tuanGuos = (ArrayList<TuanGuo2>) getIntent().getSerializableExtra("data");
        tuanguo = tuanGuos.get(num);
        tuanGuos.remove(num);

        ArrayList<Paramss> paramsses = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("groupFoodId",tuanguo.getTgid());
//        map.put("groupFoodId","44578923");
        paramsses.add(new Paramss(map,1,"/Shop/GroupFoodDetail"));
        Httptools.sendpost1(this,paramsses,handler,true);

    }

    private void setview() {
        BitmapUtils bu = new BitmapUtils(this);
        bu.display(icon, tuanguo.getBackGroundImg());
        title.setText(dianJia_TuanGuo.getTitle());
        title_tuanguo.setText(tuanguo.getTitle());
        address.setText(dianJia_TuanGuo.getAddrees());
        information.setText(dianJia_TuanGuo.getRemark());
        price_black.setText("€" + tuanguo.getBlackprice());
        price_red.setText("€" + tuanguo.getRedprice());
        TuanGuo_Cai_Adapter adapter1 = new TuanGuo_Cai_Adapter(this, tuanguo.getCaipingses());
        listview1.setAdapter(adapter1);


//        TuanGuo_Cai_Adapter adapter1 = new TuanGuo_Cai_Adapter(this, tuanguo);
//        listview1.setAdapter(adapter1);
//        TextViewLVAdapter adapter2 = new TextViewLVAdapter(this, tuanguo.getTianping_cp());
//        listview2.setAdapter(adapter2);
//        TextViewLVAdapter adapter3 = new TextViewLVAdapter(this, tuanguo.getDrinks_cp());
//        listview3.setAdapter(adapter3);


        ListAdapter adapter5 = new TuanGouLVAdapter(this, tuanGuos);
        listview5.setAdapter(adapter5);
    }

    private void setListener() {
        findViewById(R.id.plustv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count.setText("" + (Integer.valueOf(count.getText().toString()) + 1));
            }
        });
        findViewById(R.id.minustv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!"1".equals(count.getText().toString())) {
                    count.setText("" + (Integer.valueOf(count.getText().toString()) - 1));
                }
            }
        });
        listview5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TuanGuoXXActivity.this, TuanGuoXXActivity.class);
                intent.putExtra("num", position);
                intent.putExtra("data_dianjia", dianJia_TuanGuo);
                intent.putExtra("data", tuanGuos);
                tuanGuos.add(num, tuanguo);
                startActivity(intent);
                finish();
            }
        });
    }


}
