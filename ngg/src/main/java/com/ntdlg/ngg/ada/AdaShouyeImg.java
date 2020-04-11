//
//  AdaShouyeImg
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
import com.ntdlg.ngg.item.ShouyeImg;

import java.util.List;

public class AdaShouyeImg extends MAdapter<String>{

   public AdaShouyeImg(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ShouyeImg.getView(getContext(), parent);;
        }
        ShouyeImg mShouyeImg=(ShouyeImg) convertView.getTag();
//        mShouyeImg.set(item);
        return convertView;
    }
}
