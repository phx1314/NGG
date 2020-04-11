//
//  Banner
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

import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MFocus;


public class Banner extends BaseItem {
    public MImageView mImageView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_banner, null);
        convertView.setTag(new Banner(convertView));
        return convertView;
    }

    public Banner(View view) {
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


    }

    public void set(MFocus item) {
        mImageView.setObj(item.img);
    }
    public void set(String item) {
        mImageView.setObj(item );
    }


}