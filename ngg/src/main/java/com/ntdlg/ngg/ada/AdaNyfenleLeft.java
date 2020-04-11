//
//  AdaNyfenleLeft
//
//  Created by Administrator on 2017-04-08 12:41:48
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.NyfenleLeft;

import java.util.List;

public class AdaNyfenleLeft extends MAdapter<String>{

   public AdaNyfenleLeft(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = NyfenleLeft.getView(getContext(), parent);;
        }
        NyfenleLeft mNyfenleLeft=(NyfenleLeft) convertView.getTag();
//        mNyfenleLeft.set(item);
        return convertView;
    }
}
