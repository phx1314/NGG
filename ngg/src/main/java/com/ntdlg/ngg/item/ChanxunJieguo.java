//
//  ChanxunJieguo
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

import com.ntdlg.ngg.R;
import com.udows.common.proto.MPesticideSearch;
import com.udows.common.proto.MPesticideStandardSearch;


public class ChanxunJieguo extends BaseItem {
    public TextView mTextView_tilte;
    public TextView mTextView_djh;
    public TextView mTextView_chengfen;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_chanxun_jieguo, null);
        convertView.setTag(new ChanxunJieguo(convertView));
        return convertView;
    }

    public ChanxunJieguo(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_tilte = (TextView) contentview.findViewById(R.id.mTextView_tilte);
        mTextView_djh = (TextView) contentview.findViewById(R.id.mTextView_djh);
        mTextView_chengfen = (TextView) contentview.findViewById(R.id.mTextView_chengfen);


    }

    public void set(MPesticideSearch item) {
        mTextView_tilte.setText(item.name);
        mTextView_djh.setText("登记号：" + item.code);
        mTextView_chengfen.setText("生产企业：" + (TextUtils.isEmpty(item.firmName) ? "" : item.firmName));
    }

    public void set(MPesticideStandardSearch item) {
        mTextView_tilte.setText(item.name);
        mTextView_djh.setText("登记号：" + item.code);
        mTextView_chengfen.setText("生产企业：" + (TextUtils.isEmpty(item.firmName) ? "" : item.firmName));
    }


}