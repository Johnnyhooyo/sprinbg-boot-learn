package com.hyq.learning.leetcode;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;

/**
 * @author：huyuanqiang
 * @time: 2021-04-12 11:42
 * @description:
 **/
public class AllOne {

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
        allOne.inc("hello");
        allOne.inc("mac");
        allOne.inc("hello");
        allOne.inc("hello");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
        allOne.inc("leet");
        System.out.println(allOne.getMinKey());
        allOne.inc("leet");
        allOne.inc("leet");
        System.out.println(allOne.getMaxKey());
        allOne.dec("leet");
        System.out.println(allOne.getMaxKey());
        System.out.println(allOne.getMinKey());
    }

    class ListNode {
        String key;
        int val;
        ListNode pre;
        ListNode next;

        ListNode levelPre;
        ListNode levelNext;
    }

    ListNode head;
    ListNode tail;
    HashMap<String, ListNode> data = new HashMap<>();
    HashMap<Integer, ListNode> valueMap = new HashMap<>();

    /** Initialize your data structure here. */
    public AllOne() {
        head = new ListNode();
        head.key = "head";
        tail = new ListNode();
        tail.key = "tail";
        head.next = tail;
        tail.pre = head;
    }

    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (data.containsKey(key)) {
            ListNode listNode = data.get(key);
            del(listNode);
            listNode.val++;
            insert(listNode);
            return;
        }
        ListNode newNode = new ListNode();
        newNode.key = key;
        newNode.val = 1;
        if (tail.pre != head) {
            newNode.levelPre = valueMap.get(tail.pre.val);
        }
        insert(newNode);
    }

    private void insert(ListNode listNode) {
        ListNode levelHead = valueMap.get(listNode.val);
        if (null != levelHead) {
            listNode.levelNext = levelHead.levelNext;
            listNode.levelPre = levelHead.levelPre;

            listNode.next = levelHead;
            listNode.pre = levelHead.pre;

            levelHead.pre.next = listNode;
            levelHead.pre = listNode;

            levelHead.levelNext = null;
            levelHead.levelPre = null;

            valueMap.put(listNode.val, listNode);
            return;
        }



        valueMap.put(listNode.val, listNode);
    }

    private void del(ListNode node) {
        ListNode levelHead = valueMap.get(node.val);
        ListNode levelNext = levelHead.levelNext;
        ListNode levelPre = levelHead.levelPre;
        if (isLevelHead(node)) {
            if (hasLevelNext(node)) {
                ListNode newHead = node.next;
                valueMap.put(node.val, newHead);
                newHead.levelNext = levelNext;
                newHead.levelPre = levelPre;

                levelNext.levelPre = newHead;
                levelPre.levelNext = newHead;
            } else {
                valueMap.remove(node.val);
                levelNext.levelPre = levelPre;
                levelPre.levelNext = levelNext;
            }
        } else {
            node.levelNext = levelNext;
            node.levelPre = levelPre;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private boolean hasLevelNext(ListNode node) {
        return node.val == node.next.val;
    }

    private boolean isLevelHead(ListNode node) {
        return node == valueMap.get(node.val);
    }

    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {

    }

    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (head.next == tail) {
            return "";
        }
        return head.next.key;
    }

    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (tail.pre == head) {
            return "";
        }
        return tail.pre.key;
    }
}
