//
//  CardXinwenzixun
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.card;

import android.content.Context;
import android.view.View;

import com.mdx.framework.adapter.Card;
import com.ntdlg.ngg.item.Xinwenzixun;

public class CardXinwenzixun extends Card<String> {
    public String item;

    public CardXinwenzixun() {
        this.type = com.ntdlg.ngg.commons.CardIDS.CARDID_XINWENZIXUN;
    }


    @Override
    public View getView(Context context, View contentView) {
        if (contentView == null) {
            contentView = Xinwenzixun.getView(context, null);
        }
        Xinwenzixun mXinwenzixun = (Xinwenzixun) contentView.getTag();
//        mXinwenzixun.set(item);
        return contentView;
    }


}


