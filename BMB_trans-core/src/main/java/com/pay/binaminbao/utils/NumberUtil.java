package com.pay.binaminbao.utils;

import java.util.Random;

public class NumberUtil {
    /** 生产 n 位随机数
     *  min 最小的n为数
     *  max 最大的n位数
     */
    public static int NextInt(final int min, final int max)
    {
        int tmp = Math.abs( new Random().nextInt());
        return tmp % (max - min + 1) + min;
    }
}
