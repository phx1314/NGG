////
////  ActMap
////
////  Created by Administrator on 2015-08-20 13:32:00
////  Copyright (c) Administrator All rights reserved.
//
///**
//
// */
//
//package com.ntdlg.ngg.act;
//
//import android.graphics.BitmapFactory;
//import android.graphics.Color;
//import android.location.Location;
//import android.os.Bundle;
//import android.text.Html;
//import android.text.TextUtils;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationListener;
//import com.amap.api.maps.AMap;
//import com.amap.api.maps.CameraUpdateFactory;
//import com.amap.api.maps.LocationSource;
//import com.amap.api.maps.MapView;
//import com.amap.api.maps.model.BitmapDescriptorFactory;
//import com.amap.api.maps.model.CameraPosition;
//import com.amap.api.maps.model.LatLng;
//import com.amap.api.maps.model.Marker;
//import com.amap.api.maps.model.MarkerOptions;
//import com.amap.api.services.geocoder.GeocodeResult;
//import com.amap.api.services.geocoder.GeocodeSearch;
//import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
//import com.amap.api.services.geocoder.RegeocodeResult;
//import com.mdx.framework.activity.IndexAct;
//import com.mdx.framework.server.api.Son;
//import com.mdx.framework.utility.Helper;
//import com.ntdlg.ngg.R;
//import com.udows.common.proto.ApisFactory;
//
//public class ActMap extends BaseAct implements LocationSource,
//		AMapLocationListener, AMap.OnCameraChangeListener, OnGeocodeSearchListener,
//		AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.OnMapLoadedListener,
//		OnClickListener, AMap.InfoWindowAdapter {
//	private double mlat, mlog;
//	private double ylat, ylog;
//	public MapView mMapView;
//	private AMap aMap;
//	private GeocodeSearch geocoderSearch;
//	private OnLocationChangedListener mListener;
//	private LocationManagerProxy mAMapLocationManager;
//	private ImageView mImageView_xiche;
//	private ImageView mImageView_refreash;
//	private ImageView mImageView_dingwei;
//	private ImageView mImageView_liebiao;
//	private static int ZOOMSIZE = 16;
//
//	@Override
//	public void disposeMsg(int type, Object obj) {
//		switch (type) {
//		case 0:
//			refeash();
//			break;
//		}
//	}
//
//	@Override
//	protected void create(Bundle savedInstanceState) {
//		setContentView(R.layout.act_map);
//		setSwipeBackEnable(false);
//		initView(savedInstanceState);
//		loaddata();
//	}
//
//	private void initView(Bundle savedInstanceState) {
//		mMapView = (MapView) findViewById(R.id.mMapView);
//		mImageView_xiche = (ImageView) findViewById(R.id.mImageView_xiche);
//		mImageView_refreash = (ImageView) findViewById(R.id.mImageView_refreash);
//		mImageView_dingwei = (ImageView) findViewById(R.id.mImageView_dingwei);
//		mImageView_liebiao = (ImageView) findViewById(R.id.mImageView_liebiao);
//		mMapView.onCreate(savedInstanceState); // 此方法必须重写
//		Toast.makeText(this, "正在为您定位", Toast.LENGTH_SHORT).show();
//		if (aMap == null) {
//			aMap = mMapView.getMap();
//			setUpMap();
//		}
//		geocoderSearch = new GeocodeSearch(this);
//		geocoderSearch.setOnGeocodeSearchListener(this);
//		mImageView_xiche.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Helper.startActivity(getContext(), FrgMain.class,
//						IndexAct.class);
//				ActMap.this.finish();
//			}
//		});
//		mImageView_dingwei.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				LatLng marker1 = new LatLng(mlat, mlog);
//				// 设置中心点和缩放比例
//				aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
//				aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
//			}
//		});
//		mImageView_refreash.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				refeash();
//			}
//
//		});
//		mImageView_liebiao.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//			}
//		});
//	}
//
//	private void refeash() {
//		LatLng marker1 = new LatLng(ylat, ylog);
//		// 设置中心点和缩放比例
//		aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
//		aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
//	}
//
//	private void setUpMap() {
//		aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
//		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
//		aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
//		aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
//		aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
//		aMap.setOnCameraChangeListener(this);
//		aMap.setLocationSource(this);
//		aMap.getUiSettings().setZoomControlsEnabled(false);
//		aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
//		aMap.setMyLocationEnabled(true);
//		// aMap.setMyLocationType(AMap.);
//		aMap.setOnMarkerClickListener(this);
//	}
//
//	public void loaddata() {
//
//	}
//
//	@Override
//	public View getInfoContents(Marker marker) {
//		return null;
//	}
//
//	/**
//	 * 自定义infowinfow窗口
//	 */
//	public void render(Marker marker, View view) {
//	}
//
//	/**
//	 * 监听自定义infowindow窗口的infowindow事件回调
//	 */
//	@Override
//	public View getInfoWindow(Marker marker) {
//		View infoWindow = getLayoutInflater().inflate(
//				R.layout.item_custom_info_window, null);
//		TextView mTextView = (TextView) infoWindow.findViewById(R.id.title);
//		final MMachine mMMachine = (MMachine) marker.getObject();
//		String text = "";
//		if (mMMachine.state == 1) {
//			text = "可以使用";
//			mTextView.setTextColor(Color.YELLOW);
//		} else if (mMMachine.state == 2) {
//			text = "正在维修";
//			mTextView.setTextColor(Color.GREEN);
//		} else if (mMMachine.state == 3) {
//			text = "正在使用";
//			mTextView.setTextColor(Color.RED);
//		} else if (mMMachine.state == -1) {
//			text = "维护中";
//			mTextView.setTextColor(Color.GRAY);
//		}
//		if (!TextUtils.isEmpty(mMMachine.info)) {
//			mTextView.setText(Html.fromHtml(text + "<br>" + mMMachine.info));
//		} else {
//			mTextView.setText(text);
//		}
//		mTextView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				if (mMMachine.state == 1) {
//					ApisFactory.getApiMPreMachine().load(ActMap.this,
//							ActMap.this, "MPreMachine", mMMachine.mac);
//				}
//			}
//		});
//		return infoWindow;
//	}
//
//	public void MPreMachine(Son s) {
//		Helper.toast("预约成功", this);
//		refeash();
//	}
//
//	@Override
//	public void onMapLoaded() {
//		LatLng marker1 = new LatLng(mlat, mlog);
//		// 设置中心点和缩放比例
//		aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
//		aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
//	}
//
//	@Override
//	public void onInfoWindowClick(Marker arg0) {
//
//	}
//
//	@Override
//	public boolean onMarkerClick(Marker arg0) {
//		return false;
//	}
//
//	/**
//	 * 方法必须重写
//	 */
//	@Override
//	protected void onResume() {
//		super.onResume();
//		mMapView.onResume();
//	}
//
//	/**
//	 * 方法必须重写
//	 */
//	@Override
//	protected void onPause() {
//		super.onPause();
//		mMapView.onPause();
//	}
//
//	/**
//	 * 方法必须重写
//	 */
//	@Override
//	protected void onSaveInstanceState(Bundle outState) {
//		super.onSaveInstanceState(outState);
//		mMapView.onSaveInstanceState(outState);
//	}
//
//	/**
//	 * 方法必须重写
//	 */
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//		mMapView.onDestroy();
//	}
//
//
//	@Override
//	public void onGeocodeSearched(GeocodeResult arg0, int arg1) {
//
//	}
//
//	@Override
//	public void onRegeocodeSearched(RegeocodeResult arg0, int arg1) {
//
//	}
//
//	@Override
//	public void onCameraChange(CameraPosition arg0) {
//
//	}
//
//	@Override
//	public void onCameraChangeFinish(CameraPosition cameraPosition) {
//		if ((cameraPosition.zoom) == ZOOMSIZE) {
//			// aMap.clear();
//			ApisFactory.getApiMMachineList().load(this, ActMap.this,
//					"MMachineList", cameraPosition.target.latitude + "",
//					cameraPosition.target.longitude + "");
//			ylat = cameraPosition.target.latitude;
//			ylog = cameraPosition.target.longitude;
//		}
//	}
//
//	public void MMachineList(Son s) {
//		MMachineList mMMachineList = (com.udows.common.proto.MMachineList) s
//				.getBuild();
//		for (int i = 0; i < mMMachineList.list.size(); i++) {
//			MarkerOptions mMarkerOptions = new MarkerOptions();
//			mMarkerOptions
//					.position(
//							new LatLng(
//									Double.valueOf(mMMachineList.list.get(i).lat),
//									Double.valueOf(mMMachineList.list.get(i).lng)))
//					.draggable(false).title("test");
//			if (mMMachineList.list.get(i).state == 1) {
//				mMarkerOptions.icon(BitmapDescriptorFactory
//						.fromBitmap(BitmapFactory.decodeResource(
//								getResources(), R.drawable.jqd_zhizheng1)));
//			} else if (mMMachineList.list.get(i).state == 2) {
//				mMarkerOptions.icon(BitmapDescriptorFactory
//						.fromBitmap(BitmapFactory.decodeResource(
//								getResources(), R.drawable.jqd_zhizheng4)));
//			} else if (mMMachineList.list.get(i).state == 3) {
//				mMarkerOptions.icon(BitmapDescriptorFactory
//						.fromBitmap(BitmapFactory.decodeResource(
//								getResources(), R.drawable.jqd_zhizheng2)));
//			} else if (mMMachineList.list.get(i).state == -1) {
//				mMarkerOptions.icon(BitmapDescriptorFactory
//						.fromBitmap(BitmapFactory.decodeResource(
//								getResources(), R.drawable.jqd_zhizheng3)));
//			}
//			Marker marker = aMap.addMarker(mMarkerOptions);
//			marker.setObject(mMMachineList.list.get(i));
//		}
//	}
//
//	/**
//	 * 定位结束后返回的数据
//	 */
//	@Override
//	public void onLocationChanged(AMapLocation amapLocation) {
//		if (mListener != null && amapLocation != null) {
//			if (amapLocation.getAMapException().getErrorCode() == 0) {
//				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
//				mlat = amapLocation.getLatitude();
//				mlog = amapLocation.getLongitude();
//			}
//		}
//	}
//
//	@Override
//	public void activate(OnLocationChangedListener listener) {
//		mListener = listener;
//		if (mAMapLocationManager == null) {
//			mAMapLocationManager = LocationManagerProxy.getInstance(this);
//			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
//			// 注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
//			// 在定位结束后，在合适的生命周期调用destroy()方法
//			// 其中如果间隔时间为-1，则定位只定一次
//			mAMapLocationManager.requestLocationData(
//					LocationProviderProxy.AMapNetwork, 60 * 1000, 10, this);
//		}
//	}
//
//	@Override
//	public void deactivate() {
//		mListener = null;
//		if (mAMapLocationManager != null) {
//			mAMapLocationManager.removeUpdates(this);
//			mAMapLocationManager.destroy();
//		}
//		mAMapLocationManager = null;
//	}
//
//}
