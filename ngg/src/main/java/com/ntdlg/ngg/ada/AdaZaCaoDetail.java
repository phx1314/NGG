//
//  AdaBanner
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.dialog.PhotoShow;
import com.ntdlg.ngg.item.Banner;

import java.util.List;

public class AdaZaCaoDetail extends MAdapter<String> {

    public AdaZaCaoDetail(Context context, List<String> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final String item = get(position);
        if (convertView == null) {
            convertView = Banner.getView(getContext(), parent);
        }
        Banner mBanner = (Banner) convertView.getTag();
        mBanner.set(item);
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
