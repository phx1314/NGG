//
//  AdaShouye
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
import com.ntdlg.ngg.item.Shouye;
import com.udows.common.proto.STopic;

import java.util.List;

public class AdaShouye extends MAdapter<STopic> {

    public AdaShouye(Context context, List<STopic> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final STopic item = get(position);
        if (convertView == null) {
            convertView = Shouye.getView(getContext(), parent);
        }
        Shouye mShouye = (Shouye) convertView.getTag();
        mShouye.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgWentiDetail.class, TitleAct.class, "mid", item.id);
            }
        });
        return convertView;
    }
}
