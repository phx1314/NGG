//
//  WenZhuanjiaPo
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
import com.ntdlg.ngg.frg.FrgWenZhuanjia;
import com.udows.common.proto.MCategory;


public class WenZhuanjiaPo extends BaseItem {
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_wen_zhuanjia_po, null);
        convertView.setTag(new WenZhuanjiaPo(convertView));
        return convertView;
    }

    public WenZhuanjiaPo(View view) {
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

    public void set(MCategory item, int type) {
        if (type == 1) {
            if (!TextUtils.isEmpty(FrgWenZhuanjia.zhuangjia_name) && FrgWenZhuanjia.zhuangjia_name.equals(item.name)) {
                mTextView.setTextColor(context.getResources().getColor(R.color.A));
            } else {
                mTextView.setTextColor(context.getResources().getColor(R.color.E4));
            }
        } else {
            if (!TextUtils.isEmpty(FrgWenZhuanjia.paixu) && FrgWenZhuanjia.paixu.equals(item.name)) {
                mTextView.setTextColor(context.getResources().getColor(R.color.A));
            } else {
                mTextView.setTextColor(context.getResources().getColor(R.color.E4));
            }
        }
        mTextView.setText(item.name);
    }


}