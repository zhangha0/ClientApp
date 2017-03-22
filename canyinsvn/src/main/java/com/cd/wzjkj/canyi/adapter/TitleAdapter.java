package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;


/**
 * 类描述：
 * 创建时间：2016/8/12,0012 上午:11:00
 * 修改人：Administrator
 * 修改时间：2016/8/12,0012 上午:11:00
 * 修改备注：
 */
public class TitleAdapter extends BaseAdapter {

    private Context context;
    private String[] strs;
    private LayoutInflater inflater;

    public TitleAdapter(Context context, String[] strs) {
        super();
        this.context = context;
        this.strs = strs;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return strs.length;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return strs[arg0];
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
            convertView.setTag(h);
        }else{
            h = (Houlder) convertView.getTag();
        }
        h.tv1.setText(strs[position]);
        return convertView;
    }
    class Houlder {
        TextView tv1;
    }

}