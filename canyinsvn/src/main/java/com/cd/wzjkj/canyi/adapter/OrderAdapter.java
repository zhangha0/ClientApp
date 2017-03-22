package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.activity.OrderActivity;
import com.cd.wzjkj.canyi.entity.Order;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/8.
 */
public class OrderAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Order> orders;
    private LayoutInflater inflater;
    private BitmapUtils bu;
    private OrderActivity activity;

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.activity = (OrderActivity) context;
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
            convertView = inflater.inflate(R.layout.order_item_lv,null);
            h.icon = (ImageView) convertView.findViewById(R.id.icon);
            h.order_id = (TextView) convertView.findViewById(R.id.order_id);
            h.tv1 = (TextView) convertView.findViewById(R.id.tv1);
            h.tv2 = (TextView) convertView.findViewById(R.id.tv2);
            h.tv3 = (TextView) convertView.findViewById(R.id.tv3);
            h.tv4 = (TextView) convertView.findViewById(R.id.tv4);
            h.tv5 = (TextView) convertView.findViewById(R.id.tv5);
            h.tv_btn = (TextView) convertView.findViewById(R.id.tv_btn);
            convertView.setTag(h);
        }else{
            h = (Hould) convertView.getTag();
        }
        final Order o = orders.get(position);
        String str;
        String str1;
        h.order_id.setVisibility(View.GONE);
        if(o.getType()==0) {
            str = context.getResources().getString(R.string.last_data)+":"+o.getLast_user_data();
            str1 = context.getResources().getString(R.string.to_use);
        }else if(o.getType()==1){
            h.order_id.setText(context.getResources().getString(R.string.order_id)+o.getOrderId());
            str =  context.getResources().getString(R.string.creat_data)+":"+o.getOrder_data();
            str1 = context.getResources().getString(R.string.pay);
        }else if(o.getType()==2){
            str = context.getResources().getString(R.string.use_state);
            str1 = context.getResources().getString(R.string.to_comment);
        }else{
            str = context.getResources().getString(R.string.use_state);
            str1 ="";
            h.tv_btn.setVisibility(View.GONE);
        }
        bu.display(h.icon,o.getIcon());
        h.tv1.setText(o.getTitle()+o.getType());
        h.tv2.setText(o.getName()+o.getType());
        h.tv3.setText(str);
        h.tv4.setText(context.getResources().getString(R.string.count)+":"+o.getCount());
        h.tv5.setText(context.getResources().getString(R.string.all_privce)+":€"+o.getPrice());
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
        TextView order_id;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView tv5;
        TextView tv_btn;

    }
}
