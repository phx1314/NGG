//
//  FrgGoodDetail
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaGoodTopImg;
import com.ntdlg.ngg.item.GoodDetailImg;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MRet;
import com.udows.common.proto.MScGoods;

import java.util.Arrays;


public class FrgGoodDetail extends BaseFrg {

    public TextView mTextView_shoucang;
    public TextView mTextView_num;
    public TextView clk_mTextView_addcard;
    public String id;
    public MScGoods mMScGoods;
    public com.framewidget.view.DfCirleCurr mMImageView_bg;
    public ImageView clk_mImageView_back;
    public ImageView clk_mImageView_share;
    public TextView mTextView_title;
    public TextView mTextView_price;
    public TextView mTextView_content;
    public TextView mTextView_content1;
    public LinearLayout mLinearLayout1;
    public TextView mTextView_content2;
    public LinearLayout mLinearLayout2;
    public TextView mTextView_content3;
    public LinearLayout mLinearLayout3;
    public TextView mTextView_content4;
    public LinearLayout mLinearLayout4;
    public TextView mTextView_content5;
    public LinearLayout mLinearLayout5;
    public TextView mTextView_content6;
    public LinearLayout mLinearLayout6;
    public TextView mTextView_phone;
    public Handler mHandler = new Handler();
    public Runnable mRunnable;
    public MRet mMRet;
    public LinearLayout mRelativeLayout_cart;
    public LinearLayout mLinearLayout7;
    double count;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_good_detail);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                ApisFactory.getApiMShopCartNum().load(getContext(), this, "MShopCartNum");
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_shoucang = (TextView) findViewById(R.id.mTextView_shoucang);
        mTextView_num = (TextView) findViewById(R.id.mTextView_num);
        clk_mTextView_addcard = (TextView) findViewById(R.id.clk_mTextView_addcard);
        mMImageView_bg = (com.framewidget.view.DfCirleCurr) findViewById(R.id.mMImageView_bg);
        clk_mImageView_back = (ImageView) findViewById(R.id.clk_mImageView_back);
        clk_mImageView_share = (ImageView) findViewById(R.id.clk_mImageView_share);
        mTextView_title = (TextView) findViewById(R.id.mTextView_title);
        mTextView_price = (TextView) findViewById(R.id.mTextView_price);
        mTextView_content = (TextView) findViewById(R.id.mTextView_content);
        mTextView_content1 = (TextView) findViewById(R.id.mTextView_content1);
        mLinearLayout1 = (LinearLayout) findViewById(R.id.mLinearLayout1);
        mTextView_content2 = (TextView) findViewById(R.id.mTextView_content2);
        mLinearLayout2 = (LinearLayout) findViewById(R.id.mLinearLayout2);
        mTextView_content3 = (TextView) findViewById(R.id.mTextView_content3);
        mLinearLayout3 = (LinearLayout) findViewById(R.id.mLinearLayout3);
        mTextView_content4 = (TextView) findViewById(R.id.mTextView_content4);
        mLinearLayout4 = (LinearLayout) findViewById(R.id.mLinearLayout4);
        mTextView_content5 = (TextView) findViewById(R.id.mTextView_content5);
        mLinearLayout5 = (LinearLayout) findViewById(R.id.mLinearLayout5);
        mTextView_content6 = (TextView) findViewById(R.id.mTextView_content6);
        mLinearLayout6 = (LinearLayout) findViewById(R.id.mLinearLayout6);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mRelativeLayout_cart = (LinearLayout) findViewById(R.id.mRelativeLayout_cart);
        mLinearLayout7 = (LinearLayout) findViewById(R.id.mLinearLayout7);
        clk_mTextView_addcard.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mImageView_share.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mImageView_back.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_phone.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mRelativeLayout_cart.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_shoucang.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void loaddata() {
        mMImageView_bg.setFillColor(Color.parseColor("#FF552E"));
        mMImageView_bg.setPageColor(Color.parseColor("#6B5758"));
        ApisFactory.getApiMGoodsDetail().load(getContext(), this, "MGoodsDetail", id);
        ApisFactory.getApiMShopCartNum().load(getContext(), this, "MShopCartNum");


        mRunnable = new Runnable() {
            @Override
            public void run() {
                ApisFactory.getApiMAddShopCart().load(getContext(), this, "MAddShopCart", mMScGoods.id, 1.0);
            }
        };
    }

    public void MAddShopCart(Son s) {
    }

    public void MShopCartNum(Son s) {
        mMRet = (MRet) s.getBuild();
        if (!TextUtils.isEmpty(mMRet.msg) && Integer.valueOf(mMRet.msg) > 0) {
            count = Integer.valueOf(mMRet.msg);
            mTextView_num.setVisibility(View.VISIBLE);
            if (count > 99) {
                mTextView_num.setText("99");
            } else {
                mTextView_num.setText((int) count + "");
            }
        } else {
            count = 0;
            mTextView_num.setVisibility(View.GONE);
        }
    }

    public void MGoodsDetail(Son s) {
        this.mMScGoods = (MScGoods) s.getBuild();
        if (!TextUtils.isEmpty(mMScGoods.imgs)) {
            mMImageView_bg.setAdapter(new AdaGoodTopImg(getContext(), Arrays.asList(mMScGoods.imgs.split(","))));
        } else if (!TextUtils.isEmpty(mMScGoods.img)) {
            mMImageView_bg.setAdapter(new AdaGoodTopImg(getContext(), Arrays.asList(mMScGoods.img.split(","))));
        }
        if (mMScGoods.isCollect == 1) {
            mTextView_shoucang.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bt_shoucang_h, 0, 0);
            mTextView_shoucang.setText("已收藏");
        } else {
            mTextView_shoucang.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bt_shoucang_n, 0, 0);
            mTextView_shoucang.setText("收藏");
        }
        mTextView_title.setText(mMScGoods.title);
        mTextView_price.setText("￥" + mMScGoods.price);
        mTextView_content.setText("规格：" + mMScGoods.guige + "     运费：" + (TextUtils.isEmpty(mMScGoods.shipPirce) ? "" : mMScGoods.shipPirce) + "元");
        mTextView_phone.setText("客服电话：" + (TextUtils.isEmpty(mMScGoods.phone) ? "400-025-9116" : mMScGoods.phone));
        if (TextUtils.isEmpty(mMScGoods.info.info)) {
            mTextView_content1.setVisibility(View.GONE);
        } else {
            mTextView_content1.setText(mMScGoods.info.info);
        }
        if (TextUtils.isEmpty(mMScGoods.info.xiaoguo)) {
            mTextView_content2.setVisibility(View.GONE);
        } else {
            mTextView_content2.setText(mMScGoods.info.xiaoguo);
        }
        if (TextUtils.isEmpty(mMScGoods.info.product)) {
            mTextView_content3.setVisibility(View.GONE);
        } else {
            mTextView_content3.setText(mMScGoods.info.product);
        }
        if (TextUtils.isEmpty(mMScGoods.info.zhuYi)) {
            mTextView_content4.setVisibility(View.GONE);
        } else {
            mTextView_content4.setText(mMScGoods.info.zhuYi);
        }
        if (TextUtils.isEmpty(mMScGoods.info.zhongDu)) {
            mTextView_content5.setVisibility(View.GONE);
        } else {
            mTextView_content5.setText(mMScGoods.info.zhongDu);
        }
        if (TextUtils.isEmpty(mMScGoods.info.chuCun)) {
            mTextView_content6.setVisibility(View.GONE);
        } else {
            mTextView_content6.setText(mMScGoods.info.chuCun);
        }
        setImg(mMScGoods.info.infoImgs, mLinearLayout1);
        setImg(mMScGoods.info.xiaoGuoImgs, mLinearLayout2);
        setImg(mMScGoods.info.productImgs, mLinearLayout3);
        setImg(mMScGoods.info.zhuYiImgs, mLinearLayout4);
        setImg(mMScGoods.info.zhongDuImgs, mLinearLayout5);
        setImg(mMScGoods.info.chuCunImgs, mLinearLayout6);
        setImg(mMScGoods.info.cangKu, mLinearLayout7);
    }

    public void setImg(String imgs, LinearLayout mLinearLayout) {
        if (!TextUtils.isEmpty(imgs)) {
            for (String str : Arrays.asList(imgs.split(","))) {
                View view = GoodDetailImg.getView(getContext(), null);
                ((GoodDetailImg) view.getTag()).set(str);
                mLinearLayout.addView(view);
            }
            mLinearLayout.setVisibility(View.VISIBLE);
        } else {
            mLinearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mTextView_addcard == v.getId()) {
            if (count < mMScGoods.total) {
                count++;
                mTextView_num.setVisibility(View.VISIBLE);
                if (count > 99) {
                    mTextView_num.setText("99");
                } else {
                    mTextView_num.setText("" + (int) count);
                }
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 400);
            } else {
                Helper.toast("库存不足", getContext());
            }
        } else if (R.id.clk_mImageView_back == v.getId()) {
            FrgGoodDetail.this.finish();
        } else if (R.id.clk_mImageView_share == v.getId()) {
//            F.getShare();
        } else if (R.id.mTextView_phone == v.getId()) {
            if (TextUtils.isEmpty(mTextView_phone.getText().toString())) {
                Helper.toast("暂无联系电话", getContext());
                return;
            }
            Intent it = new Intent();
            it.setAction(Intent.ACTION_DIAL);
            it.setData(Uri.parse("tel:" + mMScGoods.phone));
            getContext().startActivity(it);
        } else if (R.id.mRelativeLayout_cart == v.getId()) {
            Helper.startActivity(getContext(), Intent.FLAG_ACTIVITY_CLEAR_TOP, FrgCart.class, TitleAct.class);
        } else if (R.id.mTextView_shoucang == v.getId()) {
            if (mMScGoods.isCollect == 1) {
                ApisFactory.getApiMGoodsCollect().load(getContext(), this, "MGoodsCollect", id, 0.0);
                mMScGoods.isCollect = 0;
            } else {
                ApisFactory.getApiMGoodsCollect().load(getContext(), this, "MGoodsCollect", id, 1.0);
                mMScGoods.isCollect = 1;
            }

        }
    }

    public void MGoodsCollect(Son s) {
        if (mMScGoods.isCollect == 1) {
            mTextView_shoucang.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bt_shoucang_h, 0, 0);
            mTextView_shoucang.setText("已收藏");
        } else {
            mTextView_shoucang.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bt_shoucang_n, 0, 0);
            mTextView_shoucang.setText("收藏");
        }
    }

}