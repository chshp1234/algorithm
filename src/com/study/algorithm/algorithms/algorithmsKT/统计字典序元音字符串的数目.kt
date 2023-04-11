package com.study.algorithm.algorithms.algorithmsKT

class 统计字典序元音字符串的数目 {
    fun countVowelStrings(n: Int): Int {
        //动态规划
        //设dp[i][0..4]代表i个数时,以a/e/i/o/u为开头时的字符串的数量
        //那么,有i个字符时,总的元音字符串的数量就为dp[i][0]+dp[i][1]+dp[i][2]+dp[i][3]+dp[i][4]
        //因为字符串需要按字典序排列,所以,以a开头的字符串后面可以跟a/e/i/o/u开头的字符串,并且后续的子串的数量为i-1
        //同理,以e开头的字符串后面可以跟上e/i/o/u,子串数量也为i-1
        //那么
        //dp[i][0]=dp[i-1][0]+dp[i-1][1]+dp[i-1][2]+dp[i-1][3]+dp[i-1][4]
        //dp[i][1]=dp[i-1][1]+dp[i-1][2]+dp[i-1][3]+dp[i-1][4]
        //dp[i][2]=dp[i-1][2]+dp[i-1][3]+dp[i-1][4]
        //dp[i][3]=dp[i-1][3]+dp[i-1][4]
        //dp[i][4]=dp[i-1][4]
        //由于dp[i]只跟dp[i-1]有关,这里可以使用滚动数组进行优化
        val dp = intArrayOf(1, 1, 1, 1, 1)

        for (k in 2..n) {
            for (i in 1 until 5) {
                dp[i] += dp[i - 1]
            }
        }

        return dp.sum()
    }
}