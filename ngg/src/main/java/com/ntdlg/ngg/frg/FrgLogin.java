//
//  FrgLogin
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.commons.verify.Md5;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MAccount;
import com.udows.common.proto.SUser;
import com.udows.common.proto.apis.ApiMOauthLogin;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;


public class FrgLogin extends BaseFrg {

    public EditText mEditText_phone;
    public EditText mEditText_password;
    public TextView clk_mTextView_wangji;
    public TextView mTextView_dl;
    public TextView clk_mTextView_qq;
    public TextView clk_mTextView_wx;
    public TextView clk_mTextView_zhuce;
    private String opendid = "", token = "";

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_login);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                Frame.HANDLES.sentAll("FrgWode", 0, null);
                this.finish();
                break;
            case 1:
                Frame.HANDLES.sentAll("FrgWode", 0, null);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_phone = (EditText) findViewById(R.id.mEditText_phone);
        mEditText_password = (EditText) findViewById(R.id.mEditText_password);
        clk_mTextView_wangji = (TextView) findViewById(R.id.clk_mTextView_wangji);
        mTextView_dl = (TextView) findViewById(R.id.mTextView_dl);
        clk_mTextView_qq = (TextView) findViewById(R.id.clk_mTextView_qq);
        clk_mTextView_wx = (TextView) findViewById(R.id.clk_mTextView_wx);
        clk_mTextView_zhuce = (TextView) findViewById(R.id.clk_mTextView_zhuce);

        clk_mTextView_wangji.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_qq.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_wx.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_zhuce.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_dl.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {

    }

    /**
     * 第三方登�?--qq
     */
    public void loginWithQQ() {
        UMShareAPI.get(getActivity()).doOauthVerify(getActivity(),
                SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onError(SHARE_MEDIA arg0, int arg1,
                                        Throwable arg2) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA arg0, int arg1,
                                           Map<String, String> data) {
                        opendid = data.get("openid");
                        token = data.get("access_token");// outInfoe
                        System.out.println(">>>>>>>>"
                                + data.get("access_token") + "    "
                                + data.get("openid"));
                        ApiMOauthLogin mOauthLogin = ApisFactory
                                .getApiMOauthLogin();
                        mOauthLogin.load(getContext(), FrgLogin.this,
                                "mOauthLogin", "1", opendid, token, "android",
                                PushManager.getInstance().getClientid(getContext()));
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA arg0, int arg1) {

                    }
                });

    }

    public void mOauthLogin(Son s) {
        MAccount mMAccount = (MAccount) s.getBuild();
        F.Login(mMAccount.id, mMAccount.verify);
        Helper.toast("登录成功", getContext());
        Frame.HANDLES.sentAll("FrgWode", 0, null);
        Frame.HANDLES.close("FrgZhuce");
        ApisFactory.getApiSGetUserInfo().load(getContext(), this, "SGetUserInfo", F.UserId);
    }

    public void SGetUserInfo(Son s) {
        F.mSUser = (SUser) s.getBuild();
        FrgLogin.this.finish();
    }

    public void MLogin(Son s) {
        MAccount mMAccount = (MAccount) s.getBuild();
        F.Login(mMAccount.id, mMAccount.verify);
        Helper.toast("登录成功", getContext());
        Frame.HANDLES.close("FrgZhuce");
        Frame.HANDLES.sentAll("FrgWode", 0, null);
        Frame.HANDLES.sentAll("FrgShouye,FrgWodeXiaoxi,FrgSousuoPub", 1, null);
        ApisFactory.getApiSGetUserInfo().load(getContext(), this, "SGetUserInfo", F.UserId);
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_wangji == v.getId()) {
            Helper.startActivity(getContext(), FrgZhuce.class, TitleAct.class, "type", 1);
        } else if (R.id.clk_mTextView_qq == v.getId()) {
            loginWithQQ();
        } else if (R.id.clk_mTextView_wx == v.getId()) {
            UMShareAPI.get(getActivity()).doOauthVerify(getActivity(),
                    SHARE_MEDIA.WEIXIN, new UMAuthListener() {
                        @Override
                        public void onError(SHARE_MEDIA arg0, int arg1,
                                            Throwable arg2) {

                        }

                        @Override
                        public void onComplete(SHARE_MEDIA arg0, int arg1,
                                               Map<String, String> data) {
                            opendid = data.get("openid");
                            token = data.get("access_token");// outInfoe
                            System.out.println(">>>>>>>>"
                                    + data.get("access_token") + "    "
                                    + data.get("openid"));
                            ApiMOauthLogin mOauthLogin = ApisFactory
                                    .getApiMOauthLogin();
                            mOauthLogin.load(getContext(), FrgLogin.this,
                                    "mOauthLogin", "3", opendid, token,
                                    "android", PushManager.getInstance().getClientid(getContext()));
                        }

                        @Override
                        public void onCancel(SHARE_MEDIA arg0, int arg1) {

                        }
                    });
        } else if (R.id.clk_mTextView_zhuce == v.getId()) {
            Helper.startActivity(getContext(), FrgZhuce.class, TitleAct.class, "type", 0);
        } else if (R.id.mTextView_dl == v.getId()) {
            if (TextUtils.isEmpty(mEditText_phone.getText().toString().trim())) {
                Helper.toast("请输入手机号", getContext());
                return;
            }
            if (mEditText_phone.getText().toString().trim().length() != 11) {
                Helper.toast("请输入正确手机号", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_password.getText().toString().trim())) {
                Helper.toast("请输入密码", getContext());
                return;
            }
            try {
                ApisFactory.getApiMLogin().load(getContext(), this, "MLogin", mEditText_phone.getText().toString().trim(), Md5.md5(mEditText_password.getText().toString().trim()), "android", PushManager.getInstance().getClientid(getContext()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("登录");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode,
                data);
    }
}