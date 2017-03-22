package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.entity.DianJia_WaiMai;


public class FragmentThree extends Fragment
{
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_main_3, null);
		Intent intent = new Intent(getActivity(),DianJai_DianCanActivity.class);
		DianJia_WaiMai sj = new DianJia_WaiMai();
		intent.putExtra("data",sj);
		startActivity(intent);
		return view;
	}

}
