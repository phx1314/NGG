//
//  FrgFujin
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFirm;
import com.udows.common.proto.MFirmList;

import static com.ntdlg.ngg.F.mSUser;


public class FrgFujin extends BaseFrg implements AMap.OnCameraChangeListener,
        AMap.OnMarkerClickListener, AMap.OnMapLoadedListener {

    public ImageView mImageView_back;
    public RadioGroup mRadioGroup;
    public RadioButton mRadioButton1;
    public RadioButton mRadioButton2;
    public LinearLayout mLinearLayout_add;
    public int type = 1;//1附近农资2//附近企业
    public com.amap.api.maps.MapView map;
    private String mlat, mlng;
    private AMap aMap;
    private static int ZOOMSIZE = 16;

    @Override
    protected void create(Bundle savedInstanceState) {
        LoadingShow = false;
        type = getActivity().getIntent().getIntExtra("type", 1);
        mlat = getActivity().getIntent().getStringExtra("mlat");
        mlng = getActivity().getIntent().getStringExtra("mlng");
        setContentView(R.layout.frg_fujin);
        initView(savedInstanceState);
        loaddata();
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.mRadioButton1:
                        aMap.clear();
                        type = 1;
                        if (mSUser.isStore != 1&& mSUser.isStore != -1) {
                            mLinearLayout_add.setVisibility(View.VISIBLE);
                        } else {
                            mLinearLayout_add.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.mRadioButton2:
                        aMap.clear();
                        type = 2;
                        mLinearLayout_add.setVisibility(View.GONE);
                        break;
                }
                if (!TextUtils.isEmpty(mlat)) {
                    LatLng marker1 = new LatLng(Double.valueOf(mlat), Double.valueOf(mlng));
                    // 设置中心点和缩放比例
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
                }
            }
        });
    }

    private void initView(Bundle savedInstanceState) {
        findVMethod(savedInstanceState);
    }

    private void findVMethod(Bundle savedInstanceState) {
        mImageView_back = (ImageView) findViewById(R.id.mImageView_back);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioButton1 = (RadioButton) findViewById(R.id.mRadioButton1);
        mRadioButton2 = (RadioButton) findViewById(R.id.mRadioButton2);
        mLinearLayout_add = (LinearLayout) findViewById(R.id.mLinearLayout_add);
        map = (com.amap.api.maps.MapView) findViewById(R.id.map);
        map.onCreate(savedInstanceState);
        mLinearLayout_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgJiaruMd1.class, TitleAct.class);
            }
        });
        mImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void loaddata() {
        if (type == 1) {
            mRadioButton1.setChecked(true);
            if (mSUser.isStore != 1 && mSUser.isStore != -1) {
                mLinearLayout_add.setVisibility(View.VISIBLE);
            } else {
                mLinearLayout_add.setVisibility(View.GONE);
            }
        } else {
            mRadioButton2.setChecked(true);
            mLinearLayout_add.setVisibility(View.GONE);
        }
        if (aMap == null) {
            aMap = map.getMap();
        }
        aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
        aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
        aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
        aMap.setOnCameraChangeListener(this);
        aMap.getUiSettings().setZoomControlsEnabled(false);
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.setOnMarkerClickListener(this);
        LatLng latLng = new LatLng(com.ntdlg.ngg.F.latitude, com.ntdlg.ngg.F.longitude);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        aMap.addMarker(markerOptions);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        map.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        if (cameraPosition.target.latitude == 0) {
            return;
        }
        if (type == 1) {
            ApisFactory.getApiMNearByJoinStore().load(getContext(), this, "MNearByJoinStore", cameraPosition.target.latitude + "",
                    cameraPosition.target.longitude + "");
        } else {
            ApisFactory.getApiMNearByStore().load(getContext(), this, "MNearByStore", cameraPosition.target.latitude + "",
                    cameraPosition.target.longitude + "");
        }
    }

    public void MNearByJoinStore(Son s) {
        MFirmList mMFirmList = (MFirmList) s.getBuild();
        for (MFirm mMFirm : mMFirmList.mini) {
            MarkerOptions mMarkerOptions = new MarkerOptions();
            mMarkerOptions
                    .position(
                            new LatLng(
                                    Double.valueOf(mMFirm.lat),
                                    Double.valueOf(mMFirm.lng)))
                    .draggable(false).title("ngg");
            mMarkerOptions.icon(BitmapDescriptorFactory
                    .fromBitmap(BitmapFactory.decodeResource(
                            getResources(), R.drawable.ic_nongzi)));
            Marker marker = aMap.addMarker(mMarkerOptions);
            marker.setObject(mMFirm);
        }
    }

    public void MNearByStore(Son s) {
        MFirmList mMFirmList = (MFirmList) s.getBuild();
        for (MFirm mMFirm : mMFirmList.mini) {
            MarkerOptions mMarkerOptions = new MarkerOptions();
            mMarkerOptions
                    .position(
                            new LatLng(
                                    Double.valueOf(mMFirm.lat),
                                    Double.valueOf(mMFirm.lng)))
                    .draggable(false).title("ngg");
            mMarkerOptions.icon(BitmapDescriptorFactory
                    .fromBitmap(BitmapFactory.decodeResource(
                            getResources(), R.drawable.ic_qiye)));
            Marker marker = aMap.addMarker(mMarkerOptions);
            marker.setObject(mMFirm);
        }
    }

    @Override
    public void onMapLoaded() {
        if (!TextUtils.isEmpty(mlat)) {
            LatLng marker1 = new LatLng(Double.valueOf(mlat), Double.valueOf(mlng));
            // 设置中心点和缩放比例
            aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
            aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (type == 1) {
            Helper.startActivity(getContext(), FrgNzDetail.class, NoTitleAct.class, "id", ((MFirm) marker.getObject()).id);
        } else {
            Helper.startActivity(getContext(), FrgQiyeDetail.class, NoTitleAct.class, "id", ((MFirm) marker.getObject()).id);
        }
        return true;
    }
}