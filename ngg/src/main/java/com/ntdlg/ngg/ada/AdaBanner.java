//
//  AdaBanner
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.framewidget.frg.FrgPtDetail;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.item.Banner;
import com.udows.common.proto.MFocus;

import java.util.List;

public class AdaBanner extends MAdapter<MFocus> {

    public AdaBanner(Context context, List<MFocus> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MFocus item = get(position);
        if (convertView == null) {
            convertView = Banner.getView(getContext(), parent);
        }
        Banner mBanner = (Banner) convertView.getTag();
        mBanner.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (!TextUtils.isEmpty(item.redirectContent)) {
                    switch (item.redirectType) {
                        case 0:

                            break;
                        case 1:
                            Helper.startActivity(getContext(), FrgPtDetail.class,
                                    NoTitleAct.class, "url",
                                    item.redirectContent);
                            break;
                        case 2:
                            Helper.startActivity(getContext(), FrgPtDetail.class,
                                    NoTitleAct.class, "url", BaseConfig.getUri()
                                            + item.redirectContent);
                            break;
                    }
                }
            }
        });

        return convertView;
    }
}
