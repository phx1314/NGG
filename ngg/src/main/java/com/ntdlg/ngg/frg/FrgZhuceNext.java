//
//  FrgZhuceNext
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.mdx.framework.Frame;
import com.mdx.framework.commons.verify.Md5;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MAccount;
import com.udows.common.proto.MRet;
import com.udows.common.proto.SUser;


public class FrgZhuceNext extends BaseFrg {

    public EditText mEditText_yanzhenma;
    public TextView clk_mTextView_get;
    public EditText mEditText_set;
    public EditText mEditText_setagin;
    public TextView mTextView_wancheng;
    public String phone;
    public int type;
    public EditText mEditText_yqm;
    private int times = 60;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void create(Bundle savedInstanceState) {
        phone = getActivity().getIntent().getStringExtra("phone");
        type = getActivity().getIntent().getIntExtra("type", 0);
        setContentView(R.layout.frg_zhuce_next);
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
        mEditText_yqm = (EditText) findViewById(R.id.mEditText_yqm);

        clk_mTextView_get.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_wancheng.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        if (type != 0) {
            mEditText_yqm.setVisibility(View.GONE);
        }
    }

    /**
     * 获取验证码
     */
    private void getMobileMsg(String phone) {
        if (type == 0) {
            ApisFactory.getApiMGetMobileMsg().load(getActivity(),
                    FrgZhuceNext.this, "MobileMsg", phone, 1.0);
        } else {
            ApisFactory.getApiMGetMobileMsg().load(getActivity(),
                    FrgZhuceNext.this, "MobileMsg", phone, 2.0);
        }

    }

    private void doTimer() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                if (times > 0) {
                    times--;
                    clk_mTextView_get.setText("(" + times + ")重新获取验证码");
                    clk_mTextView_get.setTextColor(getContent().getResources().getColor(R.color.gray));
                    clk_mTextView_get.setClickable(false);
                    handler.postDelayed(runnable, 1000);
                } else if (times == 0) {
                    clk_mTextView_get.setClickable(true);
                    clk_mTextView_get.setText("获取验证码");
                    clk_mTextView_get.setTextColor(getContent().getResources().getColor(R.color.Ea));
                }
            }
        };
        handler.post(runnable);
    }

    public void MobileMsg(Son s) {
        MRet rent = (MRet) s.getBuild();
        times = 60;
        doTimer();
//        mEditText_yanzhenma.setText(rent.msg + "");
    }

    public void loaddata() {

    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_get == v.getId()) {
            getMobileMsg(phone);
        } else if (R.id.mTextView_wancheng == v.getId()) {
            if (TextUtils.isEmpty(mEditText_yanzhenma.getText().toString())) {
                Helper.toast("请输入获取的验证码", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_set.getText().toString())) {
                Helper.toast("请输入设置密码", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_setagin.getText().toString())) {
                Helper.toast("请输入确认密码", getContext());
                return;
            }
            if (!mEditText_set.getText().toString()
                    .equals(mEditText_setagin.getText().toString())) {
                Helper.toast("两次输入的密码不一致", getContext());
                return;
            }

            try {
                if (type == 0) {
                    ApisFactory.getApiMRegist().load(getContext(), this, "MRegist", phone, mEditText_yanzhenma.getText().toString(), Md5.md5(mEditText_set.getText().toString().trim()), "android", PushManager.getInstance().getClientid(getContext()), mEditText_yqm.getText().toString());
                } else {
                    ApisFactory.getApiMForgetPassword().load(getContext(), this, "MForgetPassword", phone, mEditText_yanzhenma.getText().toString(), Md5.md5(mEditText_set.getText().toString().trim()), "android", PushManager.getInstance().getClientid(getContext()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void MRegist(Son s) {
        MAccount mMAccount = (MAccount) s.getBuild();
        Helper.toast("注册成功", getContext());
        F.Login(mMAccount.id, mMAccount.verify);
        Frame.HANDLES.sentAll("FrgZhuce", 0, null);
        ApisFactory.getApiSGetUserInfo().load(getContext(), this, "SGetUserInfo", F.UserId);
    }

    public void SGetUserInfo(Son s) {
        F.mSUser = (SUser) s.getBuild();
        this.finish();
    }

    public void MForgetPassword(Son s) {
        MAccount mMAccount = (MAccount) s.getBuild();
        Helper.toast("密码重设成功", getContext());
        this.finish();
        Frame.HANDLES.sentAll("FrgZhuce", 1, null);
    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        if (type == 0) {
            mHeadlayout.setTitle("下一步");
        } else {
            mHeadlayout.setTitle("忘记密码");
        }

    }

}