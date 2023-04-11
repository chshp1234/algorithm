package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

// 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
//
// 如果数组元素个数小于 2，则返回 0。
//
// 示例 1:
//
// 输入: [3,6,9,1]
// 输出: 3
// 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
// 示例 2:
//
// 输入: [10]
// 输出: 0
// 解释: 数组元素个数小于 2，因此返回 0。
// 说明:
//
// 你可以假设数组中所有元素都是非负整数，且数值在 32 位有符号整数范围内。
// 请尝试在线性时间复杂度和空间复杂度的条件下解决此问题。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/maximum-gap
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大间距 {
    @Test
    public void 最大间距() {

        int[] pillars = {2, 10, 9, 3, 2};

        System.out.println("最大间距:" + maximumGap(pillars));
    }

    public int maximumGap(int[] nums) {
        // 排序，计算两两差值，找出差值最大值
        // 注：要使得时间复杂度和空间复杂度达到线性，可以使用非比较类型的排序，基数排序和桶排序
        int len = nums.length;
        if (len < 2) {
            return 0;
        }

        int result = 0;

        Arrays.sort(nums);

        for (int i = len - 2; i >= 0; i--) {
            result = Math.max(result, nums[i + 1] - nums[i]);
            // 从大到小开始遍历
            // 由于元素都是非负数，因此若差值大于等于当前元素(nums[i])和最小元素(nums[0])的差值，则后续元素的差值肯定只会更小
            //            if (result >= (nums[i]-nums[0])) {
            //                break;
            //            }
        }

        /*PriorityQueue<Integer> queue =
                new PriorityQueue<>(
                        len,
                        new Comparator<Integer>() {
                            @Override
                            public int compare(Integer o1, Integer o2) {
                                return o2.compareTo(o1);
                            }
                        });

        for (int i = 0; i < len; i++) {
            queue.offer(nums[i]);
        }

        int last = queue.poll();
        int current;
        while (!queue.isEmpty()) {
            current = queue.poll();
            result = Math.max(result, last - current);
            last = current;

            if (result >= current) {
                break;
            }
        }*/

        return result;
    }

    // 设长度为 N 的数组中最大值为 max,min，则不难发现相邻数字的最大间距不会小于 (max−min)/(N−1)。
    // 因此，我们可以选取整数 d=⌊(max−min)/(N−1)⌋<⌈(max−min)/(N−1)⌉。
    // 随后，我们将整个区间划分为若干个大小为 d的桶，并找出每个整数所在的桶。
    // 根据前面的结论，能够知道，元素之间的最大间距一定不会出现在某个桶的内部，而一定会出现在不同桶当中。
    //
    // 因此，在找出每个元素所在的桶之后，我们可以维护每个桶内元素的最大值与最小值。
    // 随后，只需从前到后不断比较相邻的桶，用后一个桶的最小值与前一个桶的最大值之差作为两个桶的间距，最终就能得到所求的答案。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/maximum-gap/solution/zui-da-jian-ju-by-leetcode-solution/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int maximumGapByBucketSort(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int minVal = Arrays.stream(nums).min().getAsInt();
        int maxVal = Arrays.stream(nums).max().getAsInt();
        int d = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketSize = (maxVal - minVal) / d + 1;

        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; ++i) {
            Arrays.fill(bucket[i], -1); // 存储 (桶内最小值，桶内最大值) 对， (-1, -1) 表示该桶是空的
        }
        for (int i = 0; i < n; i++) {
            int idx = (nums[i] - minVal) / d;
            if (bucket[idx][0] == -1) {
                bucket[idx][0] = bucket[idx][1] = nums[i];
            } else {
                bucket[idx][0] = Math.min(bucket[idx][0], nums[i]);
                bucket[idx][1] = Math.max(bucket[idx][1], nums[i]);
            }
        }

        int ret = 0;
        int prev = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }
            if (prev != -1) {
                ret = Math.max(ret, bucket[i][0] - bucket[prev][1]);
            }
            prev = i;
        }
        return ret;
    }
}
