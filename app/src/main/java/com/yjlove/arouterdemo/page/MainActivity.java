package com.yjlove.arouterdemo.page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yjlove.arouterdemo.ParamsConstant;
import com.yjlove.arouterdemo.R;
import com.yjlove.commonlibrary.RouterConstant;
import com.yjlove.arouterdemo.entity.Person;
import com.yjlove.arouterdemo.service.HelloService;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Autowired(name = RouterConstant.SERVICE_HELLO)
    HelloService helloService;

    static WeakReference<Activity> weakReference;

    public static WeakReference<Activity> getWeakReference() {
        return weakReference;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this); //注入
        weakReference = new WeakReference(this);

        findViewById(R.id.basic_use).setOnClickListener(MainActivity.this);
        findViewById(R.id.overall_degrade).setOnClickListener(MainActivity.this);
        findViewById(R.id.basic_use_service).setOnClickListener(MainActivity.this);
//        findViewById(R.id.pretreatment_service).setOnClickListener(MainActivity.this);
        findViewById(R.id.interceptor_test).setOnClickListener(MainActivity.this);
        findViewById(R.id.scheme_url).setOnClickListener(MainActivity.this);
        findViewById(R.id.moudle_test).setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 基本用法
            case R.id.basic_use:
                //build里是URL路径
                ARouter.getInstance().build(RouterConstant.ACTIVITY_BASICUSE)
                        .withString(ParamsConstant.MAINACTIVITY_NAME_KEY, "hello 我是ARouter")
                        .withInt(ParamsConstant.MAINACTIVITY_AGE_KEY, 18)
//                        .withSerializable("data", new Person("Tom", 19))
                        .withObject(ParamsConstant.MAINACTIVITY_PERSON_KEY, new Person("Tom", 19))
                        //转场动画,navigation必须设置第一个参数
                        .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                        //第二个参数是requestCode
                        .navigation(MainActivity.this, RouterConstant.MAINACTIVITY_REQUEST_CODE, new NavigationCallback() {

                            @Override
                            public void onFound(Postcard postcard) {
                                Log.i(TAG, "onArrival: 找到了 ");
                            }

                            @Override
                            public void onLost(Postcard postcard) {
                                Log.i(TAG, "onArrival: 找不到了 ");
                            }

                            @Override
                            public void onArrival(Postcard postcard) {
                                Log.i(TAG, "onArrival: 跳转完了 ");
                            }

                            @Override
                            public void onInterrupt(Postcard postcard) {
                                Log.i(TAG, "onArrival: 被拦截了 ");
                            }
                        });
                break;
            case R.id.overall_degrade:
                ARouter.getInstance().build("/xxx/xxx").navigation();
                break;
            case R.id.basic_use_service:
                Toast.makeText(MainActivity.this, helloService.sayHello("Service"), Toast.LENGTH_SHORT).show();
                break;
//            case R.id.pretreatment_service:
//                ARouter.getInstance().build(RouterConstant.ACTIVITY_EMPTY).navigation();
//                break;
            case R.id.interceptor_test:
                ARouter.getInstance().build(RouterConstant.ACTIVITY_EMPTY).navigation();
                break;
            case R.id.scheme_url:
                ARouter.getInstance().build(RouterConstant.ACTIVITY_WEBVIEW).navigation();
                break;
            case R.id.moudle_test:
                ARouter.getInstance().build(RouterConstant.ACTIVITY_COMPONENTS_01).navigation();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RouterConstant.MAINACTIVITY_REQUEST_CODE && resultCode == RouterConstant.MAINACTIVITY_RESULT_CODE) {
            Log.i(TAG, "onActivityResult");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (weakReference != null) {
            weakReference.clear();
        }
    }
}
