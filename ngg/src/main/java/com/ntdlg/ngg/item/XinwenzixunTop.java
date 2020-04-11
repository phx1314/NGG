//
//  XinwenzixunTop
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

import com.framewidget.view.DfCirleCurr;
import com.mdx.framework.server.api.Son;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaBanner;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFocusList;


public class XinwenzixunTop extends BaseItem {
    public DfCirleCurr mDfCirleCurr;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_xinwenzixun_top, null);
        convertView.setTag(new XinwenzixunTop(convertView));
        return convertView;
    }

    public XinwenzixunTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mDfCirleCurr = (DfCirleCurr) contentview.findViewById(R.id.mDfCirleCurr);


    }

    public void set(double item) {
        ApisFactory.getApiMFocusList().load(context, this, "MFocusList", null, item);
    }

    public void MFocusList(Son s) {
        MFocusList mMFocusList = (MFocusList) s.getBuild();
        mDfCirleCurr.setAdapter(new AdaBanner(context, mMFocusList.list));
    }

}