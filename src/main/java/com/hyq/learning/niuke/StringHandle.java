package com.hyq.learning.niuke;

/**
 * @author：huyuanqiang
 * @time: 2021-03-05 14:53
 * @description:
 **/
public class StringHandle {
    public static void main(String[] args) {
       StringHandle stringHandle = new StringHandle();
        String s = stringHandle.unZip("HG[3|B[2|CA]H]F");
        System.out.println(s);

    }
    public String unZip(String str) {
        int len = str.length();
        int left = str.indexOf("[");
        int numEnd = str.indexOf("|");
        int right = str.lastIndexOf("]");

        if (left == -1) {
            return str;
        }

        StringBuilder res = new StringBuilder(str.substring(0, left));
        int round = Integer.parseInt(str.substring(left + 1, numEnd));
        for(int i = 0; i < round; i++) {
            res.append(unZip(str.substring(numEnd + 1, right)));
        }
        if (len == right) {
            return res.toString();
        }
        res.append(str, right + 1, len);
        return res.toString();
    }
}
