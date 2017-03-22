package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.activity.Plus_Minus_ChooseActivity;
import com.cd.wzjkj.canyi.entity.CaiPing;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;


/**
 * Created by liuzheng on 2016/11/28.
 */
public class WaiMai_CaiPing_Adapter extends BaseAdapter {
    private Context context;
    private List<CaiPing> caiPings;
    private LayoutInflater inflater;
    private BitmapUtils bu;
    private Plus_Minus_ChooseActivity activity;
    private int num;

    public WaiMai_CaiPing_Adapter(Plus_Minus_ChooseActivity context, List<CaiPing> caiPings) {
        this.context = (Context) context;
        this.activity = context;
        this.caiPings = caiPings;
        this.inflater = LayoutInflater.from(this.context);
        this.bu = new BitmapUtils(this.context);
    }
    public WaiMai_CaiPing_Adapter(Plus_Minus_ChooseActivity context, List<CaiPing> caiPings, int num) {
        this(context,caiPings);
        this.num = num;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        Hould h1 = null;
        if(convertView == null){
                h1 = new Hould();
            convertView = inflater.inflate(R.layout.dianjia_waimai_smalllv_item,null);
            h1.icon = (ImageView) convertView.findViewById(R.id.icon);
            h1.title = (TextView) convertView.findViewById(R.id.title);
            h1.price = (TextView) convertView.findViewById(R.id.price);
            h1.count = (TextView) convertView.findViewById(R.id.count);
            h1.beizhu = (TextView) convertView.findViewById(R.id.beizhu);
            h1.plustv = (TextView) convertView.findViewById(R.id.plustv);
            h1.minustv = (TextView) convertView.findViewById(R.id.minustv);
            h1.peiliaogv = (GridView) convertView.findViewById(R.id.peiliaogv);
            h1.rbchoose = (RadioButton) convertView.findViewById(R.id.rbchoose);
            convertView.setTag(h1);
        }else{
            h1 = (Hould) convertView.getTag();
        }
        final CaiPing cp = caiPings.get(position);
        final Hould h = h1;
        bu.display(h.icon,cp.getBackGroundImg());
        h.title.setText(cp.getTitle());
        h.price.setText("€"+cp.getPrice());
        h.count.setText(""+cp.getCount());
        h.beizhu.setText(cp.getBaiZhu());
        setcount(cp,h);
        h.rbchoose.setChecked(cp.isChoosed());
        h.plustv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp.setCount(cp.getCount()+1);
                setcount(cp,h);
                activity.plus(position);
            }
        });
        h.minustv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cp.getCount()!=0) {
                    cp.setCount(cp.getCount() - 1);
                    setcount(cp, h);
                    activity.minus(position);
                }
            }
        });
        CircularivAdapter adapter = new CircularivAdapter(context,cp.getPeiLiaos());
        if(num==0){
            h.rbchoose.setVisibility(View.GONE);
        }else if(num==1){
            h.plustv.setVisibility(View.GONE);
            h.rbchoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.choose(1,position);
                }
            });
            h.count.setVisibility(View.GONE);
            h.minustv.setVisibility(View.GONE);
        }else if(num==2){
            h.plustv.setVisibility(View.GONE);
            h.rbchoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.choose(2,position);
                }
            });
            h.count.setVisibility(View.GONE);
            h.minustv.setVisibility(View.GONE);
        }else if(num==3){
            h.plustv.setVisibility(View.GONE);
            h.rbchoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.choose(3,position);
                }
            });
            h.count.setVisibility(View.GONE);
            h.minustv.setVisibility(View.GONE);
        }
        h.peiliaogv.setAdapter(adapter);
        return convertView;
    }

    private void setcount(CaiPing cp, Hould h) {
        if(cp.getCount()==0){
            h.count.setText(cp.getCount()+"");
            h.count.setVisibility(View.GONE);
            h.minustv.setVisibility(View.GONE);
        }else{
            h.count.setText(cp.getCount()+"");
            h.count.setVisibility(View.VISIBLE);
            h.minustv.setVisibility(View.VISIBLE);
        }
    }


    class Hould{
        ImageView icon;
        TextView title;
        TextView price;
        TextView count;
        TextView beizhu;
        TextView plustv;
        TextView minustv;
        GridView peiliaogv;
        RadioButton rbchoose;
    }
}
