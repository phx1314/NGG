//
//  FrgWendalistAll
//
//  Created by df on 2017-03-01 10:04:55
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;

import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfWenDaList;
import com.udows.common.proto.ApisFactory;


public class FrgWendalistAll extends BaseFrg {

    public com.mdx.framework.widget.MPageListView mMPageListView;
    public double type = 1.0;

    @Override
    protected void create(Bundle savedInstanceState) {
        type = getActivity().getIntent().getDoubleExtra("type", 1.0);
        setContentView(R.layout.frg_wendalist_all);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (com.mdx.framework.widget.MPageListView) findViewById(R.id.mMPageListView);


    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfWenDaList());
        mMPageListView.setApiUpdate(ApisFactory.getApiSTopicList().set(type, null, F.latitude+"", F.longitude+""));
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        if (type == 1) {
            mHeadlayout.setTitle("所有问题");
        } else if (type == 2) {
            mHeadlayout.setTitle("精华问题");
        } else if (type == 3) {
            mHeadlayout.setTitle("热门问题");
        } else if (type == 4) {
            mHeadlayout.setTitle("附近问题");
        } else if (type == 5) {
            mHeadlayout.setTitle("推荐问题");
        } else if (type == 6) {
            mHeadlayout.setTitle("待解决问题");
        }

    }
}