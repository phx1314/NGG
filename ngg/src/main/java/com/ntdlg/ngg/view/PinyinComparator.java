package com.ntdlg.ngg.view;

import com.ntdlg.ngg.F;
import com.udows.common.proto.MCategory;

import java.util.Comparator;

/**
 * @author xiaanming
 */
public class PinyinComparator implements Comparator<MCategory> {

    public int compare(MCategory o1, MCategory o2) {
        if (F.toPinYin(o2.name.charAt(0)).equals("#")) {
            return -1;
        } else if (F.toPinYin(o1.name.charAt(0)).equals("#")) {
            return 1;
        } else {
            return F.toPinYin(o1.name.charAt(0)) .compareTo(F.toPinYin(o2.name.charAt(0)) );

        }
    }

}
