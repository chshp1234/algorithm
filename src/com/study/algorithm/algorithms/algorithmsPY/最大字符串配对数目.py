"""
给你一个下标从 0 开始的数组 words ，数组中包含 互不相同 的字符串。

如果字符串 words[i] 与字符串 words[j] 满足以下条件，我们称它们可以匹配：

字符串 words[i] 等于 words[j] 的反转字符串。
0 <= i < j < words.length
请你返回数组 words 中的 最大 匹配数目。

注意，每个字符串最多匹配一次。



示例 1：

输入：words = ["cd","ac","dc","ca","zz"]
输出：2
解释：在此示例中，我们可以通过以下方式匹配 2 对字符串：
- 我们将第 0 个字符串与第 2 个字符串匹配，因为 word[0] 的反转字符串是 "dc" 并且等于 words[2]。
- 我们将第 1 个字符串与第 3 个字符串匹配，因为 word[1] 的反转字符串是 "ca" 并且等于 words[3]。
可以证明最多匹配数目是 2 。
示例 2：

输入：words = ["ab","ba","cc"]
输出：1
解释：在此示例中，我们可以通过以下方式匹配 1 对字符串：
- 我们将第 0 个字符串与第 1 个字符串匹配，因为 words[1] 的反转字符串 "ab" 与 words[0] 相等。
可以证明最多匹配数目是 1 。
示例 3：

输入：words = ["aa","ab"]
输出：0
解释：这个例子中，无法匹配任何字符串。


提示：

1 <= words.length <= 50
words[i].length == 2
words 包含的字符串互不相同。
words[i] 只包含小写英文字母。
"""
from typing import List


class Solution:

    # 哈希表
    # 判断每个字符串的翻转字符串是否存在哈希表中,若存在,则哈希表中对应的数量-1,如果数量为0,则移除
    # 否则将该字符串加入哈希表中,并将数量+1
    def maximumNumberOfStringPairs(self, words: List[str]) -> int:
        map = {}
        res = 0
        for w in words:
            # 切片
            # [start:end:step]
            # start表示起始下标(包括),end表示结束下标(不包括),step表示步长(可以不写,正数从左到右,负数从右到左)
            rev = w[::-1]
            if rev in map:
                res += 1
                count = map[rev]
                if count > 0:
                    map[rev] = count - 1
                else:
                    del map[rev]
            else:
                map[w] = map.get(w, 0) + 1
        return res


print(Solution().maximumNumberOfStringPairs(["cd", "ac", "dc", "ca", "zz"]))
