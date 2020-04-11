//
//  DfShangchengDuihuanjilu
//
//  Created by DELL on 2017-04-01 15:46:12
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.ntdlg.ngg.dataformat;

import android.content.Context;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;
import com.ntdlg.ngg.ada.AdaShangchengDuihuanjilu;
import com.udows.common.proto.MCreditExchangeLogList;

public class DfShangchengDuihuanjilu extends DataFormat{
    int size = 1;

	@Override
	public boolean hasNext() {
		return size >= 10;
	}

	@Override
	public MAdapter<?> getAdapter(Context context, Son son, int page) {
		size=((MCreditExchangeLogList)son.getBuild()).mini.size();
		return new AdaShangchengDuihuanjilu(context,((MCreditExchangeLogList)son.getBuild()).mini);
	}

	@Override
	public String[][] getPageNext() {
		return null;
	}

	@Override
	public void reload() {

	}
}
