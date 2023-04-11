package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//
//( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
//
//编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
//
//示例 1:
//
//输入: nums = [2,5,6,0,0,1,2], target = 0
//输出: true
//示例 2:
//
//输入: nums = [2,5,6,0,0,1,2], target = 3
//输出: false
//进阶:
//
//这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
//这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 搜索旋转排序数组II {
    @Test
    public void 搜索旋转排序数组II() {

        System.out.println("搜索旋转排序数组II：" + search(new int[]{1, 2}, 2));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    public boolean search(int[] nums, int target) {
        //二分法
        //因为有重复元素，对于收尾相同的，我们没法判断具体属于哪个区间，所以我们只能将当前二分区间的左边界加一，右边界减一，然后在新区间上继续二分查找。

        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int len   = nums.length;
        int left  = 0;
        int right = len - 1;
        int mid;
        if (nums[0] < nums[len - 1]) {
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    return true;
                }
                if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return false;
        }

        boolean b = nums[left] == nums[right];

        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] == nums[left] && nums[mid] == nums[right]) {
                if (!b || nums[mid] > nums[0] || nums[mid] < nums[0]) {
                    //说明此区间是一条直线
                    return false;
                }
                left++;
                right--;
            } else if (nums[left] <= nums[mid]) {
                //left->mid单调递增
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                //left属于左边递增子序列，mid属于右边递增子序列
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

        }
        return false;

    }
}
