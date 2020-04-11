//
//  AdaShouyeTop
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.ntdlg.ngg.item.ShouyeTop;

public class AdaShouyeTop extends MAdapter<String>{

   public AdaShouyeTop(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ShouyeTop.getView(getContext(), parent);;
        }
        ShouyeTop mShouyeTop=(ShouyeTop) convertView.getTag();
        mShouyeTop.set(item);
        return convertView;
    }
}
