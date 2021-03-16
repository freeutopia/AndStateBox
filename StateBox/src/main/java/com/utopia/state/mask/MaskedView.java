package com.utopia.state.mask;

import android.view.View;

import com.utopia.state.StateBox;
import com.utopia.state.base.BaseStateView;

public class MaskedView extends BaseStateView {
    public MaskedView(View view, StateBox.OnReloadListener onReloadListener) {
        super(view, view.getContext(), onReloadListener);
    }

    @Override
    protected int onCreateView() {
        return 0;
    }

    public void hide() {
        obtainRootView().setVisibility(View.INVISIBLE);
    }

    public void show() {
        obtainRootView().setVisibility(View.VISIBLE);
    }

}
