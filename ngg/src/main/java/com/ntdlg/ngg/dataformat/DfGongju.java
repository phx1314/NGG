//
//  DfGongju
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.dataformat;

import android.content.Context;

import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.ntdlg.ngg.ada.AdaGongju;
import com.ntdlg.ngg.view.ModelGongJu;
import com.udows.common.proto.MGoodsList;

import java.util.ArrayList;
import java.util.List;

public class DfGongju extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        size = ((MGoodsList) son.getBuild()).mini.size();
        List<ModelGongJu> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                ModelGongJu mModelGongJu;
                if (i + 1 < size) {
                    mModelGongJu = new ModelGongJu(((MGoodsList) son.getBuild()).mini.get(i), ((MGoodsList) son.getBuild()).mini.get(i + 1));
                } else {
                    mModelGongJu = new ModelGongJu(((MGoodsList) son.getBuild()).mini.get(i), null);
                }
                list.add(mModelGongJu);
            }
        }
        if(page==1){
            Frame.HANDLES.sentAll("FrgGongju",2,null);
        }
        return new AdaGongju(context,list);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
