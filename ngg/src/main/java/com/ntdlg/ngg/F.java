package com.ntdlg.ngg;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.config.ApiConfig;
import com.mdx.framework.server.api.OnApiInitListener;
import com.mdx.framework.utility.Device;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.frg.FrgLogin;
import com.ntdlg.ngg.view.CallBackQiTa;
import com.ntdlg.ngg.view.TextViewURLSpan;
import com.ntdlg.ngg.view.TextViewURLSpanPyq;
import com.udows.common.proto.SReply;
import com.udows.common.proto.STopic;
import com.udows.common.proto.SUser;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class F {
    //    001@431504
    public static final String VERIFY = "VERIFY_KH";
    public static final String USERID = "USERID_KH";
    public static final String TOKEN = "TOKEN_KH";
    public static final String AREACODE = "AREACODE_KH";
    public static final String APPID = "APPID";
    public static final String STORENAME = "STORENAME";
    public static final String SHOWCOOKList = "SHOWCOOKList";
    public static String Verify = "", UserId = "", Phone = "", Password = "",
            Token = "", areaCode = "", city = "", address = "", AppId = "" ;
    public static double latitude = 0;
    public static double longitude = 0;
    public static String price = "100";
    public static String locationAddress = "";
    public static String DOWNLOAD_URL = "http://android.myapp.com/myapp/search.htm?kw=农乖乖";
    public static SUser mSUser;
    public static String
            partnerId = "2088721134931522",
            sellerId = "236457759@qq.com",
            rsaPrivate = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALCXVE1YADW5PVzXSNbbIrRK1a859lxMhpGL2wp0Wk4vCZf+GKqwWzuRw0bjXDQGAx6C5/w8QE3KQ1eHQQ+ZuQVImtobowAWOdty0Qf45/5hC4SmmQSMinxtvzb8TBhzwYC5dD5NOIUOMAKPxsEPfqhPso2yic+s4UB739gdAL5LAgMBAAECgYAKpAO7aVsR4aiV6I8GC0xLCttXZKm4pA/Tw1aeiaZLYcA1PWVlG51TuJPIuLh8lgQoGYE7DLJfPZnTM14zP3MLib59XeJ/XbzovD/BmOqvOdDmc0dizExmzjsyc82BLBPeD0artyDGna3KkVlj2dceiI+Nxcy7Cv/8kyvYxXr7SQJBAOdPZlgzI1AE3BQfgbpW5bFPTnajIJdfPaGh8+FrMUgZoriQWPoeSUVU5SMbv/g5D/Hp0o6a/dNk3J2SVhEkaWcCQQDDcLhj9M4sSn1AtjOtNRksMaj7ySe4zW8/Hc0e5otnOo+N1zC7HgKT/vKx1zP2/XvdCG8Tby+8YHo5KYhxZSF9AkEAzyleaOnL25Ouo1sgbYn8B1QW2sv4mXmUEhmg+aduKLvE73VgKwpK5wxEd7AXuzEn5apbiJqpb9VqfLkd2hZ5SwJADiQr01+1Q1MoePQ2DQRYi/AT8BQAAckrkX+QhfncgF2mYXb+mat0OE0sNl4B7o8s1TN1Bgz3gUPh1B0DOGE+6QJBAObaT18K6ZWkjW6VOrsdwBc8mWzjR6sA85N/SAnqhDisw9CChHn7ZuCmSm5pmXmv7Q+dEsYbE7vbiQIQWtKRO90=",
            rsaAlipayPublic = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClP3GJ7VqGIlRMdU5zCJ9jSnnGESuRNNW3U+A1QKtKwFxTcR+sdCjgwkQ8T+tbx9mxsWkVvh9EUqj9JScBLqumxg74p3VYfeRWcBJJE9+OqAq07ABtblY6wcmaZzqw5cVUw4shuIOV58F3L6tkPISufh+uYKUrnjuc6sthLPw+gwIDAQAB";

    public static void init() {
        ParamsManager.get("partnerId");
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        if (sp.contains(VERIFY)) {
            Verify = sp.getString(VERIFY, "");
            UserId = sp.getString(USERID, "");
            AppId = sp.getString(APPID, "");
        }
        ApiConfig.setAutoApiInitParams(new OnApiInitListener() {
            @Override
            public String[][] onApiInitListener(Object... objs) {
                return new String[][]{{"appid", F.AppId},
                        {"deviceid", Device.getId() }, {"userid", F.UserId},
                        {"areaCode", F.areaCode}, {"verify", F.Verify}};
            }
        });
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static void saveJson(String json) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString("json", json).commit();
    }

    public static void showToast2Login(Context context) {
        Frame.HANDLES.close("FraLogin");
        Helper.toast("请先登录", context);
        Helper.startActivity(context, FrgLogin.class, TitleAct.class);
    }


    public static String getJson() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        return sp.getString("json", "");
    }

    public static SpannableStringBuilder go2Text(STopic mSTopic,
                                                 SReply mSReply, Context context, CallBackQiTa mCallBackQiTa) {
        String data = "";
        data = "<a href='" + mSReply.userId + "'>" + mSReply.nickName
                + "</a>" + "  回复  " + "<a href='" + mSReply.targetId + "'>"
                + mSReply.targetName + "</a>" + "  ： " + "<a href='"
                + mSReply.userId + "'>" + mSReply.content
                + "</a>";
        CharSequence text = (Html.fromHtml(data));
        SpannableStringBuilder stylesBuilder = new SpannableStringBuilder(text);
        stylesBuilder.clearSpans(); // should clear old spans
        int ends = text.length();
        Spannable spannable = (Spannable) text;
        URLSpan[] urlspan = spannable.getSpans(0, ends, URLSpan.class);
        for (URLSpan url : urlspan) {
            TextViewURLSpan myURLSpan = new TextViewURLSpan(mSTopic,
                    url.getURL(), mSReply, context, mCallBackQiTa);
            stylesBuilder.setSpan(myURLSpan, spannable.getSpanStart(url),
                    spannable.getSpanEnd(url),
                    spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        stylesBuilder.setSpan(new ForegroundColorSpan(context.getResources()
                        .getColor(R.color.A)), 0, mSReply.nickName.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        stylesBuilder.setSpan(
//                new RelativeSizeSpan(0.7f),
//                text.toString().lastIndexOf(mSReply.time),
//                text.toString().lastIndexOf(mSReply.time)
//                        + mSReply.time.length(),
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        stylesBuilder
                .setSpan(new ForegroundColorSpan(context.getResources()
                                .getColor(R.color.A)), 4 + mSReply.nickName
                                .length(), 4 + mSReply.nickName.length()
                                + mSReply.targetName.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stylesBuilder;
    }

    public static SpannableStringBuilder go2TextPYQ(
            SReply mSReply, Context context) {
        String data = "";
        data = "<a href='" + mSReply.userId + "'>" + mSReply.nickName
                + "</a>" + "  回复  " + "<a href='" + mSReply.targetId + "'>"
                + mSReply.targetName + "</a>" + "  ： " + "<a href='"
                + mSReply.userId + "'>" + mSReply.content
                + "</a>";
        CharSequence text = (Html.fromHtml(data));
        SpannableStringBuilder stylesBuilder = new SpannableStringBuilder(text);
        stylesBuilder.clearSpans(); // should clear old spans
        int ends = text.length();
        Spannable spannable = (Spannable) text;
        URLSpan[] urlspan = spannable.getSpans(0, ends, URLSpan.class);
        for (URLSpan url : urlspan) {
            TextViewURLSpanPyq myURLSpan = new TextViewURLSpanPyq(
                    url.getURL(), mSReply, context);
            stylesBuilder.setSpan(myURLSpan, spannable.getSpanStart(url),
                    spannable.getSpanEnd(url),
                    spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        stylesBuilder.setSpan(new ForegroundColorSpan(context.getResources()
                        .getColor(R.color.A)), 0, mSReply.nickName.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stylesBuilder
                .setSpan(new ForegroundColorSpan(context.getResources()
                                .getColor(R.color.A)), 4 + mSReply.nickName
                                .length(), 4 + mSReply.nickName.length()
                                + mSReply.targetName.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stylesBuilder;
    }

    public static void saveImg(final Context context, final String fielname,
                               final String url, String name) {
        if (ExistSDCard()) {
            File destDir = new File(Environment.getExternalStorageDirectory()
                    + "/" + fielname);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            final File f = new File(Environment.getExternalStorageDirectory()
                    + "/" + fielname + "/" + name + ".png");
            new Thread() {
                @Override
                public void run() {
                    Bitmap bitmap = null;
                    try {
                        URL pictureUrl = new URL(url);
                        HttpURLConnection con = (HttpURLConnection) pictureUrl
                                .openConnection();
                        InputStream in = con.getInputStream();
                        bitmap = BitmapFactory.decodeStream(in);
                        in.close();
                        f.createNewFile();
                        FileOutputStream fOut = null;
                        fOut = new FileOutputStream(f);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                        fOut.close();
                        fOut.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
            }.start();
            SharedPreferences sp = PreferenceManager
                    .getDefaultSharedPreferences(Frame.CONTEXT);
            sp.edit()
                    .putString(
                            "guangGaoUrl",
                            "file:" + Environment.getExternalStorageDirectory()
                                    + "/" + fielname + "/" + name + ".png")
                    .commit();
        } else {
        }
    }

    /**
     * 描述：Date类型转化为String类型.
     *
     * @param date   the date
     * @param format the format
     * @return String String类型日期时间
     */
    public static String getStringByFormat(Date date, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            strDate = mSimpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 描述：获取指定日期时间的字符串,用于导出想要的格式.
     *
     * @param strDate String形式的日期时间，必须为yyyy-MM-dd HH:mm:ss格式
     * @param format  输出格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return String 转换后的String类型的日期时间
     */
    public static String getStringByFormat(String strDate, String format) {
        String mDateTime = null;
        try {
            Calendar c = new GregorianCalendar();
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(
                    "MM-dd");
            c.setTime(mSimpleDateFormat.parse(strDate));
            SimpleDateFormat mSimpleDateFormat2 = new SimpleDateFormat(format);
            mDateTime = mSimpleDateFormat2.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDateTime;
    }

    /**
     * 描述：String类型的日期时间转化为Date类型.
     *
     * @param strDate String形式的日期时间
     * @param format  格式化字符串，如："yyyy-MM-dd HH:mm:ss"
     * @return Date Date类型日期时间
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = mSimpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // 判断date1是否在时间date2之前
    // 时间格式 2005-4-21 16:16:34
    public static boolean isDateBefore(String date1, String date2,
                                       String dataformat) {
        if (date2 != null && getDateByFormat(date2, dataformat) != null) {
            return getDateByFormat(date1, dataformat).before(
                    getDateByFormat(date2, dataformat))
                    || getDateByFormat(date1, dataformat).equals(
                    getDateByFormat(date2, dataformat));
        } else {
            return false;
        }
    }

    public static void saveCity(String city) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString("city", city).commit();
    }

    public static String toPinYin(char hanzi) {
        HanyuPinyinOutputFormat hanyuPinyin = new HanyuPinyinOutputFormat();
        hanyuPinyin.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyin.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        hanyuPinyin.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
        String[] pinyinArray = null;
        try {
            // 是否在汉字范围内
            if (hanzi >= 0x4e00 && hanzi <= 0x9fa5) {
                pinyinArray = PinyinHelper.toHanyuPinyinStringArray(hanzi);
                return pinyinArray[0];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hanzi + "";
    }

    public static String getCity() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        return sp.getString("city", "");
    }

    public static void Login(String userid, String verify) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString(VERIFY, verify).putString(USERID, userid)
                .commit();
        F.UserId = userid;
        F.Verify = verify;
    }

    public static void Login(String verify) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString(VERIFY, verify).commit();
        F.Verify = verify;
    }

    public static void saveCode(String areaCode) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString(AREACODE, areaCode).commit();
        F.areaCode = areaCode;
    }


    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


    /**
     * list转数组
     */
    public static Object[] list2Array(List list) {
        return (Object[]) list.toArray(new Object[list.size()]);
    }

    /**
     * 数组转list
     */
    public static List<Object> Array2list(Object arr) {
        return Arrays.asList(arr);
    }

    // kfc 1
    // / 关闭软件盘
    public static void closeSoftKey(Activity act) {
        InputMethodManager localInputMethodManager = (InputMethodManager) act
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        IBinder localIBinder = act.getWindow().getDecorView().getWindowToken();
        localInputMethodManager.hideSoftInputFromWindow(localIBinder, 2);

    }

    public static List<String> getData() {
        List<String> datas = new ArrayList<String>();
        for (int i = 0; i < 15; i++) {
            datas.add("11111111");
        }
        return datas;
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(30);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 验证手机号+电话号码
     *
     * @param @param  mobile
     * @param @return
     * @return boolean
     * @throws
     * @author Administrator
     * @Title: isMobile
     * @Description: TODO
     */
    public static boolean isMobile(String mobile) {
        if (mobile.length() == 11) {
            return true;
        } else {
            return false;
        }

    }

    public static int getMax(int a, int b, int c) {
        int max;
        if (a > b) {
            max = b;
        } else {
            max = a;
        }

        if (max > c) {
            max = c;
        }
        return max;
    }

    public static int getMax(int a, int b) {
        int max;
        if (a > b) {
            max = b;
        } else {
            max = a;
        }
        return max;
    }

    /**
     * 清空栈
     *
     * @param
     * @return void
     * @throws
     * @author Administrator
     * @Title: close
     * @Description: TODO
     */
    public static void close() {
        Frame.HANDLES.closeAll();
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString(VERIFY, "").putString(USERID, "").commit();
        F.UserId = "";
        F.Verify = "";
    }

    public static String getVersionName(Context mContext) throws Exception {
        // 获取packagemanager的实例
        PackageManager packageManager = mContext.getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(
                mContext.getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }


    public static boolean ExistSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }


}
