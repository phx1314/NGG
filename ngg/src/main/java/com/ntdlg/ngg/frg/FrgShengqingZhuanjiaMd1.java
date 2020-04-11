//
//  FrgShengqingZhuanjiaMd1
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
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MProApply;

import java.util.List;


public class FrgShengqingZhuanjiaMd1 extends BaseFrg {

    public EditText mEditText_name;
    public EditText mEditText_age;
    public TextView mTextView_leixing;
    public TextView mTextView_shanchang;
    public EditText mEditText_danwei;
    public EditText mEditText_phone;
    public EditText mEditText_xiangxi;
    public TextView clk_mTextView_next;

    public MProApply mMProApply = new MProApply();

    @Override
    protected void create(Bundle savedInstanceState) {
        mMProApply = Helper.readBuilder(F.UserId + "mMProApply", mMProApply);
        setContentView(R.layout.frg_shengqing_zhuanjia_md1);
        initView();
        loaddata();
    }


    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                this.finish();
                break;
            case 5:
                this.finish();
                break;
            case 1:
                List<MCategory> data = (List<MCategory>) obj;
                String aa = "";
                String codes = "";
                for (MCategory mMCategory : data) {
                    aa += mMCategory.name + ",";
                    codes += mMCategory.code + ",";
                }
                aa = aa.substring(0, aa.length() - 1);
                codes = codes.substring(0, codes.length() - 1);
                mMProApply.product = codes;
                mTextView_shanchang.setText(aa);
                break;
            case 120:
                MCategory mMCategory = (MCategory) obj;
                mTextView_leixing.setText(mMCategory.name);
                mMProApply.type = mMCategory.code;
                break;
            case 110:
                mMProApply = Helper.readBuilder(F.UserId + "mMProApply", mMProApply);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_name = (EditText) findViewById(R.id.mEditText_name);
        mEditText_age = (EditText) findViewById(R.id.mEditText_age);
        mTextView_leixing = (TextView) findViewById(R.id.mTextView_leixing);
        mTextView_shanchang = (TextView) findViewById(R.id.mTextView_shanchang);
        mEditText_danwei = (EditText) findViewById(R.id.mEditText_danwei);
        mEditText_phone = (EditText) findViewById(R.id.mEditText_phone);
        mEditText_xiangxi = (EditText) findViewById(R.id.mEditText_xiangxi);
        clk_mTextView_next = (TextView) findViewById(R.id.clk_mTextView_next);

        clk_mTextView_next.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_leixing.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgPublicList.class, TitleAct.class, "title", "专家类型", "from", "FrgShengqingZhuanjiaMd1");
            }
        }));
        mTextView_shanchang.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgShanchangZuowu.class, TitleAct.class, "from", "FrgShengqingZhuanjiaMd1");
            }
        }));

    }

    public void loaddata() {
        if (mMProApply != null) {
            mEditText_name.setText(mMProApply.name);
            mEditText_age.setText(mMProApply.age);
            mTextView_shanchang.setText(mMProApply.productName);
            mEditText_phone.setText(mMProApply.phone);
            mEditText_xiangxi.setText(mMProApply.address);
            mTextView_leixing.setText(mMProApply.typeName);
        } else {
            mMProApply = new MProApply();
        }
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_next == v.getId()) {
            if (TextUtils.isEmpty(mEditText_name.getText().toString().trim())) {
                Helper.toast("请输入姓名", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_age.getText().toString().trim())) {
                Helper.toast("请输入年龄", getContext());
                return;
            }
            if (TextUtils.isEmpty(mTextView_leixing.getText().toString().trim())) {
                Helper.toast("请选择专家类型", getContext());
                return;
            }
            if (TextUtils.isEmpty(mTextView_shanchang.getText().toString().trim())) {
                Helper.toast("请选择擅长作物", getContext());
                return;
            }
//            if (TextUtils.isEmpty(mEditText_danwei.getText().toString().trim())) {
//                Helper.toast("请输入工作单位", getContext());
//                return;
//            }
            if (TextUtils.isEmpty(mEditText_phone.getText().toString().trim())) {
                Helper.toast("请输入联系电话", getContext());
                return;
            }
            if (mEditText_phone.getText().toString().trim().length() != 11) {
                Helper.toast("请输入正确的联系电话", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_xiangxi.getText().toString().trim())) {
                Helper.toast("请输入详细地址", getContext());
                return;
            }
            mMProApply.name = mEditText_name.getText().toString().trim();
            mMProApply.age = mEditText_age.getText().toString().trim();
//            mMProApply.da = mEditText_danwei.getText().toString().trim();
            mMProApply.phone = mEditText_phone.getText().toString().trim();
            mMProApply.address = mEditText_xiangxi.getText().toString().trim();
            mMProApply.typeName = mTextView_leixing.getText().toString().trim();
            mMProApply.productName = mTextView_shanchang.getText().toString().trim();
            Helper.saveBuilder(F.UserId + "mMProApply", mMProApply);
            Helper.startActivity(getContext(), FrgShengqingzhuanjiaMd2.class, TitleAct.class);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("申请专家");
        mHeadlayout.setRText("暂时略过");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgShengqingZhuanjiaMd1.this.finish();
            }
        });
    }
}