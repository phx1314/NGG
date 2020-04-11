package com.ntdlg.ngg.dialog;

import android.content.Context;
import android.content.Intent;

import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.prompt.ErrorPrompt;
import com.mdx.framework.server.api.ErrorMsg;
import com.mdx.framework.utility.Helper;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.frg.FrgLogin;

public class HasDeleteErrorLogin implements ErrorPrompt {
    private ErrorMsg errorMsg;

    private Context context;

    public HasDeleteErrorLogin(Context context) {
        this.context = context;
    }

    public void setMsg(ErrorMsg em) {
        this.errorMsg = em;
    }

    public void show() {
        F.Login("", "");
        Frame.HANDLES.sentAll("FrgWode", 0, null);
        Frame.HANDLES.close("FrgLogin");
        Helper.startActivity(context, Intent.FLAG_ACTIVITY_CLEAR_TOP, FrgLogin.class, TitleAct.class);
    }

    public void dismiss() {
        // dialog.dismiss();
    }
}
