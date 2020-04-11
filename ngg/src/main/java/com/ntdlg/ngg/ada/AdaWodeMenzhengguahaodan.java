//
//  AdaWodeMenzhengguahaodan
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
import com.ntdlg.ngg.frg.FrgWzhDingdanDetail;
import com.ntdlg.ngg.item.WodeMenzhengguahaodan;
import com.udows.common.proto.MCropOutpatient;

import java.util.List;

public class AdaWodeMenzhengguahaodan extends MAdapter<MCropOutpatient> {

    public AdaWodeMenzhengguahaodan(Context context, List<MCropOutpatient> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MCropOutpatient item = get(position);
        if (convertView == null) {
            convertView = WodeMenzhengguahaodan.getView(getContext(), parent);
        }
        WodeMenzhengguahaodan mWodeMenzhengguahaodan = (WodeMenzhengguahaodan) convertView.getTag();
        mWodeMenzhengguahaodan.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgWzhDingdanDetail.class, TitleAct.class, "item", item);
            }
        });
        return convertView;
    }
}
