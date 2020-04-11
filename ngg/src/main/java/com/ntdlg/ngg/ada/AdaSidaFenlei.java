//
//  AdaSidaFenlei
//
//  Created by Administrator on 2017-04-08 12:06:18
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.SidaFenlei;

import java.util.List;

public class AdaSidaFenlei extends MAdapter<String>{

   public AdaSidaFenlei(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = SidaFenlei.getView(getContext(), parent);;
        }
        SidaFenlei mSidaFenlei=(SidaFenlei) convertView.getTag();
//        mSidaFenlei.set(item);
        return convertView;
    }
}
