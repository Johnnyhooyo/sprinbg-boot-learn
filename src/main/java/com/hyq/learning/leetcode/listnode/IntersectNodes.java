package com.hyq.learning.leetcode.listnode;

import com.hyq.learning.leetcode.listnode.ListNode;

/**
 * @author dibulidohu
 * @classname IntersectNodes
 * @date 2019/5/2211:49
 * @description
 */
public class IntersectNodes {

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

        ListNode listNode6 = new ListNode(5);
        ListNode listNode7 = new ListNode(0);
        ListNode listNode8 = new ListNode(1);
        ListNode listNode9 = new ListNode(8);
        ListNode listNode10 = new ListNode(4);
        ListNode listNode11 = new ListNode(5);
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        listNode9.next = listNode10;
        listNode10.next = listNode11;
        ListNode intersectionNode = getIntersectionNode(listNode5, listNode11);
        System.out.println(intersectionNode);
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        boolean lastA = false;
        boolean lastB = false;
        while(true) {
            if(null == pA || null == pB) {
                break;
            }
            if(pA == pB) {
                return pA;
            } else if (pA.next == null && !lastA) {
                lastA = true;
                pA = headB;
                pB = pB.next;
                continue;
            } else if (pB.next == null && !lastB) {
                lastB = true;
                pB = headA;
                pA = pA.next;
                continue;
            }
            if (pA.next == null || pB.next == null) {
                break;
            }
            pA = pA.next;
            pB = pB.next;
        }
        return null;
    }
}
