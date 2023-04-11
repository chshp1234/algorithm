package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

// 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
// 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
//
// 说明:
// 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
//
// 示例:
//
// 输入: nums = [-2,5,-1], lower = -2, upper = 2,
// 输出: 3
// 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/count-of-range-sum
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 区间和的个数 {
    @Test
    public void 区间和的个数() {
        int[] graph = {-2147483647, 0, -2147483647, 2147483647};
        System.out.println("区间和的个数:" + countRangeSum(graph, -564, 3864));
    }

    public int countRangeSum(int[] nums, int lower, int upper) {

        // 方法一：
        // 使用一个辅助数组sum，sum[i]代表原数组中下标0-i的区间元素的和，
        // 区间i-j的元素和就为sum[j]-sum[i-1].
        // 依次来判断区间元素个数为1，2，3...len（数组长度）时，元素和是否符合条件即可
        int len = nums.length;

        if (len == 0) {
            return 0;
        }

        int result = 0;

        long[] sum = new long[len + 1];
        for (int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + nums[i];
            if (nums[i] >= lower && nums[i] <= upper) {
                result++;
            }
        }

        int subLen = 2;
        while (true) {
            int count = len - subLen + 1;
            if (count == 0) {
                break;
            }

            for (int i = 0; i < count; i++) {
                long subSum = sum[i + subLen] - sum[i];
                if (subSum >= lower && subSum <= upper) {
                    result++;
                }
            }

            subLen++;
        }

        return result;
    }

    public int countRangeSumByMergeSort(int[] nums, int lower, int upper) {

        // 方法：归并排序
        // 思路与算法
        //
        // 设前缀和数组为 preSum，则问题等价于求所有的下标对 (i,j)，
        // 满足preSum[j]−preSum[i]∈[lower,upper]
        //
        // 我们先考虑如下的问题：给定两个升序排列的数组 n1,n2，试找出所有的下标对 (i,j)，
        // 满足n2[j]−n1[i]∈[lower,upper]
        //
        // 在已知两个数组均为升序的情况下，这一问题是相对简单的：我们在 n2中维护两个指针 l,r。
        // 起初，它们都指向 n2的起始位置。
        // 随后，我们考察 n1的第一个元素。
        // 首先，不断地将指针 l 向右移动，直到 n2[l]≥n1[0]+lower 为止，此时， l 及其右边的元素均大于或等于 n1[0]+lower；
        // 随后，再不断地将指针 r 向右移动，直到 n2[r]>n1[0]+upper 为止，则 r 左边的元素均小于或等于 n1[0]+upper。
        // 故区间 [l,r) 中的所有下标 j，都满足n2[j]−n1[0]∈[lower,upper]
        //
        // 接下来，我们考察 n1的第二个元素。
        // 由于 n1是递增的，不难发现 l,r 只可能向右移动。因此，我们不断地进行上述过程，并对于 n1中的每一个下标，都记录相应的区间 [l,r) 的大小。
        // 最终，我们就统计得到了满足条件的下标对 (i,j) 的数量。
        //
        // 在解决这一问题后，原问题就迎刃而解了：
        // 我们采用归并排序的方式，能够得到左右两个数组排序后的形式，以及对应的下标对数量。
        // 对于原数组而言，若要找出全部的下标对数量，只需要再额外找出左端点在左侧数组，同时右端点在右侧数组的下标对数量，而这正是我们此前讨论的问题。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/count-of-range-sum/solution/qu-jian-he-de-ge-shu-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        long s = 0;
        long[] sum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            s += nums[i];
            sum[i + 1] = s;
        }
        return countRangeSumRecursive(sum, lower, upper, 0, sum.length - 1);
    }

    public int countRangeSumRecursive(long[] sum, int lower, int upper, int left, int right) {
        if (left == right) {
            return 0;
        } else {
            int mid = (left + right) / 2;
            int n1 = countRangeSumRecursive(sum, lower, upper, left, mid);
            int n2 = countRangeSumRecursive(sum, lower, upper, mid + 1, right);
            int ret = n1 + n2;

            // 首先统计下标对的数量
            int i = left;
            int l = mid + 1;
            int r = mid + 1;
            while (i <= mid) {
                while (l <= right && sum[l] - sum[i] < lower) {
                    l++;
                }
                while (r <= right && sum[r] - sum[i] <= upper) {
                    r++;
                }
                ret += r - l;
                i++;
            }

            // 随后合并两个排序数组
            int[] sorted = new int[right - left + 1];
            int p1 = left, p2 = mid + 1;
            int p = 0;
            while (p1 <= mid || p2 <= right) {
                if (p1 > mid) {
                    sorted[p++] = (int) sum[p2++];
                } else if (p2 > right) {
                    sorted[p++] = (int) sum[p1++];
                } else {
                    if (sum[p1] < sum[p2]) {
                        sorted[p++] = (int) sum[p1++];
                    } else {
                        sorted[p++] = (int) sum[p2++];
                    }
                }
            }
            for (int j = 0; j < sorted.length; j++) {
                sum[left + j] = sorted[j];
            }
            return ret;
        }
    }
}
