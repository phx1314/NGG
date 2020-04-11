//
//  AdaAddZuowuRight
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
import com.ntdlg.ngg.item.AddZuowuRight;
import com.ntdlg.ngg.view.ModelZuoWuPOsition;
import com.udows.common.proto.MCategory;

import java.util.List;

public class AdaAddZuowuRight extends MAdapter<MCategory> {
    public int position;

    public AdaAddZuowuRight(Context context, List<MCategory> list, int position) {
        super(context, list);
        this.position = position;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MCategory item = get(position);
        if (convertView == null) {
            convertView = AddZuowuRight.getView(getContext(), parent);
        }
        AddZuowuRight mAddZuowuRight = (AddZuowuRight) convertView.getTag();
        mAddZuowuRight.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frame.HANDLES.sentAll("FrgAddZuowu", 0, new ModelZuoWuPOsition(item, AdaAddZuowuRight.this.position));
            }
        });
        return convertView;
    }
}
