//
//  AdaAddZuowuLeft
//
//  Created by df on 2017-03-10 13:50:16
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.AddZuowuLeft;
import com.ntdlg.ngg.view.ModelZuoWuGaoJi;

import java.util.List;

public class AdaAddZuowuLeft extends MAdapter<ModelZuoWuGaoJi> {
    public int position_zuanzhong;

    public AdaAddZuowuLeft(Context context, List<ModelZuoWuGaoJi> list) {
        super(context, list);
    }


    @Override
    public View getview(final int position, View convertView, ViewGroup parent) {
        final ModelZuoWuGaoJi item = get(position);
        if (convertView == null) {
            convertView = AddZuowuLeft.getView(getContext(), parent);
        }
        AddZuowuLeft mAddZuowuLeft = (AddZuowuLeft) convertView.getTag();
        if (position_zuanzhong == position) {
            mAddZuowuLeft.set(item, true);
        } else {
            mAddZuowuLeft.set(item, false);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position_zuanzhong = position;
                AdaAddZuowuLeft.this.notifyDataSetChanged();
                Frame.HANDLES.sentAll("FrgAddZuowu", 1, position);
            }
        });
        return convertView;
    }
}
