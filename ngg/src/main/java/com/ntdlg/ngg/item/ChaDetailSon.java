//
//  ChaDetailSon
//
//  Created by df on 2017-03-08 16:10:04
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntdlg.ngg.R;
import com.ntdlg.ngg.view.ModelFz;


public class ChaDetailSon extends BaseItem {


    public TextView mTextView_fz;
    public TextView mTextView_fdx;
    public TextView mTextView_jl;
    public TextView mTextView_ff;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_cha_detail_son, null);
        convertView.setTag(new ChaDetailSon(convertView));
        return convertView;
    }

    public ChaDetailSon(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_fz = (TextView) findViewById(R.id.mTextView_fz);
        mTextView_fdx = (TextView) findViewById(R.id.mTextView_fdx);
        mTextView_jl = (TextView) findViewById(R.id.mTextView_jl);
        mTextView_ff = (TextView) findViewById(R.id.mTextView_ff);
    }

    public void set(ModelFz item) {
        mTextView_fz.setText(item.cropName);
        mTextView_fdx.setText(item.name);
        mTextView_jl.setText(item.num);
        mTextView_ff.setText(item.type);
    }


}