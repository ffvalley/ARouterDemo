package com.yjlove.arouterdemo.page;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yjlove.arouterdemo.ParamsConstant;
import com.yjlove.arouterdemo.R;
import com.yjlove.arouterdemo.RouterConstant;
import com.yjlove.arouterdemo.entity.Person;

@Route(path = RouterConstant.ACITIVTY_BASICUSE)
public class BasicUseActivity extends AppCompatActivity {

    private static final String TAG = "BasicUseActivity";

    @Autowired(name = ParamsConstant.MAINACTIVITY_NAME_KEY)
    String name = "无名";
    @Autowired(name = ParamsConstant.MAINACTIVITY_AGE_KEY)
    int age = 10;
    @Autowired(name = ParamsConstant.MAINACTIVITY_PERSON_KEY)
    Person person;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_use);
        ARouter.getInstance().inject(this); //注入
        textView = findViewById(R.id.textView);
        textView.setText("姓名:" + name + ",年龄:" + age + ",data:" + this.person.toString());

        String uriStr = getIntent().getStringExtra(ARouter.RAW_URI);
        Log.i(TAG, uriStr != null ? uriStr : "无原始URI");

        setResult(RouterConstant.MAINACTIVITY_RESULT_CODE);
    }
}