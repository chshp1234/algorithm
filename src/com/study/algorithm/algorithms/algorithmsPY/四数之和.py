"""
给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：

0 <= a, b, c, d < n
a、b、c 和 d 互不相同
nums[a] + nums[b] + nums[c] + nums[d] == target
你可以按 任意顺序 返回答案 。



示例 1：

输入：nums = [1,0,-1,0,-2,2], target = 0
输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
示例 2：

输入：nums = [2,2,2,2,2], target = 8
输出：[[2,2,2,2]]


提示：

1 <= nums.length <= 200
-109 <= nums[i] <= 109
-109 <= target <= 109
"""
from typing import List


class Solution:
    """
    排序+双指针
    首先对数组进行排序,方便后续双指针进行计算
    用两层循环固定第一个数f和第二个数s
    最后两个数,用高低位双指针进行求得
    如果数个数字和sum>target,则高位指针r-1,如果sum<target.则低位指针l+1,如果相等,则将结果加入结果中
    遍历过程中,如果f,s,l,r不是遍历的第一个数,并且和上一个数相等,则此组合重复遍历,可跳过当前数
    """

    def fourSum(self, nums: List[int], target: int) -> List[List[int]]:
        if len(nums) < 4:
            return []
        nums.sort()
        res = []
        for f in range(len(nums) - 3):
            if f > 0 and nums[f] == nums[f - 1]:
                continue
            for s in range(f + 1, len(nums) - 2):
                if s > f + 1 and nums[s] == nums[s - 1]:
                    continue
                l, r = s + 1, len(nums) - 1
                while l < r:
                    if l > s + 1 and nums[l] == nums[l - 1]:
                        l += 1
                        continue
                    if r < len(nums) - 1 and nums[r] == nums[r + 1]:
                        r -= 1
                        continue
                    sum = nums[f] + nums[s] + nums[l] + nums[r]
                    if sum > target:
                        r -= 1
                    elif sum < target:
                        l += 1
                    else:
                        res.append([nums[f], nums[s], nums[l], nums[r]])
                        l += 1
                        r -= 1
        return res


print(Solution().fourSum([1,-2,-5,-4,-3,3,3,5], -11))
