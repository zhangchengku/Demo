package com.example.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lxj.xpopup.impl.PartShadowPopupView;
import com.lxj.xpopup.util.XPopupUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Description:
 * Create by dance, at 2018/12/21
 */
public class CustomPartShadowPopupView extends PartShadowPopupView {
    private  Context context;
    private DiseaseNewSelectObjectListener listener;
    private ListView listview;

    public CustomPartShadowPopupView(@NonNull Context context,DiseaseNewSelectObjectListener listener) {
        super(context);
        this.context = context;
        this.listener=listener;
    }
    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_part_shadow_popup;
    }
    @Override
    protected void onCreate() {
        super.onCreate();
        listview = (ListView)findViewById(R.id.list);
        List<String> data =  new ArrayList<>();
        data.add("第一次");
        data.add("第一次");
        data.add("第一次");
        data.add("第一次");
        data.add("第一次");
        data.add("第一次");
        MyAdapter  MyAdapter =new  MyAdapter(data,context);
        listview.setAdapter(MyAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listener.selectPosition(i);
                dismiss();
            }
        });
    }
    @Override
    protected void onShow() {
        super.onShow();
        Log.e("tag","CustomPartShadowPopupView onShow");
    }

    @Override
    protected void onDismiss() {
        super.onDismiss();
        Log.e("tag","CustomPartShadowPopupView onDismiss");
    }
}
