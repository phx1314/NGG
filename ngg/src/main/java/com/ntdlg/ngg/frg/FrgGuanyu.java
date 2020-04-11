//
//  FrgGuanyu
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;


public class FrgGuanyu extends BaseFrg {

    public TextView mTextView_banbenhao;
    public TextView mTextView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_guanyu);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_banbenhao = (TextView) findViewById(R.id.mTextView_banbenhao);
        mTextView = (TextView) findViewById(R.id.mTextView);

        try {
            mTextView_banbenhao.setText("农乖乖v"
                    + getActivity().getPackageManager().getPackageInfo(
                    getActivity().getPackageName(), 0).versionName + "." + getActivity().getPackageManager().getPackageInfo(
                    getActivity().getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loaddata() {
        mTextView.setText("<用户协议>");
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("关于我们");
    }
}