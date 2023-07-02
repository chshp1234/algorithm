from typing import List

"""
给你一个长度为 n 的 整数 数组 pref 。找出并返回满足下述条件且长度为 n 的数组 arr ：

pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i].
注意 ^ 表示 按位异或（bitwise-xor）运算。

可以证明答案是 唯一 的。

 

示例 1：

输入：pref = [5,2,0,3,1]
输出：[5,7,2,3,2]
解释：从数组 [5,7,2,3,2] 可以得到如下结果：
- pref[0] = 5
- pref[1] = 5 ^ 7 = 2
- pref[2] = 5 ^ 7 ^ 2 = 0
- pref[3] = 5 ^ 7 ^ 2 ^ 3 = 3
- pref[4] = 5 ^ 7 ^ 2 ^ 3 ^ 2 = 1
示例 2：

输入：pref = [13]
输出：[13]
解释：pref[0] = arr[0] = 13
 

提示：

1 <= pref.length <= 105
0 <= pref[i] <= 106
"""


class Solution:
    # 位运算
    # 对于pref[i]=res[i]^res[1]..^res[i]
    # 而:
    # res[i]^res[1]=pref[1]
    # res[i]^res[1]^res[2]=pref[1]^res[2]=pref[2] (这就类似于前缀和的思路了)
    # 那么可得 pref[i]=pref[i-1]^res[i]
    # 所以res[i]=pref[i-1]^pref[i]
    def findArray(self, pref: List[int]) -> List[int]:
        # res=[0 for i in range(len(pref))]
        res = list(range(len(pref)))
        res[0] = pref[0]
        for i in range(1, len(pref)):
            res[i] = pref[i - 1] ^ pref[i]
        return res


print(Solution().findArray([5, 2, 0, 3, 1]))
