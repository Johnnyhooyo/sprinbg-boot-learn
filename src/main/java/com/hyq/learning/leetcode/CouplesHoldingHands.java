package com.hyq.learning.leetcode;

import java.util.HashMap;

/**
 * @ClassName CouplesHoldingHands
 * @Author dibulidohu
 * @Date 2019/7/8 19:18
 * @Description N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 *
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 *
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/couples-holding-hands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。[0,2,4,6,7,1,3,5]
 */
public class CouplesHoldingHands {

    public static void main(String[] args) {
        int[] ints = {6,2,1,7,4,5,3,8,0,9};
        int i = minSwapsCouples(ints);
        System.out.println(i);
    }

    //贪心算法  记录每个人的位置 找到伴侣的位置 和自己旁边的座位交换  并且交换对应的位置记录
    public static int minSwapsCouples(int[] row) {
        int count= 0;
        int[] seat = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            seat[row[i]] = i;
        }
        for (int i = 0; i < row.length; i += 2) {
            int next = row[i] % 2 == 0 ? row[i] + 1 : row[i] - 1;
            if (row[i+1] != next) {
                int other = row[i + 1];

                row[seat[next]] = row[i + 1];
                row[i+1] = next;
                count++;

                seat[other] = seat[next];
                seat[next] = i + 1;
            }
        }
        return count;
    }
}
