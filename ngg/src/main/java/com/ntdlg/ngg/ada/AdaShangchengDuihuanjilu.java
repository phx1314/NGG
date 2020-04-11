//
//  AdaShangchengDuihuanjilu
//
//  Created by DELL on 2017-04-01 15:46:12
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.ShangchengDuihuanjilu;
import com.udows.common.proto.MCreditExchangeLog;

import java.util.List;

public class AdaShangchengDuihuanjilu extends MAdapter<MCreditExchangeLog>{

   public AdaShangchengDuihuanjilu(Context context, List<MCreditExchangeLog> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        MCreditExchangeLog item = get(position);
        if (convertView == null) {
            convertView = ShangchengDuihuanjilu.getView(getContext(), parent);;
        }
        ShangchengDuihuanjilu mShangchengDuihuanjilu=(ShangchengDuihuanjilu) convertView.getTag();
        mShangchengDuihuanjilu.set(item);
        return convertView;
    }
}
