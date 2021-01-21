package com.hyq.learning.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author：huyuanqiang
 * @time: 2019-09-16 10:28
 * @description: 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。
 * 返回妹妹可以获得的最大糖果的种类数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distribute-candies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class DistributeCandies {

    public static void main(String[] args) {
        DistributeCandies candies = new DistributeCandies();
        int[] candie = {1,1,1,5,5,5,7,5};
        int i = candies.distributeCandies(candie);
        System.out.println(i);
    }

    public int distributeCandies(int[] candies) {
        int length = candies.length / 2;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int candy : candies) {
            if (hashSet.size() >= length) {
                return length;
            }
            hashSet.add(candy);
        }
        return hashSet.size();
    }
}
