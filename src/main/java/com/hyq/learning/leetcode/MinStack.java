package com.hyq.learning.leetcode;

import java.util.Stack;

/**
 * @ClassName MinStack
 * @Author dibulidohu
 * @Date 2019/6/28 9:38
 * @Description 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinStack {

   Stack<Integer> date;
   Stack<Integer> minDate;

    public MinStack() {
        date = new Stack<>();
        minDate = new Stack<>();
    }

    public void push(int x) {
        date.push(x);
        if (minDate.isEmpty() || x <= minDate.peek()) {
            minDate.push(x);
        }
    }

    public void pop() {
        Integer pop = date.pop();
        if (pop.equals(minDate.peek())) {
            minDate.pop();
        }
    }

    public int top() {
        return date.peek();
    }

    public int getMin() {
        return minDate.peek();
    }
}
