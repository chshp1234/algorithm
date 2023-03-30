package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

//给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
//
//返回仅包含 1 的最长（连续）子数组的长度。
//
// 
//
//示例 1：
//
//输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
//输出：6
//解释：
//[1,1,1,0,0,1,1,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 6。
//示例 2：
//
//输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
//输出：10
//解释：
//[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//粗体数字从 0 翻转到 1，最长的子数组长度为 10。
// 
//
//提示：
//
//1 <= A.length <= 20000
//0 <= K <= A.length
//A[i] 为 0 或 1 
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/max-consecutive-ones-iii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大连续1的个数III {
    @Test
    public void 最大连续1的个数III() {
        int[] ints = {0, 0, 0, 1, 0, 0, 1};
        System.out.println("最大连续1的个数III:" + longestOnesByQueue(ints, 2));
    }

    public int longestOnes(int[] A, int K) {

        //滑动窗口
        //保持窗口最大化
        //每次r指针右移，若遇到0，说明窗口中0的数量count多了一个，则记录0的个数count+1；
        //若count的数量大于k，则说明此时窗口中0的个数超出了限制，令l指针右移，若l指针处是0，说明窗口中0的数量少了一个，则count-1；
        //我们不必一直右移l指针，此窗口是最大连续子串的窗口，
        //所以窗口右移的过程中，窗口中的子串也许并不符合条件，但不改变最大子串的结果。
        int len = A.length;
        int zeroCount = 0;
        int left = 0, right = 0;

        while (right < len) {
            if (A[right] == 0) {
                zeroCount++;
            }
            if (zeroCount > K) {
                if (A[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            right++;
        }

        return right - left;
    }

    public int longestOnesByQueue(int[] A, int K) {

        //滑动窗口，队列
        //用队列记录窗口中每个‘0’的下标位置；
        //在右指针右移的过程中，若窗口中0的数量大于k，更新最大值result，
        //并从队列中弹出下一个0的下标位置i，令左指针l=i+1，表示减去一个0。

        //注意，遍历到数组头部时还需更新一次最大值。
        int len = A.length;
        Deque<Integer> deque = new LinkedList<>();
        int result = 0, left = 0;
        for (int i = 0; i < len; i++) {
            if (A[i] == 0) {
                K--;
                deque.offer(i);
            }
            if (K < 0) {
                result = Math.max(i - left, result);
                left = deque.poll() + 1;
                K++;
            }
        }

        return Math.max(result, len - left);
    }
}
