package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cd.wzjkj.canyi.R;
import com.lidroid.xutils.BitmapUtils;
import com.mikhaellopez.circularimageview.CircularImageView;

/**
 * Created by liuzheng on 2016/11/30.
 */
public class CircularivAdapter extends BaseAdapter{
    private Context context;
    private String[] peiLiaos;
    private LayoutInflater inflater;
    private BitmapUtils bu;

    public CircularivAdapter(Context context, String[] peiLiaos) {
        this.context = context;
        this.peiLiaos = peiLiaos;
        this.inflater = LayoutInflater.from(context);
        bu = new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return peiLiaos.length;
    }

    @Override
    public Object getItem(int position) {
        return peiLiaos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.circularimageview_item_gv,null);
        CircularImageView civ = (CircularImageView) convertView.findViewById(R.id.civ);
        bu.display(civ,peiLiaos[position]);
        return convertView;
    }
}
