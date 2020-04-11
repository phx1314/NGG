package com.ntdlg.ngg.view;

import android.content.Context;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;

import com.mdx.framework.Frame;
import com.udows.common.proto.SReply;

public class TextViewURLSpanPyq extends ClickableSpan {
    private String clickString;
    private SReply mSReply;
    private Context mContext;

    public TextViewURLSpanPyq(String clickString, SReply mSReply,
                              Context mContext) {
        this.clickString = clickString;
        this.mSReply = mSReply;
        this.mContext = mContext;
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
        if (clickString.equals(mSReply.userId)) {
            Frame.HANDLES.sentAll("FrgNyqDetail", 1, mSReply);
        } else if (clickString.equals(mSReply.targetId)) {
            Frame.HANDLES.sentAll("FrgNyqDetail", 2, mSReply);
        }
    }
}