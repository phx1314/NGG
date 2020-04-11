//
//  AdaNyqDetailTop
//
//  Created by df on 2017-02-10 14:01:15
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.NyqDetailTop;

import java.util.List;

public class AdaNyqDetailTop extends MAdapter<String>{

   public AdaNyqDetailTop(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = NyqDetailTop.getView(getContext(), parent);;
        }
        NyqDetailTop mNyqDetailTop=(NyqDetailTop) convertView.getTag();
//        mNyqDetailTop.set(item);
        return convertView;
    }
}
