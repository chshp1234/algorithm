package com.study.algorithm.algorithmsKT

//爱丽丝参与一个大致基于纸牌游戏 “21点” 规则的游戏，描述如下：
//
//爱丽丝以 0 分开始，并在她的得分少于 k 分时抽取数字。 抽取时，她从 [1, maxPts] 的范围中随机获得一个整数作为分数进行累计，其中 maxPts 是一个整数。 每次抽取都是独立的，其结果具有相同的概率。
//
//当爱丽丝获得 k 分 或更多分 时，她就停止抽取数字。
//
//爱丽丝的分数不超过 n 的概率是多少？
//
//与实际答案误差不超过 10-5 的答案将被视为正确答案。
//
// 
//示例 1：
//
//输入：n = 10, k = 1, maxPts = 10
//输出：1.00000
//解释：爱丽丝得到一张牌，然后停止。
//示例 2：
//
//输入：n = 6, k = 1, maxPts = 10
//输出：0.60000
//解释：爱丽丝得到一张牌，然后停止。 在 10 种可能性中的 6 种情况下，她的得分不超过 6 分。
//示例 3：
//
//输入：n = 21, k = 17, maxPts = 10
//输出：0.73278
// 
//
//提示：
//
//0 <= k <= n <= 104
//1 <= maxPts <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/new-21-game
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 新21点 {
    fun new21Game(n: Int, k: Int, maxPts: Int): Double {
        //动态规划(超出内存限制)
        //设dp[i][j]:k=i,n=j时,答案的概率
        //dp[i][j]=:
        // 如果i>=Math.min(j, maxPts),+dp[i-0..Math.min(j, maxPts)][j-Math.min(j, maxPts)] * rate
        // 否则,+rate
        if (k == 0) {
            return 1.0
        }
        val singleRate: Double = (1.0 / maxPts)
        val dp = Array(k) {
            DoubleArray(n)
        }

        for (i in 0 until k) {
            for (j in i until n) {
                val max = Math.min(j + 1, maxPts)
                for (l in 1..max) {
                    if (i - l >= 0) {
                        dp[i][j] += dp[i - l][j - l] * singleRate
                    } else {
                        dp[i][j] += singleRate
                    }

                    if (dp[i][j] >= 1.0) {
                        dp[i][j] = 1.0
                    }
                }
            }
        }

        return dp[k - 1][n - 1]
    }

    fun new21Game2(n: Int, k: Int, maxPts: Int): Double {
        //动态规划,规律
        //设dp[i]为得分不超过i时的概率，k<=i<=n
        //根据表格规律可得dp[i]=dp[i-1]*rate+dp[i-2]*rate+...dp[i-count]*rate
        //其中count=Math.min(i, maxPts)，表示可以抽的牌数
        //也就是dp[i]的值根据上一次计算的值*每一张牌的概率,并且一直累加到Math.min(i, maxPts)(表示因为最多只能抽几张牌)
        if (k == 0) {
            return 1.0
        }

        val difference = n - k
        val rate = 1.0 / maxPts
        val dp = DoubleArray(n + 1)
        for (i in 0..difference) {
            dp[i] = 1.0
        }

        for (i in 1..k) {
            val end = difference + i
            for (j in 1..Math.min(end, maxPts)) {
                dp[end] += dp[end - j] * rate
            }
        }

        return dp[n]
    }
}