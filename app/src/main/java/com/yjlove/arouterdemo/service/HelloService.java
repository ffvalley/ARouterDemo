package com.yjlove.arouterdemo.service;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface HelloService extends IProvider {
    String sayHello(String name);
}
