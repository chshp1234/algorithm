"""
给定一个包含大写字母和小写字母的字符串 s ，返回 通过这些字母构造成的 最长的回文串 。

在构造过程中，请注意 区分大小写 。比如 "Aa" 不能当做一个回文字符串。



示例 1:

输入:s = "abccccdd"
输出:7
解释:
我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
示例 2:

输入:s = "a"
输出:1
示例 3：

输入:s = "aaaaaccc"
输出:7


提示:

1 <= s.length <= 2000
s 只由小写 和/或 大写英文字母组成
"""
import collections


class Solution:
    """
    贪心
    将字符串中所有字符进行统计,其出现的频次
    如果频次是偶数,则可全部加入到回文串左右,如果频次是奇数,则将频次-1后成为偶数,就可以加入到回文串左右
    如果最后还有字符剩下,说明有频次为奇数的字符,那么可将此字符加入回文串的中间
    """

    def longestPalindrome(self, s: str) -> int:
        counter = collections.Counter(s)
        res = 0
        left = False
        for count in counter.values():
            # 如果频次是偶数,则可全部加入到回文串左右,如果频次是奇数,则将频次-1后成为偶数,就可以加入到回文串左右
            res += (count & -2)
            # 判断是否最后有字符剩余
            if count % 2 == 1:
                left = True
        # 如果有字符剩余则数量再+1,否则不加
        return res + (1 if left else 0)


print(Solution().longestPalindrome("aaabb"))
