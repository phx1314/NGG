package com.ntdlg.ngg.view;

import com.udows.common.proto.MCategory;

import java.io.Serializable;

/**
 * Created by df on 2016/12/1.
 */

public class ModelZuoWuPOsition implements Serializable {
    public MCategory mMCategory;
    public int position ;

    public ModelZuoWuPOsition(MCategory mMCategory, int position) {
        this.mMCategory = mMCategory;
        this.position = position;
    }
}
