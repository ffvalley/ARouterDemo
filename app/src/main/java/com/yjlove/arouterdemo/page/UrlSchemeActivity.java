package com.yjlove.arouterdemo.page;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yjlove.arouterdemo.R;

public class UrlSchemeActivity extends AppCompatActivity {

    private static final String TAG = "UrlSchemeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_url);

        // 直接通过ARouter处理外部Uri
        Uri uri = getIntent().getData();
        Log.i(TAG, "onCreate: uri=" + uri);

        ARouter.getInstance().build(uri).navigation(UrlSchemeActivity.this, new NavCallback() {
            @Override
            public void onArrival(Postcard postcard) {
                finish();
            }

            @Override
            public void onFound(Postcard postcard) {
                super.onFound(postcard);
            }

            @Override
            public void onLost(Postcard postcard) {
                super.onLost(postcard);
                // Toast.makeText(UrlSchemeActivity.this,String.format("找不到可以处理该URI %s 的 Activity",uri),Toast.LENGTH_SHORT).show();
                // 找不到的时候 finish 掉当前 activity
                finish();
            }
        });
    }
}
