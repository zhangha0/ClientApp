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
import com.cd.wzjkj.canyi.entity.CaiPing;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by liuzheng on 2016/11/25.
 */
public class TuanGuo_Cai_Adapter extends BaseAdapter {
    private List<List<CaiPing>> caiPings;
    private Context context;
    private LayoutInflater inflater;
    private BitmapUtils bu;

    public TuanGuo_Cai_Adapter(Context context,  List<List<CaiPing>> caiPings) {
        this.context = context;
        this.caiPings = caiPings;
        this.inflater = LayoutInflater.from(context);
        this.bu = new BitmapUtils(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.tuanguoxx_item1, null);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView title2 = (TextView) convertView.findViewById(R.id.title2);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        CaiPing cp = caiPings.get(position).get(0);
        if(cp.getFoodTypeId()!=1){
            title.setVisibility(View.GONE);
            title2.setText("[ "+cp.getTitle2()+" ]");
            title.setVisibility(View.VISIBLE);
        }else {
            title.setBackgroundColor(Color.argb(255,0,0,0));
            title.setText(cp.getTitle2());
        }
        name.setText(cp.getTitle());
        bu.display(icon, Httptools.Http2+cp.getBackGroundImg());
        return convertView;
    }
}
