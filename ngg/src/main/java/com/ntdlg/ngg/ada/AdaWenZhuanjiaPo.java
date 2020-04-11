//
//  AdaWenZhuanjiaPo
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.WenZhuanjiaPo;
import com.udows.common.proto.MCategory;

import java.util.List;

public class AdaWenZhuanjiaPo extends MAdapter<MCategory> {
    public int type;
    PopupWindow popwindow;
    public AdaWenZhuanjiaPo(Context context, List<MCategory> list, int type, PopupWindow popwindow) {
        super(context, list);
        this.type = type;
        this.popwindow = popwindow;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final MCategory item = get(position);
        if (convertView == null) {
            convertView = WenZhuanjiaPo.getView(getContext(), parent);
        }
        WenZhuanjiaPo mWenZhuanjiaPo = (WenZhuanjiaPo) convertView.getTag();
        mWenZhuanjiaPo.set(item,type);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popwindow.dismiss();
                Frame.HANDLES.sentAll("FrgWenZhuanjia", type, item);
            }
        });
        return convertView;
    }
}
