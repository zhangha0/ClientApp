package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.TuanGuo2;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/10/24.
 */
public class TuanGouLVAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<TuanGuo2> tuanguos;
    private BitmapUtils bu;
    private int num = -1;

    public TuanGouLVAdapter(Context context,ArrayList<TuanGuo2> tuanguos) {
        super();
        this.context = context;
        this.tuanguos = tuanguos;
        this.inflater = LayoutInflater.from(context);
        this.bu = new BitmapUtils(context);
    }

    public TuanGouLVAdapter(Context context,ArrayList<TuanGuo2> tuanguos, int num) {
        super();
        this.context = context;
        this.num = num;
        this.tuanguos = tuanguos;
        this.inflater = LayoutInflater.from(context);
        this.bu = new BitmapUtils(context);
    }


    @Override
    public int getCount() {
        return tuanguos.size();
    }

    @Override
    public Object getItem(int position) {
        return tuanguos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Hould hould = null;
        TuanGuo2 t = tuanguos.get(position);
        if(convertView==null){
            hould = new Hould();
            convertView= inflater.inflate(R.layout.dianjia_tuanguo_item,null);
            hould.icon = (ImageView) convertView.findViewById(R.id.icon);
            hould.name = (TextView) convertView.findViewById(R.id.name);
            hould.seld = (TextView) convertView.findViewById(R.id.seld);
            hould.red_price = (TextView) convertView.findViewById(R.id.price_red);
            hould.black_price = (TextView) convertView.findViewById(R.id.price_black);
            hould.buy = (TextView) convertView.findViewById(R.id.buy);
            convertView.setTag(hould);

        }else{
            hould = (Hould) convertView.getTag();
        }

        bu.display(hould.icon,Httptools.Http2+t.getBackGroundImg());
//        hould.name.setText(Arrays.toString(Tools.getstrsByString(t.getIngredientsIconArray())));
        hould.name.setText(t.getTitle());
        hould.seld.setText(context.getResources().getString(R.string.seld)+t.getSeld());
        hould.red_price.setText("€"+t.getRedprice());
        hould.black_price.setText("€"+t.getBlackprice());
        hould.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Httptools.makeText1(context,new Throwable().fillInStackTrace());
            }
        });
//        Log.e("TAG","id="+t.getId()+"\n图标"+ Arrays.toString(Tools.getstrsByString(t.getIngredientsIconArray())));
        return convertView;
    }

    class Hould{
        ImageView icon;
        TextView name;
        TextView seld;
        TextView red_price;
        TextView black_price;
        TextView buy;
    }
}
