package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.DianJia;
import com.cd.wzjkj.canyi.tools.Tools;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

public class ShangChang_LV_Adapter extends BaseAdapter {

	private Context context;
	private ArrayList<DianJia> shangJias;
	private LayoutInflater inflater;

	public ShangChang_LV_Adapter(Context context, ArrayList<DianJia> shangJias) {
		super();
		this.context = context;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		Houdler h;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.shouchang_activity_lv,
					null);
			h = new Houdler();
			h.icon = (ImageView) convertView.findViewById(R.id.icon);
			h.title = (TextView) convertView.findViewById(R.id.title);
			h.point = (TextView) convertView.findViewById(R.id.point);
			h.price = (TextView) convertView.findViewById(R.id.price);
			h.km = (TextView) convertView.findViewById(R.id.km);
			h.xinxinhalf = (ImageView) convertView.findViewById(R.id.xinxinhalf);
			h.xinxins = new ImageView[] {
					(ImageView) convertView.findViewById(R.id.xinxin1),
					(ImageView) convertView.findViewById(R.id.xinxin2),
					(ImageView) convertView.findViewById(R.id.xinxin3),
					(ImageView) convertView.findViewById(R.id.xinxin4),
					(ImageView) convertView.findViewById(R.id.xinxin5) };
			convertView.setTag(h);
		}else{
			h = (Houdler) convertView.getTag();
		}
		DianJia sj = shangJias.get(position);
		h.title.setText(sj.getTitle());

		h.price.setText(context.getResources().getString(R.string.single_price)+":€"+sj.getPrice());
		h.km.setText(Tools.getIntfordouble(sj.getPrice())+"km");
		Tools.setXingji(sj.getPoint(),h.xinxins,h.xinxinhalf);
		BitmapUtils bu = new BitmapUtils(context);
		bu.display(h.icon, sj.getIcon());
		return convertView;
	}


	class Houdler {
		ImageView icon;
		ImageView xinxinhalf;
		TextView title;
		ImageView[] xinxins;
		TextView point;
		TextView price;
		TextView km;

	}

}
