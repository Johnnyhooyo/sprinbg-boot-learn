package com.hyq.learning.niuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author：huyuanqiang
 * @time: 2021-03-30 11:46
 * @description:
 **/
public class LRU {

    //[[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
    public static void main(String[] args) {
        LRU l = new LRU();
        int[] lru = l.LRU(new int[][]{{1,1,1}, {1,2,2}, {1,3,2}, {2,1}, {1,4,4},{2,2}}, 3);

        System.out.println(Arrays.toString(lru));
    }
    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */

    private int cacheNum = 0;
    HashMap<Integer, ListNode> cache = new HashMap<>(16);
    ListNode root = new ListNode(0 ,0);
    ListNode tail = new ListNode(0, 0);
    public int[] LRU (int[][] operators, int k) {
        root.next = tail;
        tail.pre = root;
        cacheNum = k;
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < operators.length; i++) {
            if (operators[i][0] == 1) {
                addLRU(operators[i]);
            } else {
                int r = getLRU(operators[i]);
                res.add(r);
            }
        }
        int[] out = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            out[i] = res.get(i);
        }
        return out;
    }

    private int getLRU(int[] input) {
        if (!cache.containsKey(input[1])) {
            return -1;
        }
        ListNode value = cache.get(input[1]);

        remove(value);
        addHead(value);

        return value.val;
    }

    private void addLRU(int[] input) {
        if (cache.containsKey(input[1])) {
            ListNode value = cache.get(input[1]);

            remove(value);
            addHead(value);
            return;
        }
        if (cache.size() >= cacheNum) {
            removeLast();
        }
        ListNode value = new ListNode(input[1], input[2]);
        cache.put(input[1], value);
        addHead(value);
    }

    private void addHead(ListNode node) {
        ListNode temp = root.next;
        root.next = node;
        node.next = temp;
        temp.pre = node;
        node.pre = root;
    }

    private void removeLast() {
        if (tail.pre == root) {
            return;
        }
        cache.remove(tail.pre.key);
        remove(tail.pre);
    }

    private void remove(ListNode node) {
        node.pre.next = node.next;
        if (null != node.next) {
            node.next.pre = node.pre;
        }
    }

    class ListNode {
        int key;
        int val;
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
        ListNode pre;
        ListNode next;
    }
}
