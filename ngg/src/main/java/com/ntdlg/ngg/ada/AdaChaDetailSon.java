//
//  AdaChaDetailSon
//
//  Created by df on 2017-03-08 16:10:04
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.ChaDetailSon;

import java.util.List;

public class AdaChaDetailSon extends MAdapter<String>{

   public AdaChaDetailSon(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ChaDetailSon.getView(getContext(), parent);;
        }
        ChaDetailSon mChaDetailSon=(ChaDetailSon) convertView.getTag();
//        mChaDetailSon.set(item);
        return convertView;
    }
}
