//
//  SidaFenlei
//
//  Created by Administrator on 2017-04-08 12:06:18
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.ntdlg.ngg.R;


public class SidaFenlei extends BaseItem {
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    public TextView mTextView_4;
    public Dialog item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_sida_fenlei, null);
        convertView.setTag(new SidaFenlei(convertView));
        return convertView;
    }

    public SidaFenlei(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_1 = (TextView) contentview.findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) contentview.findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) contentview.findViewById(R.id.mTextView_3);
        mTextView_4 = (TextView) contentview.findViewById(R.id.mTextView_4);

        mTextView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.dismiss();
                Frame.HANDLES.sentAll("FrgShenqingguanfangxinxiyuan", 0, 1);
            }
        });
        mTextView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.dismiss();
                Frame.HANDLES.sentAll("FrgShenqingguanfangxinxiyuan", 0, 2);
            }
        });
        mTextView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.dismiss();
                Frame.HANDLES.sentAll("FrgShenqingguanfangxinxiyuan", 0, 3);
            }
        });
        mTextView_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.dismiss();
                Frame.HANDLES.sentAll("FrgShenqingguanfangxinxiyuan", 0, 4);
            }
        });
    }

    public void set(Dialog item) {
        this.item = item;
    }


}