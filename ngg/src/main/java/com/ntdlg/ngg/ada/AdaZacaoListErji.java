//
//  AdaZacaoListErji
//
//  Created by df on 2017-03-13 10:31:18
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.frg.FrgZacaoListSanji;
import com.ntdlg.ngg.item.ZacaoListErji;
import com.udows.common.proto.MCategory;

import java.util.List;

public class AdaZacaoListErji extends MAdapter<MCategory> {

    public AdaZacaoListErji(Context context, List<MCategory> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MCategory item = get(position);
        if (convertView == null) {
            convertView = ZacaoListErji.getView(getContext(), parent);
        }
        ZacaoListErji mZacaoListErji = (ZacaoListErji) convertView.getTag();
        mZacaoListErji.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgZacaoListSanji.class, TitleAct.class, "item", item);
            }
        });
        return convertView;
    }
}
