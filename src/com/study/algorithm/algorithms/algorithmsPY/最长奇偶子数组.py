"""
给你一个下标从 0 开始的整数数组 nums 和一个整数 threshold 。

请你从 nums 的子数组中找出以下标 l 开头、下标 r 结尾 (0 <= l <= r < nums.length) 且满足以下条件的 最长子数组 ：

nums[l] % 2 == 0
对于范围 [l, r - 1] 内的所有下标 i ，nums[i] % 2 != nums[i + 1] % 2
对于范围 [l, r] 内的所有下标 i ，nums[i] <= threshold
以整数形式返回满足题目要求的最长子数组的长度。

注意：子数组 是数组中的一个连续非空元素序列。



示例 1：

输入：nums = [3,2,5,4], threshold = 5
输出：3
解释：在这个示例中，我们选择从 l = 1 开始、到 r = 3 结束的子数组 => [2,5,4] ，满足上述条件。
因此，答案就是这个子数组的长度 3 。可以证明 3 是满足题目要求的最大长度。
示例 2：

输入：nums = [1,2], threshold = 2
输出：1
解释：
在这个示例中，我们选择从 l = 1 开始、到 r = 1 结束的子数组 => [2] 。
该子数组满足上述全部条件。可以证明 1 是满足题目要求的最大长度。
示例 3：

输入：nums = [2,3,4,5], threshold = 4
输出：3
解释：
在这个示例中，我们选择从 l = 0 开始、到 r = 2 结束的子数组 => [2,3,4] 。
该子数组满足上述全部条件。
因此，答案就是这个子数组的长度 3 。可以证明 3 是满足题目要求的最大长度。


提示：

1 <= nums.length <= 100
1 <= nums[i] <= 100
1 <= threshold <= 100
"""
from typing import List


class Solution:
    """
    双指针
    记录左指针l,遍历右指针r
    如果nums[r] > threshold 或者 nums[r] % 2 == nums[r - 1] % 2,说明当前下标的右指针r不符合条件.判断更新区间长度的最大值,并开始寻找下一个区间.
    继续判断nums[r] <= threshold and nums[r] % 2 == 0:如果成立,更新当前r为左指针,否则需要重新寻找左指针
    """

    def longestAlternatingSubarray(self, nums: List[int], threshold: int) -> int:
        l = -1
        res = 0
        for r in range(len(nums)):
            if l == -1:
                if nums[r] <= threshold and nums[r] % 2 == 0:
                    l = r
            else:
                if nums[r] > threshold or nums[r] % 2 == nums[r - 1] % 2:
                    res = max(res, r - l)
                    if nums[r] <= threshold and nums[r] % 2 == 0:
                        l = r
                    else:
                        l = -1

        return res if l == -1 else max(res, len(nums) - l)


print(Solution().longestAlternatingSubarray([4, 10, 3], 10))
