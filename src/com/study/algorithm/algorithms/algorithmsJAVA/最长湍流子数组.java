package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
//
//若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
//或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
//也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
//
//返回 A 的最大湍流子数组的长度。
//
// 
//
//示例 1：
//
//输入：[9,4,2,10,7,8,8,1,9]
//输出：5
//解释：(A[1] > A[2] < A[3] > A[4] < A[5])
//示例 2：
//
//输入：[4,8,12,16]
//输出：2
//示例 3：
//
//输入：[100]
//输出：1
// 
//
//提示：
//
//1 <= A.length <= 40000
//0 <= A[i] <= 10^9
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长湍流子数组 {
    @Test
    public void 最长湍流子数组() {
        int[] ints = {4, 4, 4};
        System.out.println("最长湍流子数组:" + maxTurbulenceSize(ints));
    }

    public int maxTurbulenceSize(int[] arr) {
        //滑动窗口
        //数组需保持“锯齿状”：
        //如果之前两个数是上升的，那么现在这两个数必须是下降的；
        //反之，上两个数若是下降的，这两个数必须是上升的；
        //同时，两个数不能相等，相等也会打破锯齿的形状。
        //我们用两个指针，代表这个锯齿子数组的起止位置。
        //每次遇到不符合条件时的两个数，则更新最大值，并更新起始指针位置。
        //需注意，当两个数相等时，起始指针位置要在当前尾指针位置；其他，则起始指针在尾指针的上一位开始（不符合条件时）。
        int len = arr.length;
        if (len < 2) {
            return 1;
        }
        int max = 1;
        int start = 0;
        int end = 1;
        //上一个锯齿状态，0代表平行；1代表上升；2代表下降。
        int lastState = 0;
        for (; end < len; end++) {
            //判断是否满足“数组锯齿状”（两两都需进行判断）
            if (arr[end] > arr[end - 1]) {
                if (lastState == 1) {
                    max = Math.max(max, end - start);
                    start = end - 1;
                } else if (lastState == 2) {
                    lastState = 1;
                } else {
                    start = end - 1;
                    lastState = 1;
                }
            } else if (arr[end] < arr[end - 1]) {
                if (lastState == 1) {
                    lastState = 2;
                } else if (lastState == 2) {
                    max = Math.max(max, end - start);
                    start = end - 1;
                } else {
                    start = end - 1;
                    lastState = 2;
                }
            } else {
                if (lastState != 0) {
                    max = Math.max(max, end - start);
                }
                start = end;
            }

        }
        return Math.max(max, end - start);
    }
}
