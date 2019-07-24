package com.hyq.learning.niuke.listnode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CopyListNode
 * @Author dibulidohu
 * @Date 2019/7/23 16:19
 * @Description
 */
public class CopyListNode {

    public static void main(String[] args) {
        CopyListNode copyListNode = new CopyListNode();
        RandomListNode randomListNode1 = new RandomListNode(1);
        RandomListNode randomListNode2 = new RandomListNode(2);
        RandomListNode randomListNode3 = new RandomListNode(3);
        RandomListNode randomListNode4 = new RandomListNode(4);
        RandomListNode randomListNode5 = new RandomListNode(5);
        RandomListNode randomListNode6 = new RandomListNode(6);
        randomListNode1.next = randomListNode2;
        randomListNode2.next = randomListNode3;
        randomListNode3.next = randomListNode4;
        randomListNode4.next = randomListNode5;
        randomListNode5.next = randomListNode6;
        randomListNode1.random = randomListNode5;
        randomListNode2.random = randomListNode5;
        randomListNode3.random = randomListNode2;
        randomListNode4.random = randomListNode1;
        randomListNode5.random = randomListNode4;
        RandomListNode clone = copyListNode.clone(randomListNode1);
        System.out.println(clone);
    }


    public RandomListNode clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode head = pHead;
        while (pHead != null) {
            RandomListNode randomListNode = new RandomListNode(pHead.val);
            randomListNode.next = pHead.next;
            pHead.next = randomListNode;
            pHead = randomListNode.next;
        }
        RandomListNode index = head;
        while (index != null && index.random != null) {
            index.next.random = index.random.next;
            index = index.next.next;
        }
        RandomListNode res = head.next;
        RandomListNode out = res;
        while (res != null && res.next != null) {
            res.next = res.next.next;
            res = res.next;
        }
        return out;
    }


}
