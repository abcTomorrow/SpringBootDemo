package com.wojiushiwo.order.utils;

import java.util.Random;

/**
 * Created by 我就是我
 * 2018/11/13 下午4:24
 */
public class KeyUtil {

    public static synchronized String getUnikey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
