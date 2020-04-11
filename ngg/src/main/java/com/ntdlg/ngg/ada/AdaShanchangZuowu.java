//
//  AdaShanchangZuowu
//
//  Created by df on 2017-02-09 15:44:30
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.ShanchangZuowu;
import com.udows.common.proto.MCategory;

import java.util.List;

public class AdaShanchangZuowu extends MAdapter<MCategory> {

    public AdaShanchangZuowu(Context context, List<MCategory> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        MCategory item = get(position);
        if (convertView == null) {
            convertView = ShanchangZuowu.getView(getContext(), parent);
        }
        ShanchangZuowu mShanchangZuowu = (ShanchangZuowu) convertView.getTag();
        mShanchangZuowu.set(item, this);
        return convertView;
    }
}
