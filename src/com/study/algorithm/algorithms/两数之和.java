package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 两数之和 {

    @Test
    public void 两数之和() {
        System.out.println(
                "两数之和:" + Arrays.toString(twoSumIIByTwoPoint(new int[] {2, 7, 11, 15}, 9)));
    }

    // 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
    //
    // 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
    //
    //
    //
    // 示例:
    //
    // 给定 nums = [2, 7, 11, 15], target = 9
    //
    // 因为 nums[0] + nums[1] = 2 + 7 = 9
    // 所以返回 [0, 1]
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/two-sum
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[] twoSum(int[] nums, int target) {
        // 用哈希表存储当前元素的值和当前元素的位置
        Map<Integer, Integer> map = new HashMap<>();

        Integer index;
        for (int i = 0, l = nums.length; i < l; i++) {
            // 按相减的值从哈希表中取值
            index = map.get(target - nums[i]);

            // 若值存在，则说明数组中可以找到与之匹配可以相加等于target的值
            // 若值不存在，则把当前值和位置添加进哈希表，继续下一轮查找
            if (index != null) {
                return new int[] {i, index};
            } else {
                map.put(nums[i], i);
            }
        }

        // 由于题目明确数组中有一个对应的答案，所以不会走到此
        return null;
    }

    // 167. 两数之和 II - 输入有序数组
    public int[] twoSumIIByBinarySearch(int[] nums, int target) {

        int index;
        int len = nums.length;

        // 因为数组是有序的，固可以用二分查找
        for (int i = 0; i < len; i++) {
            index = Arrays.binarySearch(nums, i + 1, len, target - nums[i]);

            if (index > 0 && index < len) {
                return new int[] {i + 1, index + 1};
            }
        }

        return null;
    }

    // 167. 两数之和 II - 输入有序数组
    public int[] twoSumIIByTwoPoint(int[] numbers, int target) {

        // 初始时两个指针分别指向第一个元素位置和最后一个元素的位置。每次计算两个指针指向的两个元素之和，并和目标值比较。
        // 如果两个元素之和等于目标值，则发现了唯一解。如果两个元素之和小于目标值，则将左侧指针右移一位。
        // 如果两个元素之和大于目标值，则将右侧指针左移一位。移动指针之后，重复上述操作，直到找到答案。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/liang-shu-zhi-he-ii-shu-ru-you-xu-shu-zu-by-leet-2/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[] {low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[] {-1, -1};
    }
}
