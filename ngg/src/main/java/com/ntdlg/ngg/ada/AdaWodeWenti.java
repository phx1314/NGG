//
//  AdaWodeWenti
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.frg.FrgWentiDetail;
import com.ntdlg.ngg.item.WodeWenti;
import com.udows.common.proto.STopic;

import java.util.List;

public class AdaWodeWenti extends MAdapter<STopic> {

    public AdaWodeWenti(Context context, List<STopic> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final STopic item = get(position);
        if (convertView == null) {
            convertView = WodeWenti.getView(getContext(), parent);
        }
        WodeWenti mWodeWenti = (WodeWenti) convertView.getTag();
        mWodeWenti.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgWentiDetail.class, TitleAct.class, "name", F.mSUser.nickName, "mid", item.id);
            }
        });
        return convertView;
    }
}
