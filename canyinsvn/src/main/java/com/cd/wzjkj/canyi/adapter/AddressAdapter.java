package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.activity.AddressActivity;
import com.cd.wzjkj.canyi.entity.AddressSelf;

import java.util.List;

/**
 * Created by liuzheng on 2016/12/9.
 */
public class AddressAdapter extends BaseAdapter{
    private List<AddressSelf> addressSelfs;
    private Context context;
    private LayoutInflater inflater;
    private AddressActivity activity;
    public AddressAdapter(Context context, List<AddressSelf> addressSelfs) {
        this.context = context;
        this.activity = (AddressActivity) context;
        this.addressSelfs = addressSelfs;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return addressSelfs.size();
    }

    @Override
    public Object getItem(int position) {
        return addressSelfs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.address_item_lv,null);
        TextView tv1 = (TextView) convertView.findViewById(R.id.nameandphone);
        TextView tv2 = (TextView) convertView.findViewById(R.id.shen_city_region_address);
        TextView updata = (TextView) convertView.findViewById(R.id.updata);
        TextView delete = (TextView) convertView.findViewById(R.id.delete);
        AddressSelf as = addressSelfs.get(position);
        tv1.setText(as.getName()+"    "+as.getPhone());
        tv2.setText(as.getShen_city_region()+as.getAddress());
        updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.updata(position);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.delete(position);
            }
        });
        return convertView;
    }
}
