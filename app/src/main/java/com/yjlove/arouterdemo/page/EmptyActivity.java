package com.yjlove.arouterdemo.page;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yjlove.arouterdemo.R;
import com.yjlove.arouterdemo.RouterConstant;

@Route(path = RouterConstant.ACITIVTY_EMPTY)
public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        String extra = getIntent().getStringExtra("extra");
        if (extra != null && !extra.isEmpty())
            Toast.makeText(this, extra, Toast.LENGTH_SHORT).show();
    }
}