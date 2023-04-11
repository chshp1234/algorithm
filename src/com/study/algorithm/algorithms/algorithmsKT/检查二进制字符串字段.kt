package com.study.algorithm.algorithms.algorithmsKT

//给你一个二进制字符串 s ，该字符串 不含前导零 。
//
//如果 s 包含 零个或一个由连续的 '1' 组成的字段 ，返回 true​​​ 。否则，返回 false 。
//
//如果 s 中 由连续若干个 '1' 组成的字段 数量不超过 1，返回 true​​​ 。否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：s = "1001"
//输出：false
//解释：由连续若干个 '1' 组成的字段数量为 2，返回 false
//示例 2：
//
//输入：s = "110"
//输出：true
// 
//
//提示：
//
//1 <= s.length <= 100
//s[i]​​​​ 为 '0' 或 '1'
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/check-if-binary-string-has-at-most-one-segment-of-ones
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 检查二进制字符串字段 {
    fun checkOnesSegment(s: String): Boolean {
        //要求只包含一个连续的‘1’，又因为字符串第一个字符肯定是‘1’，那么只要判断字符串中是否包含"01"即可
        //如果字符串中包含"01"，说明连续的‘1’的数量肯定大于1个
        return s.contains("01")
    }
}