package com.cd.wzjkj.canyi.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.MyPageAdapter;
import com.cd.wzjkj.canyi.adapter.ShouYieGVAdapter;
import com.cd.wzjkj.canyi.adapter.ShouYieLVAdapter2;
import com.cd.wzjkj.canyi.adapter.Title_SortAdapter1;
import com.cd.wzjkj.canyi.adapter.Title_SortAdapter2;
import com.cd.wzjkj.canyi.entity.Clazz;
import com.cd.wzjkj.canyi.entity.Paramss;
import com.cd.wzjkj.canyi.entity.TuanGou_DainJia;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.cd.wzjkj.canyi.tools.PermissionUtils;
import com.cd.wzjkj.canyi.view.AutoPage;
import com.cd.wzjkj.canyi.view.CircleAutoView;
import com.cd.wzjkj.canyi.view.MyPullToReAndLoad;
import com.google.gson.Gson;
import com.jingchen.pulltorefresh.MyAdapter;
import com.jingchen.pulltorefresh.MyHoveringScrollView;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.pullableview.PullableListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentOne extends Fragment {
    private TextView city;
    private ImageView seach_iv;
    private TextView seach_et;
    private ImageView shaoyishao;
    private int guangaonum;
    private GridView gridview;
    private ImageView iv_sort1;
    private ImageView iv_sort2;
    private ImageView iv_sort3;
    private TextView tv_sort1;
    private TextView tv_sort2;
    private TextView tv_sort3;
    private int windows_width;
    private int windows_height;
    private PullableListView listview;
    private ArrayList<Clazz> clazzs;
    private ArrayList<Clazz> clazzs1;
    private ArrayList<Clazz> clazzs2;
    private ArrayList<Clazz> shangquans;
    private ArrayList<Clazz> shangquans2;
    //    private MyHoveringScrollView view_hover;
    private RequestQueue mQueue;
    private FrameLayout ivlayout;
    private View mainView;
    private View viewPageGrid;
    private View viewTop;
    private LinearLayout ll_top;
    private AutoPage autoPage;
    private MyPageAdapter pageAdapter;
    private CircleAutoView circleAutoView;
    private ArrayList<TuanGou_DainJia> shangJias;
    Context context;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                JSONObject obj = (JSONObject) msg.obj;
                shangquans = new ArrayList<>();
                try {
                    JSONArray ary = obj.getJSONArray("data").getJSONObject(0).getJSONArray("Cities").getJSONObject(0).getJSONArray("Areas");
                    Clazz c = new Clazz();
                    c.setTitle("附近");
                    shangquans.add(c);
                    for (int i = 0; i < ary.length(); i++) {
                        c = new Clazz();
                        JSONObject obj1 = ary.getJSONObject(i);
                        c.setTitle(obj1.getString("AreaName"));
                        c.setId(obj1.getInt("AreaId"));
                        shangquans.add(c);
                    }
                    if (areaId == -1) {
                        areaId = 0;
                    }
                    shangquans.get(areaId).setIschoose(true);
                    sendnum--;
                    setViews();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (msg.what == 2) {
                JSONObject obj = (JSONObject) msg.obj;
                if (pull == 0 || pull == 1 || shangJias == null) {
                    shangJias = new ArrayList<>();
                }
                try {
                    toTuanGou(obj);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (msg.what == 3) {
                JSONObject obj = (JSONObject) msg.obj;
                try {
                    JSONArray ary = obj.getJSONArray("data");
                    clazzs = new ArrayList<>();
                    Log.e( "zhang: ", "ary.toString()="+ary.toString());
                    for (int i = 0; i < ary.length(); i++) {
                        JSONObject obj1 = ary.getJSONObject(i);
                        Clazz c = new Clazz();
                        c.setId(obj1.getInt("Id"));
                        c.setTitle(obj1.getString(getResources().getString(R.string.title_name)));
                        c.setCount(obj1.getInt(getResources().getString(R.string.ShopNumber)));
                        c.setIcon(obj1.getString("ImageUrl"));
                        if (i == shopTypeId - 1) {
                            c.setIschoose(true);
                        }
                        clazzs.add(c);
                    }
                    sendnum--;
                    setViews();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (msg.what == Httptools.timeout) {
                if (pull == 1) {
                    pulllayout.refreshFinish(PullToRefreshLayout.FAIL);
                } else if (pull == 2) {
                    pulllayout.loadmoreFinish(PullToRefreshLayout.FAIL);
                }
            }
        }
    };
    private LocationManager locMan;
    private int pull;
    private PullToRefreshLayout pulllayout;
    private int allpage;

    private void toTuanGou(JSONObject obj) throws JSONException {
        JSONArray ary = obj.getJSONArray("data").getJSONObject(0).getJSONArray("List");
        allpage = obj.getJSONArray("data").getJSONObject(0).getInt("PageCount");
        for (int i = 0; i < ary.length(); i++) {
            JSONObject obj1 = ary.getJSONObject(i);
            TuanGou_DainJia tg = new Gson().fromJson(obj1.toString(), TuanGou_DainJia.class);
            shangJias.add(tg);
        }
        sendnum--;
        setViews();
    }

    private int sendnum;
    private double latitude;
    private double longitude;
    private int sort = 0;
    private int shopTypeId = -1;
    private int pageIndex = 1;
    private int areaId = -1;
    private String keyword = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        mainView = LayoutInflater.from(context).inflate(R.layout.activity_main1_1, null);
        viewPageGrid = LayoutInflater.from(context).inflate(R.layout.layout_home_pager, null);
        viewTop = LayoutInflater.from(context).inflate(R.layout.class_sort, null);
        getLocation(context);
        getViews();
        getData();
        setAutoPage();
        setListener();
        return mainView;
    }

    public void setAutoPage() {
        ArrayList<View> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ImageView image = new ImageView(getActivity());
            image.setBackgroundResource(R.drawable.text);
            list.add(image);
        }
        AutoPage.OnScollChangeListener asvpocl = new AutoPage.OnScollChangeListener() {

            @Override
            public void scroll(int x) {
                circleAutoView.move(x, autoPage.getWidth());
            }
        };
        pageAdapter = new MyPageAdapter(list);
        autoPage.setAdapter(pageAdapter);
        autoPage.setOnScollChangeListener(asvpocl);
        circleAutoView.setIndicatorsCount(4);
    }

    private void getViews() {
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windows_width = wm.getDefaultDisplay().getWidth();
        windows_height = wm.getDefaultDisplay().getHeight();
        autoPage = (AutoPage) viewPageGrid.findViewById(R.id.autoPage);
        circleAutoView = (CircleAutoView) viewPageGrid.findViewById(R.id.circle);
        city = (TextView) mainView.findViewById(R.id.city);
        seach_iv = (ImageView) mainView.findViewById(R.id.seach_iv);
        seach_et = (TextView) mainView.findViewById(R.id.seach_et);
        shaoyishao = (ImageView) mainView.findViewById(R.id.shaoyishao);
        shaoyishao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
        ll_top = (LinearLayout) mainView.findViewById(R.id.ll_top);
        gridview = (GridView) viewPageGrid.findViewById(R.id.gridview);
        gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        listview = (PullableListView) mainView.findViewById(R.id.listView);
        listview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        ivlayout = (FrameLayout) mainView.findViewById(R.id.ivlayout);
        tv_sort1 = (TextView) viewTop.findViewById(R.id.tv_sort1);
        tv_sort2 = (TextView) viewTop.findViewById(R.id.tv_sort2);
        tv_sort3 = (TextView) viewTop.findViewById(R.id.tv_sort3);
        iv_sort1 = (ImageView) viewTop.findViewById(R.id.iv_sort1);
        iv_sort2 = (ImageView) viewTop.findViewById(R.id.iv_sort2);
        iv_sort3 = (ImageView) viewTop.findViewById(R.id.iv_sort3);
        listview.addHeaderView(viewPageGrid);
        listview.addHeaderView(viewTop);
        listview.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    ll_top.setVisibility(View.VISIBLE);
                    viewTop.setVisibility(View.GONE);
                } else {
                    ll_top.setVisibility(View.GONE);
                    viewTop.setVisibility(View.VISIBLE);
                }
            }
        });
        ((PullToRefreshLayout) mainView.findViewById(R.id.refresh_view))
                .setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                        pageIndex = 1;
                        pull = 1;
                        pulllayout = pullToRefreshLayout;
                        getData();
                    }

                    @Override
                    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
                        pull = 2;
                        pulllayout = pullToRefreshLayout;
                        if (pageIndex < allpage) {
                            pageIndex += 1;
                            getData();
                        } else {
                            handler.sendEmptyMessage(Httptools.timeout);
                        }
                    }
                });
    }


    private void getData() {
        String[] strs = getResources().getStringArray(R.array.sort);
        clazzs2 = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            Clazz c = new Clazz();
            c.setTitle(strs[i]);
            if (i == sort) {
                c.setIschoose(true);
            }
            clazzs2.add(c);
        }
         shangquans2 = new ArrayList<>();
        strs = getResources().getStringArray(R.array.sort_km);
        for (int i = 0; i < strs.length; i++) {
            Clazz c = new Clazz();
            c.setTitle(strs[i]);
            if (i == strs.length) {
                c.setIschoose(true);
            }
            shangquans2.add(c);
        }
        ArrayList<Paramss> paramsses = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("lat", "" + longitude);
        map.put("lng", "" + latitude);
        map.put("shopTypeId", "" + shopTypeId);
        map.put("areaId", "" + (areaId == 0 ? -1 : areaId));
        map.put("sort", "" + sort);
        map.put("pageIndex", "" + pageIndex);
        map.put("pageSize", "" + 10);
        map.put("keyword", "" + keyword);
        paramsses.add(new Paramss(map, 2, "/Shop/GroupShopList"));
        map = new HashMap<>();
        map.put("city", "madrid");
        paramsses.add(new Paramss(map, 1, "/ProvinceCity/List"));
        paramsses.add(new Paramss(null, 3, "/ShopType/List"));
        Httptools.sendpost1(getActivity(), paramsses, handler, true);
        sendnum = paramsses.size();
    }


    public Location getLocation(Context context) {
        locMan = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_ACCESS_FINE_LOCATION, mPermissionGrant);
            return null;
        }
        Location location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null) {
            location = locMan
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (location != null) {
            //不为空,显示地理位置经纬度
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.e("TAG", "latitude=" + latitude + "/r/n===longitude+" + longitude);
        }
        return location;
    }

    private PermissionUtils.PermissionGrant mPermissionGrant = new PermissionUtils.PermissionGrant() {
        @Override
        public void onPermissionGranted(int requestCode) {
            switch (requestCode) {
                case PermissionUtils.CODE_RECORD_AUDIO:
                    break;
                case PermissionUtils.CODE_GET_ACCOUNTS:
                    break;
                case PermissionUtils.CODE_READ_PHONE_STATE:
                    break;
                case PermissionUtils.CODE_CALL_PHONE:
                    break;
                case PermissionUtils.CODE_CAMERA:
                    break;
                case PermissionUtils.CODE_ACCESS_FINE_LOCATION:
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        PermissionUtils.requestPermission(getActivity(), PermissionUtils.CODE_ACCESS_FINE_LOCATION, mPermissionGrant);
                        return;
                    }
                    Location location = locMan.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location == null) {
                        location = locMan
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }

                    if (location != null) {
                        //不为空,显示地理位置经纬度
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Log.e("TAG", "latitude=" + latitude + "/r/n===longitude+" + longitude);
                    }
                    break;
                case PermissionUtils.CODE_ACCESS_COARSE_LOCATION:
                    break;
                case PermissionUtils.CODE_READ_EXTERNAL_STORAGE:
                    break;
                case PermissionUtils.CODE_WRITE_EXTERNAL_STORAGE:
                    break;
                default:
                    break;
            }
        }
    };

    private void setViews() {
        if (sendnum == 0) {
            ShouYieLVAdapter2 lvadapter = new ShouYieLVAdapter2(getActivity(), shangJias, 0);
            listview.setAdapter(lvadapter);
//            listview.setOnItemClickListener(new OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(getActivity(), DianJai_TuanGuoActivity.class);
//                    intent.putExtra("data", shangJias.get(position).getShopId());
//                    startActivity(intent);
//                }
//            });
            ShouYieGVAdapter gvadapter = new ShouYieGVAdapter(getActivity(), null, clazzs);
            gridview.setAdapter(gvadapter);
//            gridview.setVisibility(View.GONE);
            if (pull == 1) {
                pulllayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            } else if (pull == 2) {
                pulllayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }
    }

    private void setListener() {
        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                shopTypeId = arg2 + 1;
                tv_sort1.setText(clazzs.get(arg2).getTitle());
                getData();
            }
        });
        setPopLietener(viewTop);
        setPopLietener2(mainView);

    }

    private void setPopLietener(View v){
        v.findViewById(R.id.layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setsortview(0,viewTop.findViewById(R.id.rl_top));
            }
        });
        v.findViewById(R.id.layout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setsortview(1,viewTop.findViewById(R.id.rl_top));
            }
        });
        v.findViewById(R.id.layout3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setsortview(2,viewTop.findViewById(R.id.rl_top));
            }
        });
    }
    private void setPopLietener2(View v){
        v.findViewById(R.id.layout11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setsortview(0,mainView.findViewById(R.id.rl_top2));
            }
        });
        v.findViewById(R.id.layout22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setsortview(1,mainView.findViewById(R.id.rl_top2));
            }
        });
        v.findViewById(R.id.layout33).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setsortview(2,mainView.findViewById(R.id.rl_top2));
            }
        });
    }
    private void setsortview(int num,View v) {
        switch (num) {
            case 0:
                iv_sort1.setImageResource(R.mipmap.hei_up);
                final View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.popu_first_list, null);
                final PopupWindow mPopupwinow1 = new PopupWindow(view1,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        windows_height * 456 / 1334, true);
                mPopupwinow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        iv_sort1.setImageResource(R.mipmap.hei_down);
                    }
                });
                mPopupwinow1
                        .setBackgroundDrawable(new ColorDrawable(
                                0x00000000));
                mPopupwinow1.showAsDropDown(v, 0, -20);
                ListView listview = (ListView) view1.findViewById(R.id.listview1);
                view1.findViewById(R.id.listview2).setVisibility(View.GONE);
                listview.setAdapter(new Title_SortAdapter1(getActivity(), clazzs));
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            final int arg2, long arg3) {
                        for (int i = 0; i < clazzs.size(); i++) {
                            clazzs.get(i).setIschoose(false);
                        }
                        clazzs.get(arg2).setIschoose(true);
                        tv_sort1.setText(clazzs.get(arg2).getTitle());
                        shopTypeId = arg2 + 1;
                        getData();
                        mPopupwinow1.dismiss();
                    }
                });
                break;
            case 1:
                iv_sort2.setImageResource(R.mipmap.hei_up);
                final View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.popu_secend_list, null);
                final PopupWindow mPopupwinow2 = new PopupWindow(view2,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        windows_height * 456 / 1334, true);
                mPopupwinow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        iv_sort2.setImageResource(R.mipmap.hei_down);
                    }
                });
                mPopupwinow2.setBackgroundDrawable(new ColorDrawable(
                        0x00000000));
                mPopupwinow2.showAsDropDown(v, 0, -20);
                listview = (ListView) view2.findViewById(R.id.listview1);
                final ListView listview2 = (ListView) view2.findViewById(R.id.listview2);
                listview.setAdapter(new Title_SortAdapter2(getActivity(), shangquans));
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            final int arg2, long arg3) {
                        if (arg2 == 0) {
//                            listview2.setAdapter(new Title_SortAdapter2(getActivity(), shangquans2, 1));
//                            listview2.setOnItemClickListener(new OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    for (int i = 0; i < shangquans2.size(); i++) {
//                                        shangquans2.get(i).setIschoose(false);
//                                    }
//                                    shangquans2.get(position).setIschoose(true);
//                                    for (int i = 0; i < shangquans.size(); i++) {
//                                        shangquans.get(i).setIschoose(false);
//                                    }
//                                    shangquans.get(0).setIschoose(true);
//                                    tv_sort2.setText(shangquans2.get(position).getTitle());
//                                    areaId = -1;
//                                    getData();
//                                    mPopupwinow2.dismiss();
//                                }
//                            });
                            tv_sort2.setText("附近");
                            areaId = -1;
                            getData();
                            mPopupwinow2.dismiss();
                        } else {
                            for (int i = 0; i < shangquans.size(); i++) {
                                shangquans.get(i).setIschoose(false);
                            }
                            for (int i = 0; i < shangquans2.size(); i++) {
                                shangquans2.get(i).setIschoose(false);
                            }
                            tv_sort2.setText(shangquans.get(arg2).getTitle());
                            shangquans.get(arg2).setIschoose(true);
                            areaId = arg2;
                            getData();
                            mPopupwinow2.dismiss();
                        }
                    }
                });
                break;
            case 2:
                iv_sort3.setImageResource(R.mipmap.hei_up);
                final View view3 = LayoutInflater.from(getActivity()).inflate(R.layout.popu_first_list, null);
                final PopupWindow mPopupwinow3 = new PopupWindow(view3,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        windows_height * 456 / 1334, true);
                mPopupwinow3.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        iv_sort3.setImageResource(R.mipmap.hei_down);
                    }
                });
                mPopupwinow3
                        .setBackgroundDrawable(new ColorDrawable(
                                0x00000000));
                mPopupwinow3.showAsDropDown(v, 0, -20);
                ListView listview3 = (ListView) view3.findViewById(R.id.listview1);
                view3.findViewById(R.id.listview2).setVisibility(View.GONE);
                listview3.setAdapter(new Title_SortAdapter1(getActivity(), clazzs2, 1));
                listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            final int arg2, long arg3) {
                        for (int i = 0; i < clazzs2.size(); i++) {
                            clazzs2.get(i).setIschoose(false);
                        }
                        clazzs2.get(arg2).setIschoose(true);
                        tv_sort3.setText(clazzs2.get(arg2).getTitle());
                        mPopupwinow3.dismiss();
                        sort = arg2;
                        getData();
                    }
                });
                break;
        }
    }

    public void setmQueue(RequestQueue mQueue) {
        this.mQueue = mQueue;
    }


}
