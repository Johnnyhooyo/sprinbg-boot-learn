package com.hyq.learning;

import java.util.Arrays;

/**
 * @author dibulidohu
 * @classname Testw
 * @date 2019/5/813:48
 * @description
 */
public class Testw {

    public static void main(String[] args) {
        Testw tetst = new Testw();
        tetst.sss();
    }

    public void sss() {
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(7);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(5);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode listNode6 = oddEvenList(listNode);

        System.out.println(listNode6);
    }




    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
// head 为奇链表头结点，o 为奇链表尾节点
        ListNode o = head;
// p 为偶链表头结点
        ListNode p = head.next;
// e 为偶链表尾节点
        ListNode e = p;
        while (o.next != null && e.next != null) {
            o.next = e.next;
            o = o.next;
            e.next = o.next;
            e = e.next;
        }
        o.next = p;
        return head;
    }
}


