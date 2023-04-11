package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 所有奇数长度子数组的和 {
    @Test
    public void 所有奇数长度子数组的和() {
        System.out.println("所有奇数长度子数组的和：" + sumOddLengthSubarrays(new int[]{1, 4, 2, 5, 3}));
    }

    // 给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
    //
    // 子数组 定义为原数组中的一个连续子序列。
    //
    // 请你返回 arr 中 所有奇数长度子数组的和 。
    //
    //
    //
    // 示例 1：
    //
    // 输入：arr = [1,4,2,5,3]
    // 输出：58
    // 解释：所有奇数长度子数组和它们的和为：
    // [1] = 1
    // [4] = 4
    // [2] = 2
    // [5] = 5
    // [3] = 3
    // [1,4,2] = 7
    // [4,2,5] = 11
    // [2,5,3] = 10
    // [1,4,2,5,3] = 15
    // 我们将所有值求和得到 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
    // 示例 2：
    //
    // 输入：arr = [1,2]
    // 输出：3
    // 解释：总共只有 2 个长度为奇数的子数组，[1] 和 [2]。它们的和为 3 。
    // 示例 3：
    //
    // 输入：arr = [10,11,12]
    // 输出：66
    //
    //
    // 提示：
    //
    // 1 <= arr.length <= 100
    // 1 <= arr[i] <= 1000
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/sum-of-all-odd-length-subarrays
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int sumOddLengthSubarrays(int[] arr) {

        // 前缀和
        // 根据前缀和可以快速计算1个元素、3个元素、5个元素...为一个子序列时的序列和
        int len = arr.length;

        int[] prefixSum = new int[len + 1];

        int result = arr[0];
        prefixSum[1] = arr[0];

        // 遍历统计前缀和时，更新以下标i为结尾时，子数组为奇数的子序列和
        for (int i = 1; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + arr[i];
            result += arr[i];
            for (int j = i - 2; j >= 0; j = j - 2) {
                result += prefixSum[i + 1] - prefixSum[j];
            }
        }

//        int result = prefixSum[len];
//
//        for (int i = 3; i <= len; i = i + 2) {
//            for (int j = 0, jl = len - i; j <= jl; j++) {
//                result += prefixSum[j + i] - prefixSum[j];
//            }
//        }

        return result;

        // 方法二：
        // 想要连续子数组大小为奇数：
        // 那么算上当前元素，元素左边奇数个元素，元素右边奇数个元素（奇数+奇数+1=奇数）
        // 那么算上当前元素，元素左边偶数个元素，元素右边偶数个元素（偶数+偶数+1=奇数）
        // 所以只需一层循环遍历，分别计算出当前元素左右各有多少个元素，再算出能组合成多少个奇数/奇数、偶数/偶数对，为当前元素就为在整个结果计算中出现的次数
        /*int res = 0;
        int lEven,lOdd,rEven,rOdd;
        for(int i=0;i<arrSize;i++){
            //左边奇数
            lOdd = (i+1)/2;
            //左边偶数
            lEven = i/2 + 1;
            //右边奇数
            rOdd = (arrSize-i)/2;
            //右边偶数
            rEven = (arrSize-i+1)/2;
            //结果=（左边奇数*右边奇数+左边偶数*右边偶数）*当前元素值
            res += (lOdd*rOdd + lEven*rEven) * arr[i];
        }
        return res;*/
    }
}
