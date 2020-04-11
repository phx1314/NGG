//
//  FrgXiugaiGerenziliao
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.frg.FrgPubBianJi;
import com.framewidget.view.Pois;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MImageView;
import com.mdx.framework.widget.getphoto.PopUpdataPhoto;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.act.ActWmChooseAddress;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCategory;
import com.udows.common.proto.MFile;
import com.udows.common.proto.MFileList;
import com.udows.common.proto.MRet;
import com.udows.common.proto.SUser;

import java.util.List;

import okio.ByteString;


public class FrgXiugaiGerenziliao extends BaseFrg {

    public MImageView mMImageView_tou;
    public TextView mTextView_name;
    public TextView mTextView_yqm;
    public TextView textView3;
    public TextView mTextView_address;
    public TextView mTextView_zhiye;
    public TextView mTextView_zuowu;
    public TextView mTextView_mianji;
    public TextView mTextView_phone;
    public LinearLayout clk_shouhuo_address;
    public TextView mTextView_shouhuo_address;
    public LinearLayout clk_mLinearLayout_xiugai;
    public TextView mTextView_mima;
    public LinearLayout mLinearLayout_nicheng;
    public LinearLayout mLinearLayout_address;
    public LinearLayout mLinearLayout_zhiye;
    public LinearLayout mLinearLayout_zuowu;
    public LinearLayout mLinearLayout_mianji;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_xiugai_gerenziliao);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 101:
                mTextView_name.setText(obj.toString());
                ApisFactory.getApiMUpdateUser().load(getContext(), this, "MUpdateUser", obj.toString(), null, null, null, null, null, null, null);
                break;
            case 102:
                mTextView_zhiye.setText(obj.toString());
                ApisFactory.getApiMUpdateUser().load(getContext(), this, "MUpdateUser", null, null, obj.toString(), null, null, null, null, null);
                break;
            case 103:
                mTextView_zuowu.setText(obj.toString());
                ApisFactory.getApiMUpdateUser().load(getContext(), this, "MUpdateUser", null, null, null, obj.toString(), null, null, null, null);
                break;
            case 104:
                mTextView_mianji.setText(obj.toString());
                ApisFactory.getApiMUpdateUser().load(getContext(), this, "MUpdateUser", null, null, null, null, obj.toString(), null, null, null);
                break;
            case 3:
                Pois poi = (Pois) obj;
                mTextView_address.setText(poi.getTitle());
                ApisFactory.getApiMUpdateUser().load(getContext(), this, "MUpdateUser", null, null, null, null, null, poi.getTitle(), poi.getLat(), poi.getLng());//缺省地址修改
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
                mTextView_zuowu.setText(aa);
                ApisFactory.getApiMUpdateUser().load(getContext(), this, "MUpdateUser", null, null, null, aa, null, null, null, null);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMImageView_tou = (MImageView) findViewById(R.id.mMImageView_tou);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_yqm = (TextView) findViewById(R.id.mTextView_yqm);
        textView3 = (TextView) findViewById(R.id.textView3);
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);
        mTextView_zhiye = (TextView) findViewById(R.id.mTextView_zhiye);
        mTextView_zuowu = (TextView) findViewById(R.id.mTextView_zuowu);
        mTextView_mianji = (TextView) findViewById(R.id.mTextView_mianji);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        clk_shouhuo_address = (LinearLayout) findViewById(R.id.clk_shouhuo_address);
        mTextView_shouhuo_address = (TextView) findViewById(R.id.mTextView_shouhuo_address);
        clk_mLinearLayout_xiugai = (LinearLayout) findViewById(R.id.clk_mLinearLayout_xiugai);
        mTextView_mima = (TextView) findViewById(R.id.mTextView_mima);
        mLinearLayout_nicheng = (LinearLayout) findViewById(R.id.mLinearLayout_nicheng);
        mLinearLayout_address = (LinearLayout) findViewById(R.id.mLinearLayout_address);
        mLinearLayout_zhiye = (LinearLayout) findViewById(R.id.mLinearLayout_zhiye);
        mLinearLayout_zuowu = (LinearLayout) findViewById(R.id.mLinearLayout_zuowu);
        mLinearLayout_mianji = (LinearLayout) findViewById(R.id.mLinearLayout_mianji);

        clk_shouhuo_address.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_xiugai.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_nicheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgPubBianJi.class,
                        TitleAct.class, "from", "FrgXiugaiGerenziliao", "EDT", 101,
                        "data", mTextView_name.getText().toString(), "max", 6, "hint", "姓名");
            }
        });
        mLinearLayout_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ActWmChooseAddress.class).putExtra("from", "FrgXiugaiGerenziliao"));
            }
        });
        mLinearLayout_zhiye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgJobList.class, TitleAct.class, "from", "FrgXiugaiGerenziliao");
