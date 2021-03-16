package com.utopia.state.base;

import java.util.HashMap;
import java.util.Map;

public class ViewCache {
    private final static Map<Class<? extends BaseStateView>, BaseStateView> mCache = new HashMap<>();

    public static BaseStateView get(Class<? extends BaseStateView> stateClazz) {
        BaseStateView stateView = mCache.get(stateClazz);
        if (stateView == null){
            return add(stateClazz);
        }
        return stateView.clone();
    }

    /**
     * 创建状态页面后，将母版放置到缓存队列，下次使用时可以通过原型直接clone
     */
    public static BaseStateView add(Class<? extends BaseStateView> stateClazz){
        BaseStateView stateView = null;
        try {
            stateView = stateClazz.newInstance();
            mCache.put(stateClazz,stateView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stateView;
    }

    /**
     * 创建状态页面后，将母版放置到缓存队列，下次使用时可以通过原型直接clone
     */
    public static BaseStateView add(BaseStateView stateView){
        mCache.put(stateView.getClass(),stateView);
        return stateView;
    }
}
