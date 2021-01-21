package com.hyq.learning.leetcode.hash;

import java.util.HashMap;

/**
 * @author：huyuanqiang
 * @time: 2020-05-28 17:30
 * @description:
 **/
public class PreFix {

    /**
     * [4,5,0,-2,-3,1]
     * 5
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {4,5,0,-2,-3,5};
        System.out.println(subarraysDivByK(arr, 5));

        PreFix preFix = new PreFix();
        System.out.println(preFix.subarraySum(arr, 5));
    }

    public static int subarraysDivByK(int[] arr, int K) {
        int preSum = 0; //余数
        int res = 0; //结果
        int[] hashMap = new int[K]; //hash数组
        hashMap[0] = 1;  //初始化余数为0的个数为1 因为5的余数似乎0 如果不初始化 这个5计算不上
        for (int i : arr) {
            preSum = (preSum + i) % K;
            preSum = preSum < 0 ? preSum + K : preSum;
            if (hashMap[preSum] != 0) {
                //如果已经存在 有几个加上几个
                res += hashMap[preSum];
                hashMap[preSum]++;
            } else {
                hashMap[preSum] = 1;
            }
        }
        return res;
    }

    /**
     * To: nums[i] + ... + nums[j] = K
     * nums[i] = sum[i] - sum[i-1]
     * nums[i+1] = sum[i+1] - sum[i]
     * ...
     * nums[j] = sum[j] - sum[j-1]
     *
     * nums[i] + ... + nums[j] = sum[j] - sum[i - 1] = K
     *
     *
     */
    public int subarraySum(int[] nums, int k) {
        int pre = 0;
        int res = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0 ,1);
        for (int num : nums) {
            pre += num;
            if (hashMap.containsKey(pre - k)) {
                res += hashMap.get(pre - k);
            }
            hashMap.put(pre, hashMap.getOrDefault(pre, 0) + 1);
        }
        return res;
    }
}
