//
//  AdaShanchangZuowuSon
//
//  Created by df on 2017-02-09 15:44:30
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.ShanchangZuowuSon;

import java.util.List;

public class AdaShanchangZuowuSon extends MAdapter<String>{

   public AdaShanchangZuowuSon(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ShanchangZuowuSon.getView(getContext(), parent);;
        }
        ShanchangZuowuSon mShanchangZuowuSon=(ShanchangZuowuSon) convertView.getTag();
//        mShanchangZuowuSon.set(item);
        return convertView;
    }
}
