package com.hyq.learning.leetcode.listnode;

/**
 * @author：huyuanqiang
 * @time: 2019-09-12 10:04
 * @description: 有序链表的去重（重复都丢弃）
 **/
public class DeleteDuplicate {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode listNode = deleteDuplicates(l1);
        System.out.println(listNode);
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode root = new ListNode(-1);
        root.next = head;
        ListNode point = head;
        ListNode con = root;
        int temp = head .val;
        boolean change = true;
        while (point.next != null) {
            if (point.next.val != temp) {
                if (change) {
                    con = con.next;
                }
                con.next = point.next;
                temp = point.next.val;

                change = true;
            } else {
                change = false;
            }
            point = point.next;
        }
        if (!change) {
            point = null;
        }
        con.next = point;
        return root.next;
    }
}
