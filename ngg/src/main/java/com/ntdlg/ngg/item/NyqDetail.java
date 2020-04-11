//
//  NyqDetail
//
//  Created by df on 2017-02-10 14:00:09
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.SReply;


public class NyqDetail extends BaseItem {
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_nyq_detail, null);
        convertView.setTag(new NyqDetail(convertView));
        return convertView;
    }

    public NyqDetail(View view) {
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

    public void set(SReply item) {
        mTextView.setText(F.go2TextPYQ(item, context));
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }


}