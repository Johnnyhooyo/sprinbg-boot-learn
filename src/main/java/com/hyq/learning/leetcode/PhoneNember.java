package com.hyq.learning.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author dibulidohu
 * @classname PhoneNember
 * @date 2019/6/611:20
 * @description
 */
public class PhoneNember {

    public static void main(String[] args) {
        letterCombinations("1245");
    }

    public static List<String> letterCombinations(String digits) {
        String[] map = {"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        List<String> res = new ArrayList<>();
        for(int i = 0; i < digits.length(); i++) {
            String str = map[Integer.valueOf(digits.substring(i, i+1)) - 1];
            int size = res.size();
            if (size == 0) {
                for (char s : str.toCharArray()) {
                    res.add(String.valueOf(s));
                }
            } else {
                for (char s : str.toCharArray()) {
                    for (int j = 0; j < size; j++) {
                        String s1 = res.get(j) + String.valueOf(s);
                        res.add(s1);
                    }
                }
                res.subList(0, size).clear();
            }
        }
        return res;
    }
}
