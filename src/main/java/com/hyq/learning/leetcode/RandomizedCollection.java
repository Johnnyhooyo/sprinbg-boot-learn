//package com.hyq.learning.leetcode;
//
//import com.google.common.collect.Maps;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * @author：huyuanqiang
// * @time: 2020-10-31 11:55
// * @description:
// **/
//public class RandomizedCollection {
//
//    private HashMap<Integer, List<Integer>> positionMap;
//    private List<Integer> arr;
//
//    /** Initialize your data structure here. */
//    public RandomizedCollection() {
//        positionMap = Maps.newHashMap();
//        arr = new ArrayList<>();
//    }
//
//    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
//    public boolean insert(int val) {
//        arr.add(val);
//        int position = arr.size() - 1;
//        if (positionMap.containsKey(val)) {
//            positionMap.get(val).add(position);
//        }
//    }
//
//    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
//    public boolean remove(int val) {
//
//    }
//
//    /** Get a random element from the collection. */
//    public int getRandom() {
//
//    }
//}
