//
//  AdaBinghaiBanner
//
//  Created by df on 2017-03-10 10:14:12
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.dialog.PhotoShow;
import com.ntdlg.ngg.item.BinghaiBanner;

import java.util.List;

public class AdaBinghaiBanner extends MAdapter<String> {

    public AdaBinghaiBanner(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final String item = get(position);
        if (convertView == null) {
            convertView = BinghaiBanner.getView(getContext(), parent);
        }
        BinghaiBanner mBinghaiBanner = (BinghaiBanner) convertView.getTag();
        mBinghaiBanner.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhotoShow mPhotoShow = new PhotoShow(getContext(), getList(), item);
                mPhotoShow.show();
            }
        });
        return convertView;
    }
}
