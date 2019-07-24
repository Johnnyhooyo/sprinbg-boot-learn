package com.hyq.learning.leetcode;

import java.util.Stack;

/**
 * @ClassName ValidParentheses
 * @Author dibulidohu
 * @Date 2019/6/19 13:44
 * @Description
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidParentheses {

    public static void main(String[] args) {
        String s = "({[})";
        System.out.println(valid(s));
    }

    /***
     * 这种方法时间空间复杂度都很高---算法不要使用工具类
     */
    private static boolean valid(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s =s.replace("()", "");
        }
        return s.length() == 0;
    }

    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<String> stack = new Stack<>();
        int i = s.length() - 1;
        while (i >= 0) {
            if (stack.isEmpty()) {
                stack.push(String.valueOf(chars[i--]));
            } else {
                char right = stack.peek().charAt(0);
                if (right - chars[i] < 3 && right - chars[i] > 0) {
                    stack.pop();
                    i--;
                } else {
                    stack.push(String.valueOf(chars[i--]));
                }
            }
        }
        return stack.isEmpty();
    }
}
