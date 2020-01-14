package com.yjlove.arouterdemo.page;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yjlove.arouterdemo.R;
import com.yjlove.arouterdemo.RouterConstant;

@Route(path = RouterConstant.ACITIVTY_WEBVIEW)
public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        this.webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/scheme-index.html");
    }
}
