package com.jingchen.pulltorefresh.pullableview;

import com.jingchen.pulltorefresh.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

public class PullableListView extends ListView implements Pullable {

	public PullableListView(Context context) {
		super(context);
	}

	public PullableListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public PullableListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean canPullDown() {
		if (getCount() == 0) {
			// 没有item的时候也可以下拉刷新
			return true;
		} else if (getFirstVisiblePosition() == 0
				&& getChildAt(0).getTop() >= 0) {
			// 滑到ListView的顶部了
			return true;
		} else
			return false;
	}

	@Override
	public boolean canPullUp() {
		if (getCount() == 0) {
			// 没有item的时候也可以上拉加载
			return true;
		} else if (getLastVisiblePosition() == (getCount() - 1)) {
			// 滑到底部了
			if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
					&& getChildAt(
							getLastVisiblePosition()
									- getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
				return true;
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			handleActionDown(ev);
			break;
		case MotionEvent.ACTION_MOVE:
			return handleActionMove(ev);
		case MotionEvent.ACTION_UP:
			handleActionUp(ev);
			break;
		}
		return super.onTouchEvent(ev);
	}

	private float mDownX;
	private float mDownY;

	/**
	 * 认为是用户滑动的最小距离
	 */
	// private int mSlop;

	private int mDownPosition;

	private OnDeleteCallback onDeleteCallback;

	/**
	 * 用来标记用户是否正在滑动中
	 */
	private boolean mSwiping;

	/**
	 * 按下的item对应的View
	 */
	private View mDownView;
	/**
	 * 滑动速度检测类
	 */
	private VelocityTracker mVelocityTracker;
	/**
	 * item的宽度
	 */
	private int mViewWidth;
	/**
	 * item的高度
	 */
	private int mViewHeight;

	/**
	 * 按下事件处理
	 * 
	 * @param ev
	 * @return
	 */
	private void handleActionDown(MotionEvent ev) {
		mDownX = ev.getX();
		mDownY = ev.getY();

		mDownPosition = pointToPosition((int) mDownX, (int) mDownY);

		if (mDownPosition == AdapterView.INVALID_POSITION) {
			return;
		}
		mDownView = getChildAt(mDownPosition - getFirstVisiblePosition());
		if (mDownView != null) {
			mViewWidth = mDownView.getWidth();
			mViewHeight = mDownView.getHeight();
		}
		// 加入速度检测
		mVelocityTracker = VelocityTracker.obtain();
		mVelocityTracker.addMovement(ev);
	}

	private PopupWindow popupWindow;

	/**
	 * 手指抬起的事件处理
	 * 
	 * @param ev
	 */
	private void handleActionUp(MotionEvent ev) {
		if (mVelocityTracker == null || mDownView == null || !mSwiping) {
			return;
		}
		//侧滑删除
		if (ev.getX() < mDownX
				&& Math.abs(ev.getX() - mDownX) > Math.abs(ev.getY() - mDownY)) {// x轴左方向滑动
//			showDeletePopup();
		}
		mSwiping = false;
	}

	private void showDeletePopup() {
		View deleteView = LayoutInflater.from(getContext()).inflate(
				R.layout.layout_popupwindow, null);
		deleteView.findViewById(R.id.popupwindow_btn).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						popupWindow.dismiss();
						if (onDeleteCallback != null) {
							onDeleteCallback.onDelete(PullableListView.this,
									mDownPosition);
						}
					}
				});
		// measure deleteView 后才能获取宽高
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		deleteView.measure(w, h);
		int height = deleteView.getMeasuredHeight();
		int width = deleteView.getMeasuredWidth();

		popupWindow = new PopupWindow(deleteView, LayoutParams.WRAP_CONTENT,
				android.view.WindowManager.LayoutParams.WRAP_CONTENT, true);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 要设置背景
		popupWindow.setAnimationStyle(R.style.AnimationPreview);// 动画
		popupWindow.showAsDropDown(mDownView, mViewWidth - width,
				-(mViewHeight - (mViewHeight - height) / 2));
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setTouchable(true);
	}

	/**
	 * 处理手指滑动的方法
	 * 
	 * @param ev
	 * @return
	 */
	private boolean handleActionMove(MotionEvent ev) {
		if (mVelocityTracker == null || mDownView == null) {
			return super.onTouchEvent(ev);
		}

		float deltaX = ev.getX() - mDownX;
		float deltaY = ev.getY() - mDownY;
		float mSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
		// X方向滑动的距离大于mSlop并且Y方向滑动的距离小于mSlop，表示可以滑动
		if (Math.abs(deltaX) > mSlop && Math.abs(deltaY) < mSlop) {
			mSwiping = true;

			// 当手指滑动item,取消item的点击事件，不然我们滑动Item也伴随着item点击事件的发生
			MotionEvent cancelEvent = MotionEvent.obtain(ev);
			cancelEvent
					.setAction(MotionEvent.ACTION_CANCEL
							| (ev.getActionIndex() << MotionEvent.ACTION_POINTER_INDEX_SHIFT));
			onTouchEvent(cancelEvent);
		}

		if (mSwiping) {
			// 手指滑动的时候,返回true，表示SwipeDismissListView自己处理onTouchEvent,其他的就交给父类来处理
			return true;
		}

		return super.onTouchEvent(ev);

	}

	public void setOnItemDeleteCallback(OnDeleteCallback onDeleteCallback) {
		this.onDeleteCallback = onDeleteCallback;
	}

	/**
	 * 删除的回调接口
	 * 
	 * @author xiaanming
	 * 
	 */
	public interface OnDeleteCallback {
		public void onDelete(AdapterView<?> parent, int deletePosition);
	}
}
