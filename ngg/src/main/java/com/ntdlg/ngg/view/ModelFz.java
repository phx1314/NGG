package com.ntdlg.ngg.view;

import java.io.Serializable;

/**
 * Created by df on 2016/12/1.
 */

public class ModelFz implements Serializable {
    public String cropName;
    public String name;
    public String num;
    public String type;

    public ModelFz(String cropName, String name, String num, String type) {
        this.cropName = cropName;
        this.name = name;
        this.num = num;
        this.type = type;
    }
}
