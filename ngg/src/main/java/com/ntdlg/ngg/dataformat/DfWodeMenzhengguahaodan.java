//
//  DfWodeMenzhengguahaodan
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.dataformat;

import android.content.Context;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.ntdlg.ngg.ada.AdaWodeMenzhengguahaodan;
import com.udows.common.proto.MCropOutpatientList;

public class DfWodeMenzhengguahaodan extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        size = ((MCropOutpatientList) son.getBuild()).mini.size();
        return new AdaWodeMenzhengguahaodan(context, ((MCropOutpatientList) son.getBuild()).mini);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
