package com.study.algorithm.algorithms;

import org.junit.Test;

public class 山脉数组中查找目标值 {

    @Test
    public void 山脉数组中查找目标值() {
        int[] ints = {1, 2, 1};

        System.out.println("山脉数组中查找目标值:" + findInMountainArray(2, ints));
    }

    /**
     * （这是一个 交互式问题 ）
     *
     * <p>给你一个 山脉数组 mountainArr，请你返回能够使得 mountainArr.get(index) 等于 target 最小 的下标 index 值。
     *
     * <p>如果不存在这样的下标 index，就请返回 -1。
     *
     * <p>
     *
     * <p>何为山脉数组？如果数组 A 是一个山脉数组的话，那它满足如下条件：
     *
     * <p>首先，A.length >= 3
     *
     * <p>其次，在 0 < i < A.length - 1 条件下，存在 i 使得：
     *
     * <p>A[0] < A[1] < ... A[i-1] < A[i] A[i] > A[i+1] > ... > A[A.length - 1]
     *
     * <p>你将 不能直接访问该山脉数组，必须通过 MountainArray 接口来获取数据：
     *
     * <p>MountainArray.get(k) - 会返回数组中索引为k 的元素（下标从 0 开始） MountainArray.length() - 会返回该数组的长度
     *
     * <p>注意：
     *
     * <p>对 MountainArray.get 发起超过 100 次调用的提交将被视为错误答案。此外，任何试图规避判题系统的解决方案都将会导致比赛资格被取消。
     *
     * <p>为了帮助大家更好地理解交互式问题，我们准备了一个样例 “答案”：https://leetcode-cn.com/playground/RKhe3ave，请注意这 不是一个正确答案。
     *
     * <p>
     *
     * <p>示例 1：
     *
     * <p>输入：array = [1,2,3,4,5,3,1], target = 3 输出：2 解释：3 在数组中出现了两次，下标分别为 2 和 5，我们返回最小的下标 2。 示例 2：
     *
     * <p>输入：array = [0,1,2,4,2,1], target = 3 输出：-1 解释：3 在数组中没有出现，返回 -1。
     *
     * <p>提示：
     *
     * <p>3 <= mountain_arr.length() <= 10000 0 <= target <= 10^9 0 <= mountain_arr.get(index)
     * <= 10^9
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-in-mountain-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findInMountainArray(int target, int[] mountainArr) {

        // 查找峰值
        int peak = binarySearchPeak(0, mountainArr.length - 1, mountainArr);

        int lowNum = mountainArr[0];
        int highNum = mountainArr[mountainArr.length - 1];

        int result;

        // 如果山脉左侧最低位值比目标值大，说明左侧无目标值
        if (lowNum > target) {
            result = -1;
        } else {
            // 查找左侧目标值
            result = binarySearch(target, 0, peak, mountainArr, true);
        }

        // 如果左侧无目标值
        if (result == -1) {
            // 如果山脉右侧最低位值比目标值大，说明右侧无目标值
            if (highNum > target) {
                return -1;
            } else {
                // 查找左侧目标值
                result = binarySearch(target, peak + 1, mountainArr.length - 1, mountainArr, false);
            }
        }

        return result;

        /*// 查找峰值
        int peak = binarySearchPeak(0, mountainArr.length - 1, mountainArr);
        // 查找出左侧目标坐标
        int left = binarySearch(target, 0, peak, mountainArr, true);
        // 查找出右侧目标坐标(此题可以先判断左侧，若无值则再判断右侧)
        int right = binarySearch(target, peak + 1, mountainArr.length - 1, mountainArr, false);

        return left == -1 ? right : left;*/
    }

    // 二分法查找山脉数组中最大值
    private int binarySearchPeak(int low, int high, int[] mountainArr) {
        // 如果找到此，说明最大值在两数中间
        if ((high - low) == 2) {
            return (low + 1);
        } else if ((high - low) == 1) {
            // 如果找到此，最大值为其中之一
            return mountainArr[high] > mountainArr[low] ? high : low;
        }

        // 中间坐标
        int mid = (low + high) >> 1;

        // 1/4处坐标
        int lMid = (low + mid) >> 1;
        // 3/4处坐标
        int rMid = (mid + high) >> 1;

        int midNum = mountainArr[mid];
        int lMidNum = mountainArr[lMid];
        int rMidNum = mountainArr[rMid];

        // 如果中间坐标处值大于1/4处和3/4处，说明，峰值在1/4处和3/4处中间
        if (midNum > lMidNum && midNum > rMidNum) {
            // 进一步判断，如果中间处处于山脉左侧（升序），则峰值在中间处（1/2）与3/4之间
            if (mountainArr[mid + 1] > midNum) {
                return binarySearchPeak(mid, rMid, mountainArr);
            } else {
                // 否则峰值在1/4与中间处（1/2）之间
                return binarySearchPeak(lMid, mid, mountainArr);
            }
        }
        // 如果中间处值大于1/4处，（小于3/4处）
        else if (midNum > lMidNum) {
            // 如果3/4处处于山脉左侧（升序），则峰值在3/4处与最高位（high）之间
            if (mountainArr[rMid + 1] > rMidNum) {
                return binarySearchPeak(rMid, high, mountainArr);
            } else {
                // 否则峰值在中间处与3/4处之间
                return binarySearchPeak(mid, rMid, mountainArr);
            }
        } else /* (mountainArr[mid] > mountainArr[rMid])*/ {
            // 如果1/4处处于山脉左侧（升序），则峰值在1/4处与中间处之间
            if (mountainArr[lMid + 1] > lMidNum) {
                return binarySearchPeak(lMid, mid, mountainArr);
            } else {
                // 否则峰值在低位（low）与1/4处之间
                return binarySearchPeak(low, lMid, mountainArr);
            }
        }
    }

    // 二分查找目标值（target），isLeft代表此次查找属于山脉左侧还是山脉右侧
    private int binarySearch(int target, int low, int high, int[] mountainArr, boolean isLeft) {

        // 如果搜索到高位值小于低位值，说明无此目标值
        if ((high - low) < 0) {
            return -1;
        }

        int mid = (low + high) >> 1;
        int midNum = mountainArr[mid];
        if (midNum == target) {
            // 如果中间值等于目标值
            return mid;
        }
        // 如果中间值大于目标值
        else if (midNum > target) {
            // 判断是查找左侧山脉还是右侧山脉进行进一步查找
            if (isLeft) {
                return binarySearch(target, low, mid - 1, mountainArr, isLeft);
            } else {
                return binarySearch(target, mid + 1, high, mountainArr, isLeft);
            }

        } else {
            // 判断是查找左侧山脉还是右侧山脉进行进一步查找
            if (isLeft) {
                return binarySearch(target, mid + 1, high, mountainArr, isLeft);
            } else {
                return binarySearch(target, low, mid - 1, mountainArr, isLeft);
            }
        }
    }
}
