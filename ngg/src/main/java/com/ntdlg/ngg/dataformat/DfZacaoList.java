//
//  DfZacaoList
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
import com.ntdlg.ngg.ada.AdaZacaoList;
import com.ntdlg.ngg.view.ModelZaocao;
import com.udows.common.proto.MZaCaoInfoList;

import java.util.ArrayList;
import java.util.List;

public class DfZacaoList extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= Integer.MAX_VALUE;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        size = ((MZaCaoInfoList) son.getBuild()).mini.size();
        List<ModelZaocao> mModelZaocaos = new ArrayList<>();
        for (int i = 0; i < ((MZaCaoInfoList) son.getBuild()).mini.size(); i++) {
            if (i % 2 == 0) {
                ModelZaocao mModelZaocao;
                if (i + 1 < size) {
                    mModelZaocao = new ModelZaocao(((MZaCaoInfoList) son.getBuild()).mini.get(i), ((MZaCaoInfoList) son.getBuild()).mini.get(i + 1));
                } else {
                    mModelZaocao = new ModelZaocao(((MZaCaoInfoList) son.getBuild()).mini.get(i), null);
                }
                mModelZaocaos.add(mModelZaocao);
            }
        }
        return new AdaZacaoList(context, mModelZaocaos);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
