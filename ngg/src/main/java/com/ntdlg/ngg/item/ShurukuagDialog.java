//
//  ShurukuagDialog
//
//  Created by df on 2017-03-02 08:53:33
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.view.CallBackQiTa;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.SReply;
import com.udows.common.proto.STopic;


public class ShurukuagDialog extends BaseItem {
    public LinearLayout mLinearLayout_fs;
    public TextView mTextView_fs;
    public EditText mEditText;
    public STopic item;
    public Dialog mDialog;
    public String targetId;
    public String targetName;
    public CallBackQiTa mCallBackQiTa;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_shurukuag_dialog, null);
        convertView.setTag(new ShurukuagDialog(convertView));
        return convertView;
    }

    public ShurukuagDialog(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_fs = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_fs);
        mTextView_fs = (TextView) contentview.findViewById(R.id.mTextView_fs);
        mEditText = (EditText) findViewById(R.id.mEditText);

        mTextView_fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText.getText().toString().trim())) {
                    Helper.toast("请输入", context);
                    return;
                }
                ApisFactory.getApiSAddCommentReply().load(context, ShurukuagDialog.this, "SAddCommentReply", item.id, mEditText.getText().toString().trim(), targetId);
            }
        });
    }

    public void SAddCommentReply(Son s) {
        Helper.toast("回复成功", context);
        mDialog.dismiss();
        SReply mSReply = new SReply();
        mSReply.targetId = targetId;
        mSReply.targetName = targetName;
        mSReply.content = mEditText.getText().toString().trim();
        mSReply.userId = com.ntdlg.ngg.F.UserId;
        mSReply.nickName = com.ntdlg.ngg.F.mSUser.nickName;
        item.replys.add(mSReply);
        mCallBackQiTa.goReturnDo(mSReply);
    }


    public void set(Dialog mDialog, STopic item, String targetId, String targetName, CallBackQiTa mCallBackQiTa) {
        this.mDialog = mDialog;
        this.item = item;
        this.targetId = targetId;
        this.targetName = targetName;
        this.mCallBackQiTa = mCallBackQiTa;
        mEditText.setHint("回复："+targetName);
    }


}