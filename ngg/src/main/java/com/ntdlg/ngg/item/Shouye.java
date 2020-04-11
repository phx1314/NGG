//
//  Shouye
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
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.dialog.PhotoShow;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.frg.FrgJieda;
import com.udows.common.proto.STopic;

import java.util.Arrays;

import static com.ntdlg.ngg.F.dip2px;


public class Shouye extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_type;
    public TextView mTextView_address;
    public FixGridLayout mFixGridLayout;
    public MImageView mMImageView_touxiang;
    public TextView mTextView_name;
    public TextView mTextView_time;
    public TextView mTextView_pl;
    public TextView mTextView_jd;
    private String data[] = null;
    private STopic item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_shouye, null);
        convertView.setTag(new Shouye(convertView));
        return convertView;
    }

    public Shouye(View view) {
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
        mMImageView_touxiang = (MImageView) contentview.findViewById(R.id.mMImageView_touxiang);
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_pl = (TextView) contentview.findViewById(R.id.mTextView_pl);
        mTextView_jd = (TextView) contentview.findViewById(R.id.mTextView_jd);

        mTextView_jd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(context, FrgJieda.class, TitleAct.class, "mid", item.id);
            }
        });
        mFixGridLayout.setDividerCol(dip2px(context, 10));
    }

    public void set(STopic item) {
        this.item = item;
        mTextView_title.setText(item.title);
        if (TextUtils.isEmpty(item.label)) {
            mTextView_type.setVisibility(View.GONE);
        } else {
            mTextView_type.setVisibility(View.VISIBLE);
            mTextView_type.setText(item.label);
        }

        mTextView_address.setText(item.address);
        mTextView_time.setText(item.time);
        mTextView_pl.setText(item.commentCnt + "");
        if (item.lz != null) {
            mTextView_name.setText(item.lz.nickName);
            mMImageView_touxiang.setObj(item.lz.headImg);
        }
        mMImageView_touxiang.setCircle(true);
        if (!TextUtils.isEmpty(item.imgs)) {
            mFixGridLayout.removeAllViews();
            mFixGridLayout.setVisibility(View.VISIBLE);
            data = item.imgs.split(",");
            for (int i = 0; i < data.length; i++) {
                if (i < 3) {
                    View view = ShouyeImg.getView(context, null);
                    mFixGridLayout.addView(view);
                    ((ShouyeImg) view.getTag()).set(data[i], i, data.length);
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
            }
        } else {
            mFixGridLayout.setVisibility(View.GONE);
            data = null;
        }
    }


}