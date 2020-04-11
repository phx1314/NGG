package com.ntdlg.ngg.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.mdx.framework.Frame;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.ada.AdaWenZhuanjiaPo;
import com.udows.common.proto.MCategoryList;

public class PopShowZhuanjiaType implements OnClickListener {

    private Context context;
    private ListView mListView;
    private View view;
    private PopupWindow popwindow;
    private View popview;
    private MCategoryList mMCategoryList;

    public PopShowZhuanjiaType(Context context, View view, MCategoryList mMCategoryList) {
        super();
        this.context = context;
        this.mMCategoryList = mMCategoryList;
        this.view = view;
        LayoutInflater flater = LayoutInflater.from(this.context);
        popview = flater.inflate(R.layout.item_wen_zhuanjia, null);
        popwindow = new PopupWindow(popview, view.getWidth(),
                LayoutParams.WRAP_CONTENT);
        mListView = (ListView) popview
                .findViewById(R.id.mListView);
        popwindow.setBackgroundDrawable(new BitmapDrawable(context
                .getResources()));
        popwindow.setTouchable(true);
        popwindow.setOutsideTouchable(true);
        popwindow.setFocusable(true);
        setClick();
    }

    private void setClick() {
        mListView.setAdapter(new AdaWenZhuanjiaPo(context, mMCategoryList.mini, 1, popwindow));
        popwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Frame.HANDLES.sentAll("FrgWenZhuanjia", 3, null);
            }
        });
    }

    public void setType(int type) {

    }

    @SuppressLint("NewApi")
    public void show() {
        popwindow.showAsDropDown(view, 0, (int) context.getResources().getDimension(R.dimen.j3dp));
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
    }
}
