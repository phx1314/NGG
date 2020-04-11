//
//  AdaGuanfangQingbaoyuan
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
import com.ntdlg.ngg.item.GuanfangQingbaoyuan;
import com.udows.common.proto.MOfficeComment;

import java.util.List;

public class AdaGuanfangQingbaoyuan extends MAdapter<MOfficeComment>{

   public AdaGuanfangQingbaoyuan(Context context, List<MOfficeComment> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        MOfficeComment item = get(position);
        if (convertView == null) {
            convertView = GuanfangQingbaoyuan.getView(getContext(), parent);
        }
        GuanfangQingbaoyuan mGuanfangQingbaoyuan=(GuanfangQingbaoyuan) convertView.getTag();
        mGuanfangQingbaoyuan.set(item);
        return convertView;
    }
}
