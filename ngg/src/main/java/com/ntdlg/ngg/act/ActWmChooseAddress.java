package com.ntdlg.ngg.act;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.MyLocationStyle;
import com.framewidget.view.Pois;
import com.mdx.framework.Frame;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaLocationAddress;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ActWmChooseAddress extends Activity implements LocationSource,
        AMapLocationListener, OnCameraChangeListener {
    private MapView mapView;
    public ListView lv_address;
    public EditText mEditText_search;
    public ImageButton mImageButton_serach;
    public ImageView mImageView_back;
    private AMap aMap;
    private double lat, lng;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    Pois pois = null;
    private ProgressDialog mProgressDialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            List<Pois> data = null;
            switch (msg.what) {
                case 0:
                    data = getJson(msg.obj.toString(), msg.what);
                    break;
                case 1:
                    data = getJson(msg.obj.toString(), msg.what);
                    break;
            }
            mProgressDialog.dismiss();
            lv_address.setAdapter(new AdaLocationAddress(
                    ActWmChooseAddress.this, data));
        }
    };
    private String from;

    @SuppressLint("NewApi")
    public void peiZhi() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork() // 这里可以替换为detectAll()
                .penaltyLog() // 打印logcat，当然也可以定位到dropbox，通过文件保存相应的log
                .build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
                .penaltyLog() // 打印logcat
                .penaltyDeath().build());
    }


    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpmap();
        }
    }

    private void setUpmap() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // myLocationStyle.myLocationIcon(BitmapDescriptorFactory
        // .fromResource(R.drawable.bg_lan));
        myLocationStyle.strokeColor(Color.TRANSPARENT);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(0.1f);// 设置圆形的边框粗细
        aMap.moveCamera(CameraUpdateFactory.zoomTo(18));
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setMyLocationRotateAngle(180);
        aMap.setOnCameraChangeListener(this);
        aMap.setLocationSource(this);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);
        // aMap.setMyLocationType(AMap.MAP_TYPE_NORMAL);

    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        peiZhi();
        from = getIntent().getStringExtra("from");
        setContentView(R.layout.act_wm_choose_address);
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("请稍后...");
        mapView = (MapView) findViewById(R.id.map);
        mImageView_back = (ImageView) findViewById(R.id.mImageView_back);
        lv_address = (ListView) findViewById(R.id.lv_address);
        mEditText_search = (EditText) findViewById(R.id.mEditText_search);
        mImageButton_serach = (ImageButton) findViewById(R.id.mImageButton_serach);
        mapView.onCreate(bundle);
        init();
        mImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_address.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Pois poi = (Pois) lv_address.getAdapter().getItem(arg2);
//                if (!TextUtils.isEmpty(from)) {
                Frame.HANDLES.sentAll(from, 3, poi);
