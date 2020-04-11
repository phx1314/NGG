//
//  FrgTiwenti
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
import android.widget.TextView;

import com.framewidget.view.MGridPhotoChoose;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFile;
import com.udows.common.proto.MFileList;
import com.udows.common.proto.MRet;

import okio.ByteString;


public class FrgTiwenti extends BaseFrg {

    public EditText mEditText_title;
    public EditText mEditText_content;
    public TextView mTextView_location;
    public EditText mEditText_title_fu;
    public MGridPhotoChoose mMGridPhotoChoose;
    private MFileList mList = new MFileList();
    public String fid;

    @Override
    protected void create(Bundle savedInstanceState) {
        fid = getActivity().getIntent().getStringExtra("fid");
        setContentView(R.layout.frg_tiwenti);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_title = (EditText) findViewById(R.id.mEditText_title);
        mEditText_content = (EditText) findViewById(R.id.mEditText_content);
        mTextView_location = (TextView) findViewById(R.id.mTextView_location);
        mEditText_title_fu = (EditText) findViewById(R.id.mEditText_title_fu);
        mMGridPhotoChoose = (MGridPhotoChoose) findViewById(R.id.mMGridPhotoChoose);

    }

    public void loaddata() {
        mTextView_location.setText(F.address);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("提问题");
        mHeadlayout.setRText("完成");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_title.getText().toString().trim())) {
                    Helper.toast("请输入问题", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_title_fu.getText().toString().trim())) {
                    Helper.toast("填写与提问相关的作物名称便于系统推送给擅长专家", getContext());
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
                            FrgTiwenti.this, "MUploadFile", mList);
                } else {
                    ApisFactory.getApiSAddTopic().load(getContext(), FrgTiwenti.this, "SAddTopic", mEditText_title.getText().toString().trim(), mEditText_content.getText().toString().trim(), "", F.address, mEditText_title_fu.getText().toString().trim(), fid, F.latitude + "", F.longitude + "");
                }

            }
        });
    }

    public void MUploadFile(Son s) {
        MRet mModel = (MRet) s.getBuild();
        ApisFactory.getApiSAddTopic().load(getContext(), FrgTiwenti.this, "SAddTopic", mEditText_title.getText().toString().trim(), mEditText_content.getText().toString().trim(), mModel.msg, F.address, mEditText_title_fu.getText().toString().trim(), fid, F.latitude + "", F.longitude + "");
    }

    public void SAddTopic(Son s) {
        Helper.toast("发布成功", getContext());
        this.finish();
    }

}