package com.study.algorithm.algorithms.algorithmsKT

//给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：
//
//0 <= i < j < k < nums.length
//nums[i]、nums[j] 和 nums[k] 两两不同 。
//换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
//返回满足上述条件三元组的数目。
//
// 
//
//示例 1：
//
//输入：nums = [4,4,2,4,3]
//输出：3
//解释：下面列出的三元组均满足题目条件：
//- (0, 2, 4) 因为 4 != 2 != 3
//- (1, 2, 4) 因为 4 != 2 != 3
//- (2, 3, 4) 因为 2 != 4 != 3
//共计 3 个三元组，返回 3 。
//注意 (2, 0, 4) 不是有效的三元组，因为 2 > 0 。
//示例 2：
//
//输入：nums = [1,1,1,1,1]
//输出：0
//解释：不存在满足条件的三元组，所以返回 0 。
// 
//
//提示：
//
//3 <= nums.length <= 100
//1 <= nums[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-unequal-triplets-in-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 数组中不等三元组的数目 {
    //哈希表
    //用哈希表记录每个数字出现的次数
    //当哈希表小于3时,无法凑成三元组,返回0
    //将元素加入列表list中,记录每两两配对的元组的数量n
    //当以i为三元组最右侧元素时,res=res+n*list[i].value,并更新n=sum(list[0..i].value*list[i].value),计算处以i为二元组元素配对时的数量n
    fun unequalTriplets(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        for (n in nums) {
            map[n] = 1 + (map[n] ?: 0)
        }
        if (map.size < 3) {
            return 0
        }
        var res = 0
        val list = ArrayList(map.entries)
        var n = list[0].value * list[1].value
        for (i in 2 until list.size) {
            res += n * list[i].value
            for (j in 0 until i) {
                n += list[j].value * list[i].value
            }
        }

        return res
    }

    //官方题解三这个以当前元素i为三元组中间数秒啊,左边不管有几个元素,有没有重复,反正只选一个,右边也一样. 😂我这个以当前元素为三元组右侧,就还得考虑左边两个元素不能有重复的情况.
    //方法三：哈希表
    //类似于方法二，我们可以使用哈希表count 记录各个元素的数目，
    // 然后遍历哈希表（此时数组元素按照哈希表的遍历顺序进行排列），
    // 记当前遍历的元素数目 v，先前遍历的元素总数目为 t，那么以当前遍历的元素为中间元素的符合条件的三元组数目为：t×v×(n−t−v)
    //
    //对以上结果求和并返回最终结果。
    //
    //class Solution {
    //    public int unequalTriplets(int[] nums) {
    //        Map<Integer, Integer> count = new HashMap<>();
    //        for (int x : nums) {
    //            count.merge(x, 1, Integer::sum);
    //        }
    //        int res = 0, n = nums.length, t = 0;
    //        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
    //            res += t * entry.getValue() * (n - t - entry.getValue());
    //            t += entry.getValue();
    //        }
    //        return res;
    //    }
    //}
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode.cn/problems/number-of-unequal-triplets-in-array/solution/shu-zu-zhong-bu-deng-san-yuan-zu-de-shu-lnpsn/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}