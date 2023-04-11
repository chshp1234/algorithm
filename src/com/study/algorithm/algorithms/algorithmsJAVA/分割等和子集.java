package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

public class 分割等和子集 {

    @Test
    public void 分割等和子集() {
        int[] graph = {1, 2, 7, 6};
        System.out.println("分割等和子集:" + canPartition(graph));
    }

    // 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
    //
    // 注意:
    //
    // 每个数组中的元素不会超过 100
    // 数组的大小不会超过 200
    // 示例 1:
    //
    // 输入: [1, 5, 11, 5]
    //
    // 输出: true
    //
    // 解释: 数组可以分割成 [1, 5, 5] 和 [11].
    //
    //
    // 示例 2:
    //
    // 输入: [1, 2, 3, 5]
    //
    // 输出: false
    //
    // 解释: 数组不能分割成两个元素和相等的子集.
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean canPartitionByBacktrack(int[] nums) {

        // 思路一：回溯，dfs（超时）
        // 先计算数组总和，若总和为奇数，则返回fasle（不可能有两个相同的子集合），计算总和的一半
        // 排序，从小打到依次选取每个数字，并计算剩余的值（总和减去当前数字，之后总和更新为当前值），若剩余值小于当前数组最小值，则无法继续选取，返回false，
        // 回溯，跳过上一个选取的值到下一个元素，继续选取并计算（同上，因为已排序，此时可对重复数字进行跳过），
        // 若计算后的剩余值为0，说明到此选取的数字等于总和的一半，则返回true。

        // 排序，方便后面剪枝
        Arrays.sort(nums);

        int s = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            s += nums[i];
        }

        // 若为奇数，则返回false
        if (s % 2 != 0) {
            return false;
        }

        s = s / 2;

        return backtrack(nums, len, s, 0);
    }

    private boolean backtrack(int[] nums, int len, int sum, int index) {

        // 若当前剩余值比后续最小值还小，则无法选取，返回false
        if (index >= len || sum < nums[index]) {
            return false;
        }

        for (int i = index; i < len; i++) {
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            int temp = sum - nums[i];

            // 若剩余值等于0，说明到此选取的数字子集的总和等于总和的一半
            if (temp == 0) {
                return true;
            }
            if (backtrack(nums, len, temp, i + 1)) {
                return true;
            }
        }
        return false;
    }

    public boolean canPartition(int[] nums) {

        // 思路与算法
        //
        // 这道题可以换一种表述：
        // 给定一个只包含正整数的非空数组nums[0]，判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半。
        // 因此这个问题可以转换成「0−1背包问题」。
        // 这道题与传统的「0−1 背包问题」的区别在于，传统的「0−1 背包问题」要求选取的物品的重量之和不能超过背包的总容量，
        // 这道题则要求选取的数字的和恰好等于整个数组的元素和的一半。类似于传统的「0−1 背包问题」，可以使用动态规划求解。
        //
        // 在使用动态规划求解之前，首先需要进行以下判断。
        //
        // 根据数组的长度 n 判断数组是否可以被划分。如果 n<2，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false。
        //
        // 计算整个数组的元素和 sum 以及最大元素 maxNum。如果 sum是奇数，则不可能将数组分割成元素和相等的两个子集，因此直接返回 false。
        // 如果 sum 是偶数，则令target=sum/2，需要判断是否可以从数组中选出一些数字，使得这些数字的和等于 target。
        // 如果maxNum>target，则除了 maxNum 以外的所有元素之和一定小于target，因此不可能将数组分割成元素和相等的两个子集，直接返回 false。
        //
        // 创建二维数组 dp，包含 n 行 target+1 列，其中 dp[i][j]表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），
        // 是否存在一种选取方案使得被选取的正整数的和等于 j。初始时，dp中的全部元素都是 false。
        //
        // 在定义状态之后，需要考虑边界情况。以下两种情况都属于边界情况。
        //
        // 如果不选取任何正整数，则被选取的正整数等于 0。因此对于所有 0≤i<n，都有dp[i][0]=true。
        //
        // 当 i==0 时，只有一个正整数 nums[0] 可以被选取，因此dp[0][nums[0]]=true。
        //
        // 对于 i>0 且 j>0 的情况，如何确定 dp[i][j] 的值？需要分别考虑以下两种情况。
        //
        // 如果 j≥nums[i]，则对于当前的数字 nums[i]，可以选取也可以不选取，两种情况只要有一个为true，就有 dp[i][j]=true。
        //
        // 如果不选取 nums[i]，则 dp[i][j]=dp[i−1][j]；
        // 如果选取 nums[i]，则 dp[i][j]=dp[i−1][j−nums[i]]。
        // 如果 j<nums[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 nums[i]，因此有 dp[i][j]=dp[i−1][j]。
        //
        // 状态转移方程如下：
        //
        // dp[i][j]=
        // {
        //   dp[i−1][j] ∣ dp[i−1][j−nums[i]],j≥nums[i]
        //   dp[i−1][j],j<nums[i]
        // }
        //
        // 最终得到 dp[n−1][target] 即为答案。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        int s = 0;
        int len = nums.length;

        int maxNum = 0;
        for (int i = 0; i < len; i++) {
            s += nums[i];
            maxNum = Math.max(maxNum, nums[i]);
        }

        if (s % 2 != 0) {
            return false;
        }

        s = s / 2;

        if (s < maxNum) {
            return false;
        }

        boolean[][] dp = new boolean[len][s + 1];
        dp[0][nums[0]] = true;
        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }

        for (int j = 1; j <= s; j++) {
            for (int i = 1; i < len; i++) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[len - 1][s];
    }
}
