//
//  FrgJiaruMd1
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
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.view.Pois;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.act.ActWmChooseAddress;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MJoinStore;
import com.udows.common.proto.MJoinStoreCard;
import com.udows.common.proto.MJoinStoreUser;

import java.util.List;


public class FrgJiaruMd1 extends BaseFrg {

    public EditText mEditText_name;
    public EditText mEditText_age;
    public TextView mTextView_leixing;
    public TextView mTextView_shanchang;
    public EditText mEditText_danwei;
    public EditText mEditText_phone;
    public TextView mTextView_address;
    public EditText mEditText_xiangxi;
    public TextView clk_mTextView_next;
    public MJoinStore mMJoinStore = new MJoinStore();
    public LinearLayout mLinearLayout_area;
    public EditText mEditText_detail;


    @Override
    protected void create(Bundle savedInstanceState) {
        mMJoinStore.one = new MJoinStoreUser();
        setContentView(R.layout.frg_jiaru_md1);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                this.finish();
                break;
            case 3:
                Pois poi = (Pois) obj;
                mMJoinStore.one.lat = poi.getLat();
                mMJoinStore.one.lng = poi.getLng();
                mTextView_address.setText(poi.getTitle());
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
                mMJoinStore.one.zuoWuName = codes;
                mTextView_shanchang.setText(aa);
                break;
            case 120:
                MCategory mMCategory = (MCategory) obj;
                mTextView_leixing.setText(mMCategory.name);
                mMJoinStore.one.proCateCode = mMCategory.code;
                break;
            case 110:
                mMJoinStore = Helper.readBuilder(F.UserId + "mMJoinStore", mMJoinStore);
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
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);
        mEditText_xiangxi = (EditText) findViewById(R.id.mEditText_xiangxi);
        clk_mTextView_next = (TextView) findViewById(R.id.clk_mTextView_next);
        mLinearLayout_area = (LinearLayout) findViewById(R.id.mLinearLayout_area);
        mEditText_detail = (EditText) findViewById(R.id.mEditText_detail);

        clk_mTextView_next.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_leixing.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgPublicList.class, TitleAct.class, "title", "专家类型", "from", "FrgJiaruMd1");
            }
        }));
        mTextView_shanchang.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgShanchangZuowu.class, TitleAct.class, "from", "FrgJiaruMd1");
            }
        }));
        mLinearLayout_area.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActWmChooseAddress.class).putExtra("from", "FrgJiaruMd1"));
            }
        }));

    }

    public void loaddata() {
        mMJoinStore = Helper.readBuilder(F.UserId + "mMJoinStore", mMJoinStore);
        if (mMJoinStore.one != null && !TextUtils.isEmpty(mMJoinStore.one.name)) {
            mEditText_name.setText(mMJoinStore.one.name);
            mEditText_age.setText(mMJoinStore.one.age + "");
            mTextView_shanchang.setText(mMJoinStore.one.zuoWuName);
            mEditText_detail.setText(mMJoinStore.info);
            mEditText_phone.setText(mMJoinStore.one.phone);
            mEditText_xiangxi.setText(mMJoinStore.one.address);
            mTextView_address.setText(mMJoinStore.one.area);
            mTextView_leixing.setText(mMJoinStore.one.proCateName);
        } else {
            mMJoinStore = new MJoinStore();
            mMJoinStore.one=new MJoinStoreUser();
            mMJoinStore.two=new MJoinStoreCard();
        }
    }


    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_next == v.getId()) {
            if (TextUtils.isEmpty(mEditText_name.getText().toString().trim())) {
                Helper.toast("请输入店名", getContext());
                return;
            }
            }
            if (TextUtils.isEmpty(mEditText_phone.getText().toString().trim())) {
                Helper.toast("请输入联系电话", getContext());
                return;
            }
            if (mEditText_phone.getText().toString().trim().length() != 11) {
                Helper.toast("请输入正确的联系电话", getContext());
                return;
            }
            if (TextUtils.isEmpty(mTextView_address.getText().toString().trim())) {
                Helper.toast("请选择地区", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_xiangxi.getText().toString().trim())) {
                Helper.toast("请输入详细地址", getContext());
                return;
            }
            mMJoinStore.one.name = mEditText_name.getText().toString().trim();
//            mMJoinStore.one.age = Integer.valueOf(mEditText_age.getText().toString().trim());
            mMJoinStore.one.phone = mEditText_phone.getText().toString().trim();
            mMJoinStore.info= mEditText_detail.getText().toString().trim();
            mMJoinStore.one.address = mEditText_xiangxi.getText().toString().trim();
            mMJoinStore.one.proCateName = mTextView_leixing.getText().toString().trim();
            Helper.saveBuilder(F.UserId + "mMJoinStore", mMJoinStore);
            Helper.startActivity(getContext(), FrgJiaruMd2.class, TitleAct.class);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("加入地标门店");
        mHeadlayout.setRText("暂时略过");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgJiaruMd1.this.finish();
            }
        });
    }
}