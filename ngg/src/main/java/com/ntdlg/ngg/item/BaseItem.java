//
//  BaseItem
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.item;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class BaseItem implements OnClickListener {
	protected Context context;
	protected View contentview;

	@Override
	public void onClick(View v) {

	}

	public View findViewById(int id) {
         return this.contentview.findViewById(id);
    }

}

