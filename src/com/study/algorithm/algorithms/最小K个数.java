package com.study.algorithm.algorithms;

import java.util.PriorityQueue;

//设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
//
//示例：
//
//输入： arr = [1,3,5,7,2,4,6,8], k = 4
//输出： [1,2,3,4]
//提示：
//
//0 <= len(arr) <= 100000
//0 <= k <= min(100000, len(arr))
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/smallest-k-lcci
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最小K个数 {
    public int[] smallestK(int[] arr, int k) {
        //大顶堆
        //遍历数组k次，用大顶堆存储最小的k个数
        //继续遍历数组判断当前元素arr[i]和堆顶元素的大小，若小于堆顶元素，则将该数加入堆中，并取出堆顶元素；
        if (arr.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        int len = arr.length;
        for (int i = 0; i < k; i++) {
            queue.offer(arr[i]);
        }

        int top = queue.peek();

        for (int i = k; i < len; i++) {
            if (arr[i] < top) {
                queue.poll();
                queue.offer(arr[i]);
                top = queue.peek();
            }
        }

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;

    }
}
