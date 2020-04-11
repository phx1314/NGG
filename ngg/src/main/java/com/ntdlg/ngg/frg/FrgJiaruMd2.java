//
//  FrgJiaruMd2
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
import android.widget.ImageView;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MImageView;
import com.mdx.framework.widget.getphoto.PopUpdataPhoto;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MFile;
import com.udows.common.proto.MFileList;
import com.udows.common.proto.MJoinStore;
import com.udows.common.proto.MJoinStoreCard;
import com.udows.common.proto.MRet;

import okio.ByteString;


public class FrgJiaruMd2 extends BaseFrg {

    public ImageView imageView;
    public MImageView mMImageView_zheng;
    public MImageView mMImageView_fan;
    public ImageView imageView2;
    public TextView clk_mTextView_up;
    public TextView clk_mTextView_next;
    public MJoinStore mMJoinStore=new MJoinStore();

    @Override
    protected void create(Bundle savedInstanceState) {
        mMJoinStore = Helper.readBuilder(F.UserId + "mMJoinStore", mMJoinStore);
        setContentView(R.layout.frg_jiaru_md2);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                Frame.HANDLES.sentAll("FrgJiaruMd1", 0, null);
                this.finish();
                break;case 110:
                mMJoinStore = Helper.readBuilder(F.UserId + "mMJoinStore", mMJoinStore);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        imageView = (ImageView) findViewById(R.id.imageView);
        mMImageView_zheng = (MImageView) findViewById(R.id.mMImageView_zheng);
        mMImageView_fan = (MImageView) findViewById(R.id.mMImageView_fan);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        clk_mTextView_up = (TextView) findViewById(R.id.clk_mTextView_up);
        clk_mTextView_next = (TextView) findViewById(R.id.clk_mTextView_next);

        clk_mTextView_up.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_next.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mMImageView_zheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.getPhoto(getActivity(), new PopUpdataPhoto.OnReceiverPhoto() {
                    @Override
                    public void onReceiverPhoto(String photoPath, int width,
                                                int height) {
                        if (photoPath != null) {
                            mMImageView_zheng.setObj("file:" + photoPath);
                            ByteString imgFile = null;
                            try {
                                imgFile = ByteString.of(com.framewidget.F
                                        .bitmap2Byte(photoPath));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            MFileList mList = new MFileList();
                            MFile mMFile = new MFile();
                            mList.file.clear();
                            mMFile.file = imgFile;
                            mMFile.fileName = "1.png";
                            mList.file.add(mMFile);
                            ApisFactory.getApiMUploadFile()
                                    .load(getContext(), FrgJiaruMd2.this,
                                            "MUploadFile1", mList);
                        }
                    }
                }, 86, 54, 640, 640);
            }
        });
        mMImageView_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.getPhoto(getActivity(), new PopUpdataPhoto.OnReceiverPhoto() {
                    @Override
                    public void onReceiverPhoto(String photoPath, int width,
                                                int height) {
                        if (photoPath != null) {
                            mMImageView_fan.setObj("file:" + photoPath);
                            ByteString imgFile = null;
                            try {
                                imgFile = ByteString.of(com.framewidget.F
                                        .bitmap2Byte(photoPath));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            MFileList mList = new MFileList();
                            MFile mMFile = new MFile();
                            mList.file.clear();
                            mMFile.file = imgFile;
                            mMFile.fileName = "1.png";
                            mList.file.add(mMFile);
                            ApisFactory.getApiMUploadFile()
                                    .load(getContext(), FrgJiaruMd2.this,
                                            "MUploadFile2", mList);
                        }
                    }
                }, 86, 54, 640, 640);
            }
        });
    }

    public void MUploadFile1(Son s) {
        MRet mModel = (MRet) s.getBuild();
        mMJoinStore.two.idCardImg = mModel.msg;
    }

    public void MUploadFile2(Son s) {
        MRet mModel = (MRet) s.getBuild();
        mMJoinStore.two.idCardReverse = mModel.msg;
    }

    public void loaddata() {
        if(mMJoinStore.two!=null){
            if (!TextUtils.isEmpty(mMJoinStore.two.idCardImg)) {
                mMImageView_zheng.setObj(mMJoinStore.two.idCardImg);
            }
            if (!TextUtils.isEmpty(mMJoinStore.two.idCardImg)) {
                mMImageView_fan.setObj(mMJoinStore.two.idCardReverse);
            }
        }else{
            mMJoinStore.two=new MJoinStoreCard();
        }
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_up == v.getId()) {
            this.finish();
        } else if (R.id.clk_mTextView_next == v.getId()) {
            if (TextUtils.isEmpty(mMJoinStore.two.idCardImg)) {
                Helper.toast("请上传身份证正面照", getContext());
                return;
            }
            if (TextUtils.isEmpty(mMJoinStore.two.idCardReverse)) {
                Helper.toast("请上传身份证反面照", getContext());
                return;
            }
            Helper.saveBuilder(F.UserId + "mMJoinStore", mMJoinStore);
           Frame.HANDLES.sentAll("FrgJiaruMd1,FrgJiaruMd2,FrgJiaruMd3,FrgJiaruMd4", 110, null);
            Helper.startActivity(getContext(), FrgJiaruMd3.class, TitleAct.class);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("加入地标门店");
        mHeadlayout.setRText("暂时略过");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgJiaruMd2.this.finish();
                Frame.HANDLES.sentAll("FrgJiaruMd1", 0, null);
            }
        });
    }
}