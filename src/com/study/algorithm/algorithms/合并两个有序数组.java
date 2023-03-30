package com.study.algorithm.algorithms;

//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
//
//初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
//
// 
//
//示例 1：
//
//输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
//输出：[1,2,2,3,5,6]
//示例 2：
//
//输入：nums1 = [1], m = 1, nums2 = [], n = 0
//输出：[1]
// 
//
//提示：
//
//nums1.length == m + n
//nums2.length == n
//0 <= m, n <= 200
//1 <= m + n <= 200
//-109 <= nums1[i], nums2[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/merge-sorted-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 合并两个有序数组 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //双指针+逆向遍历
        //由于连个数组都是有序递增的，所以依次比较两个数组尾指针的元素（在num1中从m下标开始）
        //将大的元素添加到num1结尾处，并将该数组的指针往前移一位，直到某一个指针遍历到数组头部为止
        //若第一轮遍历结束，n的值>=0，说明此时num2中的元素都是小于num1中等元素，则继续把num2中剩余的元素依次添加到num1中
        if (nums1 == null || nums2 == null || n == 0) {
            return;
        }

        int index = nums1.length - 1;
        n--;
        m--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] > nums2[n]) {
                nums1[index--] = nums1[m--];
            } else {
                nums1[index--] = nums2[n--];
            }
        }

        while (n >= 0) {
            nums1[index--] = nums2[n--];
        }

    }
}
