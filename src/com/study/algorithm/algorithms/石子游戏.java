package com.study.algorithm.algorithms;

public class 石子游戏 {

    public int[][] dp;

    public boolean stoneGame(int[] nums) {
        // 动态规划解：定义方程dp[l][h],表示数组区间l~h中，第一个获取的人可获得的最大分数
        // dp[l][h]=Math.max(dp[l+1][h]+nums[l],dp[l][h-1]+nums[h])（不准确，还得根据总和进行计算）
        // *todo*/ 可优化，滚动数组

        int len = nums.length;

        if (len < 2) {
            return true;
        }

        dp = new int[len][len];

        // 数组前缀和
        int[] sum = new int[len + 1];

        sum[1] = nums[0];
        dp[0][0] = nums[0];
        for (int i = 1; i < len; i++) {
            // 初始化数组前缀和
            sum[i + 1] = sum[i] + nums[i];

            // 初始化dp
            dp[i][i] = nums[i];
        }

        // 获取区间0~len的最大值
        int maxResult = getMaxResult(nums, 0, len - 1, sum);

        return maxResult >= (sum[len] - maxResult);
    }

    public int getMaxResult(int[] nums, int low, int high, int[] sum) {

        // 边界条件
        if (low >= nums.length) {
            return 0;
        }

        // 若已有记录，则直接获取
        if (dp[low][high] != 0) {
            return dp[low][high];
        }

        // 若高低位相等，则返回当前元素
        if (low == high) {
            return nums[low];
        }

        // 当选左边元素时，剩余元素中可获得的最大值
        int L = getMaxResult(nums, low + 1, high, sum);

        // 当选右边元素时，剩余元素中可获得的最大值
        int R = getMaxResult(nums, low, high - 1, sum);

        // 当前区间最大值，为下一个区间的元素和-下一个区间的最大值+当前选择元素值
        int i = sum[high + 1] - sum[low + 1] - L + nums[low];
        int j = sum[high] - sum[low] - R + nums[high];

        // 选择最大值记录并返回
        if (i > j) {
            dp[low][high] = i;
            return i;
        } else {
            dp[low][high] = j;
            return j;
        }
    }

    //方法二：数学
    //假设有 n 堆石子，n 是偶数，则每堆石子的下标从 0 到 n−1。
    //根据下标将 n 堆石子分成两组，每组有 n/2堆石子，下标为偶数的石子堆属于第一组，下标为奇数的石子堆属于第二组。
    //
    //初始时，行的开始处的石子堆位于下标 0，属于第一组，行的结束处的石子堆位于下标 n−1，属于第二组，
    //因此作为先手的 Alex 可以自由选择取走第一组的一堆石子或者第二组的一堆石子。
    //如果 Alex 取走第一组的一堆石子，则剩下的部分在行的开始处和结束处的石子堆都属于第二组，因此 Lee 只能取走第二组的一堆石子。
    //如果 Alex 取走第二组的一堆石子，则剩下的部分在行的开始处和结束处的石子堆都属于第一组，因此 Lee 只能取走第一组的一堆石子。
    //无论 Lee 取走的是开始处还是结束处的一堆石子，剩下的部分在行的开始处和结束处的石子堆一定是属于不同组的，
    //因此轮到 Alex 取走石子时，Alex 又可以在两组石子之间进行自由选择。
    //
    //根据上述分析可知，作为先手的 Alex 可以在第一次取走石子时就决定取走哪一组的石子，并全程取走同一组的石子。既然如此，Alex 是否有必胜策略？
    //
    //答案是肯定的。将石子分成两组之后，可以计算出每一组的石子数量，同时知道哪一组的石子数量更多。Alex 只要选择取走数量更多的一组石子即可。
    // 因此，Alex 总是可以赢得比赛。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/stone-game/solution/shi-zi-you-xi-by-leetcode-solution/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public boolean stoneGameByLeetCode(int[] piles) {
        return true;
    }
}
