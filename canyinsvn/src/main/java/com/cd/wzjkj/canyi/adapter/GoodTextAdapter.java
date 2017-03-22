package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.activity.Input_CommentActivity;
import com.cd.wzjkj.canyi.entity.CaiPing;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/14.
 */
public class GoodTextAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CaiPing> caiPings;
    private Input_CommentActivity activity;

    public GoodTextAdapter(Context context, ArrayList<CaiPing> caiPings) {
        this.context = context;
        this.activity = (Input_CommentActivity) context;
        this.caiPings = caiPings;
    }

    @Override
    public int getCount() {
        return caiPings.size();
    }

    @Override
    public Object getItem(int position) {
        return caiPings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.comment_lv_item, null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv1);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);
        cb.setChecked(caiPings.get(position).isChoosed());
        convertView.findViewById(R.id.layout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.checked(!caiPings.get(position).isChoosed(), position);
            }
        });
        tv.setText(caiPings.get(position).getTitle());
        return convertView;
    }
}
