package com.ntdlg.ngg.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgHome;
import com.ntdlg.ngg.frg.FrgNzDetail;
import com.ntdlg.ngg.frg.FrgWentiDetail;
import com.ntdlg.ngg.frg.FrgZhuanjiaZhuye;

import org.json.JSONException;
import org.json.JSONObject;

public class GeTuiReceiver extends BroadcastReceiver {
    public static final String TAG = "GeTuiReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive() action=" + bundle.getInt("action"));
        switch (bundle.getInt(PushConsts.CMD_ACTION)) {
            case PushConsts.GET_MSG_DATA:

                // 获取透传数据
                // String appid = bundle.getString("appid");
                byte[] payload = bundle.getByteArray("payload");

                String taskid = bundle.getString("taskid");
                String messageid = bundle.getString("messageid");

                // smartPush第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
                boolean result = PushManager.getInstance().sendFeedbackMessage(
                        context, taskid, messageid, 90001);
                System.out.println("第三方回执接口调用" + (result ? "成功" : "失败"));

                if (payload != null) {
                    String data = new String(payload);

                    Log.d("GetuiSdkDemo", "receiver payload : " + data);

                    try {
                        JSONObject mJSONObject = new JSONObject(data);
                        Frame.HANDLES.sentAll("FrgShouye,FrgWodeXiaoxi,FrgSousuoPub", 1, null);
                        if (mJSONObject.getInt("redirectType") == 2) {//问题详情
                            showNormalPuTong(context, mJSONObject.getString("msg_android"), FrgWentiDetail.class.getName(), mJSONObject.getString("id"));
                        } else if (mJSONObject.getInt("redirectType") == 3) {//专家申请
                            showNormalPuTongNoTitleAct(context, mJSONObject.getString("msg_android"), FrgZhuanjiaZhuye.class.getName(), mJSONObject.getString("id"));
                            Frame.HANDLES.sentAll("FrgWode", 0, null);
                        } else if (mJSONObject.getInt("redirectType") == 4) {//门店申请
                            if (mJSONObject.getString("msg_android").contains("失败")) {
                                showNormal(context, mJSONObject.getString("msg_android"));
                            } else {
                                showNormalPuTongNoTitleAct(context, mJSONObject.getString("msg_android"), FrgNzDetail.class.getName(), mJSONObject.getString("id"));
                            }
                            Frame.HANDLES.sentAll("FrgWode", 0, null);
                        } else {
                            showNormal(context, mJSONObject.getString("msg_android"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                break;
            // 添加其他case
            default:
                break;
        }
    }

    public void showNormal(Context context, String content) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, IndexAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.putExtra("className", FrgHome.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher).setContentTitle("农乖乖植保服务")
                .setContentText(content).setContentIntent(contentIntent)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.defaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE;
        manager.notify(0, notification);
    }

    public void showNormalPuTong(Context context, String content, String className, String obj) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, TitleAct.class);
        intent.putExtra(IndexAct.EXTRA_CLASSNAME,
                className);
        intent.putExtra("mid",
                obj);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher).setTicker("农乖乖植保服务")
                .setContentTitle("农乖乖植保服务").setContentText(content)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent).build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        if (!TextUtils.isEmpty(F.UserId)) {
            manager.notify(0, notification);
        }
    }

    public void showNormalPuTongNoTitleAct(Context context, String content, String className, String obj) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, NoTitleAct.class);
        intent.putExtra(IndexAct.EXTRA_CLASSNAME,
                className);
        intent.putExtra("mid",
                obj);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher).setTicker("农乖乖植保服务")
                .setContentTitle("农乖乖植保服务").setContentText(content)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent).build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        if (!TextUtils.isEmpty(F.UserId)) {
            manager.notify(0, notification);
        }
    }
}
