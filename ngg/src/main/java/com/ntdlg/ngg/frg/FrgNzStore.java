//
//  FrgNzStore
//
//  Created by Administrator on 2017-04-22 15:45:58
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;

import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;

import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.dataformat.DfGongju;
import com.udows.common.proto.ApisFactory;


public class FrgNzStore extends BaseFrg {

    public MPageListView mMPageListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_nz_store);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);


    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfGongju());
        mMPageListView.setApiUpdate(ApisFactory.getApiMGoodsList().set());
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("店铺列表");
    }
}