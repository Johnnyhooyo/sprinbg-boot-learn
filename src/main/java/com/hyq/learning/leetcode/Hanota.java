package com.hyq.learning.leetcode;

/**
 * @author dibulidohu
 * @classname Hanota
 * @date 2019/5/917:34
 * @description 汉诺塔游戏
 */
public class Hanota {

    private static int count = 1;
    public static void main(String[] args) {
        moved(5, "A", "B", "C");
    }

    private static void moved(int num, String column1, String column2, String column3) {
        if (num == 1){
            display(num, column1, column2);
        } else {
            moved(num -1, column1, column3, column2);
            display(num, column1, column2);
            moved(num -1, column3, column2, column1);
        }
    }

    private static void display(int num, String column1, String column2) {
        System.out.println("第" + count + "步，移动第" + num + "个，从柱子" + column1 + "移动到柱子" + column2);
        count++;
    }
}
