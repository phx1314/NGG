//
//  NzHqTop
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
import android.widget.TextView;

import com.framewidget.view.FixGridLayout;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgNzHqSousuo;


public class NzHqTop extends BaseItem {
    public MImageView mImageView_top;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    public TextView mTextView_4;
    public TextView mTextView_5;
    public FixGridLayout mFixGridLayout;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_nz_hq_top, null);
        convertView.setTag(new NzHqTop(convertView));
        return convertView;
    }

    public NzHqTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mImageView_top = (MImageView) contentview.findViewById(R.id.mImageView_top);
        mTextView_1 = (TextView) contentview.findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) contentview.findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) contentview.findViewById(R.id.mTextView_3);
        mTextView_4 = (TextView) contentview.findViewById(R.id.mTextView_4);
        mTextView_5 = (TextView) contentview.findViewById(R.id.mTextView_5);
        mFixGridLayout = (FixGridLayout) contentview.findViewById(R.id.mFixGridLayout);

        mTextView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgNzHqSousuo.class, NoTitleAct.class, "type", 1);
            }
        });
        mTextView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgNzHqSousuo.class, NoTitleAct.class, "type", 2);
            }
        });
        mTextView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgNzHqSousuo.class, NoTitleAct.class, "type", 3);
            }
        });
        mTextView_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgNzHqSousuo.class, NoTitleAct.class, "type", 4);
            }
        });
    }

    public void set(String item) {

    }


}