package com.hyq.learning.leetcode;

import java.util.Arrays;

/**
 * @ClassName VersionCompare
 * @Author dibulidohu
 * @Date 2019/6/19 15:16
 * @Description 比较两个版本号 version1 和 version2。
 * 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
 *
 * 你可以假设版本字符串非空，并且只包含数字和 . 字符。
 *
 *  . 字符不代表小数点，而是用于分隔数字序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/compare-version-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class VersionCompare {
    public static void main(String[] args) {
        String ver1 = "1.1";
        String ver2 = "1.0";
        int i = compareVersion(ver1, ver2);
        System.out.println(i);
    }

    /***
     * 注意String.split方法的坑 转义字符的使用
     */
    public static int compareVersion(String version1, String version2) {
        String[] split1 = version1.split("\\.");
        String[] split2 = version2.split("\\.");
        int maxlen = split1.length > split2.length ? split1.length : split2.length;
        for (int i = 0; i < maxlen; i++) {
            int a = i < split1.length ? Integer.valueOf(split1[i]) : 0;
            int b = i < split2.length ? Integer.valueOf(split2[i]) : 0;
            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            }
        }
        return 0;
    }
}
