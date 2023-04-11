package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

@Deprecated
public class 和至少为K的最短子数组 {
    @Test
    public void 和至少为K的最短子数组() {

        System.out.println(
                "和至少为K的最短子数组："
                        + shortestSubarray(
                                new int[] {44, -25, 75, -50, -38, -42, -32, -6, -40, -47}, 19));
    }
    /**
     * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
     *
     * <p>如果没有和至少为 K 的非空子数组，返回 -1 。
     *
     * <p>
     *
     * <p>示例 1：
     *
     * <p>输入：A = [1], K = 1 输出：1 示例 2：
     *
     * <p>输入：A = [1,2], K = 4 输出：-1 示例 3：
     *
     * <p>输入：A = [2,-1,2], K = 3 输出：3
     *
     * <p>提示：
     *
     * <p>1 <= A.length <= 50000 -10 ^ 5 <= A[i] <= 10 ^ 5 1 <= K <= 10 ^ 9
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    /*public int shortestSubarray(int[] A, int K) {

        int[] SUM = new int[A.length + 1];

        SUM[0] = 0;
        SUM[1] = A[0];
        for (int i = 0, l = A.length; i < l; i++) {
            SUM[i + 1] = SUM[i] + A[i];
        }

        int low = 1, high = A.length;

        int result = -1;

        Loop:
        for (int length = low + ((high - low) >> 1); low <= high; ) {
            for (int index = 0, ll = A.length - length + 1; index < ll; index++) {
                if (SUM[length + index] - SUM[index] >= K) {
                    high = length - 1;
                    result = length;
                    length = low + ((high - low) >> 1);
                    continue Loop;
                }
            }
            low = length + 1;
            length = low + ((high - low) >> 1);
        }
        return result;
    }*/

    /*todo 超时，继续优化*/
    public int shortestSubarray(int[] A, int K) {
        int[] B = new int[A.length];
        for (int length = 1, l = A.length; length <= l; length++) {
            for (int index = 0, ll = A.length - length + 1; index < ll; index++) {
                int sum = 0;
                sum += B[index] + A[index + length - 1];
                if (sum >= K) {
                    return length;
                }
                B[index] = sum;
            }
        }
        return -1;
    }

    public int shortestSubarrayByOfficial(int[] A, int K) {
        int N = A.length;
        long[] P = new long[N + 1];
        for (int i = 0; i < N; ++i) P[i + 1] = P[i] + (long) A[i];

        // Want smallest y-x with P[y] - P[x] >= K
        int ans = N + 1; // N+1 is impossible
        Deque<Integer> monoq = new LinkedList<>(); // opt(y) candidates, as indices of P

        for (int y = 0; y < P.length; ++y) {
            // Want opt(y) = largest x with P[x] <= P[y] - K;
            while (!monoq.isEmpty() && P[y] <= P[monoq.getLast()]) monoq.removeLast();
            while (!monoq.isEmpty() && P[y] >= P[monoq.getFirst()] + K)
                ans = Math.min(ans, y - monoq.removeFirst());

            monoq.addLast(y);
        }

        return ans < N + 1 ? ans : -1;
    }
}
