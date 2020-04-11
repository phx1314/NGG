//
//  FrgShangchengDuihuanjilu
//
//  Created by DELL on 2017-04-01 15:42:36
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.dataformat.DfShangchengDuihuanjilu;
import com.udows.common.proto.ApisFactory;


public class FrgShangchengDuihuanjilu extends BaseFrg {

    public TextView mTextView_num;
    public TextView mTextView_jil;
    public MPageListView mMPageListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_shangcheng_duihuanjilu);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_num = (TextView) findViewById(R.id.mTextView_num);
        mTextView_jil = (TextView) findViewById(R.id.mTextView_jil);
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);


    }

    public void loaddata() {
        mMPageListView.setDataFormat(new DfShangchengDuihuanjilu());
        mMPageListView.setApiUpdate(ApisFactory.getApiMCreditExchangeLogList().set());
        mMPageListView.pullLoad();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("兑换记录");
    }
}