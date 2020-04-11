//
//  SousuoJishu
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
import com.udows.common.proto.MCropTech;


public class SousuoJishu extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_num;
    public TextView mTextView_remark;
    public MImageView mMImageView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_sousuo_jishu, null);
        convertView.setTag(new SousuoJishu(convertView));
        return convertView;
    }

    public SousuoJishu(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mTextView_num = (TextView) contentview.findViewById(R.id.mTextView_num);
        mTextView_remark = (TextView) contentview.findViewById(R.id.mTextView_remark);
        mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);


    }

    public void set(MCropTech item) {
        if (!TextUtils.isEmpty(item.imgs)) {
            mMImageView.setObj(item.imgs.split(",")[0]);
        } else {
            mMImageView.setObj("");
        }
        mTextView_title.setText(item.title);
        mTextView_num.setText(item.viewCnt + "");
        mTextView_remark.setText(item.fangZhi);
    }


}