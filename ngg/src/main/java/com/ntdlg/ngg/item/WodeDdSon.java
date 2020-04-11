//
//  WodeDdSon
//
//  Created by df on 2017-03-07 16:57:42
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

import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MOrderGoods;


public class WodeDdSon extends BaseItem {
    public MImageView mMImageView;
    public TextView mTextView_title;
    public TextView mTextView_sn;
    public TextView mTextView_price;
    public TextView mTextView_num;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_wode_dd_son, null);
        convertView.setTag(new WodeDdSon(convertView));
        return convertView;
    }

    public WodeDdSon(View view) {
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
        mTextView_sn = (TextView) contentview.findViewById(R.id.mTextView_sn);
        mTextView_price = (TextView) contentview.findViewById(R.id.mTextView_price);
        mTextView_num = (TextView) contentview.findViewById(R.id.mTextView_num);


    }

    public void set(MOrderGoods mMOrderGoods) {
        mMImageView.setObj(mMOrderGoods.img);
        mTextView_title.setText(mMOrderGoods.title);
        mTextView_sn.setText(mMOrderGoods.info);
        mTextView_price.setText("￥" + mMOrderGoods.oldPrice);
        mTextView_num.setText("X" + mMOrderGoods.num);
    }


}