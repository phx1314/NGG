//
//  ShouyeImg
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;


public class ShouyeImg extends BaseItem {
    public MImageView mImageView;
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_shouye_img, null);
        convertView.setTag(new ShouyeImg(convertView));
        return convertView;
    }

    public ShouyeImg(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mImageView = (MImageView) contentview.findViewById(R.id.mImageView);
        mTextView = (TextView) contentview.findViewById(R.id.mTextView);


    }

    public void set(String item, int i, int count) {
        mImageView.setObj(item);
        if (i == 2 && count > 3) {
            mTextView.setText("共" + count + "张");
            mTextView.setVisibility(View.VISIBLE);
        } else {
            mTextView.setVisibility(View.GONE);
        }
    }

    public void set(String item) {
        mImageView.setObj(item);
    }


}