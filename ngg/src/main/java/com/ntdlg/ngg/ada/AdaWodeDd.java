//
//  AdaWodeDd
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
import com.ntdlg.ngg.frg.FrgDingdanDetail;
import com.ntdlg.ngg.item.WodeDd;
import com.udows.common.proto.MOrderMini;

import java.util.List;

public class AdaWodeDd extends MAdapter<MOrderMini> {

    public AdaWodeDd(Context context, List<MOrderMini> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MOrderMini item = get(position);
        if (convertView == null) {
            convertView = WodeDd.getView(getContext(), parent);
        }
        WodeDd mWodeDd = (WodeDd) convertView.getTag();
        mWodeDd.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgDingdanDetail.class, TitleAct.class, "id", item.id);
            }
        });
        return convertView;
    }
}
