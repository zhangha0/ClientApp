package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.Clazz;

import java.util.ArrayList;


/**
 * 类描述：
 * 创建时间：2016/8/12,0012 上午:11:00
 * 修改人：Administrator
 * 修改时间：2016/8/12,0012 上午:11:00
 * 修改备注：
 */
public class Title_SortAdapter2 extends BaseAdapter {

    private Context context;
    private ArrayList<Clazz> clazzs;
    private LayoutInflater inflater;
    private int num;

    public Title_SortAdapter2(Context context, ArrayList<Clazz> clazzs) {
        super();
        this.context = context;
        this.clazzs = clazzs;
        this.inflater = LayoutInflater.from(context);
    }
    public Title_SortAdapter2(Context context, ArrayList<Clazz> clazzs, int num) {
        this(context,clazzs);
        this.num = num;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return clazzs.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return clazzs.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Houlder h = null;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_title_lv_popu2, null);
            h = new Houlder();
            h.tv1 = (TextView) convertView.findViewById(R.id.tv);
            h.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(h);
        }else{
            h = (Houlder) convertView.getTag();
        }
        if(num==0) {
            h.tv1.setText(clazzs.get(position).getTitle());
            h.icon.setVisibility(View.GONE);
        }else{
            h.tv1.setText(clazzs.get(position).getTitle()+"");
            if(clazzs.get(position).ischoose()){
                h.icon.setVisibility(View.VISIBLE);
            }else{
                h.icon.setVisibility(View.GONE);
            }
        }
        if(clazzs.get(position).ischoose()){
            h.tv1.setTextColor(Color.argb(255,255,0,0));
        }else{
            h.tv1.setTextColor(Color.argb(255,102,102,102));
        }
        return convertView;
    }
    class Houlder {
        TextView tv1;
        ImageView icon;
    }

}