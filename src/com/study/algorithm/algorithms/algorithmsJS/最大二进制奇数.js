/**
给你一个 二进制 字符串 s ，其中至少包含一个 '1' 。

你必须按某种方式 重新排列 字符串中的位，使得到的二进制数字是可以由该组合生成的 最大二进制奇数 。

以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。

注意 返回的结果字符串 可以 含前导零。



示例 1：

输入：s = "010"
输出："001"
解释：因为字符串 s 中仅有一个 '1' ，其必须出现在最后一位上。所以答案是 "001" 。
示例 2：

输入：s = "0101"
输出："1001"
解释：其中一个 '1' 必须出现在最后一位上。而由剩下的数字可以生产的最大数字是 "100" 。所以答案是 "1001" 。


提示：

1 <= s.length <= 100
s 仅由 '0' 和 '1' 组成
s 中至少包含一个 '1'
 * @param {string} s
 * @return {string}
 */
var maximumOddBinaryNumber = function(s) {
    //贪心
    //先统计字符串中字符"1"的数量oneCount
    //再将oneCount-1个”1“拼接到字符串开头，后面跟着s.length - oneCount个”0“，最后再拼接一个”1“表示是奇数
    //返回重新拼接后的字符串
    let oneCount = -1
    for (let i = 0; i < s.length; i++) {
        if (s[i] == "1") {
            oneCount++
        }
    }
    return "1".repeat(oneCount) + "0".repeat(s.length - oneCount - 1) + "1"

    /* let res = "1"
    for (let i = 1; i < s.length - oneCount; i++) {
        res = "0" + res
    }
    while (oneCount > 0) {
        res = "1" + res
        oneCount--
    }
    return res */
};

console.log(maximumOddBinaryNumber("0101"));