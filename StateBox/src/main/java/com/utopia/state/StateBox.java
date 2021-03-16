package com.utopia.state;

import android.app.Activity;
import android.view.View;
import com.utopia.state.base.BaseStateView;
import com.utopia.state.base.ViewCache;
import com.utopia.state.mask.MaskView;
import com.utopia.state.mask.MaskedView;
import com.utopia.state.target.Target;
import com.utopia.state.target.ViewTarget;

public final class StateBox {
    private final static StateBox STATE_BOX = new StateBox();
    private Config config = null;

    public static StateBox getDefault() {
        return STATE_BOX;
    }

    private StateBox() {}

    public void setConfig(Config config) {
        this.config = config;
    }

    public BoxManager inject(View target , OnReloadListener listener) {
        MaskView maskView = new MaskView(target, listener);//构造遮罩层
        MaskedView maskedView = new MaskedView(target, listener);//构造被遮罩层

        Target targetContext = new ViewTarget(target, maskView , maskedView);
        targetContext.replaceView();
        return new BoxManager(targetContext , config);
    }

    public BoxManager inject(Activity activity , int viewId , OnReloadListener listener) {
        View target = activity.findViewById(viewId);
        return inject(target,listener);
    }


    /**
     * 全局配置
     */
    public final static class Config {
        private Class<? extends BaseStateView> defaultPageState = null;//初始默认界面

        public Config addStateView(Class<? extends BaseStateView>  state) {
            ViewCache.add(state);
            return this;
        }

        public Config addStateView(Class<? extends BaseStateView> state , boolean isDefault) {
            ViewCache.add(state);
            if (isDefault){
                this.defaultPageState = state;
            }
            return this;
        }

        Class<? extends BaseStateView> getDefaultPageState() {
            return defaultPageState;
        }
    }

    public interface OnReloadListener {
        void onReload(View v);
    }
}
