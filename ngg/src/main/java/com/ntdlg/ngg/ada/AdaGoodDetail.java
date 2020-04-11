//
//  AdaGoodDetail
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
import com.ntdlg.ngg.item.GoodDetail;
import com.udows.common.proto.MGoodsInfo;

import java.util.List;

public class AdaGoodDetail extends MAdapter<MGoodsInfo>{

   public AdaGoodDetail(Context context, List<MGoodsInfo> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        MGoodsInfo item = get(position);
        if (convertView == null) {
            convertView = GoodDetail.getView(getContext(), parent);;
        }
        GoodDetail mGoodDetail=(GoodDetail) convertView.getTag();
//        mGoodDetail.set(item);
        return convertView;
    }
}
