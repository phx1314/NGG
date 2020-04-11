//
//  ShangchengDuihuanjilu
//
//  Created by DELL on 2017-04-01 15:46:12
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntdlg.ngg.R;
import com.udows.common.proto.MCreditExchangeLog;


public class ShangchengDuihuanjilu extends BaseItem {
    public TextView mTextView_name;
    public TextView mTextView_time;
    public TextView mTextView_ggd;
    public TextView mTextView_price;
    public TextView mTextView_type;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_shangcheng_duihuanjilu, null);
        convertView.setTag(new ShangchengDuihuanjilu(convertView));
        return convertView;
    }

    public ShangchengDuihuanjilu(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_ggd = (TextView) contentview.findViewById(R.id.mTextView_ggd);
        mTextView_price = (TextView) contentview.findViewById(R.id.mTextView_price);
        mTextView_type = (TextView) contentview.findViewById(R.id.mTextView_type);


    }

    public void set(MCreditExchangeLog item) {
        mTextView_name.setText(item.name);
        mTextView_time.setText(item.time);
        mTextView_ggd.setText(item.credit + "");
        mTextView_price.setText("￥" + item.price);
        mTextView_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
        if (item.state == 2) {
            mTextView_type.setText("兑换成功");
        } else if (item.state == 3) {
            mTextView_type.setText("兑换失败");
        }else if (item.state == 1) {
            mTextView_type.setText("兑换审核中");
        }

    }


}