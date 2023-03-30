package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//给你一个非负整数数组 nums 。如果存在一个数 x ，使得 nums 中恰好有 x 个元素 大于或者等于 x ，那么就称 nums 是一个 特殊数组 ，而 x 是该数组的 特征值 。
//
//注意： x 不必 是 nums 的中的元素。
//
//如果数组 nums 是一个 特殊数组 ，请返回它的特征值 x 。否则，返回 -1 。可以证明的是，如果 nums 是特殊数组，那么其特征值 x 是 唯一的 。
//
// 
//
//示例 1：
//
//输入：nums = [3,5]
//输出：2
//解释：有 2 个元素（3 和 5）大于或等于 2 。
//示例 2：
//
//输入：nums = [0,0]
//输出：-1
//解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。
//如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。
//如果 x = 1，应该有 1 个元素 >= x，但实际有 0 个。
//如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。
//x 不能取更大的值，因为 nums 中只有两个元素。
//示例 3：
//
//输入：nums = [0,4,3,0,4]
//输出：3
//解释：有 3 个元素大于或等于 3 。
//示例 4：
//
//输入：nums = [3,6,7,7,0]
//输出：-1
// 
//
//提示：
//
//1 <= nums.length <= 100
//0 <= nums[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/special-array-with-x-elements-greater-than-or-equal-x
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 特殊数组的特征值 {
    @Test
    public void 特殊数组的特征值() {

        int[] postorder = {0,0};

        System.out.println(
                "特殊数组的特征值:" + specialArray(postorder));
    }

    public int specialArray(int[] nums) {
        //排序+二分查找
        //首先对数组进行排序
        //1.其次根据特征值 x 的定义，x 一定是在 [1,n] 范围内的一个整数，其中 n 是数组 nums 的长度
        //2.那么我们可以先对特征值x在[1,n]范围内进行二分，找到特征值target
        //3.再对有序数组进行二分查找target应在数组中的位置index，那么大于target的数就共有count=nums.length - index个
        //4.如果count=target，说明此target即为特征值
        //5.如果count>target，说明特征值应该是个更大的数，遍历特征值的左边界l=mid+1；否则遍历特征值的右边界r=mid-1，再重复步骤1.
        //最后如果还没找到，就返回-1
        Arrays.sort(nums);
        int l = 1;
        int r = nums.length;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            int index = binarySearch(nums, mid);

            int count = nums.length - index;
            if (mid == count) {
                return count;
            } else if (mid < count) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }

        return -1;
    }

    public int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] >= target) {
                r = mid - 1;
            }else {
                l = mid + 1;
            }
        }
        return l;
    }
}
