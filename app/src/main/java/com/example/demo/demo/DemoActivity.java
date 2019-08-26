package com.example.demo.demo;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;

import com.example.demo.*;
import com.example.demo.mvp.MVPBaseActivity;
import com.lxj.matisse.CaptureMode;
import com.lxj.matisse.Matisse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DemoActivity extends MVPBaseActivity<DemoContract.View, DemoPresenter> implements DemoContract.View {
    @BindView(R.id.Time)
    Button Time;
    private int REQUEST_CODE_CHOOSE = 66;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.Time)
    public void onViewClicked() {
        Matisse.from(DemoActivity.this)
                .jumpCapture(CaptureMode.Image)//只拍照片
                .forResult(REQUEST_CODE_CHOOSE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //获取拍摄的图片路径，如果是录制视频则是视频的第一帧图片路径
            String captureImagePath = Matisse.obtainCaptureImageResult(data);
            DonwloadSaveImg.donwloadImg(DemoActivity.this,captureImagePath);

        }
    }
}
