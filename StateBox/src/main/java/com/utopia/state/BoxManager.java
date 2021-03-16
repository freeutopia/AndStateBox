package com.utopia.state;

import com.utopia.state.base.BaseStateView;
import com.utopia.state.base.ViewCache;
import com.utopia.state.mask.MaskView;
import com.utopia.state.mask.MaskedView;
import com.utopia.state.target.Target;
import com.utopia.state.util.StateBoxUtil;

public class BoxManager {
    private final MaskView maskView;
    private final MaskedView maskedView;
    /**
     * 构造方法
     * @param target 操作目标
     * @param config 默认配置
     */
    BoxManager(Target target, StateBox.Config config) {
        maskView = target.getMaskView();
        maskedView = target.getMaskedView();
        if (StateBoxUtil.checkNotNull(config)) {
            appConfig(config);
        }
    }

    /**
     * 应用系统页面全局配置信息
     * @param config 配置参数
     */
    private void appConfig(StateBox.Config config) {
        if (StateBoxUtil.checkNotNull(maskView,maskedView)) {
            maskView.show(config.getDefaultPageState() , maskedView);
        }
    }


    /**
     * 隐藏遮罩层
     */
    public void hidden() {
        if (StateBoxUtil.checkNotNull(maskView,maskedView)) {
            maskView.show(MaskedView.class,maskedView);
        }
    }

    /**
     * 按照类名显示遮罩层里已有的状态
     * @param state class
     */
    public void show(Class<? extends BaseStateView> state) {
        if (StateBoxUtil.checkNotNull(maskView)) {
            maskView.show(state,maskedView);
        }
    }

    /**
     * 返回遮罩层
     */
    public MaskView getMaskView() {
        return maskView;
    }

    /**
     * 往遮罩层里添加页面状态
     * @param state 页面状态
     * @return StateService
     */
    public BoxManager addStatePage(BaseStateView state) {
        ViewCache.add(state);
        return this;
    }
}
