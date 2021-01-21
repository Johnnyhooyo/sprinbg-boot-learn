package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2019-09-12 10:49
 * @description: "G"：直走 1 个单位 "L"：左转 90 度 "R"：右转 90 度
 **/
public class RobotInTrouble {
    public static void main(String[] args) {
        RobotInTrouble robotInTrouble = new RobotInTrouble();
        boolean bounded = robotInTrouble.isRobotBounded("GGLLRR");
        System.out.println(bounded);
    }

    public boolean isRobotBounded(String instructions) {
        int direct = 1;
        int x = 0;
        int y = 0;
        char[] chars = instructions.toCharArray();
        for (char aChar : chars) {
            switch (aChar) {
                case 'G':
                    switch (direct) {
                        case 1:
                            y++;
                            break;
                        case 2:
                            x++;
                            break;
                        case 3:
                            y--;
                            break;
                        default:
                            x--;
                    }
                    break;
                case 'L':
                    switch (direct) {
                        case 1:
                            direct = 2;
                            break;
                        case 2:
                            direct = 3;
                            break;
                        case 3:
                            direct = 4;
                            break;
                        default:
                            direct = 1;
                    }
                    break;
                default:
                    switch (direct) {
                        case 1:
                            direct = 4;
                            break;
                        case 2:
                            direct = 1;
                            break;
                        case 3:
                            direct = 2;
                            break;
                        default:
                            direct = 3;
                    }
            }
        }
        return (x == 0 && y == 0) || direct != 1;
    }
}
