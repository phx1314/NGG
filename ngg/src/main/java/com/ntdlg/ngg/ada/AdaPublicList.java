//
//  AdaPublicList
//
//  Created by df on 2017-02-07 14:42:18
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.PublicList;

import java.util.List;

public class AdaPublicList extends MAdapter<Object> {

    public AdaPublicList(Context context, List list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final Object item = get(position);
        if (convertView == null) {
            convertView = PublicList.getView(getContext(), parent);
        }
        PublicList mPublicList = (PublicList) convertView.getTag();
        mPublicList.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frame.HANDLES.sentAll("FrgPublicList", 0, item);
            }
        });

        return convertView;
    }
}
