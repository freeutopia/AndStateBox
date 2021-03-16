package com.utopia.state.util;

import android.os.Looper;

public class StateBoxUtil {

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    @SafeVarargs
    public static <T> boolean checkNotNull(T... arg) {
        if (arg == null || arg.length == 0){
            return false;
        }

        for (T t : arg){
            if (t == null){
                return false;
            }
        }

        return true;
    }
}