//                } else {
//                    Frame.HANDLES.sentAll("FrgYsltCreateHuodong", 3, poi);
//                }
                finish();
            }
        });
        mImageButton_serach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mEditText_search.getText().toString().trim().equals("")) {
                    loadLocationData(lat, lng);
                } else {
                    loadKeyData(mEditText_search.getText().toString().trim());
                }
                F.closeSoftKey(ActWmChooseAddress.this);
            }
        });
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 定位结束后返回的数据
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {

        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            // 设置定位监听
            mlocationClient.setLocationListener(this);
            // 设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            // 设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onCameraChange(CameraPosition arg0) {

    }

    /**
     * 地图移动结束后停留的点
     */
    @Override
    public void onCameraChangeFinish(final CameraPosition cameraPosition) {
        lat = cameraPosition.target.latitude;
        lng = cameraPosition.target.longitude;
        loadLocationData(cameraPosition.target.latitude,
                cameraPosition.target.longitude);
    }

    public void loadLocationData(final double lat, final double lng) {
        mProgressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message mMessage = new Message();
                    mMessage.obj = getRequest("http://apis.map.qq.com/ws/geocoder/v1/?location="
                            + lat
                            + ","
                            + lng
                            + "&key=ND5BZ-PF3AR-QGBWU-WWQG6-SJDR6-VFFI6"
                            + "&get_poi=1");
                    mMessage.what = 0;
                    mHandler.sendMessage(mMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void loadKeyData(final String key) {
        mProgressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Message mMessage = new Message();
                    mMessage.obj = getRequest("http://apis.map.qq.com/ws/place/v1/search?boundary=region("
                            + F.city
                            + ",0)&keyword="
                            + key
                            + "&key=ND5BZ-PF3AR-QGBWU-WWQG6-SJDR6-VFFI6");
                    mMessage.what = 1;
                    mHandler.sendMessage(mMessage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private List<Pois> getJson(String data, int type) {
        switch (type) {
            case 0:
                try {
                    List<Pois> arrayList = new ArrayList<Pois>();
                    JSONObject response = new JSONObject(data);
                    String json = response.toString();
                    if (!TextUtils.isEmpty(json)) {
                        JSONObject jObject = null;
                        jObject = new JSONObject(json);
                        String result = jObject.getString("result");
                        if ("0".equals(jObject.getString("status"))) {
                            JSONObject jo = new JSONObject(result);
                            String pois2 = jo.getString("pois");
                            JSONArray joArray = null;
                            joArray = new JSONArray(pois2);
                            for (int i = 0; i < joArray.length(); i++) {
                                Pois pois = new Pois();
                                jObject = joArray.getJSONObject(i);
                                pois.setId(jObject.getString("id"));
                                pois.setTitle(jObject.getString("title"));
                                pois.setAddress(jObject.getString("address"));
                                pois.setCategory(jObject.getString("category"));
                                pois.setLat(jObject.getJSONObject("location")
                                        .getString("lat"));
                                pois.setLng(jObject.getJSONObject("location")
                                        .getString("lng"));
                                pois.set_distance(jObject.getString("_distance"));
                                arrayList.add(pois);
                            }
                        }
                        return arrayList;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                try {
                    List<Pois> arrayList = new ArrayList<Pois>();
                    JSONObject response = new JSONObject(data);
                    String json = response.toString();
                    if (!TextUtils.isEmpty(json)) {
                        JSONObject jObject = null;
                        jObject = new JSONObject(json);
                        if ("0".equals(jObject.getString("status"))) {
                            JSONArray joArray = jObject.getJSONArray("data");
                            for (int i = 0; i < joArray.length(); i++) {
                                Pois pois = new Pois();
                                jObject = joArray.getJSONObject(i);
                                pois.setId(jObject.getString("id"));
                                pois.setTitle(jObject.getString("title"));
                                pois.setAddress(jObject.getString("address"));
                                pois.setCategory(jObject.getString("category"));
                                pois.setLat(jObject.getJSONObject("location")
                                        .getString("lat"));
                                pois.setLng(jObject.getJSONObject("location")
                                        .getString("lng"));
                                // pois.set_distance(jObject.getString("_distance"));
                                arrayList.add(pois);
                            }
                        }
                        return arrayList;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

        return null;
    }

    /**
     * get请求
     *
     * @param url
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public static String getRequest(final String url)
            throws InterruptedException, ExecutionException {
        FutureTask<String> task = new FutureTask<String>(
                new Callable<String>() {
                    @SuppressWarnings("deprecation")
                    @Override
                    public String call() throws Exception {
                        // 创建HttpGet对象
                        @SuppressWarnings("deprecation")
                        HttpGet get = new HttpGet(url);
                        // 获取HttpClient对象
                        @SuppressWarnings("deprecation")
                        HttpClient httpClient = new DefaultHttpClient();
                        // 发送get请求
                        @SuppressWarnings("deprecation")
                        HttpResponse httpResponse = httpClient.execute(get);
                        // 如果服务器成功返回响应
                        if (httpResponse.getStatusLine().getStatusCode() == 200) {
                            // 获取服务器响应的字符串
                            return EntityUtils.toString(httpResponse
                                    .getEntity());
                        }
                        return null;
                    }
                });
        new Thread(task).start();
        return task.get();
    }
}
