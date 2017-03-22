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
public class Title_SortAdapter1 extends BaseAdapter {

    private Context context;
    private ArrayList<Clazz> clazzs;
    private LayoutInflater inflater;
    private int num;
    private int[] ivoff;
    private int[] ivon;

    public Title_SortAdapter1(Context context, ArrayList<Clazz> clazzs) {
        super();
        this.context = context;
        this.clazzs = clazzs;
        this.inflater = LayoutInflater.from(context);
    }

    public Title_SortAdapter1(Context context, ArrayList<Clazz> clazzs, int num) {
        this(context, clazzs);
        this.num = num;
        ivoff = new int[]{R.mipmap.sort_iv1, R.mipmap.sort_iv2, R.mipmap.sort_iv3, R.mipmap.sort_iv4, R.mipmap.sort_iv5};
        ivon = new int[]{R.mipmap.sort_iv6, R.mipmap.sort_iv7, R.mipmap.sort_iv8, R.mipmap.sort_iv9, R.mipmap.sort_iv10};

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
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_title_lv_popu3, null);
            h = new Houlder();
            h.tv1 = (TextView) convertView.findViewById(R.id.tv1);
            h.tv2 = (TextView) convertView.findViewById(R.id.tv2);
            h.icon = (ImageView) convertView.findViewById(R.id.icon);
            h.sort_iv = (ImageView) convertView.findViewById(R.id.iv1);
            convertView.setTag(h);
        } else {
            h = (Houlder) convertView.getTag();
        }
        h.tv1.setText(clazzs.get(position).getTitle() + "");
        h.tv2.setText(clazzs.get(position).getCount() + "");
        if (clazzs.get(position).ischoose()) {
            if (num == 1) {
                h.sort_iv.setImageResource(ivon[position]);
                h.sort_iv.setVisibility(View.VISIBLE);
                h.tv2.setVisibility(View.GONE);
            }
            h.icon.setVisibility(View.VISIBLE);
            h.tv1.setTextColor(Color.argb(255, 255, 0, 0));
            h.tv2.setTextColor(Color.argb(255, 255, 0, 0));
        } else {
            h.tv1.setTextColor(Color.argb(255, 102, 102, 102));
            h.icon.setVisibility(View.GONE);
            h.tv2.setTextColor(Color.argb(255, 102, 102, 102));
            if (num == 1) {
                h.tv2.setVisibility(View.GONE);
                h.sort_iv.setVisibility(View.VISIBLE);
                h.sort_iv.setImageResource(ivoff[position]);
            }
        }
        return convertView;
    }

    class Houlder {
        TextView tv1;
        TextView tv2;
        ImageView icon;
        ImageView sort_iv;
    }

}