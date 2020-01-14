package com.yjlove.arouterdemo;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class App extends Application {
    private boolean isDebugARouter = false;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebugARouter) {
            //必须在init之前
            ARouter.openLog();//打印日志
            ARouter.openDebug();//线上版本需要关闭
        }
        //官方推荐在Application中初始化
        ARouter.init(this);
    }
}
