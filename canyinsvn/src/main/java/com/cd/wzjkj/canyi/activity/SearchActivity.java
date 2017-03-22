package com.cd.wzjkj.canyi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.jingchen.pulltorefresh.PullToRefreshLayout;
import com.jingchen.pulltorefresh.pullableview.PullableListView;
import com.zhy.autolayout.AutoLayoutActivity;

public class SearchActivity extends AutoLayoutActivity {
    LinearLayout invis;
    View header2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        invis = (LinearLayout) findViewById(R.id.invis);

        final String[] strs = new String[100];

        for (int i = 0; i < 20; i++) {
            strs[i] = "data-----" + i;
        }

        PullableListView lv = (PullableListView) findViewById(R.id.lv);
        View header = View.inflate(this, R.layout.class_sort, null);//头部内容
        header2=View.inflate(this, R.layout.class_sort, null);
        lv.addHeaderView(header);//添加头部
        lv.addHeaderView(header2);//ListView条目中的悬浮部分 添加到头部
        BaseAdapter ba=new BaseAdapter() {
            @Override
            public int getCount() {
                return strs.length;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                view=new TextView(SearchActivity.this);
                ((TextView)view).setText(strs[i]);
                return view;
            }
        };
        lv.setAdapter(ba);

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem >= 1) {
                    invis.setVisibility(View.VISIBLE);
                    header2.setVisibility(View.GONE);
                } else{
                    invis.setVisibility(View.GONE);
                    header2.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
