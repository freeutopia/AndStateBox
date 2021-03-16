package com.utopia.state.util;

import java.io.Closeable;
import java.io.IOException;


public class IoUtils {
    public static void close(Closeable... closeables){
        if (closeables!=null && closeables.length>0){
            for (Closeable io : closeables){
                if (io != null) {
                    try {
                        io.close();
                        io = null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
