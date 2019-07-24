package com.hyq.learning.leetcode;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName WorldSearch
 * @Author dibulidohu
 * @Date 2019/7/2 14:34
 * @Description 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]。
 *
 * [["a","b"],["a","a"]]
 * ["aba","baa","bab","aaab","aaa","aaaa","aaba"]
 *
 * [["b","a","a","b","a","b"],["a","b","a","a","a","a"],["a","b","a","a","a","b"],["a","b","a","b","b","a"],["a","a","b","b","a","b"],["a","a","b","b","b","a"],["a","a","b","a","a","b"]]
 * ["bbaabaabaaaaabaababaaaaababb","aabbaaabaaabaabaaaaaabbaaaba","babaababbbbbbbaabaababaabaaa","bbbaaabaabbaaababababbbbbaaa","babbabbbbaabbabaaaaaabbbaaab","bbbababbbbbbbababbabbbbbabaa","babababbababaabbbbabbbbabbba","abbbbbbaabaaabaaababaabbabba","aabaabababbbbbbababbbababbaa","aabbbbabbaababaaaabababbaaba","ababaababaaabbabbaabbaabbaba","abaabbbaaaaababbbaaaaabbbaab","aabbabaabaabbabababaaabbbaab","baaabaaaabbabaaabaabababaaaa","aaabbabaaaababbabbaabbaabbaa","aaabaaaaabaabbabaabbbbaabaaa","abbaabbaaaabbaababababbaabbb","baabaababbbbaaaabaaabbababbb","aabaababbaababbaaabaabababab","abbaaabbaabaabaabbbbaabbbbbb","aaababaabbaaabbbaaabbabbabab","bbababbbabbbbabbbbabbbbbabaa","abbbaabbbaaababbbababbababba","bbbbbbbabbbababbabaabababaab","aaaababaabbbbabaaaaabaaaaabb","bbaaabbbbabbaaabbaabbabbaaba","aabaabbbbaabaabbabaabababaaa","abbababbbaababaabbababababbb","aabbbabbaaaababbbbabbababbbb","babbbaabababbbbbbbbbaabbabaa"]
 */
public class WorldSearch {

    public static void main(String[] args) {
        char[][] borad = {{'b','a','a','b','a','b'},{'a','b','a','a','a','a'},{'a','b','a','a','a','b'},{'a','b','a','b','b','a'},{'a','a','b','b','a','b'},{'a','a','b','b','b','a'},{'a','a','b','a','a','b'}};
        String[] words = {"bbaabaabaaaaabaababaaaaababb","aabbaaabaaabaabaaaaaabbaaaba","babaababbbbbbbaabaababaabaaa","bbbaaabaabbaaababababbbbbaaa","babbabbbbaabbabaaaaaabbbaaab","bbbababbbbbbbababbabbbbbabaa","babababbababaabbbbabbbbabbba","abbbbbbaabaaabaaababaabbabba","aabaabababbbbbbababbbababbaa","aabbbbabbaababaaaabababbaaba","ababaababaaabbabbaabbaabbaba","abaabbbaaaaababbbaaaaabbbaab","aabbabaabaabbabababaaabbbaab","baaabaaaabbabaaabaabababaaaa","aaabbabaaaababbabbaabbaabbaa","aaabaaaaabaabbabaabbbbaabaaa","abbaabbaaaabbaababababbaabbb","baabaababbbbaaaabaaabbababbb","aabaababbaababbaaabaabababab","abbaaabbaabaabaabbbbaabbbbbb","aaababaabbaaabbbaaabbabbabab","bbababbbabbbbabbbbabbbbbabaa","abbbaabbbaaababbbababbababba","bbbbbbbabbbababbabaabababaab","aaaababaabbbbabaaaaabaaaaabb","bbaaabbbbabbaaabbaabbabbaaba","aabaabbbbaabaabbabaabababaaa","abbababbbaababaabbababababbb","aabbbabbaaaababbbbabbababbbb","babbbaabababbbbbbbbbaabbabaa"};
//        char[][] borad = {{'a','b'}};
//        String[] words = {"ba"};
        List<String> words1 = findWords(borad, words);
        System.out.println(words1);
    }

    public static List<String> findWords(char[][] board, String[] words) {
        HashSet<String> res = new HashSet<>();
        //先把board 扩大一圈
        char[][] newBoard = new char[board.length + 2][board[0].length + 2];
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j++) {
                newBoard[ 1 + i][1 + j] = board[i][j];
            }
        }
        for (String word : words) {
            int index = 0;
            boolean jump = false;
            for (int i = 1; i < newBoard.length - 1; i++) {
                if (jump) {
                    break;
                }
                for (int j = 1; j < newBoard[i].length - 1; j++) {
                    int[][] used = new int[word.length()][2];
                    if (newBoard[i][j] == word.charAt(0)) {
                        //如果单词是一位 直接结束
                        if (word.length() == 1) {
                            res.add(word);
                            jump = true;
                            break;
                        }
                        //找到单词的第一个字符 作为入口
                        used[index][0] = i;
                        used[index][1] = j;
                        search(word, 1, newBoard, i , j, res, used);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    private static void search(String word, int index, char[][] board, int row, int col, HashSet<String> res, int[][] used) {
        if (index == word.length() - 1) {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (Math.abs(i + j) == 1 && word.charAt(index) == board[row + i][col + j]) {
                        boolean eclipse = false;
                        for (int[] ints : used) {
                            if (ints[0] == row + i && ints[1] == col + j) {
                                eclipse = true;
                            }
                        }
                        if (eclipse) {
                            continue;
                        }
                        res.add(word);
                        return;
                    }
                }
            }
        } else {
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if (Math.abs(i + j) == 1 && word.charAt(index) == board[row + i][col + j]) {
                        boolean eclipse = false;
                        for (int[] ints : used) {
                            if (ints[0] == row + i && ints[1] == col + j) {
                                eclipse = true;
                            }
                        }
                        if (eclipse) {
                            continue;
                        }
                        used[index][0] = row + i;
                        used[index][1] = col + j;
                        search(word, index + 1, board, row + i, col + j, res, used);
                        return;
                    }
                }
            }
        }
    }
}
