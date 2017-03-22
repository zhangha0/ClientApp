package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.Clazz;
import com.cd.wzjkj.canyi.tools.Httptools;
import com.lidroid.xutils.BitmapUtils;

import org.xutils.x;

import java.util.ArrayList;


public class ShouYieGVAdapter extends BaseAdapter{
	private Context context;
	private int[] imageids;
	private ArrayList<Clazz> clazzs;
	private BitmapUtils bu;
	private LayoutInflater inflater;

	public ShouYieGVAdapter(Context context, int[] imageids, ArrayList<Clazz> clazzs) {
		super();
		this.context = context;
		this.imageids = imageids;
		this.clazzs = clazzs;
		this.bu = new BitmapUtils(context);
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return clazzs.size();
	}

	@Override
	public Object getItem(int position) {
		return clazzs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.shouyie_item_gv, null);
		ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
		TextView title = (TextView) convertView.findViewById(R.id.text);
		x.image().bind(icon,Httptools.Http2+clazzs.get(position).getIcon());
		title.setText(clazzs.get(position).getTitle());
		return convertView;
	}

}
