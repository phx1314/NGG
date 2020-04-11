//
//  PublicList
//
//  Created by df on 2017-02-07 14:42:18
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
import com.udows.common.proto.MCategory;


public class PublicList extends BaseItem {
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_public_list, null);
        convertView.setTag(new PublicList(convertView));
        return convertView;
    }

    public PublicList(View view) {
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

    public void set(Object item) {
        if (item instanceof MCategory) {
            mTextView.setText(((MCategory) item).name);
        }
    }


}