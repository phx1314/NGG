//
//  AdaCart
//
//  Created by Administrator on 2017-02-04 18:35:42
//  Copyright (c) Administrator All rights reserved.


/**

 */

package com.ntdlg.ngg.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mdx.framework.adapter.MAdapter;
import com.ntdlg.ngg.item.Cart;
import com.ntdlg.ngg.view.ModelCart;

import java.util.List;

public class AdaCart extends MAdapter<ModelCart> {

    public AdaCart(Context context, List<ModelCart> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelCart item = get(position);
        if (convertView == null) {
            convertView = Cart.getView(getContext(), parent);
        }
        Cart mCart = (Cart) convertView.getTag();
        mCart.set(item, this);
        return convertView;
    }
}
