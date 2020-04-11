//
//  FrgYijianFk
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;


public class FrgYijianFk extends BaseFrg {

    public EditText mEditText;
    public EditText mEditText_phone;
    public TextView clk_mTextView_next;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_yijian_fk);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText = (EditText) findViewById(R.id.mEditText);
        mEditText_phone = (EditText) findViewById(R.id.mEditText_phone);
        clk_mTextView_next = (TextView) findViewById(R.id.clk_mTextView_next);

        clk_mTextView_next.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_next == v.getId()) {
            if (TextUtils.isEmpty(mEditText.getText().toString().trim())) {
                Helper.toast("请输入意见", getContext());
                return;
            }
            if (mEditText.getText().toString().trim().length() < 10) {
                Helper.toast("请输入至少10个字符的意见", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_phone.getText().toString().trim())) {
                Helper.toast("请输入电话", getContext());
                return;
            }
            if (mEditText_phone.getText().toString().trim().length() != 11) {
                Helper.toast("请输入正确电话", getContext());
                return;
            }
            ApisFactory.getApiMFeedback().load(getContext(), this, "MFeedback", mEditText.getText().toString().trim(), mEditText_phone.getText().toString().trim());
        }
    }

    public void MFeedback(Son s) {
        Helper.toast("提交成功", getContext());
        this.finish();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("意见反馈");
    }
}