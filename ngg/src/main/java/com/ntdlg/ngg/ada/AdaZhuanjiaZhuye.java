//
//  AdaZhuanjiaZhuye
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

import com.ntdlg.ngg.item.ZhuanjiaZhuye;

public class AdaZhuanjiaZhuye extends MAdapter<String>{

   public AdaZhuanjiaZhuye(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ZhuanjiaZhuye.getView(getContext(), parent);;
        }
        ZhuanjiaZhuye mZhuanjiaZhuye=(ZhuanjiaZhuye) convertView.getTag();
        mZhuanjiaZhuye.set(item);
        return convertView;
    }
}
