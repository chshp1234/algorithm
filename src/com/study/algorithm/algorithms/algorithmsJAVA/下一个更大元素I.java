package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.*;

//给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
//
//请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
//
//nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
//
// 
//
//示例 1:
//
//输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
//输出: [-1,3,-1]
//解释:
//    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
//    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
//    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
//示例 2:
//
//输入: nums1 = [2,4], nums2 = [1,2,3,4].
//输出: [3,-1]
//解释:
//    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
//    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
// 
//
//提示：
//
//1 <= nums1.length <= nums2.length <= 1000
//0 <= nums1[i], nums2[i] <= 104
//nums1和nums2中所有整数 互不相同
//nums1 中的所有整数同样出现在 nums2 中
// 
//
//进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/next-greater-element-i
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 下一个更大元素I {

    @Test
    public void 下一个更大元素I() {

        System.out.println("下一个更大元素I：" + Arrays.toString(nextGreaterElement(
                new int[]{137, 59, 92, 122, 52, 131, 79, 236, 94},
                new int[]{137, 59, 92, 122, 52, 131, 79, 236, 94})));
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        //哈希表+单调栈
        //因为无重复元素，所以先用哈希表记录num1中所有元素及其位置
        //用单调递减栈维护num2中的元素
        //当遍历的到元素小于栈顶元素top时，将该元素加入栈中
        //当遍历到元素大于栈顶元素时，弹出栈顶元素top，并判断top是否在哈希表中，若存在，则取出位置，更新num1中对应位置的值为top
        //即可找出第一个大于该元素top的值


        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, l = nums1.length; i < l; i++) {
            map.put(nums1[i], i);
            nums1[i] = -1;
        }
        Deque<Integer> stack = new LinkedList<>();
        int top = nums2[0];
        for (int i = 1, l = nums2.length; i < l; i++) {
            if (nums2[i] < top) {
                stack.push(top);
            } else {
                Integer position = map.get(top);
                if (position != null) {
                    nums1[position] = nums2[i];
                }
                while (!stack.isEmpty()) {
                    top = stack.pop();
                    if (nums2[i] > top) {
                        position = map.get(top);
                        if (position != null) {
                            nums1[position] = nums2[i];
                        }
                    } else {
                        stack.push(top);
                        break;
                    }
                }
            }
            top = nums2[i];
        }

        return nums1;
    }
}
