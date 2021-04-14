package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2021-04-02 16:24
 * @description:
 **/
public class LoadRain {


    public int trap(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int maxLeft = height[0];
        int maxRight = height[height.length - 1];

        int res = 0;
        int left = 1;
        int right = height.length - 2;
        while (left <= right) {
            if (maxLeft <= maxRight) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    res += (maxLeft - height[left]);
                }
                left++;
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    res += (maxRight - height[right]);
                }
                right--;
            }
        }
        return res;
    }
}
