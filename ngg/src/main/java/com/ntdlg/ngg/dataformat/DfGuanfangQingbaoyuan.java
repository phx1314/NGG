//
//  DfGuanfangQingbaoyuan
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
import com.ntdlg.ngg.ada.AdaGuanfangQingbaoyuan;
import com.ntdlg.ngg.ada.AdaGuanfangQingbaoyuanAll;
import com.udows.common.proto.MOfficeCommentAllList;
import com.udows.common.proto.MOfficeCommentList;

public class DfGuanfangQingbaoyuan extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        if (son.getBuild() instanceof MOfficeCommentAllList) {
            size = ((MOfficeCommentAllList) son.getBuild()).mini.size();
            return new AdaGuanfangQingbaoyuanAll(context, ((MOfficeCommentAllList) son.getBuild()).mini);
        }
        if (son.getBuild() instanceof MOfficeCommentList) {
            size = ((MOfficeCommentList) son.getBuild()).mini.size();
            return new AdaGuanfangQingbaoyuan(context, ((MOfficeCommentList) son.getBuild()).mini);
        }
        return null;
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
