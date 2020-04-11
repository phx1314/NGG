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
import com.udows.common.proto.MCategory;

import java.util.ArrayList;
import java.util.List;

public class PopShowZhiNengPx implements OnClickListener {

    private Context context;
    private ListView mListView;
    private View view;
    private PopupWindow popwindow;
    private View popview;
    private String strs[] = {"全部", "推荐专家", "新进榜", "粉丝榜", "回答榜", "被赞榜", "视频榜"};

    public PopShowZhiNengPx(Context context, View view) {
        super();
        this.context = context;
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
        List<MCategory> datas = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            MCategory mMCategory = new MCategory();
            mMCategory.code = i + "";
            mMCategory.name = strs[i];
            datas.add(mMCategory);
        }
        mListView.setAdapter(new AdaWenZhuanjiaPo(context, datas, 2, popwindow));
        popwindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Frame.HANDLES.sentAll("FrgWenZhuanjia", 4, null);
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
