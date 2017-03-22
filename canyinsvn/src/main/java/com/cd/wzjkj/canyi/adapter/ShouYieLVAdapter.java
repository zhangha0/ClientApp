package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.TuanGou_DainJia;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.cd.wzjkj.canyi.tools.Tools;
import com.lidroid.xutils.BitmapUtils;

import org.xutils.x;

import java.util.ArrayList;

public class ShouYieLVAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TuanGou_DainJia> shangJias;
    private LayoutInflater inflater;
    private int num;


    public ShouYieLVAdapter(Context context, ArrayList<TuanGou_DainJia> shangJias, int num) {
        super();
        this.context = context;
        this.num = num;
        this.shangJias = shangJias;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(num==1){
            return 10;
        }
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (num == 0) {
            Houdler_0 h = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.shouyie_tuangou_item_lv, null);
                h = new Houdler_0();
                h.icon = (ImageView) convertView.findViewById(R.id.icon);
                h.title = (TextView) convertView.findViewById(R.id.title);
                h.address = (TextView) convertView.findViewById(R.id.address);
                h.km = (TextView) convertView.findViewById(R.id.km);
                h.title_second = (TextView) convertView.findViewById(R.id.title_second);
                h.price_red = (TextView) convertView.findViewById(R.id.price_red);
                h.price_black = (TextView) convertView.findViewById(R.id.price_black);
                h.good_tv = (TextView) convertView.findViewById(R.id.good_tv);
                h.share = (LinearLayout) convertView.findViewById(R.id.share);
                h.good = (LinearLayout) convertView.findViewById(R.id.good);
                convertView.setTag(h);
            } else {
                h = (Houdler_0) convertView.getTag();
            }
            TuanGou_DainJia djt = (TuanGou_DainJia) shangJias.get(position);
            x.image().bind(h.icon, Httptools.Http2 + djt.getIcon());
            h.title.setText(djt.getTitle());
            h.address.setText(djt.getAddrees());
            h.km.setText("15km");
//                h.title_second.setText(djt.getTuanGuos().get(0).getTitle());
            h.price_red.setText("€" + djt.getDiscountPrice());
            h.price_black.setText("€" + djt.getPrice());
            h.good_tv.setText("" + djt.getLikeNumber());
            h.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Httptools.makeText(context, "分享", true);
                }
            });
            h.good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Httptools.makeText(context, "点赞", true);
                }
            });

        } else if (num == 1) {
            Houdler_1 h = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.shouyie_waimai_item_lv, null);
                h = new Houdler_1();
                h.icon = (ImageView) convertView.findViewById(R.id.icon);
                h.title = (TextView) convertView.findViewById(R.id.title);
                h.km = (TextView) convertView.findViewById(R.id.km);
                h.startsendandsendmoney = (TextView) convertView.findViewById(R.id.startsendandsendmoney);
                h.seldbymonth = (TextView) convertView.findViewById(R.id.seldbymonth);
                h.point = (TextView) convertView.findViewById(R.id.point);
                h.xinxins = new ImageView[]{
                        (ImageView) convertView.findViewById(R.id.xinxin1),
                        (ImageView) convertView.findViewById(R.id.xinxin2),
                        (ImageView) convertView.findViewById(R.id.xinxin3),
                        (ImageView) convertView.findViewById(R.id.xinxin4),
                        (ImageView) convertView.findViewById(R.id.xinxin5)
                };
                h.xinxinhalf = (ImageView) convertView.findViewById(R.id.xinxinhalf);
                convertView.setTag(h);
            } else {
                h = (Houdler_1) convertView.getTag();
            }
            if (false) {
                TuanGou_DainJia djw = (TuanGou_DainJia) shangJias.get(position);
                Tools.setXingji(djw.getPoint(), h.xinxins, h.xinxinhalf);
                x.image().bind(h.icon, Httptools.Http2 + djw.getIcon());
                h.title.setText(djw.getTitle());
                h.km.setText(djw.getDistance() + "km");
                h.point.setText("" + djw.getPoint());
                h.seldbymonth.setText(context.getResources().getString(R.string.seldbymonth) + djw.getTakeawayOrderCount());
                h.startsendandsendmoney.setText("€" + djw.getStartTakeawayMoney() + context.getResources().getString(R.string.startseld) + "/" + context.getResources().getString(R.string.emsmoney) + "€" + djw.getFreight());
            }else {//测试使用
                Tools.setXingji(3.5, h.xinxins, h.xinxinhalf);
                h.icon.setBackgroundResource(R.drawable.text);
                h.title.setText("葡萄");
                h.km.setText("100km");
                h.point.setText("3.5" );
                h.seldbymonth.setText("已售158");
                h.startsendandsendmoney.setText("€100/€1000");
            }
        }
        return convertView;

    }

    class Houdler_0 {
        ImageView icon;
        TextView title;
        TextView address;
        TextView km;
        TextView title_second;
        TextView price_red;
        TextView price_black;
        LinearLayout share;
        LinearLayout good;
        TextView good_tv;
    }

    class Houdler_1 {
        ImageView icon;
        TextView title;
        TextView km;
        ImageView[] xinxins;
        ImageView xinxinhalf;
        TextView point;
        TextView startsendandsendmoney;
        TextView seldbymonth;
    }
}
