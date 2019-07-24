package com.hyq.learning;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dibulidohu
 * @classname Tetst
 * @date 2019/4/2420:32
 * @description
 */
public class Tetst {


    public static void main(String[] args) {
//        Tetst tetst = new Tetst();
//        tetst.sss();
        List<Integer> list = Lists.newArrayList(1,2,3,4,5);
        List<Integer> list1 = list.subList(5, 5);
        System.out.println(list1);
    }

    public void sss() {
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(6);
        ListNode listNode3 = new ListNode(7);
        ListNode listNode4 = new ListNode(8);
        ListNode listNode5 = new ListNode(9);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode listNode6 = reverseBetween(listNode, 2, 5);
        System.out.println(listNode6);
    }
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
     }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode root = new ListNode(0);
        root.next = head;
        ListNode index = root;
        ListNode left;
        ListNode right;
        ListNode temp;
        int count = n - m + 1;
        //确定左边的分界点
        while (m > 1) {
            m--;
            index = index.next;
        }
        left = index;
        index = index.next;
        right = index;
        left.next = null;
        //翻转,头插法
        while (count > 0) {
            count--;
            temp = index;
            index = index.next;
            temp.next = left.next;
            left.next = temp;
        }
        //连接右半部分
        right.next = index;
        return left == root ? left.next : head;
    }



}
