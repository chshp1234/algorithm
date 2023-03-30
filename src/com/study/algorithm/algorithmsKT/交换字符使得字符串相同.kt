package com.study.algorithm.algorithmsKT

import org.junit.Test

//有两个长度相同的字符串 s1 和 s2，且它们其中 只含有 字符 "x" 和 "y"，你需要通过「交换字符」的方式使这两个字符串相同。
//
//每次「交换字符」的时候，你都可以在两个字符串中各选一个字符进行交换。
//
//交换只能发生在两个不同的字符串之间，绝对不能发生在同一个字符串内部。也就是说，我们可以交换 s1[i] 和 s2[j]，但不能交换 s1[i] 和 s1[j]。
//
//最后，请你返回使 s1 和 s2 相同的最小交换次数，如果没有方法能够使得这两个字符串相同，则返回 -1 。
//
// 
//
//示例 1：
//
//输入：s1 = "xx", s2 = "yy"
//输出：1
//解释：
//交换 s1[0] 和 s2[1]，得到 s1 = "yx"，s2 = "yx"。
//示例 2：
//
//输入：s1 = "xy", s2 = "yx"
//输出：2
//解释：
//交换 s1[0] 和 s2[0]，得到 s1 = "yy"，s2 = "xx" 。
//交换 s1[0] 和 s2[1]，得到 s1 = "xy"，s2 = "xy" 。
//注意，你不能交换 s1[0] 和 s1[1] 使得 s1 变成 "yx"，因为我们只能交换属于两个不同字符串的字符。
//示例 3：
//
//输入：s1 = "xx", s2 = "xy"
//输出：-1
//示例 4：
//
//输入：s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
//输出：4
// 
//
//提示：
//
//1 <= s1.length, s2.length <= 1000
//s1, s2 只包含 'x' 或 'y'。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-swaps-to-make-strings-equal
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 交换字符使得字符串相同 {
    @Test
    fun 交换字符使得字符串相同() {
        println(
            "交换字符使得字符串相同:${
                minimumSwap(
                    "xx", "yy"
                )
            }"
        )
    }

    fun minimumSwap(s1: String, s2: String): Int {
        //贪心
        //1."xx"-"yy"需要1次,"yy"-"xx"需要1次
        //2."xy"-"yx"需要2次,"yx"-"xy"需要2次
        //遍历字符串,当字符不相同时,记录'x'和'y'总共不同的个数xCount和yCount
        //只有当不同的总数为偶数,两个字符串才能根据交换其中的字符转换成相同的字符串
        //为了使得交换次数尽可能少,所以应该贪心的尽可能使用方式1进行交换
        //使用方式1的方式,每次x或y将减少2个,交换次数+1,所以总的交换次数为xCount / 2 + yCount / 2
        //使用方式2的方式,x和y将分别减少1个,交换次数+2,所以总的交换次数为xCount % 2 + yCount % 2
        //那么最后最少交换次数就为xCount / 2 + yCount / 2 + xCount % 2 + yCount % 2
        var xCount = 0
        var yCount = 0

        for (i in s1.indices) {
            if (s1[i] == s2[i]) {
                continue
            }
            if (s1[i] == 'x') {
                xCount++
            } else {
                yCount++
            }
        }

        if ((xCount + yCount) % 2 != 0) {
            return -1
        }

        return xCount / 2 + yCount / 2 + xCount % 2 + yCount % 2
    }
}