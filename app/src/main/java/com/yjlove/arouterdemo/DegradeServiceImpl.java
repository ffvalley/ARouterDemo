package com.yjlove.arouterdemo;


import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.yjlove.arouterdemo.page.MainActivity;

// 自定义全局降级策略: 实现DegradeService接口，并加上一个Path内容任意的注解即可
@Route(path = "/service/degrade")
public class DegradeServiceImpl implements DegradeService {

    private static final String TAG = "DegradeServiceImpl";

    @Override
    public void onLost(Context context, Postcard postcard) {
        Toast.makeText(MainActivity.getWeakReference().get(), "全局降级策略实现！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void init(Context context) {

    }
}
