//
//  DfShanchangZuowu
//
//  Created by df on 2017-02-09 15:44:30
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.dataformat;

import android.content.Context;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.ntdlg.ngg.ada.AdaShanchangZuowu;
import com.udows.common.proto.MCategoryList;

public class DfShanchangZuowu extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= Integer.MAX_VALUE;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        return new AdaShanchangZuowu(context, ((MCategoryList) son.getBuild()).mini);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
