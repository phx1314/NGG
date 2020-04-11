//
//  DizhiGuanli
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
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgAddAddress;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.MUserAddress;


public class DizhiGuanli extends BaseItem {
    public TextView mTextView_top;
    public TextView mTextView_address;
    public TextView mTextView_moren;
    public TextView mTextView_bianji;
    public TextView mTextView_del;
    public MUserAddress item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_dizhi_guanli, null);
        convertView.setTag(new DizhiGuanli(convertView));
        return convertView;
    }

    public DizhiGuanli(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_top = (TextView) contentview.findViewById(R.id.mTextView_top);
        mTextView_address = (TextView) contentview.findViewById(R.id.mTextView_address);
        mTextView_moren = (TextView) contentview.findViewById(R.id.mTextView_moren);
        mTextView_bianji = (TextView) contentview.findViewById(R.id.mTextView_bianji);
        mTextView_del = (TextView) contentview.findViewById(R.id.mTextView_del);

        mTextView_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApisFactory.getApiMDelMyAddress().load(context, DizhiGuanli.this, "MDelMyAddress", item.id);
            }
        });
        mTextView_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgAddAddress.class,
                        TitleAct.class, "item", item);
            }
        });
        mTextView_moren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.isDefault == 1) {
                } else {
                    ApisFactory.getApiMEditAddress().load(context, DizhiGuanli.this, "MEditAddress", item.area, item.address, item.phone, item.name, "225400", item.id, 1.0);
                }
            }
        });
    }

    public void MDelMyAddress(Son s) {
        Frame.HANDLES.sentAll("FrgDizhiGuanli", 0, null);
        Helper.toast("删除成功", context);
    }

    public void MEditAddress(Son s) {
        Frame.HANDLES.sentAll("FrgDizhiGuanli", 0, null);
    }

    public void set(MUserAddress item) {
        this.item = item;
        if (item.isDefault == 1) {
            mTextView_moren.setText("默认地址");
            mTextView_moren.setBackgroundResource(R.drawable.bt_dizhi_b);
        } else {
            mTextView_moren.setText("设为默认");
            mTextView_moren.setBackgroundResource(R.drawable.bt_dizhi_g);
        }
        mTextView_top.setText(item.name + " " + item.phone);
        mTextView_address.setText(item.address);
    }


}