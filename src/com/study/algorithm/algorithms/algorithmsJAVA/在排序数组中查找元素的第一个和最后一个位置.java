package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

// 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。
//
// 进阶：
//
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
//
//
// 示例 1：
//
// 输入：nums = [5,7,7,8,8,10], target = 8
// 输出：[3,4]
// 示例 2：
//
// 输入：nums = [5,7,7,8,8,10], target = 6
// 输出：[-1,-1]
// 示例 3：
//
// 输入：nums = [], target = 0
// 输出：[-1,-1]
//
//
// 提示：
//
// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109
// nums 是一个非递减数组
// -109 <= target <= 109
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 在排序数组中查找元素的第一个和最后一个位置 {
    @Test
    public void 在排序数组中查找元素的第一个和最后一个位置() {
        //在排序数组中查找数字 I
        int[] ints = {5, 7, 7, 8, 8, 10};
        System.out.println("在排序数组中查找元素的第一个和最后一个位置:" + Arrays.toString(searchRange(ints, 8)));
    }

    public int[] searchRange(int[] nums, int target) {

        // 方法一：不用多说，一趟遍历，时间复杂度O(n)。
        // 方法二：二分查找
        // 既然数组有序，那么在有序数组中查找某一个值，可以使用二分查找，时间复杂度可降至O(log n)。
        // 第一步，二分查找“中间”位置，其所在目标值的起止位置之间。
        // 第二步，二分查找目标值的开始位置。
        // 第三步，二分查找目标值的结束位置。

        int left  = 0;
        int right = nums.length - 1;
        int mid   = -1;

        // 查找目标值所在的“中间”位置
        while (left <= right) {

            mid = left + right >> 1;

            // 如果相等，退出循环
            if (nums[mid] == target) {
                break;
            }
            // 如果小于目标值，则区间低位+1
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            // 如果大于目标值，则区间高位-1
            else {
                right = mid - 1;
            }
        }

        // 如果低位下标大于高位下标，说明数组中无此值
        if (left > right) {
            return new int[]{-1, -1};
        }

        // 说明数组中有此值，继续查找起止位置
        // （其实两个方法思路是一样的，只是为了少一个"if"判断）
        return new int[]{
                binarySearchStart(nums, target, left, mid),
                binarySearchEnd(nums, target, mid, right)
        };
    }

    // 查找目标值在数组中的起始位置
    public int binarySearchStart(int[] nums, int target, int left, int right) {
        int mid;

        while (left < right) {
            // 在此给定的区间中，查找的“中间值”一定不会大于目标值

            mid = left + right >> 1;

            // 如果中间值等于目标值，搜索向低位偏移，则高位等于中间位置
            if (nums[mid] == target) {
                right = mid;
            }
            // 如果中间值小于目标值，则低位等于中间位置+1
            else if (nums[mid] < target) {
                left = mid + 1;
            }
        }

        // 因为肯定有值，此时left=right
        return right;
    }

    // 查找目标值在数组中的结束位置
    public int binarySearchEnd(int[] nums, int target, int left, int right) {
        int mid;
        while (left < right) {
            // 在此给定的区间中，查找的“中间值”一定不会小于目标值

            // int计算的/2操作中，和算术运算不同，结果为整数部分，其总是偏向于低位值
            // 所以这里需要有个+1操作，使得mid偏向于高位值，避免出现死循环
            mid = left + 1 + right >> 1;

            // 如果中间值等于目标值，搜索向高位偏移，则低位等于中间位置
            if (nums[mid] == target) {
                left = mid;
            }
            // 如果中间值小于目标值，则高位等于中间位置-1
            else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // 因为肯定有值，此时left=right
        return left;
    }
}
