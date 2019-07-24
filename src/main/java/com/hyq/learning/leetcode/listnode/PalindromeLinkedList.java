package com.hyq.learning.leetcode.listnode;

import com.hyq.learning.leetcode.listnode.ListNode;

/**
 * @ClassName PalindeomeLinkedList
 * @Author dibulidohu
 * @Date 2019/6/20 19:10
 * @Description 请判断一个链表是否为回文链表。
 * 思路： 1.快慢指针找到中间点
 *       2.反转后半部分
 *      3.head和中间指针的下一个开始比较
 *      eg:[1,3,2,4,3,2,1]
 */
public class PalindromeLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(3);
        ListNode listNode5 = new ListNode(2);
        ListNode listNode6 = new ListNode(1);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        isPalindrome(listNode);
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null && slow.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode index = slow.next;
        while (index != null && index.next != null) {
            ListNode temp = index.next;
            index.next = temp.next;

            slow.next = temp;
        }
        ListNode node = slow.next;
        while (head != null && node != null) {
            if (head.val != node.val) {
                return false;
            }
            head = head.next;
            node = node.next;
        }
        return true;
    }
}
