//
//  AdaXinwenzixun
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
import com.ntdlg.ngg.frg.FrgXinwenzixunDetail;
import com.ntdlg.ngg.item.Xinwenzixun;
import com.udows.common.proto.MNews;

import java.util.List;

public class AdaXinwenzixun extends MAdapter<MNews> {

    public AdaXinwenzixun(Context context, List<MNews> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MNews item = get(position);
        if (convertView == null) {
            convertView = Xinwenzixun.getView(getContext(), parent);
        }
        Xinwenzixun mXinwenzixun = (Xinwenzixun) convertView.getTag();
        mXinwenzixun.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgXinwenzixunDetail.class, TitleAct.class, "item", item);
            }
        });
        return convertView;
    }
}
