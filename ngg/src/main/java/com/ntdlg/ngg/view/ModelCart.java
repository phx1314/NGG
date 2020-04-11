package com.ntdlg.ngg.view;

import com.udows.common.proto.MCart;

import java.io.Serializable;

/**
 * Created by df on 2016/12/1.
 */

public class ModelCart implements Serializable {
    public MCart mMShoppingCart;
    public boolean isChoose;

    public ModelCart(MCart mMShoppingCart) {
        this.mMShoppingCart = mMShoppingCart;
    }

    public MCart getmMShoppingCart() {
        return mMShoppingCart;
    }

    public void setmMShoppingCart(MCart mMShoppingCart) {
        this.mMShoppingCart = mMShoppingCart;
    }

    public boolean isChoose() {
        return isChoose;
    }

    public void setChoose(boolean choose) {
        isChoose = choose;
    }
}
