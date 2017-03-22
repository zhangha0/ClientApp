package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.tools.Httptools;


public class FragmentFour extends Fragment
{

	private View view;
	private TextView tv2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_mine, null);
		getview();
		getdata();
		setview();
		setListener();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		if(Httptools.user==null){
			tv2.setText(getString(R.string.logo)+"/"+getString(R.string.zhuche));
		}else{
			tv2.setText(Httptools.user.getMobile());
		}
	}

	private void getview() {
		tv2 = (TextView) view.findViewById(R.id.tv2);
	}

	private void getdata() {

	}

	private void setview() {

	}

	private void setListener() {
		view.findViewById(R.id.myrlayout3).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				MyException.sendmail2("1192566297@qq.com",getActivity());
			}
		});

		view.findViewById(R.id.to_logoorzhuche).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),ToLogoOrZhucheActivity.class);
				startActivity(intent);
			}
		});
		view.findViewById(R.id.all_order).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),OrderWaiMaiActivity.class);
				startActivity(intent);
			}
		});
		view.findViewById(R.id.mylayout1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toorder(0);
			}
		});
		view.findViewById(R.id.mylayout2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toorder(1);
			}
		});
		view.findViewById(R.id.mylayout3).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toorder(2);
			}
		});
		view.findViewById(R.id.mylayout4).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toorder(3);
			}
		});
		view.findViewById(R.id.myrlayout1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),ShouChangActivity.class);
				startActivity(intent);
			}
		});
		view.findViewById(R.id.myrlayout2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),AddressActivity.class);
				intent.putExtra("data",1);
				startActivity(intent);
			}
		});
	}

	private void toorder(int num) {
		Intent intent = new Intent(getActivity(),OrderActivity.class);
		intent.putExtra("data",num);
		startActivity(intent);
	}


}
