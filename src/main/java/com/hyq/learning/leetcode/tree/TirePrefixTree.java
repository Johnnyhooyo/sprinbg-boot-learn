package com.hyq.learning.leetcode.tree;

/**
 * @ClassName TirePrefixTree
 * @Author dibulidohu
 * @Date 2019/7/1 19:25
 * @Description  实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 */
public class TirePrefixTree {

    public static void main(String[] args) {
        TirePrefixTree tirePrefixTree = new TirePrefixTree();
        tirePrefixTree.insert("abcd");
        System.out.println(tirePrefixTree.search("abcd"));
        System.out.println(tirePrefixTree.search("abcdf"));
        System.out.println(tirePrefixTree.startsWith("abc"));
        tirePrefixTree.insert("abch");
        System.out.println(tirePrefixTree.startsWith("abc"));
        System.out.println(tirePrefixTree.search("abch"));
    }

    class Node {
        boolean isWorld;
       Node[] children;

        public Node() {
            children = new Node[26];
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public TirePrefixTree() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node point = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (point.children[index] == null) {
                point.children[index] = new Node();
            }
            point = point.children[index];
        }
        point.isWorld = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node point = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (point.children[index] == null) {
                return false;
            }
            point = point.children[index];
            if (i == word.length() - 1 && !point.isWorld) {
                return false;
            }
        }
        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node point = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (point.children[index] == null) {
                return false;
            }
            point = point.children[index];
        }
        return true;
    }
}
