package com.spring.boot.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author yuderen
 * @version 2019/7/12 14:43
 */
public class IOUtil {

    /**
     * 关闭输入流
     * @param inputStream   输入流
     */
    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 关闭输入流
     * @param inputStream1  输入流
     * @param inputStream2  输入流
     */
    public static void close(InputStream inputStream1, InputStream inputStream2) {
        if (inputStream1 != null) {
            try {
                inputStream1.close();
            } catch (IOException e) {
            }
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 关闭输出流
     * @param outputStream  输出流
     */
    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 关闭输出流
     * @param outputStream1 输出流
     * @param outputStream2 输出流
     */
    public static void close(OutputStream outputStream1, OutputStream outputStream2) {
        if (outputStream1 != null) {
            try {
                outputStream1.close();
            } catch (IOException e) {
            }
        }
        if (outputStream2 != null) {
            try {
                outputStream2.close();
            } catch (IOException e) {
            }
        }
    }

}
