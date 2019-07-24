package com.hyq.learning.leetcode;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName PerfectNumber
 * @Author dibulidohu
 * @Date 2019/7/1 15:56
 * @Description 对于一个 正整数，如果它和除了它自身以外的所有正因子之和相等，我们称它为“完美数”。
 *
 * 给定一个 正整数 n， 如果他是完美数，返回 True，否则返回 False
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/perfect-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PerfectNumber {

    public static void main(String[] args) {
        BigDecimal n1 = new BigDecimal("100");
        BigDecimal n2 = new BigDecimal("-10");
        System.out.println(n1.add(n2));
        System.out.println(checkPerfectNumber(28));
    }

    public static boolean checkPerfectNumber(int num) {
        if(num == 0) {
            return false;
        }
        int sqrt = (int)Math.sqrt(num);
        int sum = 0;
        for (int i = 1; i <= sqrt; i++) {
            //            if (num % i == 0) {
            //                int rse = num / i;
            //                if (rse != num) {
            //                    sum += rse;
            //                }
            //                if (i != num) {
            //                    sum += i;
            //                }
            //            }
            //   注释的方法会快很多
            double rse = num / (double) i;
            if (rse == (int)rse) {
                if (rse != num) {
                    sum += (int)rse;
                }
                if (i != num) {
                    sum += i;
                }
            }
        }
        return sum == num;
    }


}
