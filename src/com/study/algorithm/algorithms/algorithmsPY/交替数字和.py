"""
给你一个正整数 n 。n 中的每一位数字都会按下述规则分配一个符号：

最高有效位 上的数字分配到 正 号。
剩余每位上数字的符号都与其相邻数字相反。
返回所有数字及其对应符号的和。



示例 1：

输入：n = 521
输出：4
解释：(+5) + (-2) + (+1) = 4
示例 2：

输入：n = 111
输出：1
解释：(+1) + (-1) + (+1) = 1
示例 3：

输入：n = 886996
输出：0
解释：(+8) + (-8) + (+6) + (-9) + (+9) + (-6) = 0


提示：

1 <= n <= 109
"""


class Solution:

    """
    将余数都加入数组
    并将偶数位相加,奇数位数字相减即可
    """
    def alternateDigitSum(self, n: int) -> int:
        array = []
        while n > 0:
            array.insert(0, n % 10)
            n //= 10
        res = 0
        for i in range(len(array)):
            if i % 2 == 0:
                res += array[i]
            else:
                res -= array[i]
        return res


print(Solution().alternateDigitSum(111))
