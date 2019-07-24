package com.hyq.learning.leetcode.listnode;

/**
 * @author dibulidohu
 * @classname ListNodeSort
 * @date 2019/5/920:08
 * @description
 */
public class ListNodeMergeSort {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(8);
        ListNode listNode2 = new ListNode(5);
        ListNode listNode3 = new ListNode(7);
        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(2);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        ListNode listNode = sort(listNode1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    private static ListNode sort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode q = head;
        while (q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }
        ListNode l = head;
        ListNode r = p.next;
        p.next = null;
        l = sort(l);
        r = sort(r);
        ListNode cur = new ListNode(0);
        head = cur;
        while (l != null && r != null) {
            if (l.val < r.val) {
                cur.next = l;
                cur = cur.next;
                l = l.next;
            } else {
                cur.next = r;
                cur = cur.next;
                r = r.next;
            }
        }
        if (l != null) {
            cur.next = l;
        } else {
            cur.next = r;
        }
        return head.next;
    }
}
