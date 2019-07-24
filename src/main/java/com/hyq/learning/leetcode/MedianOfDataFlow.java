package com.hyq.learning.leetcode;

/**
 * @author dibulidohu
 * @classname MedianOfDataFlow
 * @date 2019/6/511:18
 * @description 数据流的中位数
 * 1- 两个优先级队列  保证两个队列的size 差值小于等于1
 * 2- 大小堆  原理和以上一样(使用system。arrayscopy 报错 com.eclipsesource.json.ParseException: Expected value at 1:50000
 * 3- 一个堆  超时
 */
public class MedianOfDataFlow {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(2);
        medianFinder.addNum(3);
        double median = medianFinder.findMedian();
        medianFinder.addNum(7);
        double median1 = medianFinder.findMedian();

        MedianFind medianFind = new MedianFind();
        medianFind.addNum(125);
        double median2 = medianFind.findMedian();
        medianFind.addNum(-1);
        double median3 = medianFind.findMedian();
        medianFind.addNum(9);
        medianFind.addNum(9);
        medianFind.addNum(9);
        medianFind.addNum(9);
        medianFind.addNum(9);
        medianFind.addNum(9);
        medianFind.addNum(9);
    }

    static class MedianFinder {
        int[] arr;
        int count;

        /** initialize your data structure here. */
        public MedianFinder() {
            arr = new int[16];
        }

        public void addNum(int num) {
            if(count == arr.length) {
                int[] newArr = new int[count * 2];
                for(int i= 0; i < arr.length; i++) {
                    newArr[i] = arr[i];
                }
                arr = newArr;
            }
            arr[count] = num;
            count++;
            sort(arr);
        }

        public double findMedian() {
            if(count % 2 == 1) {
                return arr[count / 2];
            } else {
                return (float)(arr[count / 2] + arr[count / 2 - 1]) / 2;
            }
        }

        public void heapBuild(int[] arr, int size) {
            for (int i = size / 2 - 1; i >= 0; i--) {
                if (arr[i ] > arr[2 * i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[2 * i + 1];
                    arr[2 * i + 1] = temp;
                }
                if (2 * i + 2 < size && arr[i] > arr[2 * i + 2]) {
                    int temp = arr[i];
                    arr[i] = arr[2 * i + 2];
                    arr[2 * i + 2] = temp;
                }
            }
        }

        public void sort(int[] arr) {
            for (int i = arr.length; i >= 1; i--) {
                heapBuild(arr, i);
                int temp = arr[i - 1];
                arr[i - 1] = arr[0];
                arr[0] = temp;
            }
        }
    }


    static class MedianFind{
        int[] maxHeap;
        int maxCount;
        int[] minHeap;
        int minCount;

        public MedianFind() {
            this.maxHeap = new int[16];
            this.minHeap = new int[16];
        }

        public void addNum(int num) {
            if (maxCount == 0 && minCount == 0) {
                minCount++;
                minHeap[0] = num;
                return;
            }
            if (maxCount == minCount) {
                if (num <= minHeap[minCount - 1]) {
                    minCount++;
                    grop();
                    minHeap[minCount - 1] = num;
                } else if (num >= maxHeap[maxCount - 1]){
                    maxCount++;
                    grop();
                    maxHeap[maxCount - 1] = num;
                } else if (num >= maxHeap[0]){
                    maxCount++;
                    grop();
                    maxHeap[maxCount - 1] = num;
                    maxSort(maxHeap);
                } else {
                    minCount++;
                    grop();
                    minHeap[minCount - 1] = num;
                    minSort(minHeap);
                }
            } else if (minCount < maxCount) {
                if (num <= minHeap[minCount - 1]) {
                    minCount++;
                    grop();
                    minHeap[minCount - 1] = num;
                } else if (num <= maxHeap[0] && num >= minHeap[0]){
                    minCount++;
                    grop();
                    if (minCount - 1 >= 0) {
                        System.arraycopy(minHeap, 0, minHeap, 1, minCount - 1);
                    }
                    minHeap[0] = num;
                } else if (num > minHeap[0]){
                    minCount++;
                    grop();
                    minHeap[minCount - 1] = num;
                    maxSort(minHeap);
                } else if (num < maxHeap[maxCount - 1]){
                    minCount++;
                    grop();
                    if (minCount - 1 >= 0) {
                        System.arraycopy(minHeap, 0, minHeap, 1, minCount - 1);
                    }
                    minHeap[0] = maxHeap[0];
                    maxHeap[0] = num;
                    maxSort(maxHeap);
                } else {
                    minCount++;
                    grop();
                    if (minCount - 1 >= 0) {
                        System.arraycopy(minHeap, 0, minHeap, 1, minCount - 1);
                    }
                    minHeap[0] = maxHeap[0];
                    if (maxCount - 1 >= 0) {
                        System.arraycopy(minHeap, 1, minHeap, 0, maxCount - 1);
                    }
                    maxHeap[maxCount - 1] = num;
                }
            } else {
                if (num <= minHeap[minCount - 1]) {
                    maxCount++;
                    grop();
                    System.arraycopy(maxHeap, 0, maxHeap, 1, maxCount - 1);
                    maxHeap[0] = minHeap[0];
                    System.arraycopy(minHeap, 1, minHeap, 0, maxCount - 1);
                    minHeap[minCount - 1] = num;
                } else if (num < minHeap[0]){
                    maxCount++;
                    grop();
                    System.arraycopy(maxHeap, 0, maxHeap, 1, maxCount - 1);
                    maxHeap[0] = minHeap[0];
                    minHeap[0] = num;
                    minSort(minHeap);
                } else if (num >= minHeap[0] && num <= maxHeap[0]){
                    maxCount++;
                    grop();
                    System.arraycopy(maxHeap, 0, maxHeap, 1, maxCount - 1);
                    maxHeap[0] = num;
                } else if (num < maxHeap[maxCount - 1]){
                    minCount++;
                    grop();
                    maxHeap[maxCount - 1] = num;
                    maxSort(maxHeap);
                } else {
                    maxCount++;
                    grop();
                    maxHeap[maxCount - 1] = num;
                }
            }
        }

        public double findMedian() {
            if (maxCount == minCount) {
                return (maxHeap[0] + minHeap[0]) / 2.0;
            } else if (maxCount > minCount) {
                return maxHeap[0];
            } else {
                return minHeap[0];
            }
        }

        private void grop() {
            if (maxCount > maxHeap.length) {
                int[] temp = new int[maxHeap.length * 2];
                System.arraycopy(maxHeap, 0, temp, 0, maxHeap.length);
                maxHeap = temp;
            }

            if (minCount > minHeap.length) {
                int[] temp = new int[minHeap.length * 2];
                System.arraycopy(minHeap, 0, temp, 0, minHeap.length);
                minHeap = temp;
            }
        }

        public void heapBuild(int[] arr, int size) {
            for (int i = size / 2 - 1; i >= 0; i--) {
                if (arr[i ] < arr[2 * i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[2 * i + 1];
                    arr[2 * i + 1] = temp;
                }
                if (2 * i + 2 < size && arr[i] < arr[2 * i + 2]) {
                    int temp = arr[i];
                    arr[i] = arr[2 * i + 2];
                    arr[2 * i + 2] = temp;
                }
            }
        }

        public void maxSort(int[] arr) {
            for (int i = maxCount; i >= 1; i--) {
                heapBuild(arr, i);
                int temp = arr[i - 1];
                arr[i - 1] = arr[0];
                arr[0] = temp;
            }
        }

        public void heapBuild1(int[] arr, int size) {
            for (int i = size / 2 - 1; i >= 0; i--) {
                if (arr[i ] > arr[2 * i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[2 * i + 1];
                    arr[2 * i + 1] = temp;
                }
                if (2 * i + 2 < size && arr[i] > arr[2 * i + 2]) {
                    int temp = arr[i];
                    arr[i] = arr[2 * i + 2];
                    arr[2 * i + 2] = temp;
                }
            }
        }

        public void minSort(int[] arr) {
            for (int i = minCount; i >= 1; i--) {
                heapBuild1(arr, i);
                int temp = arr[i - 1];
                arr[i - 1] = arr[0];
                arr[0] = temp;
            }
        }
    }
}
