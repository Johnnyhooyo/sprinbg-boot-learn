package com.hyq.learning.leetcode;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author：huyuanqiang
 * @time: 2020-04-13 17:45
 * @description:
 **/
public class TweetTwo {
    public static void main(String[] args) {
        TweetTwo obj = new TweetTwo();

        obj.postTweet(1,5);
        obj.postTweet(1,3);
        List<Integer> param_2 = obj.getNewsFeed(1);
        obj.follow(1,2);
        obj.postTweet(2,6);
        List<Integer> param_3 = obj.getNewsFeed(1);
        obj.unfollow(1,2);
        List<Integer> param_4 = obj.getNewsFeed(1);
    }
    static class Node {
        Integer tweet;
        Integer order;
        Node next;
    }
    int order = 0;
    private HashMap<Integer, Node> userTweet;
    private HashMap<Integer, Set<Integer>> followMap;

    /** Initialize your data structure here. */
    public TweetTwo() {
        followMap = new HashMap<>();
        userTweet = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Node node = userTweet.get(userId);
        if (null == node) {
            node = new Node();
            node.tweet = tweetId;
            node.order  = order++;
            userTweet.put(userId, node);
            return;
        }
        Node head = new Node();
        head.tweet = tweetId;
        head.order  = order++;
        head.next = node;
        userTweet.put(userId, head);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> followees = followMap.computeIfAbsent(userId, k -> new HashSet<>());
        followees.add(userId);
        List<Node> nodes = new ArrayList<>();
        for(Integer followee : followees) {
            Node node = userTweet.get(followee);
            if(null != node) {
                nodes.add(node);
            }
        }
        return new ArrayList<>(sortAndGet(nodes));
    }

    private List<Integer> sortAndGet(List<Node> nodes) {
        List<Integer> res = new ArrayList<>();
        if (CollectionUtils.isEmpty(nodes)) {
            return res;
        }
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.order.compareTo(o1.order));
        for (Node node : nodes) {
            priorityQueue.offer(node);
        }
        while (res.size() < 10) {
            Node node = priorityQueue.poll();
            if (node == null) {
                return res;
            }
            if (null != node.next) {
                priorityQueue.offer(node.next);
            }
            res.add(node.tweet);
        }
        return res;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        Set<Integer> followees = followMap.computeIfAbsent(followerId, k -> new HashSet<>());
        followees.add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        Set<Integer> list = followMap.get(followerId);
        if (null == list) {
            return;
        }
        Set<Integer> followees = followMap.get(followerId);
        followees.remove(followeeId);
    }
}
