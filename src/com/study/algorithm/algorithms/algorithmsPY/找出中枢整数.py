"""
给你一个正整数 n ，找出满足下述条件的 中枢整数 x ：

1 和 x 之间的所有元素之和等于 x 和 n 之间所有元素之和。
返回中枢整数 x 。如果不存在中枢整数，则返回 -1 。题目保证对于给定的输入，至多存在一个中枢整数。



示例 1：

输入：n = 8
输出：6
解释：6 是中枢整数，因为 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21 。
示例 2：

输入：n = 1
输出：1
解释：1 是中枢整数，因为 1 = 1 。
示例 3：

输入：n = 4
输出：-1
解释：可以证明不存在满足题目要求的整数。


提示：

1 <= n <= 1000
"""


class Solution:
    # 前缀和
    # 先求出1..n的总和sum,再维护一个前缀和preSum
    # 从1到n循环判断sum-preSum==preSum+i(当前遍历的数)
    # 如果相等,则i就是中枢整数
    # 如果遍历结束还没找到,则返回-1
    def pivotInteger(self, n: int) -> int:
        preSum = 0
        sum = (1 + n) * n / 2
        for i in range(1, n + 1):
            if sum - preSum == preSum + i:
                return i
            else:
                preSum += i
        return -1

    # 数学
    # 设中枢数为x
    # 根据等差数列求和公式可得sum(1...x)=(1+x)*x/2,sum(x...n)=(x+n)*(n-x+1)/2
    # 那么令sum(1...x)=sum(x...n),可得x^2=(n^2+n)/2
    # 最后只要对x^2进行开根号,判断其是否为整数即可
    def pivotInteger2(self, n: int) -> int:
        # 求x^2
        x2 = (n + n ** 2) // 2
        # 对x^2开根号,并保留其整数部分
        x = int(x2 ** 0.5)
        # 重新判断保留的整数部分的数的平方是否等于原数
        if x ** 2 == x2:
            return x
        else:
            return -1


print(Solution().pivotInteger2(15))
