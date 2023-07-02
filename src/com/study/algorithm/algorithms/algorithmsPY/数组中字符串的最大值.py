"""
一个由字母和数字组成的字符串的 值 定义如下：

如果字符串 只 包含数字，那么值为该字符串在 10 进制下的所表示的数字。
否则，值为字符串的 长度 。
给你一个字符串数组 strs ，每个字符串都只由字母和数字组成，请你返回 strs 中字符串的 最大值 。



示例 1：

输入：strs = ["alic3","bob","3","4","00000"]
输出：5
解释：
- "alic3" 包含字母和数字，所以值为长度 5 。
- "bob" 只包含字母，所以值为长度 3 。
- "3" 只包含数字，所以值为 3 。
- "4" 只包含数字，所以值为 4 。
- "00000" 只包含数字，所以值为 0 。
所以最大的值为 5 ，是字符串 "alic3" 的值。
示例 2：

输入：strs = ["1","01","001","0001"]
输出：1
解释：
数组中所有字符串的值都是 1 ，所以我们返回 1 。


提示：

1 <= strs.length <= 100
1 <= strs[i].length <= 9
strs[i] 只包含小写英文字母和数字。
"""
from typing import List


class Solution:
    def maximumValue(self, strs: List[str]) -> int:
        # 判断字符串是不是只包含数字字符
        # 是:直接转换成数字
        # 否:返回字符串长度
        # 遍历字符串数组,取最大值
        res = 0
        for s in strs:
            # leetcode题解
            """
            c.isdigit() for c in s 用于实例化一个生成器generator
            遍历s中的所有字符,生成一个每个字符是否是数字的布尔值的序列,如字符串"alic3"将生成"False,False,False,False,True"
            
            all() 函数用于判断给定的可迭代参数 iterable 中的所有元素是否都为 TRUE，如果是返回 True，否则返回 False。
            元素除了是 0、空、None、False 外都算 True。
            注意：空元组、空列表返回值为True，这里要特别注意。
            
            """
            is_digits = all(c.isdigit() for c in s)
            # 如果 字符都是数字(is_digits()) ,返回int(s)
            # 否则 返回len(s)
            res = max(res, int(s) if is_digits else len(s))

            # if s.isdigit():
            #     res = max(res, int(s))
            # else:
            #     res = max(res, len(s))

        return res


print(Solution().maximumValue(["alic3", "bob", "3", "4", "00000"]))


# gen = (c.isdigit() for c in "alic3")
# for g in gen:
#     print(g)
