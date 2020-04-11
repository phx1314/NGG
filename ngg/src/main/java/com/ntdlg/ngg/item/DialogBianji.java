//
//  DialogBianji
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaWenti;
import com.ntdlg.ngg.frg.FrgJieda;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.STopic;


public class DialogBianji extends BaseItem {
    public TextView clk_mTextView_cxbj;
    public TextView clk_mTextView_cx;
    public TextView clk_mTextView_cancel;
    public Dialog mDialog;
    public AdaWenti mAdaWenti;
    public STopic item;
    public int position;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_dialog_bianji, null);
        convertView.setTag(new DialogBianji(convertView));
        return convertView;
    }

    public DialogBianji(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        clk_mTextView_cxbj = (TextView) contentview.findViewById(R.id.clk_mTextView_cxbj);
        clk_mTextView_cx = (TextView) contentview.findViewById(R.id.clk_mTextView_cx);
        clk_mTextView_cancel = (TextView) contentview.findViewById(R.id.clk_mTextView_cancel);

        clk_mTextView_cxbj.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_cx.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        clk_mTextView_cancel.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void set(Dialog mDialog, STopic item, AdaWenti mAdaWenti, int position) {
        this.mDialog = mDialog;
        this.item = item;
        this.position = position;
        this.mAdaWenti = mAdaWenti;
    }

    @Override
    public void onClick(android.view.View v) {
        mDialog.dismiss();
        if (R.id.clk_mTextView_cxbj == v.getId()) {
            Helper.startActivity(context, FrgJieda.class, TitleAct.class, "mSTopic", item);
        } else if (R.id.clk_mTextView_cx == v.getId()) {
            mAdaWenti.remove(position);
            ApisFactory.getApiSDelTopicComment().load(context, this, "SDelTopicComment", item.id);
        } else if (R.id.clk_mTextView_cancel == v.getId()) {

        }
    }

    public void SDelTopicComment(Son s) {
    }

}