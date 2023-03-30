package com.study.algorithm.algorithmsKT

//一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
//
//比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
//给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
//
// 
//
//示例 1：
//
//输入：s = "aabcb"
//输出：5
//解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
//示例 2：
//
//输入：s = "aabcbaa"
//输出：17
// 
//
//提示：
//
//1 <= s.length <= 500
//s 只包含小写英文字母。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/sum-of-beauty-of-all-substrings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 所有子字符串美丽值之和 {
    fun beautySum(s: String): Int {
        //两层循环
        //第一层循环固定子数组的起始位置
        //第二层循环寻找子数组的结束位置
        //实时更新子数组中每个元素出现的频次,并根据题意计算并累加子数组的美丽值

        //元素数量小于3直接返回0
        if (s.length < 3) {
            return 0
        }
        val counter = IntArray(26)
        var res = 0
        counter[s[0] - 'a']++
        counter[s[1] - 'a']++

        //起始下标
        var start = 0
        while (start < s.length - 2) {
            //结束的下标首先从start+2开始,直到数组结尾
            for (end in start + 2 until s.length) {
                counter[s[end] - 'a']++
                res += compute(counter)
            }
            //起始下标+1
            counter[s[start] - 'a']--
            res += compute(counter)
            start++
            //其次,结束下标从数组结尾,返回至start + 3
            for (end in s.length - 1 downTo start + 3) {
                counter[s[end] - 'a']--
                res += compute(counter)
            }
            //起始下标+1
            counter[s[start] - 'a']--
            start++
        }
        return res
    }

    fun compute(array: IntArray): Int {
        var max = 0
        var min = Int.MAX_VALUE
        for (i in array) {
            max = Math.max(max, i)
            if (i > 0) {
                min = Math.min(min, i)
            }
        }
        return max - min
    }
}