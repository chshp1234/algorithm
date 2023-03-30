package com.study.algorithm.algorithms;

import org.junit.Test;

public class 魔术索引 {
    @Test
    public void 魔术索引() {

        System.out.println("魔术索引:" + findMagicIndexByBinarySearch(new int[] {2, 2, 3, 4, 5, 5}));
    }

    // 魔术索引。 在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] =
    // i。给定一个有序整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。若有多个魔术索引，返回索引值最小的一个。
    //
    // 示例1:
    //
    // 输入：nums = [0, 2, 3, 4, 5]
    // 输出：0
    // 说明: 0下标的元素为0
    // 示例2:
    //
    // 输入：nums = [1, 1, 1]
    // 输出：1
    // 提示:
    //
    // nums长度在[1, 1000000]之间
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/magic-index-lcci
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int findMagicIndex(int[] nums) {
        for (int i = 0, l = nums.length; i < l; i++) {
            if (nums[i] == i) {
                return i;
            }
        }
        return -1;
    }

    @Deprecated
    /*如果元素不可重复，可解，但此题元素可重复*/
    public int findMagicIndexByBinarySearch(int[] nums) {
        int result = -1;

        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] > mid) {
                high = mid - 1;
            } else if (nums[mid] < mid) {
                low = mid + 1;
            } else {
                result = mid;
                high = mid - 1;
            }
        }

        return result;
    }
}
