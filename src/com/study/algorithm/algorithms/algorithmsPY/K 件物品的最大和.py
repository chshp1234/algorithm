"""
袋子中装有一些物品，每个物品上都标记着数字 1 、0 或 -1 。

给你四个非负整数 numOnes 、numZeros 、numNegOnes 和 k 。

袋子最初包含：

numOnes 件标记为 1 的物品。
numZeroes 件标记为 0 的物品。
numNegOnes 件标记为 -1 的物品。
现计划从这些物品中恰好选出 k 件物品。返回所有可行方案中，物品上所标记数字之和的最大值。



示例 1：

输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
输出：2
解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 2 件标记为 1 的物品，得到的数字之和为 2 。
可以证明 2 是所有可行方案中的最大值。
示例 2：

输入：numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
输出：3
解释：袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 3 件标记为 1 的物品，1 件标记为 0 的物品，得到的数字之和为 3 。
可以证明 3 是所有可行方案中的最大值。


提示：

0 <= numOnes, numZeros, numNegOnes <= 50
0 <= k <= numOnes + numZeros + numNegOnes
"""


class Solution:

    # 三目运算:'true' if true else 'false'
    def kItemsWithMaximumSum(self, numOnes: int, numZeros: int, numNegOnes: int, k: int) -> int:
        return k if numOnes >= k else numOnes if numOnes + numZeros >= k else numOnes - (k - (numOnes + numZeros))
