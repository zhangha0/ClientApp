package com.cd.wzjkj.canyi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cd.wzjkj.canyi.R;
import com.zhy.autolayout.AutoLayoutActivity;

/**
 * Created by liuzheng on 2016/12/20.
 */
public class ToLogoOrZhucheActivity extends AutoLayoutActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_logoorzhuche);
        findViewById(R.id.logo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToLogoOrZhucheActivity.this,LogoActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.zhuche).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToLogoOrZhucheActivity.this,ZhucheActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
