//
//  FrgDingdanDetail
//
//  Created by Administrator on 2017-02-04 18:35:41
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.WodeDdSon;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MOrder;
import com.udows.common.proto.MOrderGoods;


public class FrgDingdanDetail extends BaseFrg {

    public LinearLayout mLinearLayout_state;
    public ImageView mImageView_state;
    public TextView mTextView_state;
    public TextView mTextView_state2;
    public TextView mTextView_code;
    public TextView mTextView_remark;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_shouhuoren;
    public TextView mTextView_phone;
    public TextView mTextView_address;
    public String id;
    public LinearLayout mLinearLayout_content;
    public LinearLayout mLinearLayout_caozuo;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_dingdan_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_state = (LinearLayout) findViewById(R.id.mLinearLayout_state);
        mImageView_state = (ImageView) findViewById(R.id.mImageView_state);
        mTextView_state = (TextView) findViewById(R.id.mTextView_state);
        mTextView_state2 = (TextView) findViewById(R.id.mTextView_state2);
        mTextView_code = (TextView) findViewById(R.id.mTextView_code);
        mTextView_remark = (TextView) findViewById(R.id.mTextView_remark);
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mTextView_shouhuoren = (TextView) findViewById(R.id.mTextView_shouhuoren);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mLinearLayout_caozuo = (LinearLayout) findViewById(R.id.mLinearLayout_caozuo);


    }

    public void loaddata() {
        ApisFactory.getApiMOrderInfo().load(getContext(), this, "MOrderInfo", id);
    }

    public void MUpdateOrder(Son s) {
        Helper.toast("操作成功", getContext());
        Frame.HANDLES.sentAll("FrgWodeDd", 0, null);
        ApisFactory.getApiMOrderInfo().load(getContext(), this, "MOrderInfo", id);
    }

    public void MOrderInfo(Son s) {
        final MOrder mMOrder = (MOrder) s.getBuild();
        mTextView_code.setText("订单号：" + mMOrder.code);
        mTextView_remark.setText("共" + mMOrder.total + "件商品，合计￥" + mMOrder.price);
        mTextView_shouhuoren.setText("收货人：" + mMOrder.address.name);
        mTextView_phone.setText("联系方式：" + mMOrder.address.phone);
        mTextView_address.setText("收货地址：" + mMOrder.address.address);
        switch (mMOrder.state) {
            case -1:
                mTextView_state.setText("已取消");
                mTextView_state2.setText("已取消");
                mLinearLayout_state.setBackgroundColor(Color.parseColor("#E5E5E5"));
                mTextView_state.setTextColor(getResources().getColor(R.color.black));
                mTextView_state2.setTextColor(getResources().getColor(R.color.E4));
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
            case 1:
                mTextView_state.setText("订单未付款");
                mTextView_state2.setText("超过3小时订单自动取消，请尽快付款");
                mLinearLayout_state.setBackgroundColor(Color.parseColor("#FFDEC3"));
                mTextView_state.setTextColor(getResources().getColor(R.color.B));
                mTextView_state2.setTextColor(getResources().getColor(R.color.E4));
                mLinearLayout_caozuo.setVisibility(View.VISIBLE);
                mTextView_1.setVisibility(View.VISIBLE);
                mTextView_2.setText("付款");
                mTextView_2.setTextColor(getResources().getColor(R.color.white));
                mTextView_2.setBackgroundResource(R.drawable.bt_orange_n);
                mTextView_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApisFactory.getApiMUpdateOrder().load(getContext(), FrgDingdanDetail.this, "MUpdateOrder", 1.0, mMOrder.id, "");
                    }
                });
                mTextView_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            case 2:
                mTextView_state.setText("待发货");
                mTextView_state2.setText("已付款成功");
                mLinearLayout_state.setBackgroundColor(Color.parseColor("#D2E4F0"));
                mTextView_state.setTextColor(getResources().getColor(R.color.A));
                mTextView_state2.setTextColor(getResources().getColor(R.color.E4));
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
            case 3:
                mTextView_state.setText("已发货");
                mTextView_state2.setText("商品已发出");
                mLinearLayout_state.setBackgroundColor(Color.parseColor("#D2E4F0"));
                mTextView_state.setTextColor(getResources().getColor(R.color.A));
                mTextView_state2.setTextColor(getResources().getColor(R.color.E4));
                mLinearLayout_caozuo.setVisibility(View.VISIBLE);
                mTextView_1.setVisibility(View.GONE);
                mTextView_2.setText("确认收货");
                mTextView_2.setTextColor(getResources().getColor(R.color.A));
                mTextView_2.setBackgroundResource(R.drawable.bt_order_l_b_n);
                mTextView_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApisFactory.getApiMUpdateOrder().load(getContext(), FrgDingdanDetail.this, "MUpdateOrder", 4.0, mMOrder.id, "");
                    }
                });
                break;
            case 4:
                mTextView_state.setText("已签收");
                mTextView_state2.setText("交易完成");
                mLinearLayout_state.setBackgroundColor(Color.parseColor("#E5E5E5"));
                mTextView_state.setTextColor(getResources().getColor(R.color.black));
                mTextView_state2.setTextColor(getResources().getColor(R.color.E4));
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
            case 5:
                mTextView_state.setText("已评价");
                mTextView_state2.setText("已评价");
                mLinearLayout_state.setBackgroundColor(Color.parseColor("#E5E5E5"));
                mTextView_state.setTextColor(getResources().getColor(R.color.black));
                mTextView_state2.setTextColor(getResources().getColor(R.color.E4));
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
        }
        mLinearLayout_content.removeAllViews();
        for (MOrderGoods mMOrderGoods : mMOrder.detail) {
            View view = WodeDdSon.getView(getContext(), null);
            ((WodeDdSon) view.getTag()).set(mMOrderGoods);
            mLinearLayout_content.addView(view);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("订单详情");
    }
}