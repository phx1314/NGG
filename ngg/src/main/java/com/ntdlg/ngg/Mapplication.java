package com.ntdlg.ngg;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mdx.framework.Frame;
import com.umeng.socialize.PlatformConfig;

import simcpux.Constants;


/**
 * Created by bob on 2015/1/30.
 */
public class Mapplication extends Application {

    @Override
    public void onCreate() {
        Frame.init(getApplicationContext());
        F.init();
        super.onCreate();
    }

    {
        com.framewidget.F.ICON_SHARE = R.drawable.icon;
        com.framewidget.F.WEIXINKEY = Constants.APP_ID;
        com.framewidget.F.APPNAME = "农乖乖";
        com.framewidget.F.WEIXINID = Constants.APP_ID;
        com.framewidget.F.WEIXINSEC = Constants.WEIXAPPSECRET;
        com.framewidget.F.QQID = "1106184256";
        com.framewidget.F.QQSEC = "STzsH9eo8ZQUHYMi";
        com.framewidget.F.SINAID = "471582927";
        com.framewidget.F.SiNASEC = "537b667a0afc901b734c39eb09588332";
        PlatformConfig.setWeixin(com.framewidget.F.WEIXINID,
                com.framewidget.F.WEIXINSEC);
        // 新浪微博
        PlatformConfig.setSinaWeibo(com.framewidget.F.SINAID,
                com.framewidget.F.SiNASEC);
        PlatformConfig.setQQZone(com.framewidget.F.QQID,
                com.framewidget.F.QQSEC);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
