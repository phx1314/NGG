//
//  FrgShenqingguanfangxinxiyuan
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.framewidget.view.CallBackOnly;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.SidaFenlei;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MOfficeInfoUser;
import com.udows.common.proto.SUser;


public class FrgShenqingguanfangxinxiyuan extends BaseFrg {

    public TextView textView2;
    public EditText mEditText_bg;
    public TextView mTextView_chanpin;
    public EditText mEditText_phone;
    public EditText mEditText_liyou;
    public TextView clk_mTextView_next;
    public EditText mEditText_name;
    public EditText mEditText_nianshu;
    public MOfficeInfoUser mMOfficeInfoUser = new MOfficeInfoUser();

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_shenqingguanfangxinxiyuan);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                Helper.startActivity(getContext(), FrgNyHqfenlei.class, TitleAct.class, "title", "农药分类");
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        textView2 = (TextView) findViewById(R.id.textView2);
        mEditText_bg = (EditText) findViewById(R.id.mEditText_bg);
        mTextView_chanpin = (TextView) findViewById(R.id.mTextView_chanpin);
        mEditText_phone = (EditText) findViewById(R.id.mEditText_phone);
        mEditText_liyou = (EditText) findViewById(R.id.mEditText_liyou);
        clk_mTextView_next = (TextView) findViewById(R.id.clk_mTextView_next);
        mEditText_name = (EditText) findViewById(R.id.mEditText_name);
        mEditText_nianshu = (EditText) findViewById(R.id.mEditText_nianshu);

        clk_mTextView_next.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_chanpin.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {

    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_next == v.getId()) {
            if (TextUtils.isEmpty(mEditText_bg.getText().toString().trim())) {
                Helper.toast("请输入工作单位", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_nianshu.getText().toString().trim())) {
                Helper.toast("请输入从业年数", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_phone.getText().toString().trim())) {
                Helper.toast("请输入QQ或者微信", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_liyou.getText().toString().trim())) {
                Helper.toast("请输入报名理由", getContext());
                return;
            }
            mMOfficeInfoUser.job = mEditText_bg.getText().toString().trim();
            mMOfficeInfoUser.qq = mEditText_phone.getText().toString().trim();
            mMOfficeInfoUser.reason = mEditText_liyou.getText().toString().trim();
            ApisFactory.getApiMOfficeUserApply().load(getContext(), this, "MOfficeInfoUser", mMOfficeInfoUser);
        } else if (R.id.mTextView_chanpin == v.getId()) {
            final View view = SidaFenlei.getView(getContext(), null);
            com.framewidget.F.showBottomDialog(getContext(), view, new CallBackOnly() {
                @Override
                public void goReturnDo(Dialog mDialog) {
                    ((SidaFenlei) view.getTag()).set(mDialog);
                }
            });
        }
    }

    public void MOfficeInfoUser(Son s) {
        Helper.toast("申请成功", getContext());
        ApisFactory.getApiSGetUserInfo().load(getContext(), this, "SGetUserInfo", F.UserId);
    }

    public void SGetUserInfo(Son s) {
        F.mSUser = (SUser) s.getBuild();
        this.finish();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("申请情报员");
    }
}