//
//  ZacaoListErji
//
//  Created by df on 2017-03-13 10:31:18
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


public class ZacaoListErji extends BaseItem {


    public TextView mTextView;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_zacao_list_erji, null);
        convertView.setTag(new ZacaoListErji(convertView));
        return convertView;
    }

    public ZacaoListErji(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {


        mTextView = (TextView) findViewById(R.id.mTextView);
    }

    public void set(MCategory item) {
        mTextView.setText(item.name);
    }


}