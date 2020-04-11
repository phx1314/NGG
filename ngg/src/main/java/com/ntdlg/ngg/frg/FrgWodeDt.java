//
//  FrgWodeDt
//
//  Created by df on 2017-02-10 13:48:52
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfNyq;
import com.udows.common.proto.ApisFactory;


public class FrgWodeDt extends BaseFrg {

    public MPageListView mMPageListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wode_dt);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mMPageListView.pullLoad();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);


    }

    public void loaddata() {
        mMPageListView.setApiUpdate(ApisFactory.getApiSGetUserCircle().set(F.UserId));
        mMPageListView.setDataFormat(new DfNyq(1));
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我的动态");
        mHeadlayout.setRightBacgroud(R.drawable.bt_fbdt_n);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgFbDt.class, TitleAct.class);
            }
        });
    }
}