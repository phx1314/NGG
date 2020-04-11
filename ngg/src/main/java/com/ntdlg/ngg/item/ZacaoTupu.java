//
//  ZacaoTupu
//
//  Created by df on 2017-03-13 13:28:39
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.MCategory;

import java.util.List;


public class ZacaoTupu extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView;
    public List<MCategory> list;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_zacao_tupu, null);
        convertView.setTag(new ZacaoTupu(convertView));
        return convertView;
    }

    public ZacaoTupu(View view) {
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
        mTextView = (TextView) contentview.findViewById(R.id.mTextView);


    }

    public void set(MCategory item, int position, List<MCategory> list) {
        this.list=list;
        mTextView.setText(item.name);
        mTextView_title.setText(F.toPinYin(item.name.charAt(0)).toUpperCase().charAt(0) + "");
        if (position == getPositionForSection(F.toPinYin(item.name.charAt(0)).charAt(0))) {
            mTextView_title.setVisibility(View.VISIBLE);
        } else {
            mTextView_title.setVisibility(View.GONE);
        }
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < list.size(); i++) {
            String sortStr = F.toPinYin(list.get(i).name.charAt(0));
            char firstChar = sortStr.charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    public void set(MCategory item) {
        mTextView.setText(item.name);
        mTextView_title.setVisibility(View.GONE);
    }


}