//
//  DfWodeXiaoxi
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.dataformat;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.ntdlg.ngg.ada.AdaWodeXiaoxi;
import com.ntdlg.ngg.item.XinwenzixunTop;
import com.udows.common.proto.MNotifyList;

public class DfWodeXiaoxi extends DataFormat {
    int size = 1;
    public View view;

    public DfWodeXiaoxi(View view) {
        this.view = view;
    }

    public DfWodeXiaoxi() {
    }

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        size = ((MNotifyList) son.getBuild()).notify.size();
        if (view != null)
            ((XinwenzixunTop) view.getTag()).set(5.0);
        return new AdaWodeXiaoxi(context, ((MNotifyList) son.getBuild()).notify);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
