//
//  FrgWzhDingdanDetail
//
//  Created by df on 2017-03-09 16:29:18
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.frg;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.dialog.PhotoShow;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.item.ShouyeImg;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCropOutpatient;

import java.util.Arrays;

import static com.ntdlg.ngg.F.dip2px;


public class FrgWzhDingdanDetail extends BaseFrg {

    public LinearLayout mLinearLayout_state;
    public ImageView mImageView_state;
    public TextView mTextView_state;
    public TextView mTextView_state2;
    public TextView mTextView_code;
    public TextView mTextView_name;
    public TextView mTextView_content;
    public TextView mTextView_remark;
    public LinearLayout mLinearLayout_caozuo;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_shouhuoren;
    public TextView mTextView_phone;
    public TextView mTextView_address;
    public MCropOutpatient item;
    public com.framewidget.view.FixGridLayout mFixGridLayout;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = (MCropOutpatient) getActivity().getIntent().getSerializableExtra("item");
        setContentView(R.layout.frg_wzh_dingdan_detail);
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
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_content = (TextView) findViewById(R.id.mTextView_content);
        mTextView_remark = (TextView) findViewById(R.id.mTextView_remark);
        mLinearLayout_caozuo = (LinearLayout) findViewById(R.id.mLinearLayout_caozuo);
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mTextView_shouhuoren = (TextView) findViewById(R.id.mTextView_shouhuoren);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);
        mFixGridLayout = (com.framewidget.view.FixGridLayout) findViewById(R.id.mFixGridLayout);
        mFixGridLayout.setDividerCol(dip2px(getContext(), 10));
        mFixGridLayout.setDividerLine(dip2px(getContext(), 10));

    }

    public void MCropOutpatientUpdate(Son s) {
        item.state = -1;
        loaddata();
        Frame.HANDLES.sentAll("FrgWodeMenzhengguahaodan", 0, null);
    }

    public void loaddata() {
        mTextView_code.setText("订单号：" + item.code);
        mTextView_remark.setText("合计￥100");
        mTextView_shouhuoren.setText("收货人：" + item.userName);
        mTextView_phone.setText("联系方式：" + item.userPhone);
        mTextView_address.setText("收货地址：" + item.address);
        mTextView_name.setText(item.name);
        mTextView_content.setText(item.content);
        if (!TextUtils.isEmpty(item.imgs)) {
            mFixGridLayout.removeAllViews();
            mFixGridLayout.setVisibility(View.VISIBLE);
            final String[] data = item.imgs.split(",");
            for (int i = 0; i < data.length; i++) {
                View view = ShouyeImg.getView(getContext(), null);
                mFixGridLayout.addView(view);
                ((ShouyeImg) view.getTag()).set(data[i]);
                final int position = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        PhotoShow mPhotoShow = new PhotoShow(getContext(), Arrays
                                .asList(data), data[position]);
                        mPhotoShow.show();
                    }
                });
            }
        } else {
            mFixGridLayout.setVisibility(View.GONE);
        }
        switch (item.state) {
            case -1:
                mTextView_state.setText("已取消");
                mTextView_state2.setText("已取消");
                mLinearLayout_state.setBackgroundColor(Color.parseColor("#E5E5E5"));
                mTextView_state.setTextColor(getResources().getColor(R.color.black));
                mTextView_state2.setTextColor(getResources().getColor(R.color.E4));
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
                        ApisFactory.getApiMCropOutpatientUpdate().load(getContext(), FrgWzhDingdanDetail.this, "MCropOutpatientUpdate", item.id, 1.0);
                    }
                });
                mTextView_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Helper.startActivity(getContext(), FrgXiadan.class, TitleAct.class, "orderId", item.id, "contetnt", item.content);
                    }
                });
                break;
            case 2:
                mTextView_state.setText("已付定金");
                mTextView_state2.setText("已付款成功");
                mLinearLayout_state.setBackgroundColor(Color.parseColor("#D2E4F0"));
                mTextView_state.setTextColor(getResources().getColor(R.color.A));
                mTextView_state2.setTextColor(getResources().getColor(R.color.E4));
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
            case 3:
                mTextView_state.setText("已付尾款");
                mTextView_state2.setText("服务已发出");
                mLinearLayout_state.setBackgroundColor(Color.parseColor("#D2E4F0"));
                mTextView_state.setTextColor(getResources().getColor(R.color.A));
                mTextView_state2.setTextColor(getResources().getColor(R.color.E4));
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("订单详情");
    }
}