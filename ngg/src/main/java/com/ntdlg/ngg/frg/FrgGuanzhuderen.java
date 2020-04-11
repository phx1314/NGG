//
//  FrgGuanzhuderen
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;

import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfSousuoZhuanjia;
import com.udows.common.proto.ApisFactory;


public class FrgGuanzhuderen extends BaseFrg {

    public MPageListView mMPageListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_guanzhuderen);
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
        mMPageListView.setDataFormat(new DfSousuoZhuanjia());
        mMPageListView.setApiUpdate(ApisFactory.getApiSMyFriends().set(2.0));
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("关注的人");
    }
}