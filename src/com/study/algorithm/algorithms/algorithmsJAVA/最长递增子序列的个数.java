package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个未排序的整数数组，找到最长递增子序列的个数。
//
//示例 1:
//
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
//示例 2:
//
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
//注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最长递增子序列的个数 {
    @Test
    public void 最长递增子序列的个数() {
        int[] ints = {4, 3, 2, 1};
        System.out.println("最长递增子序列的个数:" + findNumberOfLIS(ints));
    }

    public int findNumberOfLIS(int[] nums) {
        //动态规划

        //定义状态dp[i][0]表示以nums[i]为结尾（必须），最长递增子序列的长度；dp[i][1]表示，在上一个结论下的长度的子序列的数量
        //定义好状态后，我们就可以得到以每个元素为结尾下的最长子序列的长度，那么找到所有长度中最长的那个maxLen，那么答案就为所有满足
        //子序列长度为maxLen的子序列的数量dp[i][1]的和。

        //其中，因为要以nums[i]为结尾，那么num[i]必须大于num[0..i]中的数num[j]，才能由dp[j]+1转移过来。
        //又因为num[j]在范围[0..i]中，所以不止一个数，那么我们计算最长子序列长度dp[i][0]时，就需要判断当前已经赋值的长度和dp[j][0]的子序列长度的大小了。


        int len = nums.length;
        int[][] dp = new int[len][2];

        //初始化，考虑以i为结尾的长度和数量都为1，这里没对长度进行赋值，原因在于为方便计算，每次遍历j结束时，都对dp[i][0]进行了+1的操作
        dp[0][0] = 1;
        for (int i = 0; i < len; i++) {
            dp[i][1] = 1;
        }

        //遍历计算dp[i]
        for (int i = 1; i < len; i++) {

            //遍历[0..i]
            for (int j = 0; j < i; j++) {

                //只有当nums[i] > nums[j]时，才有必要更新答案
                if (nums[i] > nums[j]) {

                    if (dp[j][0] > dp[i][0]) {
                        //如果dp[j]的长度更长，重置dp[i]为dp[j]的值
                        dp[i][0] = dp[j][0];
                        dp[i][1] = dp[j][1];
                    } else if (dp[j][0] == dp[i][0]) {
                        //如果dp[j][0]==dp[i][0]，那么说明两个子序列长度相同，那么此长度下的子序列的数量将进行叠加更新
                        dp[i][1] += dp[j][1];
                    }
                }
            }

            //为了方便计算，其实上面遍历更新dp[i][0]时没进行+1操作，在此进行统一操作
            dp[i][0]++;
        }
        int maxLen = 1;
        int count = 1;
        //寻找最长子序列长度maxLen，以及此长度下的子序列的数量总和
        for (int i = 1; i < len; i++) {
            if (dp[i][0] > maxLen) {
                maxLen = dp[i][0];
                count = dp[i][1];
            } else if (dp[i][0] == maxLen) {
                count += dp[i][1];
            }
        }
        return count;
    }
}
