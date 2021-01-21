package com.hyq.learning.leetcode;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyq.learning.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author：huyuanqiang
 * @time: 2020-04-13 16:06
 * @description:
 **/
public class Twitter {

    public static void main(String[] args) {
        HashMap<Integer, List<Integer>> hashMap = Maps.newHashMap();
        hashMap.put(1, Lists.newArrayList(1,2));
        List<Integer> list = hashMap.computeIfAbsent(2, k -> new ArrayList<>());
        list.add(3);
        System.out.println(hashMap.get(2));
        Twitter obj = new Twitter();

        obj.postTweet(1,5);
        List<Integer> param_2 = obj.getNewsFeed(1);
        obj.follow(1,2);
        obj.postTweet(2,6);
        List<Integer> param_3 = obj.getNewsFeed(1);
        obj.unfollow(1,2);
        List<Integer> param_4 = obj.getNewsFeed(1);
    }

    static class Twitte {
        private int userId;
        private int tweeId;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getTweeId() {
            return tweeId;
        }

        public void setTweeId(int tweeId) {
            this.tweeId = tweeId;
        }
    }

    static class Node {
        Twitte twitte;
        Node next;
    }

    private Node head;
    private HashMap<Integer, List<Integer>> followMap;

    /** Initialize your data structure here. */
    public Twitter() {
        followMap = Maps.newHashMap();
        head = new Node();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Twitte twitte = new Twitte();
        twitte.setTweeId(tweetId);
        twitte.setUserId(userId);
        Node node = new Node();
        node.twitte = twitte;

        Node temp = head.next;

        head.next = node;

        node.next = temp;
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> followees = followMap.get(userId);
        if (null == followees) {
            followees  = new ArrayList<>();
        }
        followees.add(userId);
        List<Integer> res = Lists.newArrayList();
        int count = 0;
        Node next = head.next;
        while (next != null && count < 10) {
            if (followees.contains(next.twitte.getUserId())) {
                res.add(next.twitte.tweeId);
                count++;
            }
            next = next.next;
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        List<Integer> list = followMap.get(followerId);
        if (null == list) {
            followMap.put(followerId, Lists.newArrayList(followeeId));
            return;
        }
        List<Integer> followees = followMap.get(followerId);
        followees.add(followeeId);
        followMap.put(followerId, followees);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        List<Integer> list = followMap.get(followerId);
        if (null == list) {
            return;
        }
        List<Integer> followees = followMap.get(followerId);
        followees.remove((Integer) followeeId);
        followMap.put(followerId, followees);
    }
}
