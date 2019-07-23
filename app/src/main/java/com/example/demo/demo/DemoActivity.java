package com.example.demo.demo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demo.MainActivity;
import com.example.demo.R;
import com.example.demo.mvp.MVPBaseActivity;
import com.lxj.matisse.Matisse;
import com.lxj.matisse.MimeType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.jessyan.autosize.internal.CustomAdapt;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DemoActivity extends MVPBaseActivity<DemoContract.View, DemoPresenter> implements DemoContract.View ,CustomAdapt{
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.goback)
    TextView goback;
    private int REQUEST_CODE_CHOOSE = 66;

    @Override
    protected int getLayout() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initview() {
        title.setText("asdfadsf");
    }

    @Override
    protected void initPopWindow() {

    }

    @Override
    protected void initdate() {

    }

    @Override
    protected void listener() {
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @OnClick(R.id.goback)
    public void onViewClicked() {
        Matisse.from(DemoActivity.this)
                .jumpCapture()//直接跳拍摄，默认可以同时拍摄照片和视频
                //.jumpCapture(CaptureMode.Image)//只拍照片
                //.jumpCapture(CaptureMode.Video)//只拍视频
                .isCrop(true) //开启裁剪
                .forResult(REQUEST_CODE_CHOOSE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            //获取拍摄的图片路径，如果是录制视频则是视频的第一帧图片路径
            String captureImagePath = Matisse.obtainCaptureImageResult(data);

            //获取拍摄的视频路径
            String captureVideoPath = Matisse.obtainCaptureVideoResult(data);

            //获取裁剪结果的路径，不管是选择照片裁剪还是拍摄照片裁剪，结果都从这里取
            String cropPath = Matisse.obtainCropResult(data);

            //获取选择图片或者视频的结果路径，不开启裁剪的情况下
            Matisse.obtainSelectUriResult(data);//uri形式的路径
            Matisse.obtainSelectPathResult(data);//文件形式路径
        }
    }

    @Override
    public boolean isBaseOnWidth() {
        return false;
    }

    @Override
    public float getSizeInDp() {
        return 640;
    }
}
