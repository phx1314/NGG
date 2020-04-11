//
//  GoodTopImg
//
//  Created by df on 2017-03-02 15:56:26
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;


public class GoodTopImg extends BaseItem {
    public MImageView mMImageView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_good_top_img, null);
        convertView.setTag(new GoodTopImg(convertView));
        return convertView;
    }

    public GoodTopImg(View view) {
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


    }

    public void set(String item) {
        mMImageView.setObj(item);
    }


}