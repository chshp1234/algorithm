package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

//如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
//
//n >= 3
//对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
//给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
//
//（回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
//
// 
//
//示例 1：
//
//输入: arr = [1,2,3,4,5,6,7,8]
//输出: 5
//解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
//示例 2：
//
//输入: arr = [1,3,7,11,12,14,18]
//输出: 3
//解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
// 
//
//提示：
//
//3 <= arr.length <= 1000
//1 <= arr[i] < arr[i + 1] <= 10^9
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长的斐波那契子序列的长度 {
    @Test
    public void 最长的斐波那契子序列的长度() {
        System.out.println("最长的斐波那契子序列的长度:" + lenLongestFibSubseq(
                new int[]{1, 2, 3, 4, 5, 6, 7}));
    }

    public int lenLongestFibSubseq(int[] arr) {
        //哈希表
        //1.用哈希表记录每一个数
        //2.两层遍历，依照斐波那契数列性质，寻找两数之和的下一个数，是否在哈希表中，若存在，更新两数，继续判断
        //3.遍历时维护最长序列
        Set<Integer> dp = new HashSet<>();
        for (int i : arr) {
            dp.add(i);
        }

        int result = 0;

        int o;
        int t;
        int count;
        int temp;
        int max;
        for (int i = 1; i < arr.length; i++) {
            max = 0;
            for (int j = 0; j < i; j++) {
                o = arr[j];
                t = arr[i];
                count = 0;
                while (dp.contains(o + t)) {
                    count++;
                    temp = o;
                    o = t;
                    t = temp + t;
                }
                max = Math.max(max, count);
            }
            if (max > 0) {
                max += 2;
            }
            result = Math.max(result, max);
        }

        return result >= 3 ? result : 0;
    }
}
