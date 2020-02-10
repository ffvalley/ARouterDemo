package com.yjlove.arouterdemo.service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yjlove.commonlibrary.RouterConstant;

@Route(path = RouterConstant.SERVICE_HELLO, name = "测试服务")
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello, " + name;
    }

    @Override
    public void init(Context context) {

    }
}
