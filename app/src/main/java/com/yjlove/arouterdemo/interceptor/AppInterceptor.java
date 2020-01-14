package com.yjlove.arouterdemo.interceptor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.yjlove.arouterdemo.MainLooper;
import com.yjlove.arouterdemo.RouterConstant;
import com.yjlove.arouterdemo.page.MainActivity;

@Interceptor(priority = 7, name = "测试用拦截器")
public class AppInterceptor implements IInterceptor {

    private static final String TAG = "AppInterceptor";
    private static final String INTERCEPTOR = RouterConstant.ACITIVTY_EMPTY;
    Context mContext;

    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        String path = postcard.getPath();
        Log.i(TAG, "process: path=" + path);

        if (INTERCEPTOR.equals(postcard.getPath())) {
            Activity context = MainActivity.getWeakReference().get();
            if (context == null) {
                return;
            }
            final AlertDialog.Builder ab = new AlertDialog.Builder(context);
            ab.setCancelable(false);
            ab.setTitle("温馨提醒");
            ab.setMessage(String.format("想要跳转到" + RouterConstant.ACITIVTY_EMPTY + "么？(触发了%s拦截器，拦截了本次跳转)", INTERCEPTOR));
            ab.setNegativeButton("继续", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onContinue(postcard);
                }
            });
            ab.setNeutralButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onInterrupt(null);
                }
            });
            ab.setPositiveButton("加点料", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    postcard.withString("extra", "我是在拦截器中附加的参数");
                    callback.onContinue(postcard);
                }
            });

            MainLooper.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ab.create().show();
                }
            });
        } else {
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {
        mContext = context;
        Log.i(TAG, AppInterceptor.class.getName() + " has init.");
    }
}
