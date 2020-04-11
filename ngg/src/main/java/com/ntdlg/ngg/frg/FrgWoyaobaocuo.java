//
//  FrgWoyaobaocuo
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.view.Pois;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.act.ActWmChooseAddress;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFirm;


public class FrgWoyaobaocuo extends BaseFrg {

    public TextView mEditText_name;
    public TextView mTextView_address;
    public EditText mEditText_xiangxi;
    public EditText mEditText_lianxiren;
    public EditText mEditText_phone;
    public EditText mEditText_czh;
    public EditText mEditText_code;
    public TextView clk_mTextView_next;
    public MFirm mMFirm;
    public LinearLayout mLinearLayout_address;

    @Override
    protected void create(Bundle savedInstanceState) {
        mMFirm = (MFirm) getActivity().getIntent().getSerializableExtra("mMFirm");
        setContentView(R.layout.frg_woyaobaocuo);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 3:
                Pois poi = (Pois) obj;
                mMFirm.lat = poi.getLat();
                mMFirm.lng = poi.getLng();
                mTextView_address.setText(poi.getTitle());
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_name = (TextView) findViewById(R.id.mEditText_name);
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);
        mEditText_xiangxi = (EditText) findViewById(R.id.mEditText_xiangxi);
        mEditText_lianxiren = (EditText) findViewById(R.id.mEditText_lianxiren);
        mEditText_phone = (EditText) findViewById(R.id.mEditText_phone);
        mEditText_czh = (EditText) findViewById(R.id.mEditText_czh);
        mEditText_code = (EditText) findViewById(R.id.mEditText_code);
        clk_mTextView_next = (TextView) findViewById(R.id.clk_mTextView_next);
        mLinearLayout_address = (LinearLayout) findViewById(R.id.mLinearLayout_address);

        clk_mTextView_next.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_address.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        mEditText_name.setText(mMFirm.name);
        mTextView_address.setText(mMFirm.area);
        mEditText_xiangxi.setText(mMFirm.address);
        mEditText_lianxiren.setText(mMFirm.contact);
        mEditText_phone.setText(mMFirm.phone);
        mEditText_czh.setText(mMFirm.fax);
        mEditText_code.setText(mMFirm.zipCode);
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_next == v.getId()) {
            if (TextUtils.isEmpty(mTextView_address.getText().toString().trim())) {
                Helper.toast("请选择地址", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_xiangxi.getText().toString().trim())) {
                Helper.toast("请输入详细地址", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_lianxiren.getText().toString().trim())) {
                Helper.toast("请输入联系人姓名", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_phone.getText().toString().trim())) {
                Helper.toast("请输入联系电话", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_czh.getText().toString().trim())) {
                Helper.toast("请输入传真", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_code.getText().toString().trim())) {
                Helper.toast("请输入邮编", getContext());
                return;
            }
            mMFirm.area = mTextView_address.getText().toString().trim();
            mMFirm.address = mEditText_xiangxi.getText().toString().trim();
            mMFirm.contact = mEditText_lianxiren.getText().toString().trim();
            mMFirm.phone = mEditText_phone.getText().toString().trim();
            mMFirm.fax = mEditText_czh.getText().toString().trim();
            mMFirm.zipCode = mEditText_code.getText().toString().trim();
            ApisFactory.getApiMFirmError().load(getContext(), this, "MFirmError", mMFirm);
        } else if (R.id.mLinearLayout_address == v.getId()) {
            startActivity(new Intent(getActivity(), ActWmChooseAddress.class).putExtra("from", "FrgWoyaobaocuo"));
        }
    }

    public void MFirmError(Son s) {
        Helper.toast("报错成功", getContext());
        this.finish();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我要报错");
    }
}