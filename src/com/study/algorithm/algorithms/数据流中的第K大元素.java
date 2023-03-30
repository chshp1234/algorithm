package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.PriorityQueue;

//设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
//
//请实现 KthLargest 类：
//
//KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
//int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
// 
//
//示例：
//
//输入：
//["KthLargest", "add", "add", "add", "add", "add"]
//[[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
//输出：
//[null, 4, 5, 5, 8, 8]
//
//解释：
//KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
//kthLargest.add(3);   // return 4
//kthLargest.add(5);   // return 5
//kthLargest.add(10);  // return 5
//kthLargest.add(9);   // return 8
//kthLargest.add(4);   // return 8
// 
//
//提示：
//1 <= k <= 104
//0 <= nums.length <= 104
//-104 <= nums[i] <= 104
//-104 <= val <= 104
//最多调用 add 方法 104 次
//题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/kth-largest-element-in-a-stream
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 数据流中的第K大元素 {

    @Test
    public void 数字范围按位与() {
        int[] nums = {4};
        KthLargest obj = new KthLargest(2, nums);
        System.out.println("字符串的排列:" + obj.add(3));
        System.out.println("字符串的排列:" + obj.add(5));
        System.out.println("字符串的排列:" + obj.add(10));
        System.out.println("字符串的排列:" + obj.add(9));
        System.out.println("字符串的排列:" + obj.add(4));
    }

    //小根堆
    //使用大小为k的小根堆
    //堆顶即为数据流中第k大的元素（需注意当k大于数组大小len时）
    public static class KthLargest {

        PriorityQueue<Integer> queue;
        int                    k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            queue = new PriorityQueue<>();
            int len = nums.length;

            //初始化堆，使其中元素保持k个大小（当len比较小时，是len大小）
            for (int i = 0, l = Math.min(k, len); i < l; i++) {
                queue.offer(nums[i]);
            }
            for (int i = k; i < len; i++) {
                if (nums[i] > queue.peek()) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }

        public int add(int val) {
            //若k比len大，则需添加一个元素进去使其相等
            if (k > queue.size()) {
                queue.offer(val);
            } else if (val > queue.peek()) {
                //若当前元素val比堆顶元素大，则移除堆顶元素（此元素为第k+1大的元素），并添加当前元素
                queue.poll();
                queue.offer(val);
            }
            return queue.peek();
        }
    }


    /*
    //第K小的元素，并且加入一个移除一个（和题目倍道而行哈哈）
    public static class KthLargest {

        PriorityQueue<Integer> leftQueue;
        PriorityQueue<Integer> rightQueue;

        public KthLargest(int k, int[] nums) {
            int len = nums.length;
            leftQueue = new PriorityQueue<>((integer, t1) -> t1 - integer);
            rightQueue = new PriorityQueue<>();
            for (int i = 0; i < k - 1; i++) {
                leftQueue.offer(nums[i]);
            }
            for (int i = k - 1; i < len; i++) {
                if (leftQueue.peek() > nums[i]) {
                    rightQueue.offer(leftQueue.poll());
                    leftQueue.offer(nums[i]);
                } else {
                    rightQueue.offer(nums[i]);
                }
            }
        }

        public int add(int val) {
            int result;
            if (val < leftQueue.peek()) {
                result = leftQueue.poll();
                leftQueue.offer(val);
            } else if (val >= leftQueue.peek() && val <= rightQueue.peek()) {
                result = val;
            } else {
                result = rightQueue.poll();
                rightQueue.offer(val);
            }

            return result;
        }
    }*/
}
