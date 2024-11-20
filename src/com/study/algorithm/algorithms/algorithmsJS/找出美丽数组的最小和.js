/**
给你两个正整数：n 和 target 。

如果数组 nums 满足下述条件，则称其为 美丽数组 。

nums.length == n.
nums 由两两互不相同的正整数组成。
在范围 [0, n-1] 内，不存在 两个 不同 下标 i 和 j ，使得 nums[i] + nums[j] == target 。
返回符合条件的美丽数组所可能具备的 最小 和，并对结果进行取模 109 + 7。



示例 1：

输入：n = 2, target = 3
输出：4
解释：nums = [1,3] 是美丽数组。
- nums 的长度为 n = 2 。
- nums 由两两互不相同的正整数组成。
- 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
可以证明 4 是符合条件的美丽数组所可能具备的最小和。
示例 2：

输入：n = 3, target = 3
输出：8
解释：
nums = [1,3,4] 是美丽数组。
- nums 的长度为 n = 3 。
- nums 由两两互不相同的正整数组成。
- 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
可以证明 8 是符合条件的美丽数组所可能具备的最小和。
示例 3：

输入：n = 1, target = 1
输出：1
解释：nums = [1] 是美丽数组。


提示：

1 <= n <= 109
1 <= target <= 109
 * @param {number} n
 * @param {number} target
 * @return {number}
 */
var minimumPossibleSum = function(n, target) {
    //贪心，数学
    //因为整体和需要最小，那么优先从最小值1，2，3...开始选择
    //当选择到target / 2时，就该从target开始选择了，因为大于target / 2的值，将会和之前的值相加得到等于target（如果target / 2 >= n，那么直接从1选择到n），将不符合条件。
    //优化：
    //当n比较大时，循环遍历的次数较多，将会超时，那么可以用数学的办法进行一次计算
    //可以发现，每次选择的数是一个等差数列，那么我们可以用等差数列的求和公式进行计算
    //1.先计算1...target / 2区间的数列和
    //2.如果target / 2 >= n，返回结果
    //3.再计算target...target + (n - target / 2 - 1) 区间的和
    //4.每次计算区间和注意对1000000007取余
    //5.返回结果
    let mid = parseInt(target / 2)
    let res = 0

    //从1开始，依次选择每个数并相加
    // let count = 1
    // while (count <= n && count <= mid) {
    //     res = (res + count) % (1000000007)
    //     count++
    // }
    // for (let i = 0; count <= n; i++, count++) {
    //     res = ((res + target) % (1000000007)) % (1000000007)
    // }

    if (mid < n) {
        res = (((1 + mid) * mid) / 2) % (1000000007)
    } else {
        res = (((1 + n) * n) / 2) % (1000000007)
        return res
    }

    res = (res + ((((target + target + n - mid - 1) * (n - mid)) / 2) % (1000000007))) % 1000000007
    //分开计算
    /* res = (res + ((((n - mid - 1) * (n - mid)) / 2) % (1000000007))) % 1000000007
    res = (res + ((target * (n - mid)) % 1000000007)) % 1000000007 */


    return res
};