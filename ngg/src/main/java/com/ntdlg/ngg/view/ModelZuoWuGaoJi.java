package com.ntdlg.ngg.view;

import com.udows.common.proto.MCategory;

import java.io.Serializable;

/**
 * Created by df on 2016/12/1.
 */

public class ModelZuoWuGaoJi implements Serializable {
    public MCategory mMCategory;
    public int num;

    public ModelZuoWuGaoJi(MCategory mMCategory) {
        this.mMCategory = mMCategory;
    }
}
