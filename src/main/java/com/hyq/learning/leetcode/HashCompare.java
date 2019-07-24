package com.hyq.learning.leetcode;

import java.util.*;

import static java.util.Objects.hash;

/**
 * @author dibulidohu
 * @classname HashCompare
 * @date 2019/5/2214:06
 * @description
 */
public class HashCompare {

    public static void main(String[] args) {
        String[] list1 = {"Shogun","Tapioca Express","Burger King","KFC"};
        String[] list2 = {"KFC","Shogun","Burger King"};
        findRestaurant(list1, list2);

        find(list1, list2);
    }

    private static String[] find(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i< list1.length; i++) {
            map.put(list1[i], i);
        }
        List<String> res = new ArrayList<>();
        int max = Integer.MAX_VALUE;
        for (int i = 0; i< list1.length; i++) {
            if (map.containsKey(list2[i])) {
                if (max > i + map.get(list2[i])) {
                    max = i + map.get(list2[i]);
                    res.clear();
                    res.add(list2[i]);
                } else if (max == i + map.get(list2[i])) {
                    res.add(list2[i]);
                }
            }
        }

        return res.toArray(new String[0]);
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        int max = -1;
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < list1.length; i++) {
            if(max > 0 && i > max) {
                break;
            }
            for(int j = 0; j< list2.length; j++) {
                if(list1[i].equals(list2[j])) {
                    if(max < 0) {
                        max = i + j;
                        res.add(i);
                    } else if (i + j > max) {
                        break;
                    } else if (i + j == max) {
                        res.add(i);
                        break;
                    } else {
                        res = new ArrayList(i);
                        max = i + j;
                        break;
                    }

                }
            }
        }
        String[] resp = new String[res.size()];
        for(int i = 0; i< res.size(); i++) {
            resp[i] = list1[res.get(i)];
        }
        return resp;
    }
}
