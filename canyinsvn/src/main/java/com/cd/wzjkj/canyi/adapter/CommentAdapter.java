package com.cd.wzjkj.canyi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.SelfComment;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/6.
 */
public class CommentAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<SelfComment> comments;
    private LayoutInflater inflater;
    private BitmapUtils bu;
    public CommentAdapter(Context context, ArrayList<SelfComment> comments) {
        this.context = context;
        this.bu = new BitmapUtils(context);
        this.comments = comments;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Houlder h;
        if(convertView==null){
            h = new Houlder();
            convertView = inflater.inflate(R.layout.comment_item_waimai,null);
            h.icon = (ImageView) convertView.findViewById(R.id.icon);
            h.title = (TextView) convertView.findViewById(R.id.title);
            h.data = (TextView) convertView.findViewById(R.id.comment_data);
            h.comment_content = (TextView) convertView.findViewById(R.id.content_comment);
            convertView.setTag(h);
        }else{
            h = (Houlder) convertView.getTag();
        }
        SelfComment sc = comments.get(position);
        bu.display(h.icon,sc.getComment_icon());
        h.title.setText(sc.getTitle());
        h.data.setText(sc.getData());
        h.comment_content.setText(sc.getContent_comment());
        return convertView;
    }
    class Houlder{
        ImageView icon;
        TextView title;
        TextView data;
        TextView comment_content;
    }
}
