//
//  SousuoShangpin
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
import android.widget.TextView;

import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MScGoods;


public class SousuoShangpin extends BaseItem {
    public MImageView mMImageView;
    public TextView mTextView_title;
    public TextView mTextView_remark;
    public TextView mTextView_price;
    public TextView mTextView_sn;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_sousuo_shangpin, null);
        convertView.setTag(new SousuoShangpin(convertView));
        return convertView;
    }

    public SousuoShangpin(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mTextView_remark = (TextView) contentview.findViewById(R.id.mTextView_remark);
        mTextView_price = (TextView) contentview.findViewById(R.id.mTextView_price);
        mTextView_sn = (TextView) contentview.findViewById(R.id.mTextView_sn);


    }

    public void set(MScGoods item) {
        if (!TextUtils.isEmpty(item.img)) {
            mMImageView.setObj(item.img.split(",")[0]);
        } else {
            mMImageView.setObj("");
        }
        mMImageView.setObj(item.img);
        mTextView_title.setText(item.title);
        mTextView_remark.setText(item.type + "");
        mTextView_price.setText("￥" + item.price);
        mTextView_sn.setText("规格：" + item.guige);
    }


}