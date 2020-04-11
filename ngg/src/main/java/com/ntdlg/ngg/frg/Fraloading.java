package com.ntdlg.ngg.frg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.igexin.sdk.PushManager;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.activity.MFragment;
import com.mdx.framework.server.api.Son;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;
import com.ntdlg.ngg.F;
import com.ntdlg.ngg.R;
import com.ntdlg.ngg.service.LocService;
import com.udows.common.proto.ApisFactory;
import com.udows.common.proto.SUser;


public class Fraloading extends MFragment {
    private MImageView iv_loading;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.act_start);
        PushManager.getInstance().initialize(
                getActivity().getApplicationContext());
        iv_loading = (MImageView) findViewById(R.id.iv_loading);

        getActivity().startService(
                new Intent(getContext(), LocService.class));
        if (TextUtils.isEmpty(F.UserId)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Helper.startActivity(getContext(), FrgHome.class, IndexAct.class);
                    Fraloading.this.finish();
                }
            }, 2000);
        } else {
            ApisFactory.getApiSGetUserInfo().load(getContext(), this, "SGetUserInfo", F.UserId);
        }
    }

    public void SGetUserInfo(Son s) {
        F.mSUser = (SUser) s.getBuild();
        Fraloading.this.finish();
        Helper.startActivity(getContext(), FrgHome.class, IndexAct.class);
    }
}
