package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.activity.DianJai_TuanGuoActivity;
import com.cd.wzjkj.canyi.entity.TuanGou_DainJia;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.lidroid.xutils.BitmapUtils;

import org.xutils.x;

import java.util.ArrayList;

public class ShouYieLVAdapter2 extends BaseAdapter {

    private Context context;
    private ArrayList<TuanGou_DainJia> shangJias;
    private LayoutInflater inflater;
    private int num;


    public ShouYieLVAdapter2(Context context, ArrayList<TuanGou_DainJia> shangJias, int num) {
        super();
        this.context = context;
        this.num = num;
        this.shangJias = shangJias;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return shangJias.size();
    }

    @Override
    public Object getItem(int position) {
        return shangJias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (num==0) {
            Houdler_0 h = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_home_item, null);
                h = new Houdler_0();
                h.icon = (ImageView) convertView.findViewById(R.id.iv_homeItem);
                h.title = (TextView) convertView.findViewById(R.id.tv_home_title);
                h.km = (TextView) convertView.findViewById(R.id.tv_home_km);
                h.price = (TextView) convertView.findViewById(R.id.tv_home_price);
                h.like = (TextView) convertView.findViewById(R.id.tv_home_likeNum);
                h.share = (ImageView) convertView.findViewById(R.id.iv_home_share);
                h.ll_like= (LinearLayout) convertView.findViewById(R.id.ll_home_like);
                convertView.setTag(h);
            } else {
                h = (Houdler_0) convertView.getTag();
            }
            TuanGou_DainJia djt = (TuanGou_DainJia) shangJias.get(position);
            x.image().bind(h.icon,Httptools.Http2+djt.getFoodImageUrl());
            h.title.setText(djt.getFoodTitle());
            h.km.setText(djt.getDistance()+"km");
            h.price.setText("€" + djt.getPrice());
            h.like.setText("" + djt.getLikeNumber());
            h.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Httptools.makeText(context, "分享", true);
                }
            });
            h.ll_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Httptools.makeText(context, "点赞", true);
                }
            });
            h.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DianJai_TuanGuoActivity.class);
                    intent.putExtra("data", shangJias.get(position).getShopId());
                    context.startActivity(intent);
                }
            });

        }else if(num==1){
//            Houdler_1 h = null;
//            if (convertView == null) {
//                convertView = inflater.inflate(R.layout.shouyie_waimai_item_lv, null);
//                h = new Houdler_1();
//                h.icon = (ImageView) convertView.findViewById(R.id.icon);
//                h.title = (TextView) convertView.findViewById(R.id.title);
//                h.km = (TextView) convertView.findViewById(R.id.km);
//                h.startsendandsendmoney = (TextView) convertView.findViewById(R.id.startsendandsendmoney);
//                h.seldbymonth = (TextView) convertView.findViewById(R.id.seldbymonth);
//                h.point = (TextView) convertView.findViewById(R.id.point);
//                h.xinxins = new ImageView[]{
//                        (ImageView) convertView.findViewById(R.id.xinxin1),
//                        (ImageView) convertView.findViewById(R.id.xinxin2),
//                        (ImageView) convertView.findViewById(R.id.xinxin3),
//                        (ImageView) convertView.findViewById(R.id.xinxin4),
//                        (ImageView) convertView.findViewById(R.id.xinxin5)
//                };
//                h.xinxinhalf = (ImageView) convertView.findViewById(R.id.xinxinhalf);
//                convertView.setTag(h);
//            } else {
//                h = (Houdler_1) convertView.getTag();
//            }
//            DianJia_WaiMai djw = (DianJia_WaiMai) shangJias.get(position);
//            Tools.setXingji(djw.getPoint(),h.xinxins,h.xinxinhalf);
//            BitmapUtils bu = new BitmapUtils(context);
//            bu.configDefaultLoadFailedImage(R.mipmap.ic_launcher);
//            bu.display(h.icon, djw.getIcon());
//            h.title.setText(djw.getTitle());
//            h.km.setText("15km");
//            h.point.setText(""+djw.getPoint());
//            h.seldbymonth.setText(context.getResources().getString(R.string.seldbymonth)+djw.getSeldbymonth());
//            h.startsendandsendmoney.setText("€"+djw.getStartseld()+context.getResources().getString(R.string.startseld)+"/"+context.getResources().getString(R.string.emsmoney)+"€"+djw.getEmsmoney());
        }
        return convertView;

    }

    class Houdler_0 {
        ImageView icon;
        TextView title;
        TextView km;
        TextView price;
        ImageView share;
        TextView like;
        LinearLayout ll_like;
    }
}
