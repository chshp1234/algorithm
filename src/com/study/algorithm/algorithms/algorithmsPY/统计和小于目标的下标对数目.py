"""
给你一个下标从 0 开始长度为 n 的整数数组 nums 和一个整数 target ，请你返回满足 0 <= i < j < n 且 nums[i] + nums[j] < target 的下标对 (i, j) 的数目。


示例 1：

输入：nums = [-1,1,2,3,1], target = 2
输出：3
解释：总共有 3 个下标对满足题目描述：
- (0, 1) ，0 < 1 且 nums[0] + nums[1] = 0 < target
- (0, 2) ，0 < 2 且 nums[0] + nums[2] = 1 < target
- (0, 4) ，0 < 4 且 nums[0] + nums[4] = 0 < target
注意 (0, 3) 不计入答案因为 nums[0] + nums[3] 不是严格小于 target 。
示例 2：

输入：nums = [-6,2,5,-2,-7,-1,3], target = -2
输出：10
解释：总共有 10 个下标对满足题目描述：
- (0, 1) ，0 < 1 且 nums[0] + nums[1] = -4 < target
- (0, 3) ，0 < 3 且 nums[0] + nums[3] = -8 < target
- (0, 4) ，0 < 4 且 nums[0] + nums[4] = -13 < target
- (0, 5) ，0 < 5 且 nums[0] + nums[5] = -7 < target
- (0, 6) ，0 < 6 且 nums[0] + nums[6] = -3 < target
- (1, 4) ，1 < 4 且 nums[1] + nums[4] = -5 < target
- (3, 4) ，3 < 4 且 nums[3] + nums[4] = -9 < target
- (3, 5) ，3 < 5 且 nums[3] + nums[5] = -3 < target
- (4, 5) ，4 < 5 且 nums[4] + nums[5] = -8 < target
- (4, 6) ，4 < 6 且 nums[4] + nums[6] = -4 < target


提示：

1 <= nums.length == n <= 50
-50 <= nums[i], target <= 50
"""
from typing import List


class Solution:
    """
    排序，双指针
    这里不管i<j，因为其实只要任意一对元素，都会凑成“i<j”
    所以直接对数组进行排序，找出其中元素和小于目标值的下标对的数量即可
    排序后，使用高低双指针l，r进行计算，当nums[l] + nums[r] < target时，说明，nums[l] + nums(l..r]的所有元素都将小于target，那么这个区间的数量就为r-l，加入结果中，然后另l++
    否则说明nums[l] + nums(l..r]的所有元素都将大于等于target，那么另r--
    最后当l==r时跳出循环，返回结果即可
    """

    def countPairs(self, nums: List[int], target: int) -> int:
        nums.sort()
        l, r = 0, len(nums) - 1
        res = 0
        while l < r:
            if nums[l] + nums[r] < target:
                res += r - l
                l += 1
            else:
                r -= 1
        return res


print(Solution().countPairs([-6, 2, 5, -2, -7, -1, 3], -2))
