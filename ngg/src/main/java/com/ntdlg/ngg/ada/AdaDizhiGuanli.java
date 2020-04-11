//
//  AdaDizhiGuanli
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.DizhiGuanli;
import com.udows.common.proto.MUserAddress;

import java.util.List;

public class AdaDizhiGuanli extends MAdapter<MUserAddress> {

    public AdaDizhiGuanli(Context context, List<MUserAddress> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MUserAddress item = get(position);
        if (convertView == null) {
            convertView = DizhiGuanli.getView(getContext(), parent);
        }
        DizhiGuanli mDizhiGuanli = (DizhiGuanli) convertView.getTag();
        mDizhiGuanli.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frame.HANDLES.sentAll("FrgDizhiGuanli", 1, item);
            }
        });
        return convertView;
    }
}
