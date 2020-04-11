//
//  AdaWodeHuida
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.frg.FrgWentiDetail;
import com.ntdlg.ngg.item.WodeHuida;
import com.udows.common.proto.STopic;

import java.util.List;

public class AdaWodeHuida extends MAdapter<STopic>{

   public AdaWodeHuida(Context context, List<STopic> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final STopic item = get(position);
        if (convertView == null) {
            convertView = WodeHuida.getView(getContext(), parent);;
        }
        WodeHuida mWodeHuida=(WodeHuida) convertView.getTag();
        mWodeHuida.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgWentiDetail.class, TitleAct.class , "mid", item.topic.get(0).id);
            }
        });
        return convertView;
    }
}
