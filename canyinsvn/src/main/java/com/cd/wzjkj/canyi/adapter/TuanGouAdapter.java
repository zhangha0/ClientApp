package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;


/**
 * Created by liuzheng on 2016/10/24.
 */
public class TuanGouAdapter extends BaseAdapter {
    private Context context;
    private String[] strs1;
    private String[] strs2;
    private int[] imageids;

    public TuanGouAdapter(FragmentActivity activity) {
        context = activity;
        strs1 = context.getResources().getStringArray(R.array.tuanguo_item1);
        strs2 = context.getResources().getStringArray(R.array.tuanguo_item2);
        imageids = new int[]{R.mipmap.tuanguo_gv1,R.mipmap.tuanguo_gv2,R.mipmap.tuanguo_gv3,R.mipmap.tuanguo_gv4};
    }

    @Override
    public int getCount() {
        return strs1.length;
    }

    @Override
    public Object getItem(int position) {
        return strs1[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.tuanguo_item_gv1,null);
        TextView tv1 = (TextView) convertView.findViewById(R.id.tv1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.tv2);
        ImageView iv1 = (ImageView) convertView.findViewById(R.id.iv1);
        tv1.setText(strs1[position]);
        tv2.setText(strs2[position]);
        iv1.setImageResource(imageids[position]);
        return convertView;
    }
}
