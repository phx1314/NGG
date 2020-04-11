//
//  FrgJieda
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.framewidget.F;
import com.framewidget.view.MGridPhotoChoose;
import com.mdx.framework.Frame;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFile;
import com.udows.common.proto.MFileList;
import com.udows.common.proto.MRet;
import com.udows.common.proto.STopic;

import java.util.Arrays;

import okio.ByteString;


public class FrgJieda extends BaseFrg {

    public EditText mEditText_content;
    public MGridPhotoChoose mMGridPhotoChoose;
    public String mid;
    private MFileList mList = new MFileList();
    public STopic mSTopic;
    private String imgs = "";
    private String address;

    @Override
    protected void create(Bundle savedInstanceState) {
        mid = getActivity().getIntent().getStringExtra("mid");
        mSTopic = (STopic) getActivity().getIntent().getSerializableExtra("mSTopic");
        setContentView(R.layout.frg_jieda);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_content = (EditText) findViewById(R.id.mEditText_content);
        mMGridPhotoChoose = (MGridPhotoChoose) findViewById(R.id.mMGridPhotoChoose);

    }

    public void loaddata() {
        mMGridPhotoChoose.setMax(6);
        if (mSTopic != null && !TextUtils.isEmpty(mSTopic.imgs)) {
            mMGridPhotoChoose.initView(Arrays.asList(mSTopic.imgs.split(",")));
            mEditText_content.setText(mSTopic.content);
            address = mSTopic.address;
        } else {
            address = com.ntdlg.ngg.F.address;
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我来解答");
        mHeadlayout.setRText("完成");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_content.getText().toString().trim())) {
                    Helper.toast("请输入内容", getContext());
                    return;
                }
                if (mMGridPhotoChoose.getData().size() > 0) {
                    mList.file.clear();
                    for (int i = 0; i < mMGridPhotoChoose.getData().size(); i++) {
                        if (mMGridPhotoChoose.getData().get(i).contains("file:")) {
                            ByteString imgFile = null;
                            try {
                                imgFile = ByteString.of(F
                                        .bitmap2Byte(mMGridPhotoChoose.getData().get(i).split(":")[1]));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            ByteString bs = imgFile;
                            MFile mMFile = new MFile();
                            mMFile.file = bs;
                            mMFile.fileName = "1.png";
                            mList.file.add(mMFile);
                        } else {
                            imgs += "," + mMGridPhotoChoose.getData().get(i);
                        }
                    }
                    if (mList.file.size() <= 0) {
                        mSTopic.imgs = imgs.substring(1, imgs.length());
                        ApisFactory.getApiSReplyEdit().load(getContext(), FrgJieda.this, "SReplyEdit", mSTopic.id, mEditText_content.getText().toString().trim(), mSTopic.imgs, address);
                    } else {
                        ApisFactory.getApiMUploadFile().load(getContext(),
                                FrgJieda.this, "MUploadFile", mList);
                    }
                } else {
                    if (mSTopic != null) {
                        ApisFactory.getApiSReplyEdit().load(getContext(), FrgJieda.this, "SReplyEdit", mSTopic.id, mEditText_content.getText().toString().trim(), "", address);
                    } else {
                        ApisFactory.getApiSAddTopicComment().load(getContext(), FrgJieda.this, "SAddTopicComment", mid, mEditText_content.getText().toString().trim(), "", address);
                    }
                }

            }
        });
    }

    public void MUploadFile(Son s) {
        MRet mModel = (MRet) s.getBuild();
        if (mSTopic != null) {
            ApisFactory.getApiSReplyEdit().load(getContext(), FrgJieda.this, "SReplyEdit", mSTopic.id, mEditText_content.getText().toString().trim(), mModel.msg + imgs, address);
        } else {
            ApisFactory.getApiSAddTopicComment().load(getContext(), FrgJieda.this, "SAddTopicComment", mid, mEditText_content.getText().toString().trim(), mModel.msg, address);
        }
    }

    public void SAddTopicComment(Son s) {
        Helper.toast("发布成功", getContext());
        Frame.HANDLES.sentAll("FrgWentiDetail,FrgWode", 0, null);
        this.finish();
    }

    public void SReplyEdit(Son s) {
        Helper.toast("编辑成功", getContext());
        Frame.HANDLES.sentAll("FrgWentiDetail,FrgWode", 0, null);
        this.finish();
    }

}