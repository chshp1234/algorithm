"""
给你两个下标从 0 开始长度为 n 的整数排列 A 和 B 。

A 和 B 的 前缀公共数组 定义为数组 C ，其中 C[i] 是数组 A 和 B 到下标为 i 之前公共元素的数目。

请你返回 A 和 B 的 前缀公共数组 。

如果一个长度为 n 的数组包含 1 到 n 的元素恰好一次，我们称这个数组是一个长度为 n 的 排列 。



示例 1：

输入：A = [1,3,2,4], B = [3,1,2,4]
输出：[0,2,3,4]
解释：i = 0：没有公共元素，所以 C[0] = 0 。
i = 1：1 和 3 是两个数组的前缀公共元素，所以 C[1] = 2 。
i = 2：1，2 和 3 是两个数组的前缀公共元素，所以 C[2] = 3 。
i = 3：1，2，3 和 4 是两个数组的前缀公共元素，所以 C[3] = 4 。
示例 2：

输入：A = [2,3,1], B = [3,1,2]
输出：[0,1,3]
解释：i = 0：没有公共元素，所以 C[0] = 0 。
i = 1：只有 3 是公共元素，所以 C[1] = 1 。
i = 2：1，2 和 3 是两个数组的前缀公共元素，所以 C[2] = 3 。


提示：

1 <= A.length == B.length == n <= 50
1 <= A[i], B[i] <= n
题目保证 A 和 B 两个数组都是 n 个元素的排列。
"""
from typing import List


class Solution:

    """
    哈希表
    遍历时,两个元素加入集合中,两种办法可求当前下标处的公共元素数量:
    1.同时判断当前元素是否再对方集合中,需要注意,当这两个元素相同时,数量只能+1;否则,依次判断当前元素是否再对方集合中,如果存在,数量+1
    2.直接判断两个集合的交集,也即集合的公共元素的数量
    """
    def findThePrefixCommonArray(self, A: List[int], B: List[int]) -> List[int]:
        mapA = set()
        mapB = set()
        res = [0]
        mapA.add(A[0])
        mapB.add(B[0])
        if A[0] == B[0]:
            res[0] = 1
        for i in range(1, len(A)):
            mapA.add(A[i])
            mapB.add(B[i])
            res.append(res[i - 1])
            if A[i] == B[i]:
                res[i] += 1
            else:
                if A[i] in mapB:
                    res[i] += 1
                if B[i] in mapA:
                    res[i] += 1
        return res

    def findThePrefixCommonArray2(self, A: List[int], B: List[int]) -> List[int]:
        mapA = set()
        mapB = set()
        res = []
        for i in range(0, len(A)):
            mapA.add(A[i])
            mapB.add(B[i])
            res.append(len(mapA & mapB))
        return res


print(Solution().findThePrefixCommonArray2([1], [3]))
