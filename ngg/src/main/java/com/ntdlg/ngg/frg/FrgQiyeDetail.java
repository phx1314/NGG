//
//  FrgQiyeDetail
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFirm;

import java.net.URISyntaxException;


public class FrgQiyeDetail extends BaseFrg {

    public ImageView mImageView_back;
    public TextView mTextView_name;
    public MImageView mImageView_touxiang;
    public TextView mTextView_store_address;
    public TextView mTextView_phone;
    public TextView mTextView_chuanzhen;
    public TextView mTextView_code;
    public TextView mTextView_pepole;
    public LinearLayout clk_LinearLayout_next;
    public String id;
    public MFirm mMFirm;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_qiye_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mImageView_back = (ImageView) findViewById(R.id.mImageView_back);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mImageView_touxiang = (MImageView) findViewById(R.id.mImageView_touxiang);
        mTextView_store_address = (TextView) findViewById(R.id.mTextView_store_address);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mTextView_chuanzhen = (TextView) findViewById(R.id.mTextView_chuanzhen);
        mTextView_code = (TextView) findViewById(R.id.mTextView_code);
        mTextView_pepole = (TextView) findViewById(R.id.mTextView_pepole);
        clk_LinearLayout_next = (LinearLayout) findViewById(R.id.clk_LinearLayout_next);

        clk_LinearLayout_next.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgQiyeDetail.this.finish();
            }
        });
        mTextView_store_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mMFirm.lat)) {
                    if (checkApkExist(getContext(), "com.baidu.BaiduMap")) {
                        try {
                            @SuppressWarnings("deprecation")
                            Intent intent = Intent
                                    .getIntent("intent://map/direction?origin=latlng:"
                                            + F.latitude
                                            + ","
                                            + F.longitude
                                            + "|name:我的位置&destination=latlng:"
                                            + mMFirm.lat
                                            + ","
                                            + mMFirm.lng
                                            + "|name:"
                                            + ""
                                            + "&mode=drivingion=西安&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                            startActivity(intent
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); // 启动调用
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Helper.toast("您必须先安装百度地图", getContext());
                        Uri uri = Uri
                                .parse("http://p.gdown.baidu.com/41044bd4fefb0146beeab83e3022080764eaae8deef939419635733d29aab7e11ff70ecd052938d163f875f8e29df04faed9e1c956bb11b138cb12f9f3ba87e3c58f29b5df2eeb110c4fc32710c7b7822e5318964907626b66c06796288902afbaa2a93b66a8a9e4edf50123ea8eb59001a5ce15405544a4dc98241ae600462518b282897edba7d8b3d5233187449ab7f495bc3ec23f8805");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                } else {
                    Helper.toast("经纬度不存在", getContext());
                }
            }
        });
    }

    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void loaddata() {
        ApisFactory.getApiMFirmDetail().load(getContext(), this, "MFirmDetail", id);
    }

    public void MFirmDetail(Son s) {
        mMFirm = (MFirm) s.getBuild();
        if (mMFirm.user != null)
            mImageView_touxiang.setObj(mMFirm.user.headImg);
        mTextView_name.setText(mMFirm.name);
        mTextView_store_address.setText(mMFirm.address);
        mTextView_phone.setText("电话：" + mMFirm.phone);
        mTextView_chuanzhen.setText("传真：" + mMFirm.fax);
        mTextView_code.setText("邮编：" + mMFirm.zipCode);
        mTextView_pepole.setText("联系人：" + mMFirm.contact);
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_LinearLayout_next == v.getId()) {
            Helper.startActivity(getContext(), FrgWoyaobaocuo.class, TitleAct.class, "mMFirm", mMFirm);
        }
    }

}