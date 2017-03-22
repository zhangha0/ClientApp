package com.cd.wzjkj.canyi.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cd.wzjkj.canyi.R;
import com.cd.wzjkj.canyi.adapter.GoodTextAdapter;
import com.cd.wzjkj.canyi.adapter.UPImageAdaapter;
import com.cd.wzjkj.canyi.entity.Order;
import com.cd.wzjkj.canyi.view.SelfGridView;
import com.cd.wzjkj.canyi.view.SelfListView;
import com.lidroid.xutils.BitmapUtils;
import com.zhy.autolayout.AutoLayoutActivity;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by liuzheng on 2016/12/13.
 */

public class Input_CommentActivity extends AutoLayoutActivity {
    private TextView title;
    private TextView right_tv2;
    private TextView name;
    private ImageView icon;
    private ImageView[] xingxings;
    private EditText comment;
    private SelfListView listview;
    private SelfGridView gridview;
    private Order o;
    private ArrayList<Bitmap> images;
    private int num;
    private GoodTextAdapter lvadapter;
    private UPImageAdaapter gvadapter;
    private static final int CHOOSE_PICTURE = 1;
    private static final int TAKE_PICTURE = 0;
    private ContentResolver resolver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_comment_activity);
        images = new ArrayList<>();
        images.add(null);
        resolver = getContentResolver();
        getview();
        getdata();
        setview();
        setlistener();
    }

    private void getview() {
        title = (TextView) findViewById(R.id.title);
        right_tv2 = (TextView) findViewById(R.id.right_tv2);
        name = (TextView) findViewById(R.id.name);
        comment = (EditText) findViewById(R.id.intput_comment);
        icon = (ImageView) findViewById(R.id.icon);
        xingxings = new ImageView[]{(ImageView) findViewById(R.id.xinxin1), (ImageView) findViewById(R.id.xinxin2), (ImageView) findViewById(R.id.xinxin3), (ImageView) findViewById(R.id.xinxin4), (ImageView) findViewById(R.id.xinxin5)};
        listview = (SelfListView) findViewById(R.id.listview);
        gridview = (SelfGridView) findViewById(R.id.gridview);

    }

    private void getdata() {
        o = (Order) getIntent().getSerializableExtra("data");
    }

    private void setview() {
        title.setText(getResources().getString(R.string.comment));
        right_tv2.setText(getResources().getString(R.string.tijiao));
        name.setText(o.getName());
        BitmapUtils bu = new BitmapUtils(this);
        bu.display(icon, o.getIcon());
        lvadapter = new GoodTextAdapter(this, o.getCaiPings());
        gvadapter = new UPImageAdaapter(this, images);
        gridview.setAdapter(gvadapter);
        listview.setAdapter(lvadapter);
    }

    private void setlistener() {
        for (int i = 0; i < xingxings.length; i++) {
            final int j = i;
            xingxings[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int m = 0; m < xingxings.length; m++) {
                        xingxings[m].setImageResource(R.mipmap.xinxin2);
                    }
                    for (int m = 0; m <= j; m++) {
                        xingxings[m].setImageResource(R.mipmap.xinxin);
                    }
                    num = j;
                }
            });
        }
    }

    public void checked(boolean isChecked, int position) {
        o.getCaiPings().get(position).setChoosed(isChecked);
        lvadapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bm = null;
            switch (requestCode) {
                case TAKE_PICTURE:
                    bm = (Bitmap) data.getExtras().get("data");
                    break;
                case CHOOSE_PICTURE:
                    Uri photoUri = data.getData();
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getContentResolver(), photoUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
            images.add(0, bm);
            if (images.size() == 9) {
                gvadapter.flag = true;
                images.remove(8);
            } else {
                gvadapter.flag = false;
            }
            gvadapter.notifyDataSetChanged();
        }
    }


    public void showPicturePicker(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setNegativeButton("取消", null);
        builder.setItems(new String[]{"拍摄照片", "从相册中选择"},
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case TAKE_PICTURE:
                                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), TAKE_PICTURE);
                                break;
                            case CHOOSE_PICTURE:
                                if (ContextCompat.checkSelfPermission(Input_CommentActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                        != PackageManager.PERMISSION_GRANTED) {
                                    //申请WRITE_EXTERNAL_STORAGE权限
                                    ActivityCompat.requestPermissions(Input_CommentActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                            1);
                                }
                                Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);

                                getAlbum.setType("image/*");

                                startActivityForResult(getAlbum, CHOOSE_PICTURE);
                                break;
                        }
                    }
                });
        builder.create().show();
    }

}
