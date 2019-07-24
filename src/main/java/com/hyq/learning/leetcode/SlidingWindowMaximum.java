package com.hyq.learning.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @ClassName SlidingWindowMaximum
 * @Author dibulidohu
 * @Date 2019/6/28 15:41
 * @Description 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口 k 内的数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口最大值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * [1,3,-1,-3,5,3,6,7], 和 k = 3
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,9,8,7,6,5,1};
        System.out.println(Arrays.toString(maxSlidingWindow2(nums, 3)));
    }

    /***
     * 最佳实现
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || nums.length < k) {
            return new int[0];
        }

        int[] ret = new int[nums.length - k + 1];
        //max记录最大值  m 记录最大值的index   left是窗口左index  right 是窗口有index
        int max = Integer.MIN_VALUE, m = 0, left = 0, right = 0;
        for ( ; right < k; right++) {
            if (nums[right] >= max) {
                max = nums[right];
                m = right;
            }
        }
        ret[0] = max;

        for ( ; right < nums.length; right++) {
            if (nums[right] >= max) {
                max = nums[right];
                m = right;
            }
            //如果最大值 已经小于窗口的左index 重新找到max和m
            if (m < right - k + 1) {
                max = Integer.MIN_VALUE;
                for (left = right - k + 1; left <= right; left++) {
                    if (nums[left] >= max) {
                        max = nums[left];
                        m = left;
                    }
                }
            }
            ret[right - k + 1] = max;
        }

        return ret;
    }

    /***
     * 效率一般
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        LinkedList<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[i] > queue.peek()) {
                queue.pollFirst();
            }
            if (queue.isEmpty() || queue.peek() >= nums[i]) {
                queue.push(nums[i]);
            }
        }
        res[0] = queue.peekLast();
        for (int i= 1; i <= nums.length - k; i++) {
            if (queue.peekLast() == nums[i- 1]) {
                queue.pollLast();
            }
            while (!queue.isEmpty() && nums[i + k - 1] > queue.peek()) {
                queue.pollFirst();
            }
            if (queue.isEmpty() || queue.peek() >= nums[i + k - 1]) {
                queue.push(nums[i + k - 1]);
            }
            res[i] = queue.peekLast();
        }
        return res;
    }

    /***
     * 效率太低
     */
    public int[] maxSlidingWindows(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        int[] sort = new int[k];
        sort[0] = nums[0];
        for (int i = 1; i < k; i++) {
            int insert = insert(sort, 0, i - 1, nums[i]);
            for (int j = i; j > insert; j--) {
                sort[j] = sort[j - 1];
            }
            sort[insert] = nums[i];
        }
        res[0] = max(sort);
        for (int i= 1; i <= nums.length - k; i++) {
            int remove = remove(sort, nums[i - 1], 0, sort.length - 1);
            for (int j = remove; j < sort.length - 1; j++) {
                sort[j] = sort[j + 1];
            }
            int insert = insert(sort, 0, sort.length - 2, nums[i + k - 1]);
            for (int j = sort.length - 1; j > insert; j--) {
                sort[j] = sort[j - 1];
            }
            sort[insert] = nums[i + k - 1];
            res[i] = max(sort);
        }
        return res;
    }

    private static int remove(int[] sort, int num, int start, int end) {
        int mid = (start + end) / 2;
        if (sort[mid] == num) {
            return mid;
        } else if (sort[mid] < num){
            return remove(sort, num, start, mid - 1);
        } else {
            return remove(sort, num, mid + 1, end);
        }
    }

    private static int max(int[] sort) {
        return sort[0];
    }

    private static int insert(int[] sort, int start, int end, int num) {
        if (end < start) {
            return 0;
        }
        if (start == end) {
            if (sort[start] < num) {
                return start;
            } else {
                return start + 1;
            }
        }
        int mid = (start + end) / 2;
        if (sort[mid] < num) {
            return insert(sort, start, mid, num);
        } else {
            return insert(sort, mid + 1, end, num);
        }
    }
}
