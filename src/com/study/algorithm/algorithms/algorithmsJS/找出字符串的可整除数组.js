/**
给你一个下标从 0 开始的字符串 word ，长度为 n ，由从 0 到 9 的数字组成。另给你一个正整数 m 。

word 的 可整除数组 div  是一个长度为 n 的整数数组，并满足：

如果 word[0,...,i] 所表示的 数值 能被 m 整除，div[i] = 1
否则，div[i] = 0
返回 word 的可整除数组。



示例 1：

输入：word = "998244353", m = 3
输出：[1,1,0,0,0,1,1,0,0]
解释：仅有 4 个前缀可以被 3 整除："9"、"99"、"998244" 和 "9982443" 。
示例 2：

输入：word = "1010", m = 10
输出：[0,1,0,1]
解释：仅有 2 个前缀可以被 10 整除："10" 和 "1010" 。


提示：

1 <= n <= 105
word.length == n
word 由数字 0 到 9 组成
1 <= m <= 109
 * @param {string} word
 * @param {number} m
 * @return {number[]}
 */
var divisibilityArray = function(word, m) {
    //数学，前缀和
    //依照题意，将字符串前缀中每一位数字字符转换成一个数字，在判断该数字能否整除m即可
    //但字符串长度过长，会导致数字溢出，那么在进行前缀相加时，直接对其进行取余并将余数赋值给此前缀和，之后直接判断此前缀和是否等于0即可
    //注意取字符串中的字符时，需将其转换成数字类型
    let left = 0
    let res = []
    for (let i = 0; i < word.length; i++) {
        //两种方式转换成数字类型
        const element = word[i] - "0"
        // const element = parseInt(word[i])
        left = (left * 10 + element) % m
        res[i] = left == 0 ? 1 : 0
    }
    return res
};