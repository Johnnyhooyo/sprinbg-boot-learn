package com.hyq.learning.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author：huyuanqiang
 * @time: 2021-03-08 10:46
 * @description:
 **/
public class OrderNoUtil {

    /**
     * 32位 32进制
     */
    private static final char[] BASE_32 = new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','J','K',
            'L','M','N','P','Q','R','S','T','U','W','X','Y'};

    /**
     * 64位 64进制
     */
    private static final char[] BASE_64 = new char[]{'0','1','2','3','4','5','6','7','8','9',
            'A','B','C','D','E','F','G','H','I','J','K', 'L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k', 'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            '*','#'};

    private AtomicInteger countInMill = new AtomicInteger(0);
    private long curMill = 0L;

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);
        System.out.println(convert32(l));
        System.out.println(convert64(l));
    }

    public static String no() {
        long l = System.currentTimeMillis();
        System.out.println(l);
        String orderNo = convert32(l);
        return orderNo;
    }

    /**
     * 一毫秒内32/64个累加 及单机最大产出
     * @return
     */
    private static String getCount() {
        return "";
    }

    private static String convert32(long ten) {
        StringBuilder res = new StringBuilder();

        while (ten > 0) {
            res.insert(0, BASE_32[(int) ten & 0x1f]);
            ten = ten >> 5;
        }

        return res.toString();
    }

    /**
     * 大小写敏感 且包含特殊字符 '*','#'
     * @param ten 10进制输入
     * @return 64进制输出
     */
    private static String convert64(long ten) {
        StringBuilder res = new StringBuilder();

        while (ten > 0) {
            res.insert(0, BASE_64[(int) ten & 0x3f]);
            ten = ten >> 6;
        }

        return res.toString();
    }


}
