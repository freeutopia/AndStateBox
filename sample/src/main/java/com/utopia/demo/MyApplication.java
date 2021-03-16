package com.utopia.demo;

import android.app.Application;

import com.utopia.demo.view.EmptyStateView;
import com.utopia.demo.view.LoadingStateView;
import com.utopia.state.StateBox;

/**
 * TODO
 *
 * @author free_
 * @version 1.0
 * @date 2021/3/16 10:21
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //预置常用的状态页面，可以加快页面加载速度
        StateBox.Config boxConfig = new StateBox.Config()
                .addStateView(EmptyStateView.class)
                //...添加自定义状态页面
                .addStateView(LoadingStateView.class, true);//默认显示的状态页面
        //应用配置
        StateBox.getDefault().setConfig(boxConfig);
    }
}
