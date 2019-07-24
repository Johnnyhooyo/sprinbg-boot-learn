package com.hyq.learning.leetcode.listnode;

/**
 * @author dibulidohu
 * @classname ListNodeInsertSort
 * @date 2019/5/1011:22
 * @description
 */
public class ListNodeInsertSort {

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(-1);
        ListNode listNode2 = new ListNode(5);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(0);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        ListNode listNode = insertSortList(listNode1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    private static ListNode insertSortList(ListNode head) {
        ListNode index = head;
        ListNode root = new ListNode(0);
        root.next = head;
        while(index != null && index.next != null) {
            ListNode after = index.next;
            index.next = null;
            ListNode point = root;
            boolean swap = false;
            while(point.next != null) {
                if(after.val < point.next.val) {
                    ListNode temp = point.next;
                    index.next = after.next;
                    point.next = after;
                    after.next = temp;
                    swap = true;
                    break;
                }
                point = point.next;
            }
            if (!swap) {
                index.next = after;
                index = index.next;
            }
        }
        return root.next;
    }

    private static ListNode insertionSortList(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode root = new ListNode(0);
        ListNode point = root;
        ListNode listNode = insertionSortList(head.next);
        while (listNode != null && head != null) {
            if (head.val < listNode.val) {
                point.next = head;
                head = null;
                point = point.next;
            } else {
                point.next = listNode;
                listNode = listNode.next;
                point = point.next;
            }
        }
        if (head != null) {
            head.next = null;
            point.next = head;
        }
        if (listNode != null) {
            point.next = listNode;
        }
        return root.next;
    }
}
