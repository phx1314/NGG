//
//  FrgAddAddress
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

import com.mdx.framework.Frame;
import com.mdx.framework.dialog.DataSelectDialog;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.commons.AddressChoose;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MUserAddress;


public class FrgAddAddress extends BaseFrg implements DataSelectDialog.OnSelected {

    public EditText mTextView_name;
    public EditText mTextView_phone;
    public TextView mTextView_address;
    public EditText mTextView_detail;
    public TextView clk_mTextView_sure;
    public MUserAddress item;
    private DataSelectDialog addressdialog;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = (MUserAddress) getActivity().getIntent().getSerializableExtra("item");
        setContentView(R.layout.frg_add_address);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        AddressChoose data = new AddressChoose();
        addressdialog = new DataSelectDialog(getActivity(), data);
        addressdialog.setOnSelected(this);

        mTextView_name = (EditText) findViewById(R.id.mTextView_name);
        mTextView_phone = (EditText) findViewById(R.id.mTextView_phone);
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);
        mTextView_detail = (EditText) findViewById(R.id.mTextView_detail);
        clk_mTextView_sure = (TextView) findViewById(R.id.clk_mTextView_sure);

        clk_mTextView_sure.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressdialog.show();
            }
        });
    }

    @Override
    public void onSelected(Dialog dia, String first, String second,
                           String thread) {
        mTextView_address.setText(first + second + thread);
    }

    public void loaddata() {
        if (item != null) {
            mTextView_name.setText(item.name);
            mTextView_phone.setText(item.phone);
            mTextView_address.setText(item.area);
            mTextView_detail.setText(item.address);
        }
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_sure == v.getId()) {
            if (TextUtils.isEmpty(mTextView_name.getText().toString())) {
                Helper.toast("请输入姓名", getContext());
                return;
            }
            if (TextUtils.isEmpty(mTextView_phone.getText().toString())) {
                Helper.toast("请输入电话", getContext());
                return;
            }
            if (mTextView_phone.getText().toString().length() != 11) {
                Helper.toast("请输入正确的电话", getContext());
                return;
            }
            if (TextUtils.isEmpty(mTextView_address.getText().toString())) {
                Helper.toast("请选择地址", getContext());
                return;
            }
            if (TextUtils.isEmpty(mTextView_detail.getText().toString())) {
                Helper.toast("请输入详细地址", getContext());
                return;
            }

            if (item != null) {
                ApisFactory.getApiMEditAddress().load(getContext(), this, "MEditAddressb", mTextView_address.getText().toString(), mTextView_detail.getText().toString(), mTextView_phone.getText().toString(), mTextView_name.getText().toString(), "225400", item.id, (double) item.isDefault);
            } else {
                ApisFactory.getApiMEditAddress().load(getContext(), this, "MEditAddressa", mTextView_address.getText().toString(), mTextView_detail.getText().toString(), mTextView_phone.getText().toString(), mTextView_name.getText().toString(), "225400", null, 0.0);
            }
        }
    }

    public void MEditAddressb(Son s) {
        Helper.toast("编辑成功", getContext());
        Frame.HANDLES.sentAll("FrgDizhiGuanli", 0, null);
        this.finish();
    }

    public void MEditAddressa(Son s) {
        Helper.toast("添加成功", getContext());
        Frame.HANDLES.sentAll("FrgDizhiGuanli", 0, null);
        this.finish();
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("新增收获地址");
    }
}