package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.CaiPing;
import com.cd.wzjkj.canyi.entity.TuanGuo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzheng on 2016/12/19.
 */
public class Order_Pay_Adapter extends BaseAdapter{
    private Context context;
    private List<CaiPing> caiPings;
    private List<TuanGuo> tuanGuos;
    private List<Object> objects;
    private int money;
    private LayoutInflater inflater;
    private int num;

    public Order_Pay_Adapter(Context context, List<CaiPing> caiPings, List<TuanGuo> tuanGuos,int num) {
        this(context,caiPings,tuanGuos);
        this.num = num;
    }
    public Order_Pay_Adapter(Context context, List<CaiPing> caiPings, List<TuanGuo> tuanGuos) {
        this.context = context;
        this.objects = getobjects(caiPings,tuanGuos);
        this.inflater = LayoutInflater.from(context);
        this.caiPings = caiPings;
        this.tuanGuos = tuanGuos;
    }

    private List<Object> getobjects(List<CaiPing> caiPings, List<TuanGuo> tuanGuos) {
        List<Object> objects = new ArrayList<>();
        for(int i=0;i<caiPings.size();i++){
            objects.add(caiPings.get(i));
        }
        for(int i=0;i<tuanGuos.size();i++){
            objects.add(tuanGuos.get(i));
        }
        objects.add(new TuanGuo());
        return objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(num == 0){
            convertView = inflater.inflate(R.layout.sendorder_item_lv,null);
        }else{
            convertView = inflater.inflate(R.layout.sendorder_item2_lv,null);
        }
        TextView tv1 = (TextView) convertView.findViewById(R.id.tv1);
        TextView tv2 = (TextView) convertView.findViewById(R.id.tv2);
        TextView tv3 = (TextView) convertView.findViewById(R.id.tv3);
        if(position<caiPings.size()){
            CaiPing cp = (CaiPing) objects.get(position);
            tv1.setText(cp.getTitle());
            tv2.setText("X"+cp.getCount());
            tv3.setText("€"+cp.getPrice());
        }else if(position<caiPings.size()+tuanGuos.size()){
            TuanGuo tg = (TuanGuo) objects.get(position);
            tv1.setText(tg.getTitle());
            tv2.setText("X"+tg.getCount());
            tv3.setText("€"+tg.getprice2()*tg.getCount());
        }else{
            tv1.setText(context.getResources().getString(R.string.emsmoney));
            tv2.setVisibility(View.GONE);
            tv3.setText("€"+32);
        }
        return convertView;
    }
}
