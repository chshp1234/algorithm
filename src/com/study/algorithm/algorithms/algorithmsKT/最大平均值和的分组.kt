package com.study.algorithm.algorithms.algorithmsKT

//给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 分数 由每个子数组内的平均值的总和构成。
//
//注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
//
//返回我们所能得到的最大 分数 是多少。答案误差在 10-6 内被视为是正确的。
//
// 
//
//示例 1:
//
//输入: nums = [9,1,2,3,9], k = 3
//输出: 20.00000
//解释:
//nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
//我们也可以把 nums 分成[9, 1], [2], [3, 9].
//这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
//示例 2:
//
//输入: nums = [1,2,3,4,5,6,7], k = 4
//输出: 20.50000
// 
//
//提示:
//
//1 <= nums.length <= 100
//1 <= nums[i] <= 104
//1 <= k <= nums.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-sum-of-averages
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 最大平均值和的分组 {
    fun largestSumOfAverages(nums: IntArray, k: Int): Double {
        //前缀和+动态规划
        //首先使用一个前缀和数组preSum,方便后续计算区间子数组的和
        //定义状态dp[i][j]:当最大可分为i个区间时,以j为结尾的最大平均值
        //1.首先:
        //k=1时,那么整个数组就是一个区间,平均值就是总和/数量;
        //当k=nums.size时,可以分成nums.size个区间,那么每个单独的元素就是一个单独的区间,平均值就是总和
        //2.当i>1,以j为下标时的最大平均数为:
        //从i-1 到 j进行遍历:dp[i][j]=max(dp[i][j],dp[x-1]+(preSum[i + 1] - preSum[x]) / (i - x + 1)),
        //其中后面一个式子表示,在i-1个最大区间个数,当下标x到下标j为单独一个区间时,总共的平均值的和是多少,那么我们只要求出从i-1 到 j
        //每一个平均值的和,并更新其最大值,即可求出dp[i][j]的最大平均值之和
        //3.优化:由于每个dp[i]都只跟dp[i-1]相关,因此可以使用滚动数组,用一个数组来代表dp[i-1],以此来更新dp[i];
        //更新dp[i][j]时,因为里层的遍历是从i-1 到 j,因此更新遍历j时可以使用倒叙,从数组结尾往前遍历,这样数组后续更新的值不会对数组前面更新的值产生影响

        //当k=1或者k=nums.size时可以直接计算结果
        if (k == 1) {
            return (nums.sum() / nums.size.toDouble())
        }
        if (k == nums.size) {
            return nums.sum().toDouble()
        }

        val preSum = IntArray(nums.size + 1)
        for (i in nums.indices) {
            preSum[i + 1] = preSum[i] + nums[i]
        }

        val dp = DoubleArray(nums.size) {
            preSum[it + 1] / (it + 1).toDouble()
        }

        for (r in 2..k) {
            //倒序更新,数组后续更新的值不会对前面值的计算产生影响
            for (i in nums.size - 1 downTo r - 1) {
                for (c in r - 1..i) {
                    //以c..i为一个单独区间时,平均值之和为dp[c - 1] + (preSum[i + 1] - preSum[c]) / (i - c + 1).toDouble()
                    dp[i] =
                        Math.max(dp[i], dp[c - 1] + ((preSum[i + 1] - preSum[c]) / (i - c + 1).toDouble()))
                }
            }
        }
        return dp[nums.size - 1]
    }
}