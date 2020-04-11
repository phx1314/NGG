package com.ntdlg.ngg.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MLinearLayout extends LinearLayout {
    private int swidth = -1, sheight = -1;

    public void setOnLayoutSizeChange(OnLayoutSizeChange onLayoutSizeChange) {
        this.onLayoutSizeChange = onLayoutSizeChange;
    }

    private OnLayoutSizeChange onLayoutSizeChange;

    public MLinearLayout(Context context) {
        super(context);
    }

    public MLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public MLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = this.getMeasuredHeight();
        int width = this.getMeasuredWidth();
        if (swidth == width && sheight == height) {

        } else {
            if (onLayoutSizeChange != null) {
                onLayoutSizeChange.onLayoutSizeChange(width, height);
            }
            this.swidth = width;
            this.sheight = height;
        }
    }


    public interface OnLayoutSizeChange {
        public void onLayoutSizeChange(int width, int height);
    }
}
