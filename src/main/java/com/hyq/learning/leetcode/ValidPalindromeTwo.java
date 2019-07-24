package com.hyq.learning.leetcode;

import org.springframework.util.StopWatch;

/**
 * @ClassName ValidPalindromeTwo
 * @Author dibulidohu
 * @Date 2019/6/19 16:06
 * @Description 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 */
public class ValidPalindromeTwo {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println(validPalindrome("pidbliassaqozokmtgahluruufwbjdtayuhbxwoicviygilgzduudzgligyviciowxbhuyatdjbwfuurulhagtmkozoqassailbdip"));
        stopWatch.stop();
        System.out.println(stopWatch);

        System.out.println(isPalindrome("0P"));
    }

    public static boolean validPalindrome(String s) {
        boolean isdeleted = false;
        boolean canBackTrace = false;
        int mark = 0;
        int left = 0;
        int right = 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length() / 2; i++) {
            if (chars[i + left] != chars[s.length() - i - right]) {
                if (!isdeleted) {
                    if (chars[i + left + 1] == chars[s.length() - i - right] && chars[i + left] == chars[s.length() - i - right - 1]) {
                        left = 1;
                        mark = i;
                        isdeleted = true;
                        canBackTrace = true;
                        i--;
                    } else if (chars[i + left + 1] == chars[s.length() - i - right]) {
                        left = 1;
                        i--;
                        isdeleted = true;
                    } else if (chars[i + left] == chars[s.length() - i - right - 1]) {
                        right = 2;
                        i--;
                        isdeleted = true;
                    } else {
                        return false;
                    }
                } else if (canBackTrace){
                    canBackTrace = false;
                    i = mark;
                    right = 2;
                    left = 0;
                    i--;
                    isdeleted = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     */
    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        int i = 0;
        int j = chars.length - 1;
        while(j > i && i + left < chars.length && j - right > 0) {
            if (!(chars[i + left] >= '0' && chars[i + left] <= '9')
                    && !(chars[i + left] >= 'a' && chars[i + left] <= 'z')
                    && !(chars[i + left] >= 'A' && chars[i + left] <= 'Z')) {
                left++;
            } else if (!(chars[j - right] >= '0' && chars[j - right] <= '9')
                    && !(chars[j - right] >= 'a' && chars[j- right] <= 'z')
                    && !(chars[j - right] >= 'A' && chars[j - right] <= 'Z')) {
                right++;
            } else {
                if(chars[i + left] == chars[j - right]
                        || (chars[i + left] + 32 == chars[j - right] && chars[i+left] > 64 && chars[i+left] < 91 )
                        || (chars[i + left] - 32 == chars[j - right] && chars[i+left] > 96 && chars[i+left] < 123 )) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
