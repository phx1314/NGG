//
//  FrgFbDt
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
import com.mdx.framework.Frame;
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


public class FrgFbDt extends BaseFrg {

    public EditText mEditText_content;
    public TextView mTextView_store_address;
    public MGridPhotoChoose mMGridPhotoChoose;
    private MFileList mList = new MFileList();

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_fb_dt);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_content = (EditText) findViewById(R.id.mEditText_content);
        mTextView_store_address = (TextView) findViewById(R.id.mTextView_store_address);
        mMGridPhotoChoose = (MGridPhotoChoose) findViewById(R.id.mMGridPhotoChoose);
        mMGridPhotoChoose.setMax(9);

    }

    public void loaddata() {
        mTextView_store_address.setText(F.address);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("发布动态");
        mHeadlayout.setRText("完成");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEditText_content.getText().toString().trim())) {
                    Helper.toast("请输入描述", getContext());
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
                            FrgFbDt.this, "MUploadFile", mList);
                } else {
                    ApisFactory.getApiSAddFriendCircle().load(getContext(), FrgFbDt.this, "SAddFriendCircle", mEditText_content.getText().toString().trim(), "", F.address, 1.0);
                }

            }
        });
    }

    public void MUploadFile(Son s) {
        MRet mModel = (MRet) s.getBuild();
        ApisFactory.getApiSAddFriendCircle().load(getContext(), FrgFbDt.this, "SAddFriendCircle", mEditText_content.getText().toString().trim(), mModel.msg, F.address, 1.0);
    }

    public void SAddFriendCircle(Son s) {
        Helper.toast("发布成功", getContext());
        Frame.HANDLES.sentAll("FrgWodeDt", 0, null);
        this.finish();
    }
}