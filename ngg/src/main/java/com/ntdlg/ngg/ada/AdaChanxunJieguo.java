//
//  AdaChanxunJieguo
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
import com.ntdlg.ngg.frg.FrgChanxunDetail;
import com.ntdlg.ngg.item.ChanxunJieguo;
import com.udows.common.proto.MPesticideSearch;

import java.util.List;

public class AdaChanxunJieguo extends MAdapter<MPesticideSearch> {

    public AdaChanxunJieguo(Context context, List<MPesticideSearch> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MPesticideSearch item = get(position);
        if (convertView == null) {
            convertView = ChanxunJieguo.getView(getContext(), parent);
        }
        ChanxunJieguo mChanxunJieguo = (ChanxunJieguo) convertView.getTag();
        mChanxunJieguo.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgChanxunDetail.class, TitleAct.class, "id", item.id);
            }
        });
        return convertView;
    }
}
