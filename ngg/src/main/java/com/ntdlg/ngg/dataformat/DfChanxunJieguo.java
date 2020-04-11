//
//  DfChanxunJieguo
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
import com.ntdlg.ngg.ada.AdaChanxunJieguo;
import com.ntdlg.ngg.ada.AdaChanxunJieguoEr;
import com.udows.common.proto.MPesticideSearchList;
import com.udows.common.proto.MPesticideStandardSearchList;

public class DfChanxunJieguo extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        if (son.getBuild() instanceof MPesticideSearchList) {
            size = ((MPesticideSearchList) son.getBuild()).mini.size();
            return new AdaChanxunJieguo(context, ((MPesticideSearchList) son.getBuild()).mini);
        } else if (son.getBuild() instanceof MPesticideStandardSearchList) {
            size = ((MPesticideStandardSearchList) son.getBuild()).mini.size();
            return new AdaChanxunJieguoEr(context, ((MPesticideStandardSearchList) son.getBuild()).mini);
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
