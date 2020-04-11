//
//  AdaShurukuagDialog
//
//  Created by df on 2017-03-02 08:53:33
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.ShurukuagDialog;

import java.util.List;

public class AdaShurukuagDialog extends MAdapter<String>{

   public AdaShurukuagDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ShurukuagDialog.getView(getContext(), parent);;
        }
        ShurukuagDialog mShurukuagDialog=(ShurukuagDialog) convertView.getTag();
//        mShurukuagDialog.set(item);
        return convertView;
    }
}
