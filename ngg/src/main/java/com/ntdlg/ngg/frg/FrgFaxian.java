//
//  FrgFaxian
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;

import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;

import static com.ntdlg.ngg.F.DOWNLOAD_URL;
import static com.ntdlg.ngg.F.mSUser;


public class FrgFaxian extends BaseFrg {

    public LinearLayout clk_mLinearLayout1;
    public LinearLayout clk_mLinearLayout2;
    public LinearLayout clk_mLinearLayout3;
    public LinearLayout clk_mLinearLayout4;
    public LinearLayout clk_mLinearLayout5;
    public LinearLayout clk_mLinearLayout6;
    public LinearLayout clk_mLinearLayout7;
    public LinearLayout clk_mLinearLayout8;
    public LinearLayout clk_mLinearLayout9;
    public LinearLayout clk_mLinearLayout10;
    public LinearLayout clk_mLinearLayout11;
    public LinearLayout clk_mLinearLayout12;
    public LinearLayout clk_mLinearLayout13;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_faxian);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        clk_mLinearLayout1 = (LinearLayout) findViewById(R.id.clk_mLinearLayout1);
        clk_mLinearLayout2 = (LinearLayout) findViewById(R.id.clk_mLinearLayout2);
        clk_mLinearLayout3 = (LinearLayout) findViewById(R.id.clk_mLinearLayout3);
        clk_mLinearLayout4 = (LinearLayout) findViewById(R.id.clk_mLinearLayout4);
        clk_mLinearLayout5 = (LinearLayout) findViewById(R.id.clk_mLinearLayout5);
        clk_mLinearLayout6 = (LinearLayout) findViewById(R.id.clk_mLinearLayout6);
        clk_mLinearLayout7 = (LinearLayout) findViewById(R.id.clk_mLinearLayout7);
        clk_mLinearLayout8 = (LinearLayout) findViewById(R.id.clk_mLinearLayout8);
        clk_mLinearLayout9 = (LinearLayout) findViewById(R.id.clk_mLinearLayout9);
        clk_mLinearLayout10 = (LinearLayout) findViewById(R.id.clk_mLinearLayout10);
        clk_mLinearLayout11 = (LinearLayout) findViewById(R.id.clk_mLinearLayout11);
        clk_mLinearLayout12 = (LinearLayout) findViewById(R.id.clk_mLinearLayout12);
        clk_mLinearLayout13 = (LinearLayout) findViewById(R.id.clk_mLinearLayout13);

        clk_mLinearLayout1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout3.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout4.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout5.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout6.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout7.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout8.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout9.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout10.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout11.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout12.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout13.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {

    }

    @Override
    public void onClick(android.view.View v) {
        if (TextUtils.isEmpty(F.UserId)) {
            F.showToast2Login(getContext());
            return;
        }
        if (R.id.clk_mLinearLayout1 == v.getId()) {
            if (mSUser.isPro == 1) {
                Helper.toast("已经是专家", getContext());
            } else if (mSUser.isPro == -1) {
                Helper.toast("申请中", getContext());
            } else {
                Helper.startActivity(getContext(), FrgShengqingZhuanjiaMd1.class, TitleAct.class);
            }
        } else if (R.id.clk_mLinearLayout2 == v.getId()) {
//            Helper.startActivity(getContext(), FrgShenqingguanfangxinxiyuan.class, TitleAct.class);
            Helper.toast("开发中", getContext());
        } else if (R.id.clk_mLinearLayout3 == v.getId()) {
            Helper.startActivity(getContext(), FrgZhenweichaxun.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout4 == v.getId()) {
            Helper.startActivity(getContext(), FrgZhuanjiaShangmen.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout5 == v.getId()) {
            Helper.startActivity(getContext(), FrgTuwennongji.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout6 == v.getId()) {
            Helper.startActivity(getContext(), FrgXinwenzixun.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout7 == v.getId()) {
            Helper.startActivity(getContext(), FrgNyHqfenlei.class, TitleAct.class, "title", "农药全书");
        } else if (R.id.clk_mLinearLayout8 == v.getId()) {
//            Helper.startActivity(getContext(), FrgNzHq.class, NoTitleAct.class);
            Helper.toast("开发中", getContext());
        } else if (R.id.clk_mLinearLayout9 == v.getId()) {
            Helper.startActivity(getContext(), FrgNyq.class, NoTitleAct.class);
        } else if (R.id.clk_mLinearLayout10 == v.getId()) {
            Helper.startActivity(getContext(), FrgFujin.class, NoTitleAct.class, "type", 1, "mlat", F.latitude + "", "mlng", F.longitude + "");
        } else if (R.id.clk_mLinearLayout11 == v.getId()) {
            com.framewidget.F.getShare(getContext(), "", DOWNLOAD_URL
                    , "", "");
        } else if (R.id.clk_mLinearLayout12 == v.getId()) {
            Helper.startActivity(getContext(), FrgGgdShangcheng.class, TitleAct.class);
        } else if (R.id.clk_mLinearLayout13 == v.getId()) {
            Helper.startActivity(getContext(), FrgYijianFk.class, TitleAct.class);
        }
    }

}