package com.hyq.learning.niuke.listnode;

import java.util.ArrayList;

/**
 * @ClassName ReverseListNode
 * @Author dibulidohu
 * @Date 2019/7/17 19:54
 * @Description
 */
public class ReverseListNode {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(8);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        System.out.println(printListFromTailToHead(listNode1));
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if(listNode == null) {
            return res;
        }
        ListNode head = listNode;
        ListNode tail = listNode.next;
        do{
            head.next = tail.next;
            tail.next = listNode;
            listNode = tail;
            tail = head.next;
        } while(tail != null);
        while(listNode != null) {
            res.add(listNode.val);
            listNode = listNode.next;
        }
        return res;
    }
}
