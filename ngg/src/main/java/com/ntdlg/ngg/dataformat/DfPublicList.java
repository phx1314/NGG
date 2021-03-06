//
//  DfPublicList
//
//  Created by df on 2017-02-07 14:42:18
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.dataformat;

import android.content.Context;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.ntdlg.ngg.ada.AdaPublicList;
import com.udows.common.proto.MCategoryList;

public class DfPublicList extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= Integer.MAX_VALUE;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        if (son.getBuild() instanceof MCategoryList) {
            return new AdaPublicList(context, ((MCategoryList) son.getBuild()).mini);
        }
        return new AdaPublicList(context, null);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