//                Helper.startActivity(getContext(), FrgPubBianJi.class,
//                        TitleAct.class, "from", "FrgXiugaiGerenziliao", "EDT", 102,
//                        "data", mTextView_zhiye.getText().toString(), "max", 10, "hint", "职业");
            }
        });
        mLinearLayout_zuowu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgShanchangZuowu.class, TitleAct.class, "from", "FrgXiugaiGerenziliao");
//                Helper.startActivity(getContext(), FrgPubBianJi.class,
//                        TitleAct.class, "from", "FrgXiugaiGerenziliao", "EDT", 103,
//                        "data", mTextView_zuowu.getText().toString(), "max", 150, "hint", "种植作物");
            }
        });
        mLinearLayout_mianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgPubBianJi.class,
                        TitleAct.class, "from", "FrgXiugaiGerenziliao", "EDT", 104,
                        "data", mTextView_mianji.getText().toString(), "max", 10, "hint", "种植面积");
            }
        });
        mMImageView_tou.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.getPhoto(getActivity(), new PopUpdataPhoto.OnReceiverPhoto() {
                    @Override
                    public void onReceiverPhoto(String photoPath, int width,
                                                int height) {
                        if (photoPath != null) {
                            mMImageView_tou.setObj("file:" + photoPath);
                            mMImageView_tou.setCircle(true);
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
                                    .load(getContext(), FrgXiugaiGerenziliao.this,
                                            "MUploadFile", mList);
                        }
                    }
                }, 10, 10, 640, 640);
            }
        }));

    }

    public void MUploadFile(Son s) {
        MRet mModel = (MRet) s.getBuild();
        ApisFactory.getApiMUpdateUser().load(getContext(), this, "MUpdateUser", null, mModel.msg, null, null, null, null, null, null);
    }

    public void MUpdateUser(Son s) {
        Frame.HANDLES.sentAll("FrgWode", 0, null);
    }


    public void loaddata() {
        ApisFactory.getApiSGetUserInfo().load(getContext(), this, "SGetUserInfo", F.UserId);
    }

    public void SGetUserInfo(Son s) {
        SUser mSUser = (SUser) s.getBuild();
        mMImageView_tou.setObj(mSUser.headImg);
        mMImageView_tou.setCircle(true);
        mTextView_name.setText(mSUser.nickName);
        mTextView_yqm.setText(mSUser.id);
        mTextView_address.setText(mSUser.area);
        mTextView_zhiye.setText(mSUser.job);
        mTextView_mianji.setText(mSUser.vast);
        mTextView_phone.setText(mSUser.phone);
        mTextView_zuowu.setText(mSUser.crop);
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_shouhuo_address == v.getId()) {
            Helper.startActivity(getContext(), FrgDizhiGuanli.class,
                    TitleAct.class, "from", "FrgXiugaiGerenziliao");
        } else if (R.id.clk_mLinearLayout_xiugai == v.getId()) {
            Helper.startActivity(getContext(), FrgYsltXiugaiMima.class,
                    TitleAct.class);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("修改个人资料");
    }
}