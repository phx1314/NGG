package com.ntdlg.ngg.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import com.framewidget.F;
import com.framewidget.view.CallBackOnly;
import com.ntdlg.ngg.item.ShurukuagDialog;
import com.udows.common.proto.SReply;
import com.udows.common.proto.STopic;

public class TextViewURLSpan extends ClickableSpan {
    private String clickString;
    private SReply mSReply;
    private Context mContext;
    private STopic mSTopic;
    private CallBackQiTa mCallBackQiTa;

    public TextViewURLSpan(STopic mSTopic, String clickString, SReply mSReply,
                           Context mContext, CallBackQiTa mCallBackQiTa) {
        this.clickString = clickString;
        this.mSReply = mSReply;
        this.mContext = mContext;
        this.mSTopic = mSTopic;
        this.mCallBackQiTa = mCallBackQiTa;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setUnderlineText(false); // 去掉下划线
    }

    @Override
    public void onClick(View widget) {
        if (TextUtils.isEmpty(com.ntdlg.ngg.F.UserId)) {
            com.ntdlg.ngg.F.showToast2Login(mContext);
            return;
        }
        final View view_diaolog = ShurukuagDialog.getView(mContext, null);
        F.showBottomDialog(mContext, view_diaolog, new CallBackOnly() {
            @Override
            public void goReturnDo(Dialog mDialog) {
                if (clickString.equals(mSReply.userId))
                    ((ShurukuagDialog) view_diaolog.getTag()).set(mDialog, mSTopic, clickString, clickString.equals(mSReply.userId) ? mSReply.nickName : mSReply.targetName, mCallBackQiTa);
            }
        });
//        if (clickString.equals(mSReply.targetId)) {
//            final View view_diaolog = ShurukuagDialog.getView(mContext, null);
//            F.showBottomDialog(mContext, view_diaolog, new CallBackOnly() {
//                @Override
//                public void goReturnDo(Dialog mDialog) {
//                    ((ShurukuagDialog) view_diaolog.getTag()).set(mDialog, mSTopic, mSReply.targetId, mSReply.targetName, mCallBackQiTa);
//                }
//            });
//        }
    }
}