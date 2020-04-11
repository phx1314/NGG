//
//  DfNyqDetail
//
//  Created by df on 2017-02-10 14:00:09
//  Copyright (c) df All rights reserved.


/**

 */

package com.ntdlg.ngg.dataformat;

import android.content.Context;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.ntdlg.ngg.ada.AdaNyqDetail;
import com.udows.common.proto.SReplyList;

public class DfNyqDetail extends DataFormat {
    int size = 1;
//    int count = 1;

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        size = ((SReplyList) son.getBuild()).replys.size();// 每页10条
//        count += size;
//        if (size < 10 && count > 5) {
//            Frame.HANDLES.sentAll("FrgNyqDetail", 0, null);
//        }
        return new AdaNyqDetail(context,
                ((SReplyList) son.getBuild()).replys);
    }

    @Override
    public String[][] getPageNext() {
//        return new String[][]{{"page", count + ""}};
        return null;
    }

    @Override
    public void reload() {

    }
}
