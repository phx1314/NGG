package com.ntdlg.ngg.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.ntdlg.ngg.R;

public class PopShowShouye implements OnClickListener {

    private Context context;
    private TextView clk_mTextView_fjdwt;
    private TextView clk_mTextView_tjwt;
    private TextView clk_mTextView_djjwt;
    private View view;
    private PopupWindow popwindow;
    private View popview;

    public PopShowShouye(Context context, View view
    ) {
        super();
        this.context = context;
        this.view = view;
        LayoutInflater flater = LayoutInflater.from(this.context);
        popview = flater.inflate(R.layout.item_shouye_pop, null);
        popwindow = new PopupWindow(popview, LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        clk_mTextView_fjdwt = (TextView) popview
                .findViewById(R.id.clk_mTextView_fjdwt);
        clk_mTextView_tjwt = (TextView) popview
                .findViewById(R.id.clk_mTextView_tjwt);
        clk_mTextView_djjwt = (TextView) popview
                .findViewById(R.id.clk_mTextView_djjwt);
        popwindow.setBackgroundDrawable(new BitmapDrawable(context
                .getResources()));
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);
        setClick();
    }

    private void setClick() {
        clk_mTextView_fjdwt.setOnClickListener(this);
        clk_mTextView_tjwt.setOnClickListener(this);
        clk_mTextView_djjwt.setOnClickListener(this);
    }

    public void setType(int type) {

    }

    @SuppressLint("NewApi")
    public void show() {
        popwindow.showAsDropDown(view, 0, 0);
    }

    public void hide() {
        popwindow.dismiss();
    }

    public boolean isShow() {
        return popwindow.isShowing();
    }

    @Override
    public void onClick(View arg0) {
        hide();
        if (arg0.getId() == R.id.clk_mTextView_fjdwt) {
            Frame.HANDLES.sentAll("FrgShouye", 2, 4.0);
        } else if (arg0.getId() == R.id.clk_mTextView_tjwt) {
            Frame.HANDLES.sentAll("FrgShouye", 2, 5.0);
        } else if (arg0.getId() == R.id.clk_mTextView_djjwt) {
            Frame.HANDLES.sentAll("FrgShouye", 2, 6.0);
        }
    }
}
