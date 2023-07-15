"""
给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。

下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。



示例 1：



输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
输出：13
解释：如图所示，为和最小的两条下降路径
示例 2：



输入：matrix = [[-19,57],[-40,-5]]
输出：-59
解释：如图所示，为和最小的下降路径


提示：

n == matrix.lgth == matrix[i].lgth
1 <= n <= 100
-100 <= matrix[i][j] <= 100
"""
from typing import List


class Solution:
    """
    动态规划
    设dp[r][c]:是到达第r行c列的位置处的最小路径和
    因为路径只能从上到下,而且最多横跨一列,所以:
    dp[r][c]=min(dp[r-1][c-1],dp[r-1][c],dp[r-1][c+1])+dp[r][c],如果c=1或c=len-1,那么只需要取上面靠边的两列数据作比较即可
    最后,返回dp矩阵最后一行中最小的数字即可

    优化:
    因为dp[r][0..len]的值只跟dp[r-1][0..len],也就是上一行的值有关,所以可以使用滚动数组进行空间上的优化
    """

    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        l = len(matrix)

        if l == 1:
            return matrix[0][0]
        tmp = matrix[0]
        for r in range(1, l - 1):
            last = tmp[0]
            tmp[0] = min(tmp[0], tmp[1]) + matrix[r][0]
            for c in range(1, l - 1):
                last, tmp[c] = tmp[c], min(last, tmp[c], tmp[c + 1]) + matrix[r][c]
            tmp[l - 1] = min(last, tmp[l - 1]) + matrix[r][l - 1]

        last = tmp[0]
        res = tmp[0] = min(tmp[0], tmp[1]) + matrix[l - 1][0]
        for c in range(1, l - 1):
            last, tmp[c] = tmp[c], min(last, tmp[c], tmp[c + 1]) + matrix[l - 1][c]
            res = min(res, tmp[c])
        tmp[l - 1] = min(last, tmp[l - 1]) + matrix[l - 1][l - 1]
        res = min(res, tmp[l - 1])
        return res
