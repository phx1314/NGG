//
//  AdaWodeDdSon
//
//  Created by df on 2017-03-07 16:57:42
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.WodeDdSon;

import java.util.List;

public class AdaWodeDdSon extends MAdapter<String>{

   public AdaWodeDdSon(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = WodeDdSon.getView(getContext(), parent);;
        }
        WodeDdSon mWodeDdSon=(WodeDdSon) convertView.getTag();
//        mWodeDdSon.set(item);
        return convertView;
    }
}
