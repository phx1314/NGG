//
//  CardGgdShangcheng
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**
   
*/

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.GgdShangcheng;

public class CardGgdShangcheng extends Card<String>{
	public String item;
	
	public CardGgdShangcheng() {
    	this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_GGDSHANGCHENG;
    }


 	@Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = GgdShangcheng.getView(context, null);;
        }
        GgdShangcheng mGgdShangcheng=(GgdShangcheng) contentView.getTag();
//        mGgdShangcheng.set(item);
        return contentView;
    }
    
    

}


