//
//  DfShouye
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.dataformat;

import android.content.Context;
import android.text.TextUtils;

import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.ntdlg.ngg.ada.AdaShouye;
import com.udows.common.proto.STopicList;

public class DfShouye extends DataFormat {
    int size = 1;
    public String from;

    public DfShouye(String from) {
        this.from = from;
    }

    public DfShouye() {
    }

    @Override
    public boolean hasNext() {
        return size >= 10;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        size = ((STopicList) son.getBuild()).list.size();
        if (page == 1) {
            if(TextUtils.isEmpty(from)){
                Frame.HANDLES.sentAll("FrgShouye", 0, null);
            }else{
                Frame.HANDLES.sentAll(from, 0, null);
            }
        }
        return new AdaShouye(context, ((STopicList) son.getBuild()).list);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
