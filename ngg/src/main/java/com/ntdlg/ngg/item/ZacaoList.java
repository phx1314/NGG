//
//  ZacaoList
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

import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgZacaoDetail;
import com.ntdlg.ngg.view.ModelZaocao;


public class ZacaoList extends BaseItem {
    public MImageView mImageView_1;
    public TextView mTextView_1;
    public MImageView mImageView_2;
    public TextView mTextView_2;
    public LinearLayout mLinearLayout2;
    public LinearLayout mLinearLayout1;
    public ModelZaocao item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_zacao_list, null);
        convertView.setTag(new ZacaoList(convertView));
        return convertView;
    }

    public ZacaoList(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mImageView_1 = (MImageView) contentview.findViewById(R.id.mImageView_1);
        mTextView_1 = (TextView) contentview.findViewById(R.id.mTextView_1);
        mImageView_2 = (MImageView) contentview.findViewById(R.id.mImageView_2);
        mTextView_2 = (TextView) contentview.findViewById(R.id.mTextView_2);
        mLinearLayout2 = (LinearLayout) findViewById(R.id.mLinearLayout2);
        mLinearLayout1 = (LinearLayout) findViewById(R.id.mLinearLayout1);
        mLinearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgZacaoDetail.class, NoTitleAct.class, "id", item.mMZaCaoInfo.id);
            }
        });
        mLinearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgZacaoDetail.class, NoTitleAct.class, "id", item.mMZaCaoInfo2.id);
            }
        });

    }

    public void set(ModelZaocao item) {
        this.item = item;
        mImageView_1.setObj(item.mMZaCaoInfo.img);
        mTextView_1.setText(item.mMZaCaoInfo.name);
        if (item.mMZaCaoInfo2 != null) {
            mImageView_2.setObj(item.mMZaCaoInfo2.img);
            mTextView_2.setText(item.mMZaCaoInfo2.name);
            mLinearLayout2.setVisibility(View.VISIBLE);
        } else {
            mLinearLayout2.setVisibility(View.INVISIBLE);
        }
    }


}