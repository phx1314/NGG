//
//  AdaNyqDetail
//
//  Created by df on 2017-02-10 14:00:09
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.NyqDetail;
import com.udows.common.proto.SReply;

import java.util.List;

public class AdaNyqDetail extends MAdapter<SReply>{

   public AdaNyqDetail(Context context, List<SReply> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        SReply item = get(position);
        if (convertView == null) {
            convertView = NyqDetail.getView(getContext(), parent);;
        }
        NyqDetail mNyqDetail=(NyqDetail) convertView.getTag();
        mNyqDetail.set(item);
        return convertView;
    }
}
