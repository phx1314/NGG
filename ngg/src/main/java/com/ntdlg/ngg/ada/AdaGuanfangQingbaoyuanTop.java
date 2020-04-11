//
//  AdaGuanfangQingbaoyuanTop
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
import com.ntdlg.ngg.item.GuanfangQingbaoyuanTop;

import java.util.List;

public class AdaGuanfangQingbaoyuanTop extends MAdapter<String>{

   public AdaGuanfangQingbaoyuanTop(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = GuanfangQingbaoyuanTop.getView(getContext(), parent);;
        }
        GuanfangQingbaoyuanTop mGuanfangQingbaoyuanTop=(GuanfangQingbaoyuanTop) convertView.getTag();
//        mGuanfangQingbaoyuanTop.set(item);
        return convertView;
    }
}
