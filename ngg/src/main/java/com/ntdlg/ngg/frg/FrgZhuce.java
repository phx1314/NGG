//
//  FrgZhuce
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;


public class FrgZhuce extends BaseFrg {

    public EditText mEditText_phone;
    public TextView mTextView_next;
    public int type = 0;

    @Override
    protected void create(Bundle savedInstanceState) {
        type = getActivity().getIntent().getIntExtra("type", 0);
        setContentView(R.layout.frg_zhuce);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                this.finish();
                Frame.HANDLES.sentAll("FrgLogin", 0, null);
                break;
            case 1:
                this.finish();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_phone = (EditText) findViewById(R.id.mEditText_phone);
        mTextView_next = (TextView) findViewById(R.id.mTextView_next);

        mTextView_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_phone.getText().toString().trim())) {
                    Helper.toast("请输入手机号", getContext());
                    return;
                }
                if (mEditText_phone.getText().toString().trim().length() != 11) {
                    Helper.toast("请输入正确手机号", getContext());
                    return;
                }
                Helper.startActivity(getContext(), FrgZhuceNext.class, TitleAct.class, "type", type, "phone", mEditText_phone.getText().toString().trim());
            }
        });
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        if (type == 0) {
            mHeadlayout.setTitle("注册");
        } else {
            mHeadlayout.setTitle("找回密码");
        }

    }
}