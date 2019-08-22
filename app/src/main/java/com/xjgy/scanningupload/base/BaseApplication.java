package com.xjgy.scanningupload.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;


/**
 * Created by wuyang on 2019/8/1.
 */

public class BaseApplication extends MultiDexApplication {
    private static BaseApplication demoApplication;
    private boolean isLoginOk = false;
    public static String username;

    static {
        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
//            return new CustomHeader(context);
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
//            //指定为经典Footer，默认是 BallPulseFooter
//            return new CustomFooter(context);
//        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initFresco();
        demoApplication = this;
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口

        //扫码集成
        ZXingLibrary.initDisplayOpinion(this);

        initFresco();
    }

    /**
     * 初始化fresco
     */
    private void initFresco() {
        Fresco.initialize(this);
    }

    public static Context getAppContext() {
        return demoApplication;
    }


}
