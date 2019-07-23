package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 张成昆 on 2019-7-16.
 */

public class MyAdapter extends BaseAdapter {
    private List<String> Datas;
    private Context mContext;

    public MyAdapter(List<String> datas, Context mContext) {
        Datas = datas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return Datas.size();
    }

    @Override
    public Object getItem(int i) {
        return Datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);
        TextView textView = (TextView) view.findViewById(R.id.te);
        textView.setText(Datas.get(i));
        return view;
    }
}