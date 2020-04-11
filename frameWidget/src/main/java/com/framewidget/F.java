package com.framewidget;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.framewidget.view.CallBackOnly;
import com.framewidget.view.CallBackShareJieKou;
import com.framewidget.view.ShareDialog;
import com.framewidget.view.goReturn;
import com.mdx.framework.Frame;
import com.mdx.framework.config.BaseConfig;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class F {
    public static String WEIXINKEY = null, APPNAME = null, WEIXINID = null,
            WEIXINSEC = null, SINAID = null, SiNASEC = null, QQID = null,
            QQSEC = null;
    public static int ICON_SHARE = 0;
//    public static String ICON_SHARE_URL = "";
    public static CallBackShareJieKou mCallBackShareJieKou;
    public static int isShare = 0;
    public static String ShareId = "";

    // kfc 1
    // / 关闭软件盘
    public static void closeSoftKey(Activity act) {
        InputMethodManager localInputMethodManager = (InputMethodManager) act
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        IBinder localIBinder = act.getWindow().getDecorView().getWindowToken();
        localInputMethodManager.hideSoftInputFromWindow(localIBinder, 2);
        // InputMethodManager imm = (InputMethodManager) getActivity()
        // .getSystemService(Context.INPUT_METHOD_SERVICE);
        // imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static void saveUrlString(String url) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        sp.edit().putString("loadingUrl", url).commit();
    }

    public static boolean ExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else
            return false;
    }

    public static byte[] bitmap2ByteTrue(String picpathcrop) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        com.mdx.framework.utility.BitmapRead.decodeSampledBitmapFromFile(
                picpathcrop, 720, 0).compress(Bitmap.CompressFormat.JPEG, 100,
                out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toByteArray();
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
    public static void showImgDialog(Context context, View view,
                                     goReturn mgoReturn) {
        final Dialog mDialog = new Dialog(context, R.style.full_dialog);
        mDialog.setContentView(view);
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        // lp.width = (int) ((MActivityActionbar) context).getWindowManager()
        // .getDefaultDisplay().getWidth();// 设置宽度
        // lp.height = (int) ((MActivityActionbar) context).getWindowManager()
        // .getDefaultDisplay().getHeight(); // 高度设置为屏幕的0.6
        // lp.gravity = Gravity.CENTER;
        // mDialog.getWindow().setAttributes(lp);
        mDialog.show();
        // mDialog.setCanceledOnTouchOutside(true);
        mgoReturn.go2Object(mDialog);
    }
    // 判断date1是否在时间date2之前
    // 时间格式 2005-4-21 16:16:34
    public static boolean isDateBefore(String date1, String date2) {
        if (date2 != null && getDateByFormat(date2, "yyyy-MM-dd HH") != null) {
            return getDateByFormat(date1, "yyyy-MM-dd HH").before(
                    getDateByFormat(date2, "yyyy-MM-dd HH"))
                    || getDateByFormat(date1, "yyyy-MM-dd HH").equals(
                    getDateByFormat(date2, "yyyy-MM-dd HH"));
        } else {
            return false;
        }
    }

    public static byte[] File2byte(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
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
            F.saveUrlString("file:" + Environment.getExternalStorageDirectory()
                    + "/" + fielname + "/" + name + ".png");
        } else {
        }
    }

    public static String getUrlString() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(Frame.CONTEXT);
        return sp.getString("loadingUrl", "");
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
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        // return new String(c);
        return stringFilter(new String(c));
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]")
                .replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static byte[] bitmap2Byte(String picpathcrop) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        com.mdx.framework.utility.BitmapRead.decodeSampledBitmapFromFile(
                picpathcrop, 480, 0).compress(Bitmap.CompressFormat.JPEG, 80,
                out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    public static void showCenterDialog(Context context, View view,
                                        CallBackOnly mCallBackOnly) {
        Dialog mDialog = new Dialog(context, R.style.dialog_1);
        mDialog.setContentView(view, new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        WindowManager windowManager = ((FragmentActivity) context)
                .getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        // lp.alpha = 0.7f;
        // lp.width = (display.getWidth()); // 设置宽度
        // lp.height = (int) (display.getHeight() * 0.6); // 高度设置为屏幕的0.6
        lp.gravity = Gravity.CENTER;
        mDialog.getWindow().setAttributes(lp);
        mDialog.show();
        mDialog.setCanceledOnTouchOutside(true);
        mCallBackOnly.goReturnDo(mDialog);
    }

    public static void showCenterDialogQuanJu(Context context, View view,
                                              CallBackOnly mCallBackOnly) {
        Dialog mDialog = new Dialog(context, R.style.dialog_1);
        mDialog.setContentView(view, new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        WindowManager windowManager = ((FragmentActivity) context)
                .getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        // lp.alpha = 0.7f;
        // lp.width = (display.getWidth()); // 设置宽度
        // lp.height = (int) (display.getHeight() * 0.6); // 高度设置为屏幕的0.6
        lp.gravity = Gravity.CENTER;
        mDialog.getWindow().setAttributes(lp);
//		mDialog.getWindow().setType(WindowManager.LayoutParams.);//将弹出框设置为全局
        mDialog.show();
        mDialog.setCanceledOnTouchOutside(true);
        mCallBackOnly.goReturnDo(mDialog);
    }

    public static void showBottomDialog(Context context, View view,
                                        CallBackOnly mCallBackOnly) {
        Dialog mDialog = new Dialog(context, R.style.dialog_1);
        mDialog.setContentView(view, new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        WindowManager windowManager = ((FragmentActivity) context)
                .getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        // lp.alpha = 0.7f;
         lp.width = (display.getWidth()); // 设置宽度
        // lp.height = (int) (display.getHeight() * 0.6); // 高度设置为屏幕的0.6
        lp.gravity = Gravity.BOTTOM;
        mDialog.getWindow().setAttributes(lp);
        mDialog.show();
        mDialog.setCanceledOnTouchOutside(true);
        mCallBackOnly.goReturnDo(mDialog);
    }

    public static void showCenterDialog(Activity context, View view,
                                        CallBackOnly mCallBackOnly) {
        Dialog mDialog = new Dialog(context, R.style.dialog_1);
        mDialog.setContentView(view, new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        WindowManager windowManager = context.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
        // lp.alpha = 0.7f;
        // lp.width = (display.getWidth()); // 设置宽度
        // lp.height = (int) (display.getHeight() * 0.6); // 高度设置为屏幕的0.6
        lp.gravity = Gravity.CENTER;
        mDialog.getWindow().setAttributes(lp);
        mDialog.show();
        mDialog.setCanceledOnTouchOutside(true);
        mCallBackOnly.goReturnDo(mDialog);
    }

    /**
     * 获取分享dialog
     *
     * @param context
     */
    public static void getShare(Context context, String imageUrl, String url, String content,
                                String title) {
        ShareDialog dialog = new ShareDialog(context, R.style.dialog, imageUrl, url,
                content, title);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (display.getWidth()); // 设置宽度
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    /**
     * 获取分享dialog
     *
     * @param context
     */
    public static void getShareObj(Context context, String imageUrl, String url, String content,
                                   String title, Object obj) {
        ShareDialog dialog = new ShareDialog(context, R.style.dialog, imageUrl, url,
                content, title, obj);
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (display.getWidth()); // 设置宽度
        lp.gravity = Gravity.BOTTOM;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }

    public static String go2Wei(Double f) {
        return String.format("%.2f", f);
    }

    public static void yShoure(Context act, String title, String content,
                               DialogInterface.OnClickListener click) {
        new AlertDialog.Builder(act).setTitle(title).setMessage(content)
                .setPositiveButton("是", click).setNegativeButton("否", null)
                .show();

    }

    public String uploadFile(byte[] bytes, String fileName) throws Exception {
        InputStream is = new ByteArrayInputStream(bytes);
        String fname = null;
        try {
            fname = uploadFile(is, fileName);
        } catch (Exception e) {
            try {
                if (is != null)
                    is.close();
            } catch (Exception e2) {
            }
        }
        return fname;
    }

    public String uploadFile(InputStream is, String fileName) throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(BaseConfig.getUri() + "/fileUpload");
        InputStreamBody isb = new InputStreamBody(is, fileName);
        MultipartEntity multipartEntity = new MultipartEntity();
        multipartEntity.addPart("MyFile", isb);
        post.setEntity(multipartEntity);
        HttpResponse response = client.execute(post);
        System.out.println(response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            is = response.getEntity().getContent();
            String result = inStream2String(is);
            // Assert.assertEquals(result, "UPLOAD_SUCCESS");
            System.out.println(result);
            result = result.replace("\"", "");
            if (!result.equals("0"))
                return result.replace("\"", "");
        }
        return null;
    }

    // 将输入流转换成字符串
    private static String inStream2String(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len = is.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        return new String(baos.toByteArray());
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void setBackgroundAlpha(Activity act, float bgAlpha) {
        WindowManager.LayoutParams lp = act.getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        act.getWindow().setAttributes(lp);
    }
}
