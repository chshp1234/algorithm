package com.study.algorithm.algorithms.algorithmsKT

//给你一个下标从 0 开始的整数数组 nums 。一次操作中，选择 任意 非负整数 x 和一个下标 i ，更新 nums[i] 为 nums[i] AND (nums[i] XOR x) 。
//
//注意，AND 是逐位与运算，XOR 是逐位异或运算。
//
//请你执行 任意次 更新操作，并返回 nums 中所有元素 最大 逐位异或和。
//
//
//
//示例 1：
//
//输入：nums = [3,2,4,6]
//输出：7
//解释：选择 x = 4 和 i = 3 进行操作，num[3] = 6 AND (6 XOR 4) = 6 AND 2 = 2 。
//现在，nums = [3, 2, 4, 2] 且所有元素逐位异或得到 3 XOR 2 XOR 4 XOR 2 = 7 。
//可知 7 是能得到的最大逐位异或和。
//注意，其他操作可能也能得到逐位异或和 7 。
//示例 2：
//
//输入：nums = [1,2,3,9,2]
//输出：11
//解释：执行 0 次操作。
//所有元素的逐位异或和为 1 XOR 2 XOR 3 XOR 9 XOR 2 = 11 。
//可知 11 是能得到的最大逐位异或和。
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 108
class 操作后的最大异或和 {
    //思路
    //想了10分钟,确实脑筋急转弯.
    //
    //某一位上要为1,那么所有数的这一位为1的数目必须为奇数,即在n次操作后,尽量保证每一位上的1的出现频次为奇数.
    //这题精髓就在于num[i]的计算方式,相当于清除这个数的某一位上的1,另外可以进行n次这样的操作.
    //那么如果所有数中,在某一位上有1,那么n次操作后这一位肯定可以是1,因为如果这一位的1的数量是偶数,就清除,如果是奇数就不动
    //有人会好奇,每个数字在二进制上会有好几位是1,会不会有影响.放心,完全不会. 因为这题的"精髓",最极端情况,可以使得每个元素只保留其中一个有效位的1,比如[3,2,4,6],在n次操作后就会变成[1,2,4,0],最后异或和就为7.
    //
    //解题方法
    //思如如此,解题也就简单了,直接把所有数按位或操作,即可满足所有数的所有有效位上的1都参与到结果中.
    //
    //作者：果然翁
    //链接：https://leetcode.cn/problems/maximum-xor-after-operations/solutions/2311625/que-shi-nao-jin-ji-zhuan-wan-by-chshp-ecow/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    fun maximumXOR(nums: IntArray): Int {
        var res = 0
        for (n in nums) {
            res = res or n
        }
        return res
    }
}