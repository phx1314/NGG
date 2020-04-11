package com.ntdlg.ngg.view;

import com.udows.common.proto.MCreditGoods;

import java.io.Serializable;

/**
 * Created by df on 2016/12/1.
 */

public class ModelShangCheng implements Serializable {
    public MCreditGoods mMCreditGoods1;
    public MCreditGoods mMCreditGoods2;

    public ModelShangCheng(MCreditGoods mMCreditGoods1, MCreditGoods mMCreditGoods2) {
        this.mMCreditGoods1 = mMCreditGoods1;
        this.mMCreditGoods2 = mMCreditGoods2;
    }
}
