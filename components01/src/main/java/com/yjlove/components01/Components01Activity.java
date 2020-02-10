package com.yjlove.components01;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yjlove.commonlibrary.RouterConstant;

@Route(path = RouterConstant.ACTIVITY_COMPONENTS_01)
public class Components01Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_components01);
    }
}
