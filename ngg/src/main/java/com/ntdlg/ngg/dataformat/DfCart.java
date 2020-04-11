//
//  DfCart
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
import com.ntdlg.ngg.ada.AdaCart;
import com.ntdlg.ngg.view.ModelCart;
import com.udows.common.proto.MCart;
import com.udows.common.proto.MShoppingCartList;

import java.util.ArrayList;
import java.util.List;

public class DfCart extends DataFormat {
    int size = 1;

    @Override
    public boolean hasNext() {
        return size >= Integer.MAX_VALUE;
    }

    @Override
    public MAdapter<?> getAdapter(Context context, Son son, int page) {
        List<ModelCart> mModelCarts = new ArrayList<>();
        if (((MShoppingCartList) son.getBuild()).cart.size() > 0) {
            for (MCart mMShoppingCart : ((MShoppingCartList) son.getBuild()).cart.get(0).goods) {
                ModelCart mModelCart = new ModelCart(mMShoppingCart);
                if (TextUtils.isEmpty(mMShoppingCart.goodsId)) {
                    mModelCart.isChoose = true;
                }
                mModelCarts.add(mModelCart);
            }
        }
        if (((MShoppingCartList) son.getBuild()).cart.size() > 0) {
            Frame.HANDLES.sentAll("FrgCart", 2, ((MShoppingCartList) son.getBuild()).cart.get(0));
        }
        return new AdaCart(context, mModelCarts);
    }

    @Override
    public String[][] getPageNext() {
        return null;
    }

    @Override
    public void reload() {

    }
}
