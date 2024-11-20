/**
给你一个下标从 0 开始、由正整数组成的数组 nums 。

你可以在数组上执行下述操作 任意 次：

选中一个同时满足 0 <= i < nums.length - 1 和 nums[i] <= nums[i + 1] 的整数 i 。将元素 nums[i + 1] 替换为 nums[i] + nums[i + 1] ，并从数组中删除元素 nums[i] 。
返回你可以从最终数组中获得的 最大 元素的值。



示例 1：

输入：nums = [2,3,7,9,3]
输出：21
解释：我们可以在数组上执行下述操作：
- 选中 i = 0 ，得到数组 nums = [5,7,9,3] 。
- 选中 i = 1 ，得到数组 nums = [5,16,3] 。
- 选中 i = 0 ，得到数组 nums = [21,3] 。
最终数组中的最大元素是 21 。可以证明我们无法获得更大的元素。
示例 2：

输入：nums = [5,3,3]
输出：11
解释：我们可以在数组上执行下述操作：
- 选中 i = 1 ，得到数组 nums = [5,6] 。
- 选中 i = 0 ，得到数组 nums = [11] 。
最终数组中只有一个元素，即 11 。


提示：

1 <= nums.length <= 105
1 <= nums[i] <= 106
 * @param {number[]} nums
 * @return {number}
 */
var maxArrayValue = function(nums) {
    //贪心
    //因为每次需要比较nums[i]和其右侧nums[i+1]的大小
    //如果nums[i]<nums[i+1]，则nums[i+1]=nums[i]+nums[i+1]，并且删去nums[i]，如此将得到一个更大的nums[i+1](想象成nums[i+1]吃掉了nums[i])
    //那么我们可以从数组尾部开始遍历：
    //1. 如果nums[i+1]>nums[i]，则nums[i+1]吞并nums[i]得到一个新的值sum，然后再继续和nums[i-1]比较
    //2. 直到sum<nums[i]，因为sum<nums[i]，所以sum<(nums[i]+nums[i-1])，此时的sum将是可以获取i...j区间中可以获取到的最大值（j相当于上一个i），那么比较并更新最大值
    //返回可获取到的最大值
    let res = 0
    let i = nums.length - 1
    let sum = 0
    while (i >= 0) {
        if (sum >= nums[i]) {
            sum += nums[i]
        } else {
            res = sum > res ? sum : res
            sum = nums[i]
        }
        i--
    }
    res = sum > res ? sum : res
    return res
};