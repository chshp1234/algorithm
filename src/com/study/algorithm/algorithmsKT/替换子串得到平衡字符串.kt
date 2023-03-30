package com.study.algorithm.algorithmsKT

import org.junit.Test

//有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
//
//假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
//
// 
//
//给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
//
//你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
//
//请返回待替换子串的最小可能长度。
//
//如果原字符串自身就是一个平衡字符串，则返回 0。
//
// 
//
//示例 1：
//
//输入：s = "QWER"
//输出：0
//解释：s 已经是平衡的了。
//示例 2：
//
//输入：s = "QQWE"
//输出：1
//解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
//示例 3：
//
//输入：s = "QQQW"
//输出：2
//解释：我们可以把前面的 "QQ" 替换成 "ER"。
//示例 4：
//
//输入：s = "QQQQ"
//输出：3
//解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
// 
//
//提示：
//
//1 <= s.length <= 10^5
//s.length 是 4 的倍数
//s 中只含有 'Q', 'W', 'E', 'R' 四种字符
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/replace-the-substring-for-balanced-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 替换子串得到平衡字符串 {
    @Test
    fun 替换子串得到平衡字符串() {
        println(
            "替换子串得到平衡字符串:${
                balancedString(
                    "WQWRQQQW"
                )
            }"
        )
    }

    fun balancedString(s: String): Int {
        //贪心，滑动窗口
        //贪心，只需要改变数量大于平衡的字符。
        //记录每一个字符出现的次数，当字符大于平衡数量时，则该字符是需要改变的
        //维护一个窗口，窗口内只记录需要改变的字符的数量
        //右指针右移，遇到需要改变的字符时，统计该字符数量+1
        //当窗口内包含所有需要改变的字符，且数量都大于等于之前记录的需要改变的字符的数量时，说明改变此窗口的子串，可以使得原串为平衡字符串
        //此时左移左指针，遇到需要改变的字符时，令窗口内该字符数量-1，直到该窗口内某一个需要改变的字符数量小于记录的目标数量为止 此时左指针上一个下标到右指针之间的距离就是当前窗口最短距离（但不一定是所有情况的最短距离）
        //判断并更新窗口最短距离。如果窗口距离等于需要改变的字符的数量时，可直接返回该距离。
        val balancedString = ChildString(s)
        val winString = ChildString()
        if (balancedString == winString) {
            return 0
        }

        var l = 0
        var r = 0
        var res = Int.MAX_VALUE
        while (r < s.length) {
            when (s[r]) {
                'Q' -> if (balancedString.QCount > 0) winString.QCount++
                'W' -> if (balancedString.WCount > 0) winString.WCount++
                'E' -> if (balancedString.ECount > 0) winString.ECount++
                'R' -> if (balancedString.RCount > 0) winString.RCount++
            }
            if (balancedString == winString) {
                while (balancedString == winString) {
                    when (s[l]) {
                        'Q' -> if (balancedString.QCount > 0) winString.QCount--
                        'W' -> if (balancedString.WCount > 0) winString.WCount--
                        'E' -> if (balancedString.ECount > 0) winString.ECount--
                        'R' -> if (balancedString.RCount > 0) winString.RCount--
                    }
                    l++
                }
                if (r - l + 2 == balancedString.balanceCount) {
                    return balancedString.balanceCount
                }
                res = Math.min(res, r - l + 2)
            }
            r++
        }
        return res
    }

    class ChildString(s: String = "") {
        var QCount = 0
        var WCount = 0
        var ECount = 0
        var RCount = 0
        val balanceCount: Int

        init {
            for (c in s) {
                when (c) {
                    'Q' -> QCount++
                    'W' -> WCount++
                    'E' -> ECount++
                    'R' -> RCount++
                }
            }
            val balance = s.length / 4
            QCount -= balance
            WCount -= balance
            ECount -= balance
            RCount -= balance
            if (QCount <= 0) {
                QCount = 0
            }
            if (WCount <= 0) {
                WCount = 0
            }
            if (ECount <= 0) {
                ECount = 0
            }
            if (RCount <= 0) {
                RCount = 0
            }
            balanceCount = QCount + WCount + ECount + RCount
        }

        override fun equals(other: Any?): Boolean {
            if (other !is ChildString) {
                return false
            }
            return QCount <= other.QCount && WCount <= other.WCount && ECount <= other.ECount && RCount <= other.RCount
        }
    }
}