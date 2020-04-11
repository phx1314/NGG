//
//  AdaChaContent
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
import com.ntdlg.ngg.item.ChaContent;

import java.util.List;

public class AdaChaContent extends MAdapter<String>{

   public AdaChaContent(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ChaContent.getView(getContext(), parent);;
        }
        ChaContent mChaContent=(ChaContent) convertView.getTag();
//        mChaContent.set(item);
        return convertView;
    }
}
