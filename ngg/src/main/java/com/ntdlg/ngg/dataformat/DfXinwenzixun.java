//
//  DfXinwenzixun
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
import com.ntdlg.ngg.ada.AdaXinwenzixun;
import com.ntdlg.ngg.item.XinwenzixunTop;
import com.udows.common.proto.MNewsList;

public class DfXinwenzixun extends DataFormat {
    int size = 1;
    public View view;

    public DfXinwenzixun(View view) {
        this.view = view;
    }

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        size = ((MNewsList) son.getBuild()).news.size();
        ((XinwenzixunTop) view.getTag()).set(4.0);
        return new AdaXinwenzixun(context, ((MNewsList) son.getBuild()).news);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
