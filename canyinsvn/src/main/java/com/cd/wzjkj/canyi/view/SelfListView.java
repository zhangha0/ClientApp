package com.cd.wzjkj.canyi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class SelfListView extends ListView {

	public SelfListView(Context context) {
		super(context);
	}
	public SelfListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
