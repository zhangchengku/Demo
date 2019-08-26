package com.example.demo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.example.demo.demo.DemoActivity;
import com.jaeger.library.StatusBarUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.XPopupCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.Time)
    TextView Time;
    @BindView(R.id.List)
    TextView Lists;
    @BindView(R.id.nice_spinner)
    TextView niceSpinner;
    @BindView(R.id.ed)
    EditText ed;


    private List<String> mOptionsItems = new ArrayList<>();
    private OptionsPickerView pvCustomOptions;
    private CustomPartShadowPopupView popupView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTransparent(MainActivity.this);
        StatusBarUtil.setTranslucent(MainActivity.this, 30);
//        StatusBarUtil.setColor(MainActivity.this, Color.TRANSPARENT);
        ButterKnife.bind(this);

        mOptionsItems.add("item1");
        mOptionsItems.add("item2");

    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @OnClick({R.id.Time, R.id.List, R.id.nice_spinner})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Time:
                startActivity(new Intent(MainActivity.this, DemoActivity.class));
//                //时间选择器
//                TimePickerView pvTime = new TimePickerView.Builder(MainActivity.this, new TimePickerView.OnTimeSelectListener() {
//                    @Override
//                    public void onTimeSelect(Date date, View v) {//选中事件回调
//                        Time.setText(getTime(date));
//                    }
//                }).setType(new boolean[]{true, true, true, false, false, false})
//                        .build();
//                pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
//
//                pvTime.show();
                break;
            case R.id.List:
                pvCustomOptions = new OptionsPickerView.Builder(MainActivity.this, new OptionsPickerView.OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int option2, int options3, View v) {
                        //返回的分别是三个级别的选中位置
                        String tx = mOptionsItems.get(options1);
                        Lists.setText(tx);
                    }
                }).setLayoutRes(R.layout.pic, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.returnData();
                                pvCustomOptions.dismiss();
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomOptions.dismiss();
                            }
                        });
                    }
                })

                        .build();
                pvCustomOptions.setPicker(mOptionsItems);
                pvCustomOptions.show();
                break;
            case R.id.nice_spinner:
                showPartShadow(niceSpinner);
                break;
        }
    }

    private void showPartShadow(final View v) {

        if (popupView != null && popupView.isShow()) return;
        popupView = (CustomPartShadowPopupView) new XPopup.Builder(MainActivity.this)
                .atView(v)
//                .dismissOnTouchOutside(false)
                .setPopupCallback(new XPopupCallback() {
                    @Override
                    public void onShow() {
                    }

                    @Override
                    public void onDismiss() {
                        popupView = null;
//                        showPartShadow(v);
                    }
                })
                .asCustom(new CustomPartShadowPopupView(MainActivity.this, new DiseaseNewSelectObjectListener() {
                    @Override
                    public void selectPosition(int position) {
                        Log.e("张成昆: ", position + "");
                    }
                }));
        popupView.show();
    }


}