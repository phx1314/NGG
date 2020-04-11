//
//  AdaZacaoTupu
//
//  Created by df on 2017-03-13 13:28:39
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
import com.ntdlg.ngg.frg.FrgZacaoListErji;
import com.ntdlg.ngg.item.ZacaoTupu;
import com.udows.common.proto.MCategory;

import java.util.List;

public class AdaZacaoTupu2 extends MAdapter<MCategory> {

    public AdaZacaoTupu2(Context context, List<MCategory> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MCategory item = get(position);
        if (convertView == null) {
            convertView = ZacaoTupu.getView(getContext(), parent);
        }
        ZacaoTupu mZacaoTupu = (ZacaoTupu) convertView.getTag();
        mZacaoTupu.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgZacaoListErji.class, TitleAct.class, "mMCategory", item);
            }
        });
        return convertView;
    }
}
