package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.activity.Input_CommentActivity;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/14.
 */
public class UPImageAdaapter extends BaseAdapter{
    private Context context;
    private Input_CommentActivity activity;
    private BitmapUtils bu;
    public boolean flag;
    private ArrayList<Bitmap> images;
    public UPImageAdaapter(Context context, ArrayList<Bitmap> images) {
        this.context = context;
        this.bu = new BitmapUtils(context);
        this.images = images;
        this.activity = (Input_CommentActivity) context;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(position<=7) {
            Houder h;
            if (convertView == null) {
                h = new Houder();
                convertView = LayoutInflater.from(context).inflate(R.layout.comment_gv_item, null);
                h.icon = (ImageView) convertView.findViewById(R.id.icon);
                convertView.setTag(h);
            } else {
                h = (Houder) convertView.getTag();
            }
            if (position == images.size() - 1 && !flag) {
                h.icon.setImageResource(R.mipmap.addimage);
                h.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.showPicturePicker(context);
                    }
                });
            } else {
                bu.configDefaultLoadFailedImage(images.get(position));
                bu.display(h.icon,"");
                h.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            return convertView;
        }else{
            return null;
        }
    }
    class Houder{
        ImageView icon;
    }
}
