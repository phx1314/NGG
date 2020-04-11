//
//  FrgShenqingzhuanjiaMd4
//
//  Created by df on 2017-02-08 13:41:58
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MProApply;


public class FrgShenqingzhuanjiaMd4 extends BaseFrg {

    public ImageView imageView;
    public EditText mEditText;
    public ImageView imageView2;
    public TextView clk_mTextView_up;
    public TextView clk_mTextView_next;
    public MProApply mMProApply = new MProApply();

    @Override
    protected void create(Bundle savedInstanceState) {
        mMProApply = Helper.readBuilder(F.UserId + "mMProApply", mMProApply);
        setContentView(R.layout.frg_shenqingzhuanjia_md4);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {

        switch (type) {
            case 110:
                mMProApply = Helper.readBuilder(F.UserId + "mMProApply", mMProApply);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        imageView = (ImageView) findViewById(R.id.imageView);
        mEditText = (EditText) findViewById(R.id.mEditText);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        clk_mTextView_up = (TextView) findViewById(R.id.clk_mTextView_up);
        clk_mTextView_next = (TextView) findViewById(R.id.clk_mTextView_next);

        clk_mTextView_up.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_next.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        if (!TextUtils.isEmpty(mMProApply.info)) {
            mEditText.setText(mMProApply.info);
        }
    }

    @Override
    public void onClick(android.view.View v) {

        if (R.id.clk_mTextView_up == v.getId()) {
            this.finish();
        } else if (R.id.clk_mTextView_next == v.getId()) {
            if (TextUtils.isEmpty(mEditText.getText().toString().trim())) {
                Helper.toast("请输入个人简介", getContext());
                return;
            }
            mMProApply.info = mEditText.getText().toString().trim();
            Helper.saveBuilder(F.UserId + "mMProApply", mMProApply);
            Frame.HANDLES.sentAll("FrgShengqingZhuanjiaMd1,FrgShengqingzhuanjiaMd2,FrgShenqingzhuanjiaMd3,FrgShenqingzhuanjiaMd4", 110, null);
            ApisFactory.getApiMProApply().load(getContext(), this, "MProApply", mMProApply);
        }
    }

    public void MProApply(Son s) {
        Helper.toast("提交成功", getContext());
        Frame.HANDLES.sentAll("FrgShenqingzhuanjiaMd3,FrgWode", 0, null);
        Helper.saveBuilder(F.UserId + "mMProApply", new MProApply());
        FrgShenqingzhuanjiaMd4.this.finish();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("申请专家");
        mHeadlayout.setRText("暂时略过");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgShenqingzhuanjiaMd4.this.finish();
                Frame.HANDLES.sentAll("FrgShenqingzhuanjiaMd3", 0, null);
            }
        });
    }
}