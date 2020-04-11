//
//  FrgChanxunJieguo
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;

import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;

import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.dataformat.DfChanxunJieguo;
import com.udows.common.proto.ApisFactory;


public class FrgChanxunJieguo extends BaseFrg {

    public MPageListView mMPageListView;
    public double type1 = 1;
    public double type2 = 1;
    public String key;

    @Override
    protected void create(Bundle savedInstanceState) {
        type1 = getActivity().getIntent().getDoubleExtra("type1", 1.0);
        type2 = getActivity().getIntent().getDoubleExtra("type2", 1.0);
        key = getActivity().getIntent().getStringExtra("key");
        setContentView(R.layout.frg_chanxun_jieguo);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);
        if (type1 == 1) {
            mMPageListView.setApiUpdate(ApisFactory.getApiMSearchPesticideRight().set(type1, type2, key));
        } else if (type1 == 2) {
            mMPageListView.setApiUpdate(ApisFactory.getApiMPesticideStandardSearchList().set(type1, type2, key));
        }
        mMPageListView.setDataFormat(new DfChanxunJieguo());
        mMPageListView.pullLoad();
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("查询结果");
    }
}