package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

public class 视频拼接 {

    @Test
    public void 视频拼接() {
        //        System.out.println(
        //                "视频拼接:"
        //                        + videoStitching(
        //                                new int[][] {{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5,
        // 9}}, 10));
        //        System.out.println("视频拼接:" + videoStitching(new int[][] {{0, 1}, {1, 2}}, 10));
        //        System.out.println("视频拼接:" + videoStitching(new int[][] {{0, 4}, {2, 8}}, 5));
        //        System.out.println("视频拼接:" + videoStitching(new int[][] {{0, 4}, {5, 8}, {6, 10}},
        // 10));
        System.out.println(
                "视频拼接:" + videoStitching(new int[][] {{0, 5}, {1, 3}, {4, 6}, {5, 7}, {5, 8}}, 8));
        //        System.out.println(
        //                "视频拼接:"
        //                        + videoStitching(
        //                                new int[][] {
        //                                    {0, 1},
        //                                    {6, 8},
        //                                    {0, 2},
        //                                    {5, 6},
        //                                    {0, 4},
        //                                    {0, 3},
        //                                    {6, 7},
        //                                    {1, 3},
        //                                    {4, 7},
        //                                    {1, 4},
        //                                    {2, 5},
        //                                    {2, 6},
        //                                    {3, 4},
        //                                    {4, 5},
        //                                    {5, 7},
        //                                    {6, 9}
        //                                },
        //                                9));
    }

    public int videoStitching(int[][] clips, int T) {
        // 动态规划（假的），我们令 dp[i] 表示将区间 [0,i) 覆盖所需的最少子区间的数量。
        // 首先对clips数组进行排序，根据clips[i][0]从小到大排序。

        // 遍历clips数组的过程中，维护一个boolean[] visit数组，代表dp[i]的值是否主动更新，
        // 当clips[i][0] == clips[i - 1][0]时，并且dp[i]的值是主动更新，也就是visit[i]==true时，
        // clips[i -1][1]~clips[i][1]之间的值可填充为dp[clips[i - 1][1]]。

        // 比如区间｛1，3｝计算出dp[3]=2,那么计算区间｛1，6｝时，可得出dp[4]=dp[5]=dp[6]=2。

        // 为什么需要visit数组进行进一步记录？
        // 因为若区间｛1，3｝的值dp[3]=1不是主动更新的，而是由于之前区间｛0，4｝计算的结果dp[4]=1得出来的，
        // 则后续的区间｛1，6｝需要重新进行计算，很明显此时计算结果为dp[5]=dp[6]=2。

        // 维护lastEnd，作为填充dp时最大的起始位置，
        // 还是比如{0, 4}, {1, 3}, {1, 6}, {4, 8}，
        // 当遍历到区间{1,6}，时，应该从5也就是dp[5]开始,上一个填充的结束位置为4，dp[5]=dp[6]=2。

        // 之后的状态转移方程即为dp[clips[i][1]]=min{dp[clips[i][0]]+1，dp[clips[i][1]]}

        // 特别注意超过dp数组大小的越界情况
        Arrays.sort(
                clips,
                (o1, o2) -> {
                    if (o1[0] == o2[0]) {
                        return o1[1] - o2[1];
                    }
                    return o1[0] - o2[0];
                });

        System.out.println("视频拼接:" + Arrays.deepToString(clips));

        if (clips[0][0] != 0) {
            return -1;
        }

        int[] dp = new int[T + 1];
        boolean[] visit = new boolean[T + 1];
        visit[0] = true;
        Arrays.fill(dp, 101);

        int lastEnd = Math.min(clips[0][1], T);

        Arrays.fill(dp, 0, lastEnd + 1, 1);

        for (int i = 1, len = clips.length; i < len; i++) {
            // 剪枝，若clips[i - 1][1] > clips[i][1]，比如{0, 4}, {1, 3},很明显{1,3}区间就不必进行计算了
            if (clips[i][0] > T || clips[i - 1][1] > clips[i][1]) {
                continue;
            }
            lastEnd = Math.max(lastEnd, Math.min(clips[i - 1][1], T));
            int toIndex = Math.min(clips[i][1], T);
            if (clips[i][0] == clips[i - 1][0] && visit[clips[i - 1][0]]) {
                Arrays.fill(dp, lastEnd + 1, toIndex + 1, dp[lastEnd]);
            } else {
                int pre = dp[clips[i][0]] + 1;
                if (pre < dp[toIndex]) {
                    visit[clips[i][0]] = true;
                    Arrays.fill(dp, lastEnd + 1, toIndex + 1, pre);
                }
            }
        }
        return dp[T] == 101 ? -1 : dp[T];
    }

    public int videoStitchingByDp(int[][] clips, int T) {
        // 思路及解法
        //
        // 比较容易想到的方法是动态规划，我们令 dp[i] 表示将区间 [0,i) 覆盖所需的最少子区间的数量。
        // 由于我们希望子区间的数目尽可能少，因此可以将所有 dp[i] 的初始值设为一个大整数，并将dp[0]（即空区间）的初始值设为 0。
        //
        // 我们可以枚举所有的子区间来依次计算出所有的 dp 值。我们首先枚举 i，同时对于任意一个子区间 [a_j,b_j)，
        // 若其满足j<i≤b_j，那么它就可以覆盖区间 [0,i) 的后半部分，而前半部分则可以用 dp[a_j] 对应的最优方法进行覆盖，
        // 因此我们可以用 dp[a_j] + 1来更新 dp[i]。状态转移方程如下：
        // dp[i]=min{dp[a_j]}+1   (a_j<i≤b_j)
        //
        // 最终的答案即为 dp[T]。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/video-stitching/solution/shi-pin-pin-jie-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            for (int[] clip : clips) {
                if (clip[0] < i && i <= clip[1]) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == Integer.MAX_VALUE - 1 ? -1 : dp[T];
    }

    public int videoStitchingByGreedy(int[][] clips, int T) {
        // 思路及解法
        //
        // 注意到对于所有左端点相同的子区间，其右端点越远越有利。且最佳方案中不可能出现两个左端点相同的子区间。
        // 于是我们预处理所有的子区间，对于每一个位置i，我们记录以其为左端点的子区间中最远的右端点，记为 maxn[i]。
        //
        // 我们可以参考「55. 跳跃游戏的官方题解」，使用贪心解决这道题。
        //
        // 具体地，我们枚举每一个位置，假设当枚举到位置 i 时，记左端点不大于 i 的所有子区间的最远右端点为 last。
        // 这样last 就代表了当前能覆盖到的最远的右端点。
        //
        // 每次我们枚举到一个新位置，我们都用 maxn[i] 来更新 last。
        // 如果更新后 last==i，那么说明下一个位置无法被覆盖，我们无法完成目标。
        //
        // 同时我们还需要记录上一个被使用的子区间的结束位置为 pre，每次我们越过一个被使用的子区间，就说明我们要启用一个新子区间，
        // 这个新子区间的结束位置即为当前的last。也就是说，每次我们遇到 i==pre，则说明我们用完了一个被使用的子区间。
        // 这种情况下我们让答案加1，并更新 pre 即可。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/video-stitching/solution/shi-pin-pin-jie-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int[] maxn = new int[T];
        int last = 0, ret = 0, pre = 0;
        for (int[] clip : clips) {
            if (clip[0] < T) {
                maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
            }
        }
        for (int i = 0; i < T; i++) {
            last = Math.max(last, maxn[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                ret++;
                pre = last;
            }
        }
        return ret;
    }
}
