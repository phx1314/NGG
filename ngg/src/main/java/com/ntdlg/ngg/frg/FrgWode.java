//
//  FrgWode
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.SUser;

import static com.ntdlg.ngg.F.DOWNLOAD_URL;
import static java.security.AccessController.getContext;


public class FrgWode extends BaseFrg {

    public MImageView mMImageView;
    public ImageView mImageView_1;
    public ImageView mImageView_2;
    public ImageView mImageView_3;
    public TextView mTextView_ggd;
    public TextView mTextView_cns;
    public TextView mTextView_yqm;
    public LinearLayout clk_mLinearLayout_shoucang;
    public LinearLayout clk_mLinearLayout_gzhderen;
    public LinearLayout clk_mLinearLayout1;
    public TextView textView;
    public LinearLayout clk_mLinearLayout2;
    public LinearLayout clk_mLinearLayout3;
    public LinearLayout clk_mLinearLayout4;
    public LinearLayout clk_mLinearLayout5;
    public LinearLayout clk_mLinearLayout6;
    public LinearLayout clk_mLinearLayout7;
    public LinearLayout clk_mLinearLayout8;
    public TextView mTextView_name;
    public LinearLayout mLinearLayout_zhiwei;
    public LinearLayout mLinearLayout_ggd;
    public LinearLayout clk_mLinearLayoutdt;
    public SUser mSUser;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wode);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                loaddata();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMImageView = (MImageView) findViewById(R.id.mMImageView);
        mImageView_1 = (ImageView) findViewById(R.id.mImageView_1);
        mImageView_2 = (ImageView) findViewById(R.id.mImageView_2);
        mImageView_3 = (ImageView) findViewById(R.id.mImageView_3);
        mTextView_ggd = (TextView) findViewById(R.id.mTextView_ggd);
        mTextView_cns = (TextView) findViewById(R.id.mTextView_cns);
        mTextView_yqm = (TextView) findViewById(R.id.mTextView_yqm);
        clk_mLinearLayout_shoucang = (LinearLayout) findViewById(R.id.clk_mLinearLayout_shoucang);
        clk_mLinearLayout_gzhderen = (LinearLayout) findViewById(R.id.clk_mLinearLayout_gzhderen);
        clk_mLinearLayout1 = (LinearLayout) findViewById(R.id.clk_mLinearLayout1);
        textView = (TextView) findViewById(R.id.textView);
        clk_mLinearLayout2 = (LinearLayout) findViewById(R.id.clk_mLinearLayout2);
        clk_mLinearLayout3 = (LinearLayout) findViewById(R.id.clk_mLinearLayout3);
        clk_mLinearLayout4 = (LinearLayout) findViewById(R.id.clk_mLinearLayout4);
        clk_mLinearLayout5 = (LinearLayout) findViewById(R.id.clk_mLinearLayout5);
        clk_mLinearLayout6 = (LinearLayout) findViewById(R.id.clk_mLinearLayout6);
        clk_mLinearLayout7 = (LinearLayout) findViewById(R.id.clk_mLinearLayout7);
        clk_mLinearLayout8 = (LinearLayout) findViewById(R.id.clk_mLinearLayout8);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mLinearLayout_zhiwei = (LinearLayout) findViewById(R.id.mLinearLayout_zhiwei);
        mLinearLayout_ggd = (LinearLayout) findViewById(R.id.mLinearLayout_ggd);
        clk_mLinearLayoutdt = (LinearLayout) findViewById(R.id.clk_mLinearLayoutdt);

        clk_mLinearLayout_shoucang.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_gzhderen.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout3.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout4.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout5.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout6.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout7.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout8.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mMImageView.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_name.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_ggd.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayoutdt.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        if (TextUtils.isEmpty(F.UserId)) {
            mTextView_name.setText("登录 | 注册");
            mMImageView.setObj(null);
            mMImageView.setImageResource(R.drawable.morentouxiang_n);
            mLinearLayout_zhiwei.setVisibility(View.GONE);
            mTextView_ggd.setText("");
            mTextView_cns.setText("");
            mTextView_yqm.setText("");
        } else {
            ApisFactory.getApiSGetUserInfo().load(getContext(), this, "SGetUserInfo", F.UserId);
        }
    }

    public void SGetUserInfo(Son s) {
        mLinearLayout_zhiwei.setVisibility(View.VISIBLE);
        mSUser = (SUser) s.getBuild();
        F.mSUser = mSUser;
        mMImageView.setObj(mSUser.headImg);
        mMImageView.setCircle(true);
        mTextView_name.setText(mSUser.nickName);
        mTextView_ggd.setText(mSUser.credit + "");
        mTextView_cns.setText(mSUser.caina + "");
        mTextView_yqm.setText(mSUser.id);
        mImageView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSUser.isPro == 1) {
                    Helper.startActivity(getContext(), FrgZhuanjiaZhuye.class, NoTitleAct.class, "id", mSUser.id);
                } else if (mSUser.isPro == -1) {
                    Helper.toast("申请中", getContext());
                } else {
                    Helper.startActivity(getContext(), FrgShengqingZhuanjiaMd1.class, TitleAct.class);
                }
            }
        });
        mImageView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (mSUser.isOffice == 1) {
//                    Helper.startActivity(getContext(), FrgGuanfangQingbaoyuan.class, NoTitleAct.class, "id", mSUser.id);
//                } else if (mSUser.isOffice == -1) {
//                    Helper.toast("申请中", getContext());
//                } else {
//                    Helper.startActivity(getContext(), FrgShenqingguanfangxinxiyuan.class, TitleAct.class);
//                }
                Helper.toast("开发中", getContext());
            }
        });
        mImageView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSUser.isStore == 1) {
                    Helper.startActivity(getContext(), FrgNzDetail.class, NoTitleAct.class, "id", mSUser.id);
//                    Helper.startActivity(getContext(), FrgDibiaoMendian.class, TitleAct.class, "id", mSUser.id);
                } else if (mSUser.isStore == -1) {
                    Helper.toast("申请中", getContext());
                } else {
                    Helper.startActivity(getContext(), FrgJiaruMd1.class, TitleAct.class);
                }
            }
        });
        if (mSUser.isPro == 1) {
            mImageView_1.setImageResource(R.drawable.ic_rzzj_h);
        } else {
            mImageView_1.setImageResource(R.drawable.ic_rzzj_d);
        }
        if (mSUser.isOffice == 1) {
            mImageView_2.setImageResource(R.drawable.ic_gfqby_h);
        } else {
            mImageView_2.setImageResource(R.drawable.ic_gfqby_d);
        }
        if (mSUser.isStore == 1) {
            mImageView_3.setImageResource(R.drawable.ic_dbmd_h);
        } else {
            mImageView_3.setImageResource(R.drawable.ic_dbmd_d);
        }
    }

    @Override
    public void onClick(android.view.View v) {

        if (TextUtils.isEmpty(F.UserId)) {
            F.showToast2Login(getContext());
            return;
        }
        if (R.id.clk_mLinearLayout_shoucang == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeShoucang.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout_gzhderen == v.getId()) {
            Helper.startActivity(getContext(), FrgGuanzhuderen.class, TitleAct.class);
        } else if (R.id.mLinearLayout_ggd == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeGgd.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout1 == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeWenda.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout2 == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeDd.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout3 == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeMenzhengguahaodan.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout4 == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeGgd.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout5 == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeShenqing.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout6 == v.getId()) {//填写邀请码
            if (TextUtils.isEmpty(mSUser.inviteCode)) {
                Helper.startActivity(getContext(), FrgTianxieyqm.class, TitleAct.class);
            } else {
                Helper.toast("邀请码已存在", getContext());
            }
        } else if (R.id.clk_mLinearLayout7 == v.getId()) {//分享
            com.framewidget.F.getShare(getContext(), "", DOWNLOAD_URL
                    , "", "");
        } else if (R.id.clk_mLinearLayout8 == v.getId()) {
            Helper.startActivity(getContext(), FrgSet.class, TitleAct.class);
        } else if (R.id.mTextView_name == v.getId() || R.id.mMImageView == v.getId()) {
            Helper.startActivity(getContext(), FrgXiugaiGerenziliao.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayoutdt == v.getId()) {
            Helper.startActivity(getContext(), FrgWodeDt.class, TitleAct.class);
        }
    }

}