package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.activity.Plus_Minus_ChooseActivity;
import com.cd.wzjkj.canyi.entity.CaiPing;

import java.util.List;

/**
 * Created by liuzheng on 2016/11/25.
 */
public class TextViewLVAdapter extends BaseAdapter{
    private Context context;
    private List<CaiPing> caiPings;
    private LayoutInflater inflater;
    private int num;
    private Plus_Minus_ChooseActivity djwma;
    public TextViewLVAdapter(Context context, List<CaiPing> caiPings) {
        this.context = context;
        this.caiPings = caiPings;
        this.inflater = LayoutInflater.from(context);
    }

    public TextViewLVAdapter(Context context, List<CaiPing> caiPings, int num) {
        this.context = context;
        this.caiPings = caiPings;
        this.inflater = LayoutInflater.from(context);
        this.num = num;
        this.djwma = (Plus_Minus_ChooseActivity) context;
    }

    @Override
    public int getCount() {
        return caiPings.size();
    }

    @Override
    public Object getItem(int position) {
        return caiPings.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.tuanguoxx_item2,null);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(caiPings.get(position).getTitle());
        if(num != 0){
            if(caiPings.get(position).isChoosed()){
                name.setTextColor(Color.argb(255,21,21,21));
            }else{
                name.setTextColor(Color.argb(255,128,128,128));
            }
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    djwma.choose(num+3,position);
                }
            });
        }
        return convertView;
    }
}
