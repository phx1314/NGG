//
//  DfGgdShangcheng
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
import com.ntdlg.ngg.ada.AdaGgdShangcheng;
import com.ntdlg.ngg.view.ModelShangCheng;
import com.udows.common.proto.MCreditGoodsList;

import java.util.ArrayList;
import java.util.List;

public class DfGgdShangcheng extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        size = ((MCreditGoodsList) son.getBuild()).mini.size();
        List<ModelShangCheng> mModelShangChengs = new ArrayList<>();
        for (int i = 0; i < ((MCreditGoodsList) son.getBuild()).mini.size(); i++) {
            if (i % 2 == 0) {
                ModelShangCheng mModelShangCheng;
                if (i + 1 < size) {
                    mModelShangCheng = new ModelShangCheng(((MCreditGoodsList) son.getBuild()).mini.get(i), ((MCreditGoodsList) son.getBuild()).mini.get(i + 1));
                } else {
                    mModelShangCheng = new ModelShangCheng(((MCreditGoodsList) son.getBuild()).mini.get(i), null);
                }
                mModelShangChengs.add(mModelShangCheng);
            }
        }
        return new AdaGgdShangcheng(context, mModelShangChengs);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
