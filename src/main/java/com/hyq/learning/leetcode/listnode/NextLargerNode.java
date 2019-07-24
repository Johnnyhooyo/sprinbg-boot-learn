package com.hyq.learning.leetcode.listnode;

import com.hyq.learning.leetcode.listnode.ListNode;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author dibulidohu
 * @classname NextLargerNode
 * @date 2019/5/2120:40
 * @description
 */
public class NextLargerNode {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(9);
        ListNode listNode2 = new ListNode(7);
        ListNode listNode3 = new ListNode(6);
        ListNode listNode4 = new ListNode(7);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(9);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        int[] ints = nextLargerNodes(listNode1);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] nextLargerNodes(ListNode head) {
        //to array
        ListNode point = head;
        int len = 0;
        while(point !=null) {
            len++;
            point = point.next;
        }
        int[] arr = new int[len];
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        while(head != null) {
            arr[index] = head.val;
            if (stack.empty()) {
                stack.push(index);
            } else {
                while(!stack.empty()) {
                    Integer pop = stack.pop();
                    if (head.val > arr[pop]) {
                        res[pop] = head.val;
                    } else {
                        stack.push(pop);
                        stack.push(index);
                        break;
                    }
                }
            }
            index ++;
            head = head.next;
        }
        return res;
    }
}
