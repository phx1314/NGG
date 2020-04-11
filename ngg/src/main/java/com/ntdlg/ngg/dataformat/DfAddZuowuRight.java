//
//  DfAddZuowuRight
//
//  Created by df on 2017-03-10 13:50:16
//  Copyright (c) df All rights reserved.


/**
   
*/

package com.ntdlg.ngg.dataformat;

import android.content.Context;

import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.widget.util.DataFormat;

public class DfAddZuowuRight extends DataFormat{
    int size = 1;

	@Override
	public boolean hasNext() {
		return size >= Integer.MAX_VALUE;
	}

	@Override
	public MAdapter<?> getAdapter(Context context, Son son, int page) {
		return  null ;
	}

	@Override
	public String[][] getPageNext() {
		return null;
	}

	@Override
	public void reload() {

	}
}
