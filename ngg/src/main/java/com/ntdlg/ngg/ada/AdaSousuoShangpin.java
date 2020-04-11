//
//  AdaSousuoShangpin
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
import com.ntdlg.ngg.frg.FrgGoodDetail;
import com.ntdlg.ngg.item.SousuoShangpin;
import com.udows.common.proto.MScGoods;

import java.util.List;

public class AdaSousuoShangpin extends MAdapter<MScGoods> {

    public AdaSousuoShangpin(Context context, List<MScGoods> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MScGoods item = get(position);
        if (convertView == null) {
            convertView = SousuoShangpin.getView(getContext(), parent);
        }
        SousuoShangpin mSousuoShangpin = (SousuoShangpin) convertView.getTag();
        mSousuoShangpin.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgGoodDetail.class, NoTitleAct.class, "id", item.id);
            }
        });
        return convertView;
    }
}
