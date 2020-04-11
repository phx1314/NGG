package com.ntdlg.ngg.view;

import com.udows.common.proto.MZaCaoInfo;

import java.io.Serializable;

/**
 * Created by df on 2016/12/1.
 */

public class ModelZaocao implements Serializable {
    public com.udows.common.proto.MZaCaoInfo mMZaCaoInfo;
    public MZaCaoInfo mMZaCaoInfo2;

    public ModelZaocao(MZaCaoInfo mMZaCaoInfo, MZaCaoInfo mMZaCaoInfo2) {
        this.mMZaCaoInfo = mMZaCaoInfo;
        this.mMZaCaoInfo2 = mMZaCaoInfo2;
    }
}
