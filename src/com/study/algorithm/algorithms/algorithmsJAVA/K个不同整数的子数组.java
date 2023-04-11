package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
//
//（例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
//
//返回 A 中好子数组的数目。
//
// 
//
//示例 1：
//
//输入：A = [1,2,1,2,3], K = 2
//输出：7
//解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
//示例 2：
//
//输入：A = [1,2,1,3,4], K = 3
//输出：3
//解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
// 
//
//提示：
//
//1 <= A.length <= 20000
//1 <= A[i] <= A.length
//1 <= K <= A.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/subarrays-with-k-different-integers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class K个不同整数的子数组 {

    @Test
    public void K个不同整数的子数组() {
        int[] ints = {
                27, 27, 43, 28, 11, 20, 1, 4, 49, 18, 37, 31, 31, 7, 3, 31, 50, 6, 50, 46, 4, 13,
                31, 49, 15, 52, 25, 31, 35, 4, 11, 50, 40, 1, 49, 14, 46, 16, 11, 16, 39, 26, 13, 4,
                37, 39, 46, 27, 49, 39, 49, 50, 37, 9, 30, 45, 51, 47, 18, 49, 24, 24, 46, 47, 18,
                46, 52, 47, 50, 4, 39, 22, 50, 40, 3, 52, 24, 50, 38, 30, 14, 12, 1, 5, 52, 44, 3,
                49, 45, 37, 40, 35, 50, 50, 23, 32, 1, 2
        };
        System.out.println("K个不同整数的子数组:" + subarraysWithKDistinct(ints, 20));
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        //滑动窗口
        //首先因为1 <= A[i] <= A.length，我们可以用一个int[len+1]大小的数组counter来对数组中每个数出现的数字进行计数。
        //遍历过程中，只需对counter进行计数，并且，当counter[i]=0时，说明此数第一次加入计数，说明在子数组中遇到一个新数。
        //每当我们遇到一个元素，使得子数组内元素数量count为K时，就对结果进行统计：
        //起始点开始向右移动，每次移动计数器counter中此数数量减一，结果加一；
        //当counter[i]=0时，说明此区间count数将减一；
        //最后我们还需将此过程从计数中减去的数重新加回来，同起始点也要移回原来的位置。
        //因为在后续的遍历计数中，任然有可能此区间有效，所以起始点不能直接向右移动。
        //只有当count>k时，说明此子区间已不符合条件，起始点才可向右移动，直到count==k（再重复上述计算步骤）。

        int len = A.length;
        int[] counter = new int[len + 1];
        int start = 0;
        int count = 0;
        int result = 0;
        for (int i = 0; i < len; i++) {
            if (counter[A[i]]++ == 0) {
                count++;
            }
            while (count > K) {
                if (--counter[A[start]] == 0) {
                    count--;
                }
                start++;
            }
            if (count == K) {
                int index = start;
                while (true) {
                    result++;
                    if (--counter[A[index++]] == 0) {
                        break;
                    }
                }
                //将此过程从计数中减去的数重新加回来
                for (int j = start; j < index; j++) {
                    counter[A[j]]++;
                }
            }
        }

        return result;
    }
}
