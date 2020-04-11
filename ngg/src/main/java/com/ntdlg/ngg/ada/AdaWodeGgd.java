//
//  AdaWodeGgd
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.WodeGgd;
import com.udows.common.proto.MCreditLog;

import java.util.List;

public class AdaWodeGgd extends MAdapter<MCreditLog>{

   public AdaWodeGgd(Context context, List<MCreditLog> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        MCreditLog item = get(position);
        if (convertView == null) {
            convertView = WodeGgd.getView(getContext(), parent);;
        }
        WodeGgd mWodeGgd=(WodeGgd) convertView.getTag();
        mWodeGgd.set(item);
        return convertView;
    }
}
