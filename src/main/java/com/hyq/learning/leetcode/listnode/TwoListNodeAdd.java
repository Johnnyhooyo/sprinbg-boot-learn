package com.hyq.learning.leetcode.listnode;

import java.util.Stack;

/**
 * @author：huyuanqiang
 * @time: 2020-04-14 11:18
 * @description:
 **/
public class TwoListNodeAdd {

    /**
     * [7,2,4,3]
     * [5,6,4]
     */
    public static void main(String[] args) {
        ListNode node = new ListNode(3);
        ListNode node1 = new ListNode(9);
        node.next = node1;
        ListNode node2 = new ListNode(9);
        node1.next = node2;
        ListNode node3 = new ListNode(9);
        node2.next = node3;
        ListNode node5 = new ListNode(9);
        node3.next = node5;
        ListNode node6 = new ListNode(9);
        node5.next = node6;
        ListNode node7 = new ListNode(9);
        node6.next = node7;
        ListNode node8 = new ListNode(9);
        node7.next = node8;
        ListNode node9 = new ListNode(9);
        node8.next = node9;
        ListNode node10 = new ListNode(9);
        node9.next = node10;

        ListNode node4 = new ListNode(7);
//        ListNode node5 = new ListNode(6);
//        node4.next = node5;
//        ListNode node6 = new ListNode(4);
//        node5.next = node6;

        System.out.println(addTwoNumbers(node, node4));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int temp = 0;
        ListNode point = new ListNode(1);
        while (!stack1.isEmpty() || !stack2.isEmpty() || temp != 0) {
            Integer p1;
            if (stack1.isEmpty()) {
                p1 = 0;
            } else {
                p1 = stack1.pop();
            }
            Integer p2;
            if (stack2.isEmpty()) {
                p2 = 0;
            } else {
                p2 = stack2.pop();
            }

            ListNode node = new ListNode((p1 + p2 + temp) % 10);
            temp = (p1 + p2 + temp) / 10;
            node.next = point.next;
            point.next = node;
        }
        return point.next;
    }
}
