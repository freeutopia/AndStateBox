package com.utopia.demo.view;

import android.content.Context;
import android.view.View;

import com.utopia.demo.R;
import com.utopia.state.base.BaseStateView;

public class EmptyStateView extends BaseStateView {

    @Override
    protected int onCreateView() {
        return R.layout.layout_empty;
    }

    @Override
    protected void onViewCreate(Context context, View view) {
        super.onViewCreate(context, view);

        get(R.id.tv_retry).setOnClickListener(v->{
            if (getOnReloadListener()!=null) {
                getOnReloadListener().onReload(v);
            }
        });
    }
}
