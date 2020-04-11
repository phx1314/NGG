//
//  Nyq
//
//  Created by df on 2017-02-10 14:53:54
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.view.FixGridLayout;
import com.mdx.framework.Frame;
import com.mdx.framework.dialog.PhotoShow;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.SReply;
import com.udows.common.proto.STopic;

import java.util.Arrays;

import static com.ntdlg.ngg.F.dip2px;


public class Nyq extends BaseItem {
    public TextView mTextView_name;
    public TextView mTextView_guanzhu;
    public TextView mTextView_content;
    public FixGridLayout mFixGridLayout;
    public TextView mTextView_time;
    public TextView mTextView_kan;
    public TextView mTextView_hf;
    public LinearLayout mLinearLayout_content;
    public TextView mTextView_more;
    public LinearLayout mLinearLayout_name;
    public STopic item;
    public LinearLayout mLinearLayout_content_xia;
    public TextView mTextView_del;
    private String data[] = null;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_nyq, null);
        convertView.setTag(new Nyq(convertView));
        return convertView;
    }

    public Nyq(View view) {
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
        mTextView_guanzhu = (TextView) contentview.findViewById(R.id.mTextView_guanzhu);
        mTextView_content = (TextView) contentview.findViewById(R.id.mTextView_content);
        mFixGridLayout = (FixGridLayout) contentview.findViewById(R.id.mFixGridLayout);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_kan = (TextView) contentview.findViewById(R.id.mTextView_kan);
        mTextView_hf = (TextView) contentview.findViewById(R.id.mTextView_hf);
        mLinearLayout_content = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_content);
        mTextView_more = (TextView) contentview.findViewById(R.id.mTextView_more);
        mLinearLayout_name = (LinearLayout) findViewById(R.id.mLinearLayout_name);
        mLinearLayout_content_xia = (LinearLayout) findViewById(R.id.mLinearLayout_content_xia);
        mTextView_del = (TextView) findViewById(R.id.mTextView_del);

        mTextView_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApisFactory.getApiSDelCircle().load(context, Nyq.this, "SDelCircle", item.id);
            }
        });
        mFixGridLayout.setDividerLine(dip2px(context, 5));
        mFixGridLayout.setDividerCol(dip2px(context, 5));
    }

    public void SDelCircle(Son s) {
        Helper.toast("删除成功", context);
        Frame.HANDLES.sentAll("FrgWodeDt,FrgNyq", 0, null);
    }

    public void set(STopic item, int isWodeType) {
        this.item = item;
        if (isWodeType == 1) {
            mLinearLayout_name.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 0);
            mLinearLayout_content_xia.setLayoutParams(layoutParams);
        }
        mTextView_name.setText(item.lz.nickName);
        mTextView_content.setText(item.content);
        mTextView_time.setText(item.time + " " + item.address);
        if (item.lz.id.equals(F.UserId)) {
            mTextView_del.setVisibility(View.VISIBLE);
        } else {
            mTextView_del.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(item.imgs)) {
            mFixGridLayout.removeAllViews();
            mFixGridLayout.setVisibility(View.VISIBLE);
            data = item.imgs.split(",");
            for (int i = 0; i < data.length; i++) {
                View view = ShouyeImg.getView(context, null);
                mFixGridLayout.addView(view);
                ((ShouyeImg) view.getTag()).set(data[i]);
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
            data = null;
        }
        mTextView_kan.setText(item.praiseCnt + "");
        if (item.replyCnt > 2) {
            mTextView_more.setVisibility(View.VISIBLE);
        } else {
            mTextView_more.setVisibility(View.GONE);
        }
        mLinearLayout_content.removeAllViews();
        if (item.replys.size() > 0) {
            mLinearLayout_content.setVisibility(View.VISIBLE);
            for (SReply mSReply : item.replys) {
                if (mLinearLayout_content.getChildCount() < 2) {
                    TextView mTextView = new TextView(context);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, (int) context.getResources().getDimension(R.dimen.j5dp), 0, 0);
                    mTextView.setLayoutParams(layoutParams);
                    mTextView.setText(Html.fromHtml("<font color='" + context.getResources().getColor(R.color.A) + "'>" + mSReply.nickName + "</font>回复" + mSReply.targetName + ":" + mSReply.content));
                    mLinearLayout_content.addView(mTextView);
                } else {
                    break;
                }
            }
        } else {
            mLinearLayout_content.setVisibility(View.GONE);
        }
    }


}