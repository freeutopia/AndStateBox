package com.utopia.state.base;

import android.content.Context;
import android.view.View;

import com.utopia.state.StateBox;
import com.utopia.state.util.IoUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class BaseStateView implements Serializable ,Cloneable {
    private View rootView;
    private Context context;
    private StateBox.OnReloadListener onReloadListener;

    public BaseStateView() {

    }

    public BaseStateView(View view, Context context, StateBox.OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
    }

    public void setOnReloadListener(StateBox.OnReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public View getRootView() {
        int resId = onCreateView();
        if (resId == 0 && rootView != null) {
            return rootView;
        }

        if (onBuildView(context) != null) {
            rootView = onBuildView(context);
        }

        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }

        onViewCreate(context, rootView);
        return rootView;
    }

    public StateBox.OnReloadListener getOnReloadListener() {
        return onReloadListener;
    }

    protected View onBuildView(Context context) {
        return null;
    }

    public View obtainRootView() {
        if (rootView == null) {
            rootView = View.inflate(context, onCreateView(), null);
        }
        return rootView;
    }

    protected void onViewCreate(Context context, View view) {
    }

    protected abstract int onCreateView();

    public void onAttach(Context context, View view) {
    }

    public void onDetach() {
    }

    protected <T extends View> T get(int resId) {
        return obtainRootView().findViewById(resId);
    }

    /**
     * 通过复制流的形式完成对象深拷贝
     */

    @Override
    protected BaseStateView clone() {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            //将object对象写入到ByteArrayOutputStream
            oos = new ObjectOutputStream(bao);
            oos.writeObject(this);

            //将ByteArrayOutputStream写入到object
            byteArrayInputStream = new ByteArrayInputStream(bao.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (BaseStateView) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(oos, bao, objectInputStream, byteArrayInputStream);
        }

        return null;
    }
}
