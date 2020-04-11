//
//  ZhuanjiazhuyeTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.udows.common.proto.SUser;


public class ZhuanjiazhuyeTop extends BaseItem {
    public MImageView mMImageView_bg;
    public ImageView mImageView_back;
    public MImageView mImageView_touxiang;
    public TextView mTextView_name;
    public TextView mTextView_remark;
    public TextView mTextView_cn_count;
    public TextView mTextView_fs_count;
    public TextView mTextView_yqm;
    public TextView mTextView_age;
    public TextView mTextView_address;
    public TextView mTextView_shanchang;
    public TextView mTextView_jianjie;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_zhuanjiazhuye_top, null);
        convertView.setTag(new ZhuanjiazhuyeTop(convertView));
        return convertView;
    }

    public ZhuanjiazhuyeTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mMImageView_bg = (MImageView) contentview.findViewById(R.id.mMImageView_bg);
        mImageView_back = (ImageView) contentview.findViewById(R.id.mImageView_back);
        mImageView_touxiang = (MImageView) contentview.findViewById(R.id.mImageView_touxiang);
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mTextView_remark = (TextView) contentview.findViewById(R.id.mTextView_remark);
        mTextView_cn_count = (TextView) contentview.findViewById(R.id.mTextView_cn_count);
        mTextView_fs_count = (TextView) contentview.findViewById(R.id.mTextView_fs_count);
        mTextView_yqm = (TextView) contentview.findViewById(R.id.mTextView_yqm);
        mTextView_age = (TextView) contentview.findViewById(R.id.mTextView_age);
        mTextView_address = (TextView) contentview.findViewById(R.id.mTextView_address);
        mTextView_shanchang = (TextView) contentview.findViewById(R.id.mTextView_shanchang);
        mTextView_jianjie = (TextView) contentview.findViewById(R.id.mTextView_jianjie);

        mImageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frame.HANDLES.sentAll("FrgZhuanjiaZhuye", 1, null);
            }
        });
    }

    public void set(SUser mSUser) {
        mMImageView_bg.setObj(mSUser.circleBg);
        mImageView_touxiang.setObj(mSUser.headImg);
        mImageView_touxiang.setCircle(true);
        mTextView_name.setText(mSUser.nickName);
        mTextView_remark.setText(TextUtils.isEmpty(mSUser.proType) ? "" : mSUser.proType);
        mTextView_cn_count.setText(mSUser.caina + "");
        mTextView_fs_count.setText(mSUser.fensi + "");
        mTextView_yqm.setText("邀请码：" + (TextUtils.isEmpty(mSUser.id) ? "" : mSUser.id));
        mTextView_age.setText("年龄：" + (TextUtils.isEmpty(mSUser.age) ? "" : mSUser.age));
        mTextView_address.setText("位置：" + (TextUtils.isEmpty(mSUser.area) ? "" : mSUser.area));
        mTextView_shanchang.setText("擅长作物：" + (TextUtils.isEmpty(mSUser.crop) ? "" : mSUser.crop));
        mTextView_jianjie.setText("个人简介：" + (TextUtils.isEmpty(mSUser.info) ? "" : mSUser.info));
    }


}