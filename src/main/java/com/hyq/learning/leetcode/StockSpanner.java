package com.hyq.learning.leetcode;

import java.util.Stack;

/**
 * @ClassName StockSpanner
 * @Author dibulidohu
 * @Date 2019/8/13 15:20
 * @Description
 */
public class StockSpanner {
    private Stack<Integer> mainStack= new Stack<>();
    private Stack<Integer> subStack= new Stack<>();

    public StockSpanner() {
    }

    public int next(int price) {
        if(mainStack.isEmpty() || mainStack.peek() <= price) {
            mainStack.push(price);
            return mainStack.size();
        }else {
            while(!mainStack.isEmpty() && mainStack.peek() > price) {
                subStack.push(mainStack.pop());
            }
            mainStack.push(price);
            int res = mainStack.size();
            while(!subStack.isEmpty()) {
                mainStack.push(subStack.pop());
            }
            return res;
        }
    }

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();
        stockSpanner.next(100);
        stockSpanner.next(80);
        stockSpanner.next(60);
        stockSpanner.next(70);
        stockSpanner.next(60);
        stockSpanner.next(75);

    }
}
