//
//  DfNyq
//
//  Created by df on 2017-02-10 14:53:54
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.dataformat;

import android.content.Context;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.ntdlg.ngg.ada.AdaNyq;
import com.udows.common.proto.STopicList;

public class DfNyq extends DataFormat {
    int size;
    String begin = "";
    int isWodeType;

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    public DfNyq(int isWodeType) {
        this.isWodeType = isWodeType;
    }

    public DfNyq() {
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        size = ((STopicList) son.getBuild()).list.size();// 每页10条
//        if (size > 0)
//            begin = ((STopicList) son.getBuild()).list.get(size - 1).createTime;
        return new AdaNyq(context, ((STopicList) son.getBuild()).list,isWodeType);
    }

    @Override
    public String[][] getPageNext() {
//        return new String[][]{{"begin", begin}};
        return null;
    }

    @Override
    public void reload() {
    }
}
