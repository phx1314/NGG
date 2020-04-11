//
//  AdaNyq
//
//  Created by df on 2017-02-10 14:53:54
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.frg.FrgNyqDetail;
import com.ntdlg.ngg.item.Nyq;
import com.udows.common.proto.STopic;

import java.util.List;

public class AdaNyq extends MAdapter<STopic> {
    int isWodeType;
    public AdaNyq(Context context, List<STopic> list,int isWodeType ) {
        super(context, list);
        this.isWodeType=isWodeType;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final STopic item = get(position);
        if (convertView == null) {
            convertView = Nyq.getView(getContext(), parent);
        }
        Nyq mNyq = (Nyq) convertView.getTag();
        mNyq.set(item,isWodeType);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgNyqDetail.class, TitleAct.class, "item", item);
            }
        });
        return convertView;
    }
}
