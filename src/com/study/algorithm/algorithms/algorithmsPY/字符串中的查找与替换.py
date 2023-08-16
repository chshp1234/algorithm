"""
你会得到一个字符串 s (索引从 0 开始)，你必须对它执行 k 个替换操作。替换操作以三个长度均为 k 的并行数组给出：indices, sources,  targets。

要完成第 i 个替换操作:

检查 子字符串  sources[i] 是否出现在 原字符串 s 的索引 indices[i] 处。
如果没有出现， 什么也不做 。
如果出现，则用 targets[i] 替换 该子字符串。
例如，如果 s = "abcd" ， indices[i] = 0 , sources[i] = "ab"， targets[i] = "eee" ，那么替换的结果将是 "eeecd" 。

所有替换操作必须 同时 发生，这意味着替换操作不应该影响彼此的索引。测试用例保证元素间不会重叠 。

例如，一个 s = "abc" ，  indices = [0,1] ， sources = ["ab"，"bc"] 的测试用例将不会生成，因为 "ab" 和 "bc" 替换重叠。
在对 s 执行所有替换操作后返回 结果字符串 。

子字符串 是字符串中连续的字符序列。



示例 1：



输入：s = "abcd", indices = [0,2], sources = ["a","cd"], targets = ["eee","ffff"]
输出："eeebffff"
解释：
"a" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
"cd" 从 s 中的索引 2 开始，所以它被替换为 "ffff"。
示例 2：

输入：s = "abcd", indices = [0,2], sources = ["ab","ec"], targets = ["eee","ffff"]
输出："eeecd"
解释：
"ab" 从 s 中的索引 0 开始，所以它被替换为 "eee"。
"ec" 没有从原始的 S 中的索引 2 开始，所以它没有被替换。


提示：

1 <= s.length <= 1000
k == indices.length == sources.length == targets.length
1 <= k <= 100
0 <= indices[i] < s.length
1 <= sources[i].length, targets[i].length <= 50
s 仅由小写英文字母组成
sources[i] 和 targets[i] 仅由小写英文字母组成
"""
from typing import List


class Solution:

    """
    排序，模拟
    为了方便判断，先用map将indices的值和下标进行绑定，再将indices进行排序
    再对indices进行遍历，按题意进行模拟判断
    判断indices[i]对应的下标处的字符串是否等于sources[i],如果相等，则替换（加入targets[i]）,否则加入原子串
    """
    def findReplaceString(self, s: str, indices: List[int], sources: List[str], targets: List[str]) -> str:
        last = 0
        res = ""
        map = {indices[i]: i for i in range(len(indices))}
        indices.sort()
        for i in range(len(indices)):
            if last < indices[i]:
                res += s[last: indices[i]]
                last = indices[i]
            if s[indices[i]:indices[i] + len(sources[map[indices[i]]])] == sources[map[indices[i]]]:
                res += targets[map[indices[i]]]
                last = indices[i] + len(sources[map[indices[i]]])
        if last < len(s):
            res += s[last: len(s)]
        return res


print(Solution().findReplaceString("jjievdtjfb",
                                   [4,6,1],
                                   ["md","tjgb","jf"],
                                   ["foe","oov","e"]))
