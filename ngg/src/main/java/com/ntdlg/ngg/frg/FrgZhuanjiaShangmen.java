//
//  FrgZhuanjiaShangmen
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.framewidget.view.MGridPhotoChoose;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFile;
import com.udows.common.proto.MFileList;
import com.udows.common.proto.MRet;

import okio.ByteString;


public class FrgZhuanjiaShangmen extends BaseFrg {

    public EditText mEditText_title;
    public EditText mEditText_content;
    public MGridPhotoChoose mMGridPhotoChoose;
    public EditText mEditText_name;
    public EditText mEditText_phone;
    public EditText mEditText_address;
    public TextView clk_mTextView_next;
    private MFileList mList = new MFileList();

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_zhuanjia_shangmen);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_title = (EditText) findViewById(R.id.mEditText_title);
        mEditText_content = (EditText) findViewById(R.id.mEditText_content);
        mMGridPhotoChoose = (MGridPhotoChoose) findViewById(R.id.mMGridPhotoChoose);
        mEditText_name = (EditText) findViewById(R.id.mEditText_name);
        mEditText_phone = (EditText) findViewById(R.id.mEditText_phone);
        mEditText_address = (EditText) findViewById(R.id.mEditText_address);
        clk_mTextView_next = (TextView) findViewById(R.id.clk_mTextView_next);

        clk_mTextView_next.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {

    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_next == v.getId()) {
            if (TextUtils.isEmpty(mEditText_title.getText().toString().trim())) {
                Helper.toast("请输入作物名称", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_content.getText().toString().trim())) {
                Helper.toast("详细描述你的问题，最少5个字", getContext());
                return;
            }
            if (mEditText_content.getText().toString().trim().length() < 5) {
                Helper.toast("详细描述你的问题，最少5个字", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_name.getText().toString().trim())) {
                Helper.toast("请输入你的姓名", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_phone.getText().toString().trim())) {
                Helper.toast("请输入联系方式", getContext());
                return;
            }
            if (mEditText_phone.getText().toString().trim().length() != 11) {
                Helper.toast("请输入正确的联系方式", getContext());
                return;
            }
            if (TextUtils.isEmpty(mEditText_address.getText().toString().trim())) {
                Helper.toast("请输入详细地址", getContext());
                return;
            }
            if (mMGridPhotoChoose.getData().size() > 0) {
                for (int i = 0; i < mMGridPhotoChoose.getData().size(); i++) {
                    ByteString imgFile = null;
                    try {
                        imgFile = ByteString.of(com.framewidget.F
                                .bitmap2Byte(mMGridPhotoChoose.getData().get(i).split(":")[1]));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ByteString bs = imgFile;
                    MFile mMFile = new MFile();
                    mMFile.file = bs;
                    mMFile.fileName = "1.png";
                    mList.file.add(mMFile);
                }
                ApisFactory.getApiMUploadFile().load(getContext(),
                        FrgZhuanjiaShangmen.this, "MUploadFile", mList);
            } else {
                ApisFactory.getApiMProVisit().load(getContext(), FrgZhuanjiaShangmen.this, "MProVisit", mEditText_title.getText().toString().trim(), mEditText_content.getText().toString().trim(), mEditText_name.getText().toString().trim(), mEditText_phone.getText().toString().trim(), mEditText_address.getText().toString().trim(), "");
            }
        }
    }

    public void MProVisit(Son s) {
        MRet mMRet = (MRet) s.getBuild();
        Helper.toast("提交成功", getContext());
        this.finish();
        Helper.startActivity(getContext(), FrgXiadan.class, TitleAct.class, "orderId", mMRet.msg, "contetnt", mEditText_content.getText().toString().trim());
    }

    public void MUploadFile(Son s) {
        MRet mModel = (MRet) s.getBuild();
        ApisFactory.getApiMProVisit().load(getContext(), FrgZhuanjiaShangmen.this, "MProVisit", mEditText_title.getText().toString().trim(), mEditText_content.getText().toString().trim(), mEditText_name.getText().toString().trim(), mEditText_phone.getText().toString().trim(), mEditText_address.getText().toString().trim(), mModel.msg);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("专家上门");
    }
}