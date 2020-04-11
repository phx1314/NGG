//
//  AdaSousuoZhuanjia
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.frg.FrgZhuanjiaZhuye;
import com.ntdlg.ngg.item.SousuoZhuanjia;
import com.udows.common.proto.SUser;

import java.util.List;

public class AdaSousuoZhuanjia extends MAdapter<SUser> {

    public AdaSousuoZhuanjia(Context context, List<SUser> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final SUser item = get(position);
        if (convertView == null) {
            convertView = SousuoZhuanjia.getView(getContext(), parent);
        }
        SousuoZhuanjia mSousuoZhuanjia = (SousuoZhuanjia) convertView.getTag();
        mSousuoZhuanjia.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgZhuanjiaZhuye.class, NoTitleAct.class, "id", item.id);
            }
        });
        return convertView;
    }
}
