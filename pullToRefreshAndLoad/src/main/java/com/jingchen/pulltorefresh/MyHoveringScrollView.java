package com.jingchen.pulltorefresh;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import com.jingchen.pulltorefresh.pullableview.Pullable;

/**
 * * * * * * * * * * * * * * * * * * * * * * *
 * Created by zhaoyiding
 * Date: 15/9/3
 * * * * * * * * * * * * * * * * * * * * * * *
 **/
public class MyHoveringScrollView extends FrameLayout implements Pullable {

    /**
     * 固定在顶部的View
     */
    private ViewGroup mTopView;

    /**
     * 固定在顶部的View里面的内容
     */
    public View mTopContent;

    /**
     * 固定在顶部的View在滚动条里最上端的位置
     */
    private int mTopViewTop;

    /**
     * 滚动条的内容
     */
    private ViewGroup mContentView;
    private boolean flag = true;
    public boolean flag2 = false;
    public int height;
    private int scrollY;


    public MyHoveringScrollView(Context context) {
        this(context, null);
    }

    public MyHoveringScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public MyHoveringScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyHoveringScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        post(new Runnable() {
            @Override
            public void run() {
                mContentView = (ViewGroup) getChildAt(0);
                removeAllViews();

                MyScrollView scrollView = new MyScrollView(getContext(), MyHoveringScrollView.this);
                scrollView.addView(mContentView);
                addView(scrollView);

            }
        });
    }


    public void setTopView(final int id) {
        post(new Runnable() {
            @Override
            public void run() {
                mTopView = (ViewGroup) mContentView.findViewById(id);
                int height = mTopView.getChildAt(0).getMeasuredHeight();
                ViewGroup.LayoutParams params = mTopView.getLayoutParams();
                params.height = height;
                mTopView.setLayoutParams(params);
                mTopViewTop = mTopView.getTop();
                mTopContent = mTopView.getChildAt(0);
            }
        });
    }

    public void onScroll(final int scrollY) {
        this.scrollY = scrollY;
        post(new Runnable() {
            @Override
            public void run() {
                if (mTopView == null) {
                    return;
                }
//
                if (scrollY >= mTopViewTop
                        && mTopContent.getParent() == mTopView) {
                    mTopView.removeView(mTopContent);
                    addView(mTopContent);
                    flag = false;
                } else if (scrollY < mTopViewTop
                        && mTopContent.getParent() == MyHoveringScrollView.this) {
                    removeView(mTopContent);
                    mTopView.addView(mTopContent);
                    flag = true;
                }
                if (scrollY >= (height - getBottom())) {
                    flag2 = true;
                } else {
                    flag2 = false;
                }
            }
        });
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0 && flag)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
        return flag2;
    }

    @SuppressLint("ViewConstructor")
    private static class MyScrollView extends ScrollView {

        private MyHoveringScrollView mScrollView;

        public MyScrollView(Context context, MyHoveringScrollView scrollView) {
            super(context);
            mScrollView = scrollView;
        }


        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollView.onScroll(t);
            super.onScrollChanged(l, t, oldl, oldt);
        }

    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//            switch (ev.getAction()) {
//                case MotionEvent.ACTION_MOVE:
//                    if (scrollY >= mTopViewTop
//                            && mTopContent.getParent() == mTopView) {
//                        mTopView.removeView(mTopContent);
//                        addView(mTopContent);
//                        flag = false;
//                    } else if (scrollY < mTopViewTop
//                            && mTopContent.getParent() == MyHoveringScrollView.this) {
//                        removeView(mTopContent);
//                        mTopView.addView(mTopContent);
//                        flag = true;
//                    }
//
//            }
//        return super.dispatchTouchEvent(ev);
//    }
}