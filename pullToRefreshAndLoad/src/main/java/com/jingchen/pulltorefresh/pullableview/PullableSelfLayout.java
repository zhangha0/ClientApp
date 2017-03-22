package com.jingchen.pulltorefresh.pullableview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PullableSelfLayout extends LinearLayout implements Pullable{

	public PullableSelfLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public PullableSelfLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullableSelfLayout(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown() {
		if (getScrollY() == 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean canPullUp() {
		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
			return true;
		else
			return false;
	}

}
