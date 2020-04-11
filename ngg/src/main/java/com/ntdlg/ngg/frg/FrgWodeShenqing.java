//
//  FrgWodeShenqing
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;

import static com.ntdlg.ngg.F.mSUser;


public class FrgWodeShenqing extends BaseFrg {

    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wode_shenqing);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) findViewById(R.id.mTextView_3);


    }

    public void loaddata() {
        if (mSUser.isPro == 1) {
            mTextView_1.setText("已申请");
            mTextView_1.setTextColor(getResources().getColor(R.color.green));
        } else if (mSUser.isPro == -1) {
            mTextView_1.setText("申请中");
            mTextView_1.setTextColor(getResources().getColor(R.color.yellow));
        } else {
            mTextView_1.setText("未申请");
            mTextView_1.setTextColor(getResources().getColor(R.color.black));
        }
        if (mSUser.isStore == 1) {
            mTextView_2.setText("已申请");
            mTextView_2.setTextColor(getResources().getColor(R.color.green));
        } else if (mSUser.isStore == -1) {
            mTextView_2.setText("申请中");
            mTextView_2.setTextColor(getResources().getColor(R.color.yellow));
        } else {
            mTextView_2.setText("未申请");
            mTextView_2.setTextColor(getResources().getColor(R.color.black));
        }
        if (mSUser.isOffice == 1) {
            mTextView_3.setText("已申请");
            mTextView_3.setTextColor(getResources().getColor(R.color.green));
        } else if (mSUser.isOffice == -1) {
            mTextView_3.setText("申请中");
            mTextView_3.setTextColor(getResources().getColor(R.color.yellow));
        } else {
            mTextView_3.setText("未申请");
            mTextView_3.setTextColor(getResources().getColor(R.color.black));
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我的申请");
    }
}