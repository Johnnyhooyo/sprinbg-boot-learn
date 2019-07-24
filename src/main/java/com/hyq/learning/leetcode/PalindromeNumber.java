package com.hyq.learning.leetcode;

/**
 * @ClassName PalindeomeNumber
 * @Author dibulidohu
 * @Date 2019/6/24 19:10
 * @Description
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        System.out.println(isPalindrome(10000221));
        System.out.println(palindrome(10000221));
        System.out.println(drome(10000221));
    }

    /***
     * 反转后半部分 最后x/10 是如果是奇数位 去掉中间一位
     */
    public static boolean drome(int x) {
        if (x <0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int right = 0;
        while (x > right) {
            right = right * 10 + x % 10;
            x = x /10;
        }
        return x == right || x / 10 == right;
    }

    /**
     * 思路：1.先求出位数
     * 2. 去除第一位和最后一位比较 并且把原来数字取中间位数循环
     */
    public static boolean palindrome(int x) {
        if (x < 0) {
            return false;
        }
        int len =1;
        while (x / len > 1) {
            len *= 10;
        }
        while (x > 0) {
            int left = x / len;
            int right = x % 10;
            if (left != right) {
                return false;
            }
            x = x % len / 10;
            len /= 100;
        }
        return true;
    }

    /**
     * Math.pow(10, len) 太耗时
     */
    public static boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        int len = 1;
        while(x / Math.pow(10, len) >= 1) {
            len++;
        }
        int right = 1;
        while(len >= right) {
            if(getNum(x, len) != getNum(x, right)) {
                return false;
            }
            len--;
            right++;
        }
        return true;
    }

    public static int getNum(int x, int point) {
        if (x >= 10) {
            int i = (int) (x / Math.pow(10, point - 1) % 10);
            return i;
        } else {
            return x % 10;
        }
    }


}
