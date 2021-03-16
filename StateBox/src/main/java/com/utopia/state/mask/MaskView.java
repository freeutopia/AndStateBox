package com.utopia.state.mask;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.utopia.state.StateBox;
import com.utopia.state.base.ViewCache;
import com.utopia.state.util.StateBoxUtil;
import com.utopia.state.base.BaseStateView;

@SuppressLint("ViewConstructor")
public class MaskView extends FrameLayout {
    private Class<? extends BaseStateView> preCallback;
    private final StateBox.OnReloadListener reloadListener;

    public MaskView(View view, StateBox.OnReloadListener reloadListener) {
        super(view.getContext());
        this.reloadListener = reloadListener;
    }

    /**
     * 展示成功页面（被遮罩层页面）
     */
    public void setupSuccessLayout(BaseStateView state) {
        if (StateBoxUtil.checkNotNull(state)) {
            state.setContext(getContext());

            View rootView = state.getRootView();
            rootView.setVisibility(View.INVISIBLE);

            addView(rootView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    public void show(final Class<? extends BaseStateView> state , MaskedView maskedView) {
        if (StateBoxUtil.checkNotNull(state)) {
            if (StateBoxUtil.isMainThread()) {
                showWithMainThread(state , maskedView);
            } else {
                post(() -> showWithMainThread(state , maskedView));
            }
        }
    }


    private void showWithMainThread(Class<? extends BaseStateView> status , MaskedView maskedView) {

        if (preCallback == status) {//重复调用
            return;
        }

        //销毁上一个页面
        BaseStateView preBaseStateView = ViewCache.get(preCallback);
        if (StateBoxUtil.checkNotNull(preBaseStateView)) {
            preBaseStateView.onDetach();
        }

        //清理容器页面元素
        if (getChildCount() > 1) {
            removeViewAt(1);
        }

        BaseStateView currentStateView = ViewCache.get(status);
        if (status == MaskedView.class && StateBoxUtil.checkNotNull(maskedView)) {
            //显示被遮罩层
            maskedView.show();
        } else if (StateBoxUtil.checkNotNull(currentStateView, maskedView)) {
            maskedView.hide();
            currentStateView.setContext(getContext());
            currentStateView.setOnReloadListener(reloadListener);
            View rootView = currentStateView.getRootView();
            addRootView(rootView);
            currentStateView.onAttach(getContext(), rootView);
        }

        preCallback = status;
    }

    private void addRootView(View rootView) {
        ViewGroup parentView = (ViewGroup) rootView.getParent();
        if (StateBoxUtil.checkNotNull(rootView)) {
            final int childIndex = parentView == null ? -1 : parentView.indexOfChild(rootView);

            if (childIndex >= 0) {
                parentView.removeViewAt(childIndex);
            }

            addView(rootView);
        }
    }
}
