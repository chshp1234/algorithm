package com.study.algorithm.algorithms.algorithmsKT

//给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。s 中可能会有一些 额外的字符 不在任何子字符串中。
//
//请你采取最优策略分割 s ，使剩下的字符 最少 。
//
//
//
//示例 1：
//
//输入：s = "leetscode", dictionary = ["leet","code","leetcode"]
//输出：1
//解释：将 s 分成两个子字符串：下标从 0 到 3 的 "leet" 和下标从 5 到 8 的 "code" 。只有 1 个字符没有使用（下标为 4），所以我们返回 1 。
//示例 2：
//
//输入：s = "sayhelloworld", dictionary = ["hello","world"]
//输出：3
//解释：将 s 分成两个子字符串：下标从 3 到 7 的 "hello" 和下标从 8 到 12 的 "world" 。下标为 0 ，1 和 2 的字符没有使用，所以我们返回 3 。
//
//
//提示：
//
//1 <= s.length <= 50
//1 <= dictionary.length <= 50
//1 <= dictionary[i].length <= 50
//dictionary[i] 和 s 只包含小写英文字母。
//dictionary 中的单词互不相同。
class 字符串中的额外字符 {

    //动态规划，哈希表
    //为方便判断字符子串是否在字典中，先将字典中的字符串加入集合中。
    //设dp[i]，代表以下标为结尾时，最少的额外字符数
    //那么我们在遍历下标i时，再从0..i遍历区间j，分割字符子串s[j..i]，当子串s[j..i]
    //1.存在于字典中时，说明可以将字符串s可以从j到i进行分割，dp[i] = Math.min(dp[i], dp[j])
    //2.不存在于字典中，说明j到i的子串只能当作额外字符，那么dp[i] = Math.min(dp[i], dp[j] + (i - j))
    //遍历完j时，即可得到以i为结尾时，最少的额外字符数
    //那么最后只要返回dp[s.len]即可得到答案
    //初始化dp数组长度为s.length+1，初始化元素为s.length，代表一开始所有字符都是额外字符，s[0]=0，代表空字符的额外数量为0。
    fun minExtraChar(s: String, dictionary: Array<String>): Int {
        val dp = IntArray(s.length + 1) {
            s.length
        }
        dp[0] = 0
        val map = dictionary.toSet()
        for (i in 1..s.length) {
            for (j in 0..i) {
                if (map.contains(s.subSequence(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j])
                } else {
                    dp[i] = Math.min(dp[i], dp[j] + (i - j))
                }
            }
        }

        return dp[s.length]
    }
}