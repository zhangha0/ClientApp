package com.cd.wzjkj.canyi.activity;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by liuzheng on 2016/10/11.
 */
public class BusTypeShopActivity extends AutoLayoutActivity {
//    private String[] bustypes;
//    private String[] sorts;
//    private String[] citys;
//    private int bustypeid;
//    private int CapeId=1;
//    private String city ="";
//    private TextView title;
//    private TextView tv1;
//    private TextView tv2;
//    private TextView tv3;
//    private int PageIndex = 1;
//    private ListView listview;
//    private ArrayList<ShangJia> shangjias;
//    private BaseAdapter adapter;
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if(msg.what==1){
//                JSONObject obj = (JSONObject) msg.obj;
//                Gson gson = new Gson();
//                try {
//                    JSONArray ary = obj.getJSONArray("data");
//                    if(PageIndex==1||shangjias==null) {
//                        shangjias = new ArrayList<>();
//                    }
//                    for(int i=0;i<ary.length();i++){
//                        ShangJia sj = gson.fromJson(ary.getJSONObject(i).toString(),ShangJia.class);
//                        shangjias.add(sj);
//                    }
//                    setview();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    };
//    private View layout1;
//    private View layout2;
//    private View layout3;
//
//    private void setview() {
////        adapter = new ShangChang_LV_Adapter(this,shangjias);
////        listview.setAdapter(adapter);
//    }
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bustypeshop);
//        bustypes = getResources().getStringArray(R.array.shouyie_class);
//        sorts = getResources().getStringArray(R.array.sort);
//        bustypeid = getIntent().getIntExtra("title",0);
//        getview();
//        setlistener();
//    }
//
//    private void setlistener() {
//        layout1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getPop(tv1,layout1,bustypes,1);
//            }
//        });
//        layout3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getPop(tv2,layout2,sorts,3);
//            }
//        });
//        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private void getPop(TextView tv1, View layout, final String[] bustypes, final int num) {
//        View view2 = LayoutInflater.from(this).inflate(R.layout.popu_secend_list, null);
//        final PopupWindow mPopupwinow1 = new PopupWindow(view2,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        mPopupwinow1
//                .setBackgroundDrawable(new ColorDrawable(
//                        0x00000000));
//
//        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        int width = wm.getDefaultDisplay().getWidth();
//        mPopupwinow1.showAsDropDown(layout1, 0, 0);
//        ListView listview = (ListView) view2.findViewById(R.id.listView);
//        listview.setAdapter(new TitleAdapter(this, bustypes));
//        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> arg0, View arg1,
//                                    int arg2, long arg3) {
//                switch (num){
//                    case 1:
//                        bustypeid = arg2;
//                        break;
//                    case 2:
//                        city= citys[arg2];
//                        break;
//                    case 3:
//                        CapeId =  arg2;
//                        break;
//                }
//                PageIndex = 1;
//                getdata();
//                mPopupwinow1.dismiss();
//            }
//        });
//    }
//
//    private void getview() {
//        title = (TextView) findViewById(R.id.title);
//        tv1 = (TextView) findViewById(R.id.tv1);
//        tv2 = (TextView) findViewById(R.id.tv2);
//        tv3 = (TextView) findViewById(R.id.tv3);
//        listview = (ListView) findViewById(R.id.listview);
//        layout1 = findViewById(R.id.layout1);
//        layout2 = findViewById(R.id.layout2);
//        layout3 = findViewById(R.id.layout3);
//        getdata();
//    }
//
//    private void getdata() {
//        title.setText(bustypes[bustypeid]);
//        tv1.setText(bustypes[bustypeid]);
//        tv3.setText(sorts[CapeId]);
//        ArrayList<Paramss> paramsses = new ArrayList<>();
//        Map<String, String> map= new HashMap<>();
//        map.put("BustypeId",""+(bustypeid+1));
//        map.put("CapeId",""+(CapeId+1));
//        map.put("PageIndex",""+PageIndex);
//        map.put("City",city);
//        paramsses.add(new Paramss(map,1,"/Business/GetTypeBus"));
//        Httptools.sendpost1(this,paramsses,handler,true);
//    }
}
