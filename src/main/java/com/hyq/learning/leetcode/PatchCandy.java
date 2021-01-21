package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2020-12-24 20:40
 * @description:
 **/
public class PatchCandy {

    public static void main(String[] args) {
        PatchCandy patchCandy = new PatchCandy();
        int candy = patchCandy.candy(new int[]{1, 2, 2,3,2,1,4,2,3,8});
        System.out.println(candy);
    }


    public int candy(int[] ratings) {
        int[] candys = new int[ratings.length];
        for (int i = 0; i < candys.length; i++) {
            candys[i] = 1;
        }

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candys[i] = candys[i - 1] + 1;
            }
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candys[i] = Math.max(candys[i], candys[i + 1] + 1);
            }
        }

        int res = 0;
        for (int candy : candys) {
            res += candy;
        }
        return res;
    }
}
