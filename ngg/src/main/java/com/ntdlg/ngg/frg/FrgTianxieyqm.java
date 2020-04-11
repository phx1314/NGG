//
//  FrgTianxieyqm
//
//  Created by Administrator on 2017-04-16 16:21:55
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.mdx.framework.Frame;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;


public class FrgTianxieyqm extends BaseFrg {

    public EditText mEditText_content;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_tianxieyqm);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_content = (EditText) findViewById(R.id.mEditText_content);


    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("填写邀请码");
        mHeadlayout.setRText("保存");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_content.getText().toString().trim())) {
                    Helper.toast("请填写邀请码", getContext());
                    return;
                }
                ApisFactory.getApiMUserSetInviteCode().load(getContext(), FrgTianxieyqm.this, "MUserSetInviteCode", mEditText_content.getText().toString().trim());
            }
        });
    }

    public void MUserSetInviteCode(Son s) {
        Frame.HANDLES.sentAll("FrgWode", 0, null);
        this.finish();
        Helper.toast("保存成功", getContext());
    }

}