//
//  AdaNyfenleSon
//
//  Created by Administrator on 2017-04-08 12:56:49
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.NyfenleSon;

import java.util.List;

public class AdaNyfenleSon extends MAdapter<String>{

   public AdaNyfenleSon(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = NyfenleSon.getView(getContext(), parent);;
        }
        NyfenleSon mNyfenleSon=(NyfenleSon) convertView.getTag();
//        mNyfenleSon.set(item);
        return convertView;
    }
}
