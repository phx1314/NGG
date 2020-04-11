//
//  AdaGongju
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
import com.ntdlg.ngg.item.Gongju;
import com.ntdlg.ngg.view.ModelGongJu;
import com.udows.common.proto.MScGoods;

import java.util.List;

public class AdaGongju extends MAdapter<ModelGongJu>{

   public AdaGongju(Context context, List<ModelGongJu> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelGongJu item = get(position);
        if (convertView == null) {
            convertView = Gongju.getView(getContext(), parent);;
        }
        Gongju mGongju=(Gongju) convertView.getTag();
        mGongju.set(item);
        return convertView;
    }
}
