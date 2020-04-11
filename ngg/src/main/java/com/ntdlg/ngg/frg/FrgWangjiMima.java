//
//  FrgWangjiMima
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;


public class FrgWangjiMima extends BaseFrg {

    public EditText mEditText_yanzhenma;
    public TextView clk_mTextView_get;
    public EditText mEditText_set;
    public EditText mEditText_setagin;
    public TextView mTextView_wancheng;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wangji_mima);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_yanzhenma = (EditText) findViewById(R.id.mEditText_yanzhenma);
        clk_mTextView_get = (TextView) findViewById(R.id.clk_mTextView_get);
        mEditText_set = (EditText) findViewById(R.id.mEditText_set);
        mEditText_setagin = (EditText) findViewById(R.id.mEditText_setagin);
        mTextView_wancheng = (TextView) findViewById(R.id.mTextView_wancheng);

        clk_mTextView_get.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {

    }

    @Override
    public void onClick(android.view.View v) {

        if (R.id.clk_mTextView_get == v.getId()) {

        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("忘记密码");
    }
}