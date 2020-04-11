//
//  WodeDd
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MOrderGoods;
import com.udows.common.proto.MOrderMini;


public class WodeDd extends BaseItem {
    public TextView mTextView_ddh;
    public TextView mTextView_state;
    public TextView mTextView_remark;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public LinearLayout mLinearLayout_content;
    public LinearLayout mLinearLayout_caozuo;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_wode_dd, null);
        convertView.setTag(new WodeDd(convertView));
        return convertView;
    }

    public WodeDd(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_ddh = (TextView) contentview.findViewById(R.id.mTextView_ddh);
        mTextView_state = (TextView) contentview.findViewById(R.id.mTextView_state);
        mTextView_remark = (TextView) contentview.findViewById(R.id.mTextView_remark);
        mTextView_1 = (TextView) contentview.findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) contentview.findViewById(R.id.mTextView_2);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mLinearLayout_caozuo = (LinearLayout) findViewById(R.id.mLinearLayout_caozuo);


    }

    public void MUpdateOrder(Son s) {
        Helper.toast("操作成功", context);
        Frame.HANDLES.sentAll("FrgWodeDd", 0, null);
    }

    public void set(final MOrderMini item) {
        mTextView_ddh.setText("订单号：" + item.code);
        mTextView_remark.setText("共" + item.total + "件商品，合计￥" + item.price);
        switch (item.state) {
            case -1:
                mTextView_state.setText("已取消");
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
            case 1:
                mTextView_state.setText("待付款");
                mLinearLayout_caozuo.setVisibility(View.VISIBLE);
                mTextView_1.setVisibility(View.VISIBLE);
                mTextView_2.setText("付款");
                mTextView_2.setTextColor(context.getResources().getColor(R.color.white));
                mTextView_2.setBackgroundResource(R.drawable.bt_orange_n);
                mTextView_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApisFactory.getApiMUpdateOrder().load(context, WodeDd.this, "MUpdateOrder", 1.0, item.id, "");
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
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
            case 3:
                mTextView_state.setText("已发货");
                mLinearLayout_caozuo.setVisibility(View.VISIBLE);
                mTextView_1.setVisibility(View.GONE);
                mTextView_2.setText("确认收货");
                mTextView_2.setTextColor(context.getResources().getColor(R.color.A));
                mTextView_2.setBackgroundResource(R.drawable.bt_order_l_b_n);
                mTextView_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApisFactory.getApiMUpdateOrder().load(context, WodeDd.this, "MUpdateOrder", 4.0, item.id, "");
                    }
                });
                break;
            case 4:
                mTextView_state.setText("已签收");
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
            case 5:
                mTextView_state.setText("已评价");
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
        }
        mLinearLayout_content.removeAllViews();
        for (MOrderGoods mMOrderGoods : item.detail) {
            View view = WodeDdSon.getView(context, null);
            ((WodeDdSon) view.getTag()).set(mMOrderGoods);
            mLinearLayout_content.addView(view);
        }
    }


}