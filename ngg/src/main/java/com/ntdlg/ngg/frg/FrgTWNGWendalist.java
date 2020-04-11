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
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfWenDaList;
import com.udows.common.proto.ApisFactory;


public class FrgTWNGWendalist extends BaseFrg {

    public com.mdx.framework.widget.MPageListView mMPageListView;
    public String key;
    public String cateCode;

    @Override
    protected void create(Bundle savedInstanceState) {
        key = getActivity().getIntent().getStringExtra("key");
        cateCode = getActivity().getIntent().getStringExtra("cateCode");
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
        mMPageListView.setApiUpdate(ApisFactory.getApiSTopicList().set(0.0, key, null, null));
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("问答");
    }
}