//
//  AddZuowuRight
//
//  Created by df on 2017-03-10 13:50:16
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgAddZuowu;
import com.udows.common.proto.MCategory;


public class AddZuowuRight extends BaseItem {
    public MImageView mMImageView;
    public ImageView mImageView_dui;
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_add_zuowu_right, null);
        convertView.setTag(new AddZuowuRight(convertView));
        return convertView;
    }

    public AddZuowuRight(View view) {
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
        mImageView_dui = (ImageView) contentview.findViewById(R.id.mImageView_dui);
        mTextView = (TextView) contentview.findViewById(R.id.mTextView);


    }

    public void set(MCategory item) {
        mMImageView.setObj(item.icon);
        mMImageView.setCircle(true);
        mTextView.setText(item.name);
        mImageView_dui.setVisibility(View.GONE);
        for (MCategory mMCategory : FrgAddZuowu.mMCategorys) {
            if (item.code.equals(mMCategory.code)) {
                mImageView_dui.setVisibility(View.VISIBLE);
            }
        }
    }


}