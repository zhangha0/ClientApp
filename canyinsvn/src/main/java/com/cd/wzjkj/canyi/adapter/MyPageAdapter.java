package com.cd.wzjkj.canyi.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 44967 on 2017/3/20.
 */
public class MyPageAdapter extends PagerAdapter {
    ArrayList<View> mList = new ArrayList<>();

    public MyPageAdapter(ArrayList<View> mlist) {
        super();
        mList = mlist;

    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position % mList.size();
        container.removeView(mList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mList.size();


        container.addView(mList.get(position));
        // Log.e("xxx",position+"");
        return mList.get(position);
    }

    public ArrayList<View> getview() {
        return mList;
    }

}




