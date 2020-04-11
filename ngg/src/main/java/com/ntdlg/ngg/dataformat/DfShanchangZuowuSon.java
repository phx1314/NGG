//
//  DfShanchangZuowuSon
//
//  Created by df on 2017-02-09 15:44:30
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.dataformat;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;

import android.content.Context;

import com.ntdlg.ngg.ada.AdaShanchangZuowuSon;

public class DfShanchangZuowuSon extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= Integer.MAX_VALUE;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        return new AdaShanchangZuowuSon(context, null);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
