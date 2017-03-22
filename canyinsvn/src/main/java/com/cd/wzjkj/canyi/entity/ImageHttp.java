package com.cd.wzjkj.canyi.entity;

import android.widget.ImageView;

public class ImageHttp {
	private ImageView iv;
	private String url;
	private int imageerroid;
	public ImageHttp(ImageView iv, String url, int imageerroid) {
		super();
		this.iv = iv;
		this.url = url;
		this.imageerroid = imageerroid;
	}
	public ImageView getIv() {
		return iv;
	}
	public int getImageerroid() {
		return imageerroid;
	}
	public String getUrl() {
		return url;
	}
	
	
	
}
