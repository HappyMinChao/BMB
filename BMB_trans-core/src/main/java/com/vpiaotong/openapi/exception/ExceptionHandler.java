package com.vpiaotong.openapi.exception;

/**
 * 处理Exception的工具类
 */
public final class ExceptionHandler {

    /**
     * 转换异常信息，把异常转换为运行时期异常
     * 
     * @param e 目标异常
     */
    public static void castException(Throwable e) {
        throw new RuntimeException(e);
    }

}
