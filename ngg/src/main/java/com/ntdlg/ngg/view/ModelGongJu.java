package com.ntdlg.ngg.view;

import com.udows.common.proto.MScGoods;

import java.io.Serializable;

/**
 * Created by df on 2016/12/1.
 */

public class ModelGongJu implements Serializable {
    public MScGoods mMScGoods;
    public MScGoods mMScGoods2;

    public ModelGongJu(MScGoods mMScGoods, MScGoods mMScGoods2) {
        this.mMScGoods = mMScGoods;
        this.mMScGoods2 = mMScGoods2;
    }
}
