package com.hyq.learning.niuke;

import java.util.Stack;

/**
 * @ClassName ListUseStack
 * @Author dibulidohu
 * @Date 2019/7/17 20:50
 * @Description
 */
public class ListUseStack {

    public static void main(String[] args) {
        ListUseStack stack = new ListUseStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        System.out.println(stack.pop());
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
        Stack<Integer> stack1 = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();

        public void push(int node) {
            stack1.push(node);
        }

        public int pop() {
            if(stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return pop();
            } else {
                return stack2.pop();
            }
        }
}
