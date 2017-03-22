package com.cd.wzjkj.canyi.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.cd.wzjkj.canyi.adapter.MyPageAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 44967 on 2017/3/20.
 */
public class AutoPage extends ViewPager {

    Timer timer;
    private TimerTask task;

    // 自定义的一个监听器
    private OnScollChangeListener onScollChangeListener;
    private Handler handler = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            // TODO Auto-generated method stub
            setCurrentItem(getCurrentItem() + 1, false);
            return false;
        }
    });

    public AutoPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        timer = new Timer();
    }

    public AutoPage(Context context) {
        super(context);
        timer = new Timer();
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        int cur = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % ((MyPageAdapter) adapter).getview().size();
        setCurrentItem(cur, false);
        startSliding();
    }

    public void startSliding() {
        if (task == null) {
            task = new TimerTask() {

                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            };
            timer.schedule(task, 3000, 3000);

        }
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (onScollChangeListener != null) {

            onScollChangeListener.scroll(l);


        }

    }


    public interface OnScollChangeListener {
        void scroll(int x);
    }

    public void setOnScollChangeListener(
            OnScollChangeListener onScollChangeListener) {
        this.onScollChangeListener = onScollChangeListener;
    }
}
