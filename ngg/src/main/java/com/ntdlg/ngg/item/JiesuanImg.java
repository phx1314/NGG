//
//  JiesuanImg
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

import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;


public class JiesuanImg extends BaseItem {
    public MImageView mMImageView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_jiesuan_img, null);
        convertView.setTag(new JiesuanImg(convertView));
        return convertView;
    }

    public JiesuanImg(View view) {
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
        if(!TextUtils.isEmpty(item)){
            mMImageView.setObj(item.split(",")[0]);
        }else{
            mMImageView.setObj("");

        }
    }


}