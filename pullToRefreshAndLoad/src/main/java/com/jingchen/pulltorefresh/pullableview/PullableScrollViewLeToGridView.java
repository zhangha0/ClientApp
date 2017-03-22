package com.jingchen.pulltorefresh.pullableview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class PullableScrollViewLeToGridView extends ScrollView implements Pullable
{
	private GridView gv;

	public PullableScrollViewLeToGridView(Context context)
	{
		super(context);
		LinearLayout llayout = (LinearLayout) getChildAt(0);
		gv = (GridView) llayout.getChildAt(llayout.getChildCount());
	}

	public PullableScrollViewLeToGridView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		LinearLayout llayout = (LinearLayout) getChildAt(0);
		Log.i("TAG", ""+getChildCount());
		gv = (GridView) llayout.getChildAt(llayout.getChildCount());
	}

	public PullableScrollViewLeToGridView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		LinearLayout llayout = (LinearLayout) getChildAt(0);
		gv = (GridView) llayout.getChildAt(llayout.getChildCount());
	}

	@Override
	public boolean canPullDown()
	{
		if (getScrollY() == 0&&(gv.getFirstVisiblePosition() == 0
				&& gv.getChildAt(0).getTop() >= 0))
			return true;
		else
			return false;
	}

	@Override
	public boolean canPullUp()
	{
		if (gv.getCount() == 0)
		{
			// 没有item的时候也可以上拉加载
			return true;
		} else if (gv.getLastVisiblePosition() == (gv.getCount() - 1))
		{
			// 滑到底部了
			if (gv.getChildAt(gv.getLastVisiblePosition() - gv.getFirstVisiblePosition()) != null
					&& getChildAt(
							gv.getLastVisiblePosition()
									- gv.getFirstVisiblePosition()).getBottom() <= gv.getMeasuredHeight())
				return true;
		}
		return false;
	}

}
