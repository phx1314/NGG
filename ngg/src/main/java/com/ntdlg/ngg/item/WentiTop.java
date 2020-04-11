//
//  WentiTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framewidget.view.FixGridLayout;
import com.mdx.framework.dialog.PhotoShow;
import com.ntdlg.ngg.R;
import com.udows.common.proto.STopic;

import java.util.Arrays;

import static com.ntdlg.ngg.F.dip2px;


public class WentiTop extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_type;
    public TextView mTextView_address;
    public FixGridLayout mFixGridLayout;
    public TextView mTextView_content;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_wenti_top, null);
        convertView.setTag(new WentiTop(convertView));
        return convertView;
    }

    public WentiTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mTextView_type = (TextView) contentview.findViewById(R.id.mTextView_type);
        mTextView_address = (TextView) contentview.findViewById(R.id.mTextView_address);
        mFixGridLayout = (FixGridLayout) contentview.findViewById(R.id.mFixGridLayout);
        mTextView_content = (TextView) findViewById(R.id.mTextView_content);
        mFixGridLayout.setDividerCol(dip2px(context,10));
        mFixGridLayout.setDividerLine(dip2px(context,10));

    }

    public void set(STopic item) {
        mTextView_title.setText(item.title);
        mTextView_content.setText(item.content);
        if (TextUtils.isEmpty(item.label)) {
            mTextView_type.setVisibility(View.GONE);
        } else {
            mTextView_type.setVisibility(View.VISIBLE);
            mTextView_type.setText(item.label);
        }
        mTextView_address.setText(item.address);
        if (!TextUtils.isEmpty(item.imgs)) {
            mFixGridLayout.removeAllViews();
            mFixGridLayout.setVisibility(View.VISIBLE);
            final String[] data = item.imgs.split(",");
            for (int i = 0; i < data.length; i++) {
                View view = ShouyeImg.getView(context, null);
                mFixGridLayout.addView(view);
                ((ShouyeImg) view.getTag()).set(data[i] );
                final int position = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        PhotoShow mPhotoShow = new PhotoShow(context, Arrays
                                .asList(data), data[position]);
                        mPhotoShow.show();
                    }
                });
            }
        } else {
            mFixGridLayout.setVisibility(View.GONE);
        }
    }


}