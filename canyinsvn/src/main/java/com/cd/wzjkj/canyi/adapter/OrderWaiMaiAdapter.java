package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.activity.OrderWaiMaiActivity;
import com.cd.wzjkj.canyi.entity.Order;
import com.cd.wzjkj.canyi.view.SelfListView;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/8.
 */
public class OrderWaiMaiAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Order> orders;
    private LayoutInflater inflater;
    private BitmapUtils bu;
    private OrderWaiMaiActivity activity;

    public OrderWaiMaiAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.activity = (OrderWaiMaiActivity) context;
        this.orders = orders;
        this.inflater = LayoutInflater.from(context);
        this.bu = new BitmapUtils(context);
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int position) {
        return orders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Hould h;
        if(convertView==null){
            h = new Hould();
            convertView = inflater.inflate(R.layout.waimai_order_item_lv,null);
            h.icon = (ImageView) convertView.findViewById(R.id.icon);
            h.item_title = (TextView) convertView.findViewById(R.id.item_title);
            h.all_price = (TextView) convertView.findViewById(R.id.tv5);
            h.listview = (SelfListView) convertView.findViewById(R.id.listview);
            h.tv_btn = (TextView) convertView.findViewById(R.id.tv_btn);
            convertView.setTag(h);
        }else{
            h = (Hould) convertView.getTag();
        }
        final Order o = orders.get(position);
        String str1="";
        if(o.getType()==0) {
            str1 = context.getResources().getString(R.string.to_pay);
        }else if(o.getType()==1){
            str1 = context.getResources().getString(R.string.order_state);
        }else if(o.getType()==2){
            str1 = context.getResources().getString(R.string.to_comment);
        }
        bu.display(h.icon,o.getIcon());
        h.listview.setAdapter(new Order_Pay_Adapter(context,o.getCaiPings(),o.getTuanGuos(),1));
        h.tv_btn.setText(str1);
        h.tv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onclick(o.getType(),position);
            }
        });
        return convertView;
    }
    class Hould{
        ImageView icon;
        TextView item_title;
        SelfListView listview;
        TextView all_price;
        TextView tv_btn;

    }
}
