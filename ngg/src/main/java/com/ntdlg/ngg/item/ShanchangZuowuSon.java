//
//  ShanchangZuowuSon
//
//  Created by df on 2017-02-09 15:44:30
//  Copyright (c) df All rights reserved.


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

import com.ntdlg.ngg.R;
import com.udows.common.proto.MCategory;


public class ShanchangZuowuSon extends BaseItem {
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_shanchang_zuowu_son, null);
        convertView.setTag(new ShanchangZuowuSon(convertView));
        return convertView;
    }

    public ShanchangZuowuSon(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView = (TextView) contentview.findViewById(R.id.mTextView);


    }

    public void set(MCategory item) {
        mTextView.setText(item.name);
        if (!TextUtils.isEmpty(item.icon) && item.icon.equals("1")) {
            mTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ekzx_ic_chongxuan_h, 0);
        } else {
            mTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }


}