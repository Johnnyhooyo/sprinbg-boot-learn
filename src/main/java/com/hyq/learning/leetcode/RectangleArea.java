package com.hyq.learning.leetcode;

/**
 * @author dibulidohu
 * @classname RectangleArea
 * @date 2019/6/319:53
 * @description  矩形面积 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
 */
public class RectangleArea {

    public static void main(String[] args) {
        System.out.println(computeArea(-3, 0, 3,4, 0, -1, 9, 2 ));
        System.out.println(trailingZeroes(100));
    }

    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int lx = A > E ? A : E;
        int ly = B > F ? B : F;
        int rx = C < G ? C : G;
        int ry = D < H ? D : H;
        int cross = 0;
        if (rx > lx && ry > ly) {
            cross = (rx - lx) * (ry - ly);
        }
        return (C-A) * (D-B) + (G-E) * (H-F) - cross;
    }


    /**
     * @author dibulidohu
     * @classname RectangleArea
     * @date 2019/6/319:53
     * @description  给定一个整数 n，返回 n! 结果尾数中零的数量。
     */
    public static int trailingZeroes(int n) {
        if(n >= 5) {
            return n / 5 + trailingZeroes(n / 5);
        } else {
            return 0;
        }
    }
}
