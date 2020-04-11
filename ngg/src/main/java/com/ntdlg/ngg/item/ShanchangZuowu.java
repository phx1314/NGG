//
//  ShanchangZuowu
//
//  Created by df on 2017-02-09 15:44:30
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaShanchangZuowu;
import com.udows.common.proto.MCategory;


public class ShanchangZuowu extends BaseItem {
    public LinearLayout mLinearLayout;
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_shanchang_zuowu, null);
        convertView.setTag(new ShanchangZuowu(convertView));
        return convertView;
    }

    public ShanchangZuowu(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout = (LinearLayout) contentview.findViewById(R.id.mLinearLayout);
        mTextView = (TextView) findViewById(R.id.mTextView);


    }

    public void set(MCategory item, final AdaShanchangZuowu mAdaShanchangZuowu) {
        mTextView.setText(item.name);
        mLinearLayout.removeAllViews();
        for (final MCategory mMCategory : item.son) {
            View view = ShanchangZuowuSon.getView(context, null);
            ((ShanchangZuowuSon) view.getTag()).set(mMCategory);
            mLinearLayout.addView(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!TextUtils.isEmpty(mMCategory.icon)&&mMCategory.icon.equals("1")) {
                        mMCategory.icon = "0";
                        Frame.HANDLES.sentAll("FrgShanchangZuowu", 1, mMCategory);
                    } else {
                        mMCategory.icon = "1";
                        Frame.HANDLES.sentAll("FrgShanchangZuowu", 0, mMCategory);
                    }
                    mAdaShanchangZuowu.notifyDataSetChanged();
                }
            });
        }
    }


}