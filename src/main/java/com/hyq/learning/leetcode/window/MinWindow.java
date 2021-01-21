package com.hyq.learning.leetcode.window;

import java.util.HashMap;

/**
 * @author：huyuanqiang
 * @time: 2020-05-27 19:20
 * @description:
 **/
public class MinWindow {

    public static void main(String[] args) {
        String s = minWindow("sfdsasdfdsg", "sdd");
        System.out.println(s);
    }

    /**
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     */
    public static String minWindow(String s, String t) {
        char[] chars = t.toCharArray();
        HashMap<Character, Integer> charNum = new HashMap<>();
        HashMap<Character, Integer> tempNum = new HashMap<>();
        for (char aChar : chars) {
            charNum.merge(aChar, 1, Integer::sum);
        }
        char[] sArr = s.toCharArray();
        int resLeft = 0;
        int resRight = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while (right < sArr.length) {
            if (!charNum.containsKey(sArr[right])) {
                right++;
                continue;
            }
            tempNum.merge(sArr[right], 1, Integer::sum);
            if (check(charNum, tempNum)) {
                while (left <= right) {
                    if (tempNum.containsKey(sArr[left])) {
                        tempNum.put(sArr[left], tempNum.get(sArr[left]) - 1);
                        if (!check(charNum, tempNum)) {
                            if (right - left < resRight - resLeft) {
                                resRight = right;
                                resLeft = left;
                            }
                            left++;
                            break;
                        }
                    }
                    left++;
                }
            }
            right++;
        }
        return resRight == Integer.MAX_VALUE ? "" : s.substring(resLeft, resRight+1);
    }

    private static boolean check(HashMap<Character, Integer> charNum, HashMap<Character, Integer> tempNum) {
        for (Character character : charNum.keySet()) {
            if (tempNum.containsKey(character) && tempNum.get(character).compareTo(charNum.get(character)) >= 0) {
                continue;
            }
            return false;
        }
        return true;
    }
}
