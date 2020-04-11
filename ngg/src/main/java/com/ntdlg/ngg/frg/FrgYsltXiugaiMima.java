//
//  FrgYsltXiugaiMima
//
//  Created by df on 2016-02-02 10:25:31
//  Copyright (c) df All rights reserved.

/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.commons.verify.Md5;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;


public class FrgYsltXiugaiMima extends BaseFrg {

    public EditText mEditText_old;
    public EditText mEditText_new;
    public EditText mEditText_sure;
    public TextView clk_mTextView_wc;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_yslt_xiugai_mima);
        initView();
        loaddata();
    }

    private void initView() {
        mEditText_old = (EditText) findViewById(R.id.mEditText_old);
        mEditText_new = (EditText) findViewById(R.id.mEditText_new);
        mEditText_sure = (EditText) findViewById(R.id.mEditText_sure);
        clk_mTextView_wc = (TextView) findViewById(R.id.clk_mTextView_wc);

        clk_mTextView_wc.setOnClickListener(this);

    }

    public void loaddata() {

    }

    @Override
    public void onClick(android.view.View v) {
        if (v.getId() == R.id.clk_mTextView_wc) {
            if (mEditText_old.getText().toString().trim().equals("")) {
                Helper.toast("请输入旧密码", getContext());
            } else if (mEditText_new.getText().toString().trim().equals("")) {
                Helper.toast("请输入新密码", getContext());
            } else if (mEditText_sure.getText().toString().trim().equals("")) {
                Helper.toast("请再一次输入新密码", getContext());
            } else if (!mEditText_sure.getText().toString().trim()
                    .equals(mEditText_new.getText().toString().trim())) {
                Helper.toast("两次新密码输入不一致", getContext());
            } else {
                try {
                    ApisFactory.getApiMChangePassword().load(getContext(),
                            FrgYsltXiugaiMima.this, "MChangePassword",
                            Md5.md5(mEditText_old.getText().toString().trim()),
                            Md5.md5(mEditText_new.getText().toString().trim()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void MChangePassword(Son s) {
        Helper.toast("修改成功", getContext());
        FrgYsltXiugaiMima.this.finish();
        F.Login("", "");
        Frame.HANDLES.sentAll("FrgWode", 0, null);
        Helper.startActivity(getContext(), FrgLogin.class,
                TitleAct.class);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("修改密码");
    }
}