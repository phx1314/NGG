//
//  WodeMenzhengguahaodan
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
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgXiadan;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MCropOutpatient;


public class WodeMenzhengguahaodan extends BaseItem {
    public TextView mTextView_ddh;
    public TextView mTextView_state;
    public TextView mTextView_title;
    public TextView mTextView_content;
    public TextView mTextView_remark;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public LinearLayout mLinearLayout_caozuo;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_wode_menzhengguahaodan, null);
        convertView.setTag(new WodeMenzhengguahaodan(convertView));
        return convertView;
    }

    public WodeMenzhengguahaodan(View view) {
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
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mTextView_content = (TextView) contentview.findViewById(R.id.mTextView_content);
        mTextView_remark = (TextView) contentview.findViewById(R.id.mTextView_remark);
        mTextView_1 = (TextView) contentview.findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) contentview.findViewById(R.id.mTextView_2);
        mLinearLayout_caozuo = (LinearLayout) findViewById(R.id.mLinearLayout_caozuo);


    }

    public void MCropOutpatientUpdate(Son s) {
        Frame.HANDLES.sentAll("FrgWodeMenzhengguahaodan", 0, null);
    }

    public void set(final MCropOutpatient item) {
        mTextView_ddh.setText("订单号：" + item.code);
        mTextView_title.setText(item.name);
        mTextView_content.setText(item.content);
        mTextView_remark.setText("合计￥" + item.price);

        switch (item.state) {
            case -1:
                mTextView_state.setText("已取消");
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
            case 1:
                mTextView_state.setText("待付款");
                mLinearLayout_caozuo.setVisibility(View.VISIBLE);
                mTextView_2.setText("付款");
                mTextView_2.setTextColor(context.getResources().getColor(R.color.white));
                mTextView_2.setBackgroundResource(R.drawable.bt_orange_n);
                mTextView_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApisFactory.getApiMCropOutpatientUpdate().load(context, WodeMenzhengguahaodan.this, "MCropOutpatientUpdate", item.id, 1.0);
                    }
                });
                mTextView_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Helper.startActivity(context, FrgXiadan.class, TitleAct.class, "orderId", item.id, "contetnt", item.content);
                    }
                });
                break;
            case 2:
                mTextView_state.setText("已付定金");
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
            case 3:
                mTextView_state.setText("已付尾款");
                mLinearLayout_caozuo.setVisibility(View.GONE);
                break;
        }
    }


}