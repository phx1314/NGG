//
//  Sousuo
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

import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgSousuo;
import com.ntdlg.ngg.frg.FrgSousuoPub;


public class Sousuo extends BaseItem {
    public TextView mRadioButton3;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_sousuo, null);
        convertView.setTag(new Sousuo(convertView));
        return convertView;
    }

    public Sousuo(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mRadioButton3 = (TextView) contentview.findViewById(R.id.mRadioButton3);
        mRadioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgSousuoPub.class, NoTitleAct.class, "type", FrgSousuo.type, "key", mRadioButton3.getText().toString().trim());
            }
        });

    }

    public void set(String item) {
        mRadioButton3.setText(item);
    }


}