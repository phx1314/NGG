package com.ntdlg.ngg.view;

import com.udows.common.proto.MCategory;

import java.io.Serializable;

/**
 * Created by df on 2016/12/1.
 */

public class ModelZuoWu implements Serializable {
    public MCategory mMCategory;
    public boolean isXuanZhong;

    public ModelZuoWu(MCategory mMCategory, boolean isXuanZhong) {
        this.mMCategory = mMCategory;
        this.isXuanZhong = isXuanZhong;
    }
}
