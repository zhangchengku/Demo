package com.example.demo.demo;

import android.content.Context;

import com.example.demo.mvp.BasePresenter;
import com.example.demo.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DemoContract {
    interface View extends BaseView {
        
    }

    interface  Presenter extends BasePresenter<View> {
        
    }
}
