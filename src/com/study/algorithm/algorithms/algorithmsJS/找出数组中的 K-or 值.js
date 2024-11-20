/**
给你一个整数数组 nums 和一个整数 k 。让我们通过扩展标准的按位或来介绍 K-or 操作。在 K-or 操作中，如果在 nums 中，至少存在 k 个元素的第 i 位值为 1 ，那么 K-or 中的第 i 位的值是 1 。

返回 nums 的 K-or 值。



示例 1：

输入：nums = [7,12,9,8,9,15], k = 4
输出：9
解释：
用二进制表示 numbers：
Number	Bit 3	Bit 2	Bit 1	Bit 0
7	0	1	1	1
12	1	1	0	0
9	1	0	0	1
8	1	0	0	0
9	1	0	0	1
15	1	1	1	1
Result = 9	1	0	0	1
位 0 在 7, 9, 9, 15 中为 1。位 3 在 12, 9, 8, 9, 15 中为 1。 只有位 0 和 3 满足。结果是 (1001)2 = 9。
示例 2：

输入：nums = [2,12,1,11,4,5], k = 6
输出：0
解释：没有位在所有 6 个数字中都为 1，如 k = 6 所需要的。所以，答案为 0。
示例 3：

输入：nums = [10,8,5,9,11,6,8], k = 1
输出：15
解释：因为 k == 1 ，数组的 1-or 等于其中所有元素按位或运算的结果。因此，答案为 10 OR 8 OR 5 OR 9 OR 11 OR 6 OR 8 = 15 。


提示：

1 <= nums.length <= 50
0 <= nums[i] < 231
1 <= k <= nums.length
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var findKOr = function(nums, k) {
    //排序，位运算
    //首先对数组进行，排序，方便后续的计算和剪枝
    //记录当前判断位step，结果值res，以及下一次遍历数组时开始的下标index
    //统计当前位step下，数组中共有多少个元素该位值为1，记录数量count
    //当count>=k时，将当前位的值加入结果中，并且进行下一轮循环，step++
    //剪枝，由于数组是升序的：
    //1. 当1 << step) > nums[i]时，更新下一次遍历的起始下标index = i + 1，因为当前位已经比当前数组元素的值还要大，那么下一次进位后，进位的值肯定也比当前数组元素大，不必进行重复计算。
    //2. 当nums.length - index < k时，退出循环，返回结果，因为剩余元素的数量已经小于k，不必进行后续的计算。
    nums.sort((a, b) => a - b)
    let step = 0
    let index = 0
    let res = 0
    while (step < 31) {
        if (nums.length - index < k) {
            return res
        }
        let count = 0
        for (let i = index; i < nums.length; i++) {
            if ((1 << step) > nums[i]) {
                index = i + 1
                continue
            }
            if ((nums[i] & (1 << step)) != 0) {
                count++
            }
            if (count >= k) {
                res |= (1 << step)
                break
            }
        }
        step++
    }
    return res
};