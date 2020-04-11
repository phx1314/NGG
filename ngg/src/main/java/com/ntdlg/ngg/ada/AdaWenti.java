//
//  AdaWenti
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.framewidget.F;
import com.framewidget.view.CallBackOnly;
import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.ShurukuagDialog;
import com.ntdlg.ngg.item.Wenti;
import com.ntdlg.ngg.view.CallBackQiTa;
import com.udows.common.proto.STopic;

import java.util.List;

public class AdaWenti extends MAdapter<STopic> {
    public CallBackQiTa mCallBackQiTa;

    public AdaWenti(Context context, List<STopic> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final STopic item = get(position);
        if (convertView == null) {
            convertView = Wenti.getView(getContext(), parent);
        }
        Wenti mWenti = (Wenti) convertView.getTag();
        mCallBackQiTa = new CallBackQiTa() {
            @Override
            public void goReturnDo(Object mObject) {
                AdaWenti.this.notifyDataSetChanged();
            }
        };
        mWenti.set(item, this, position, mCallBackQiTa);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(com.ntdlg.ngg.F.UserId)) {
                    com.ntdlg.ngg.F.showToast2Login(getContext());
                    return;
                }
                final View view_diaolog = ShurukuagDialog.getView(getContext(), null);
                F.showBottomDialog(getContext(), view_diaolog, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        ((ShurukuagDialog) view_diaolog.getTag()).set(mDialog, item, item.lz.id, item.lz.nickName, mCallBackQiTa);
                    }
                });
            }
        });
        return convertView;
    }
}
