"""
给你一个下标从 0 开始、由 n 个整数组成的数组 nums 和一个整数 target 。

你的初始位置在下标 0 。在一步操作中，你可以从下标 i 跳跃到任意满足下述条件的下标 j ：

0 <= i < j < n
-target <= nums[j] - nums[i] <= target
返回到达下标 n - 1 处所需的 最大跳跃次数 。

如果无法到达下标 n - 1 ，返回 -1 。



示例 1：

输入：nums = [1,3,6,4,1,2], target = 2
输出：3
解释：要想以最大跳跃次数从下标 0 到下标 n - 1 ，可以按下述跳跃序列执行操作：
- 从下标 0 跳跃到下标 1 。
- 从下标 1 跳跃到下标 3 。
- 从下标 3 跳跃到下标 5 。
可以证明，从 0 到 n - 1 的所有方案中，不存在比 3 步更长的跳跃序列。因此，答案是 3 。
示例 2：

输入：nums = [1,3,6,4,1,2], target = 3
输出：5
解释：要想以最大跳跃次数从下标 0 到下标 n - 1 ，可以按下述跳跃序列执行操作：
- 从下标 0 跳跃到下标 1 。
- 从下标 1 跳跃到下标 2 。
- 从下标 2 跳跃到下标 3 。
- 从下标 3 跳跃到下标 4 。
- 从下标 4 跳跃到下标 5 。
可以证明，从 0 到 n - 1 的所有方案中，不存在比 5 步更长的跳跃序列。因此，答案是 5 。
示例 3：

输入：nums = [1,3,6,4,1,2], target = 0
输出：-1
解释：可以证明不存在从 0 到 n - 1 的跳跃序列。因此，答案是 -1 。


提示：

2 <= nums.length == n <= 1000
-109 <= nums[i] <= 109
0 <= target <= 2 * 109
"""
from typing import List


class Solution:
    """
    动态规划
    设dp[i]为0~i处的最大的跳跃次数
    那么dp[i]=max(dp[i],dp[0..i]+1)(dp[0..i]>0)
    两层循环,第一层循环固定位置j,第二层循环固定位置i
    当dp[i]>0,说明从0可以跳跃到i,如果abs(nums[j] - nums[i]) <= target,说明从i可以跳跃到j
    那么dp[i]=max(dp[i], dp[j] + 1)
    最后如果dp[-1]=0,返回-1,否则返回dp[-1]
    """

    def maximumJumps(self, nums: List[int], target: int) -> int:
        dp = [1 if abs(nums[i] - nums[0]) <= target else 0 for i in range(len(nums))]
        dp[0] = 0
        for i in range(1, len(nums)):
            for j in range(1, i):
                if dp[j] > 0 and abs(nums[j] - nums[i]) <= target:
                    dp[i] = max(dp[i], dp[j] + 1)
        return -1 if dp[-1] == 0 else dp[-1]


print(Solution().maximumJumps([1, 0], 0))
