//
//  Wenti
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.view.CallBackOnly;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.dialog.PhotoShow;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaWenti;
import com.ntdlg.ngg.frg.FrgWentiDetail;
import com.ntdlg.ngg.frg.FrgZhuanjiaZhuye;
import com.ntdlg.ngg.view.CallBackQiTa;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.SReply;
import com.udows.common.proto.STopic;

import java.util.Arrays;

import static com.ntdlg.ngg.F.dip2px;


public class Wenti extends BaseItem {
    public MImageView mMImageView_touxiang;
    public ImageView mImageView_rzh;
    public ImageView mImageView_qbyh;
    public ImageView mImageView_dbm;
    public TextView mTextView_time;
    public TextView mTextView_content;
    public LinearLayout mLinearLayout_content;
    public LinearLayout clk_mLinearLayout_fandui;
    public TextView mTextView_fd;
    public LinearLayout clk_mLinearLayout_zantong;
    public TextView mTextView_zt;
    public LinearLayout clk_mLinearLayout_bianji;
    public TextView mTextView_name;
    public com.framewidget.view.FixGridLayout mFixGridLayout;
    public AdaWenti mAdaWenti;
    public STopic item;
    public int position;
    public ImageView mImageView_bianji;
    public CallBackQiTa mCallBackQiTa;
    public LinearLayout mLinearLayout_hf;
    public ImageView mImageView_lz;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_wenti, null);
        convertView.setTag(new Wenti(convertView));
        return convertView;
    }

    public Wenti(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mMImageView_touxiang = (MImageView) contentview.findViewById(R.id.mMImageView_touxiang);
        mImageView_rzh = (ImageView) contentview.findViewById(R.id.mImageView_rzh);
        mImageView_qbyh = (ImageView) contentview.findViewById(R.id.mImageView_qbyh);
        mImageView_dbm = (ImageView) contentview.findViewById(R.id.mImageView_dbm);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_content = (TextView) contentview.findViewById(R.id.mTextView_content);
        mLinearLayout_content = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_content);
        clk_mLinearLayout_fandui = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout_fandui);
        mTextView_fd = (TextView) contentview.findViewById(R.id.mTextView_fd);
        clk_mLinearLayout_zantong = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout_zantong);
        mTextView_zt = (TextView) contentview.findViewById(R.id.mTextView_zt);
        clk_mLinearLayout_bianji = (LinearLayout) contentview.findViewById(R.id.clk_mLinearLayout_bianji);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mFixGridLayout = (com.framewidget.view.FixGridLayout) findViewById(R.id.mFixGridLayout);
        mImageView_bianji = (ImageView) findViewById(R.id.mImageView_bianji);
        mLinearLayout_hf = (LinearLayout) findViewById(R.id.mLinearLayout_hf);
        mImageView_lz = (ImageView) findViewById(R.id.mImageView_lz);

        clk_mLinearLayout_fandui.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_zantong.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mLinearLayout_bianji.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mMImageView_touxiang.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageView_rzh.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageView_qbyh.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageView_dbm.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

        mFixGridLayout.setDividerCol(dip2px(context, 10));
        mFixGridLayout.setDividerLine(dip2px(context, 10));
    }

    public void set(STopic item, AdaWenti mAdaWenti, int position, CallBackQiTa mCallBackQiTa) {
        this.item = item;
        this.position = position;
        this.mAdaWenti = mAdaWenti;
        this.mCallBackQiTa = mCallBackQiTa;
        mMImageView_touxiang.setObj(item.lz.headImg);
        if (item.lz.id.equals(F.mSUser.id)) {
            mMImageView_touxiang.setObj(F.mSUser.headImg);
            mImageView_lz.setVisibility(View.VISIBLE);
            mMImageView_touxiang.setCircle(true);
        }else{
            mImageView_lz.setVisibility(View.GONE);
        }
        if (item.lz.id.equals(FrgWentiDetail.mSTopicDetail.lz.id)) {
            mImageView_lz.setVisibility(View.VISIBLE);
        }else{
            mImageView_lz.setVisibility(View.GONE);
        }
        mTextView_name.setText(item.lz.nickName);
        mTextView_time.setText(item.time);
        mTextView_content.setText(item.content);
        mTextView_zt.setText("赞同（" + item.praiseCnt + "）");
        mTextView_fd.setText("反对（" + item.praiseNoCnt + "）");
        if (item.isPraised == 1) {
            mTextView_zt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_zan_h, 0, 0, 0);
            mTextView_fd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_fan_n, 0, 0, 0);
            mTextView_zt.setTextColor(context.getResources().getColor(R.color.B));
            mTextView_fd.setTextColor(context.getResources().getColor(R.color.gray));
        } else if (item.isPraised == 2) {
            mTextView_fd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_fan_h, 0, 0, 0);
            mTextView_zt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_zan_n, 0, 0, 0);
            mTextView_zt.setTextColor(context.getResources().getColor(R.color.gray));
            mTextView_fd.setTextColor(context.getResources().getColor(R.color.B));
        } else {
            mTextView_zt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_zan_n, 0, 0, 0);
            mTextView_fd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bt_fan_n, 0, 0, 0);
            mTextView_zt.setTextColor(context.getResources().getColor(R.color.gray));
            mTextView_fd.setTextColor(context.getResources().getColor(R.color.gray));
        }
        if (item.lz.isPro == 1) {
            mImageView_rzh.setVisibility(View.VISIBLE);
        } else {
            mImageView_rzh.setVisibility(View.GONE);
        }
        if (item.lz.isOffice == 1) {
            mImageView_qbyh.setVisibility(View.VISIBLE);
        } else {
            mImageView_qbyh.setVisibility(View.GONE);
        }
        if (item.lz.isStore == 1) {
            mImageView_dbm.setVisibility(View.VISIBLE);
        } else {
            mImageView_dbm.setVisibility(View.GONE);
        }
        if (!item.lz.id.equals(F.UserId)) {
            mImageView_bianji.setVisibility(View.GONE);
            clk_mLinearLayout_bianji.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(item.imgs)) {
            mFixGridLayout.removeAllViews();
            mFixGridLayout.setVisibility(View.VISIBLE);
            final String[] data = item.imgs.split(",");
            for (int i = 0; i < data.length; i++) {
                if (i < 3) {
                    View view = ShouyeImg.getView(context, null);
                    mFixGridLayout.addView(view);
                    ((ShouyeImg) view.getTag()).set(data[i], i, data.length);
                    final int pos = i;
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            PhotoShow mPhotoShow = new PhotoShow(context, Arrays
                                    .asList(data), data[pos]);
                            mPhotoShow.show();
                        }
                    });
                }
            }
        } else {
            mFixGridLayout.setVisibility(View.GONE);
        }
        mLinearLayout_content.removeAllViews();
        if (item.replys.size() > 0) {
            mLinearLayout_content.setVisibility(View.VISIBLE);
            for (SReply mSReply : item.replys) {
                TextView mTextView = new TextView(context);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(0, (int) context.getResources().getDimension(R.dimen.j5dp), 0, 0);
                mTextView.setLayoutParams(layoutParams);
                mTextView.setText(F.go2Text(item, mSReply, context, mCallBackQiTa));
                mTextView.setMovementMethod(LinkMovementMethod.getInstance());
                mLinearLayout_content.addView(mTextView);
            }
        } else {
            mLinearLayout_content.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(android.view.View v) {
        if (R.id.clk_mLinearLayout_fandui == v.getId()) {
            if (item.isPraised == 0) {
                ApisFactory.getApiSUserReplyPraise().load(context, this, "SEditCommentPraiseAddDao", item.id, 2.0, 1.0);
            }
        } else if (R.id.clk_mLinearLayout_zantong == v.getId()) {
            if (item.isPraised == 0) {
                ApisFactory.getApiSUserReplyPraise().load(context, this, "SEditCommentPraiseAddZan", item.id, 1.0, 1.0);
            }
        } else if (R.id.clk_mLinearLayout_bianji == v.getId()) {
            final View view = DialogBianji.getView(context, null);
            com.framewidget.F.showBottomDialog(context, view, new CallBackOnly() {
                @Override
                public void goReturnDo(Dialog mDialog) {
                    ((DialogBianji) view.getTag()).set(mDialog, item, mAdaWenti, position);
                }
            });
        } else if (R.id.mMImageView_touxiang == v.getId()) {

        } else if (R.id.mImageView_rzh == v.getId()) {
            Helper.startActivity(context, FrgZhuanjiaZhuye.class, TitleAct.class, "id", item.lz.id);
        } else if (R.id.mImageView_qbyh == v.getId()) {

        } else if (R.id.mImageView_dbm == v.getId()) {

        }
    }

    public void SEditCommentPraiseCancel(Son s) {
        item.isPraised = 0;
        mAdaWenti.notifyDataSetChanged();
    }

    public void SEditCommentPraiseAdd(Son s) {
        item.isPraised = 1;
        mAdaWenti.notifyDataSetChanged();
    }


    public void SEditCommentPraiseAddZan(Son s) {
        item.isPraised = 1;
        item.praiseCnt++;
        mAdaWenti.notifyDataSetChanged();
    }

    public void SEditCommentPraiseAddDao(Son s) {
        item.isPraised = 2;
        item.praiseNoCnt++;
        mAdaWenti.notifyDataSetChanged();
    }

}