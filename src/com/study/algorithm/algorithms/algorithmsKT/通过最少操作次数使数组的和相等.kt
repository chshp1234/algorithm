package com.study.algorithm.algorithms.algorithmsKT

//给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
//
//每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
//
//请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
//
// 
//
//示例 1：
//
//输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
//输出：3
//解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
//- 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
//- 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
//- 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。
//示例 2：
//
//输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
//输出：-1
//解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
//示例 3：
//
//输入：nums1 = [6,6], nums2 = [1]
//输出：3
//解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
//- 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
//- 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
//- 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。
// 
//
//提示：
//
//1 <= nums1.length, nums2.length <= 105
//1 <= nums1[i], nums2[i] <= 6
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 通过最少操作次数使数组的和相等 {
    fun minOperations(nums1: IntArray, nums2: IntArray): Int {
        //排序,贪心
        //1.排除不可能的情况,当一个数组的数量比另一个数组数量的6倍还大时,肯定不可能使得和相等
        //2.计算两个数组的和sum1,和sum2,如果和已经相等,那么不用经过修改
        //3.对两个数组进行排序,贪心得进行修改(设sum1为较大的和),记录修改步骤数step:
        //(1).和较大的数组,从末端开始遍历,使得每个数变成1,设差值为diff1
        //(2).和较小的数组从起始端开始遍历,使得每个数变成6,设差值为diff2
        //(3).如果diff1比diff2大,则修改和较大的那个数组,令末端的数字为1,此时和为sum1-diff1,step+1
        //(4).如果diff1比diff2小,则修改和较小的那个数组,令始端的数字为6,此时和为sum2+diff2,step+1
        //(5).修改某个数组后,如果sum1<sum2,则此修改步数可以使得sum1=sum2,退出循环
        //(6).如果有一个数组遍历到边界了,还没使得sum1<sum2,那么再继续按照(3)或者(4)的步骤,遍历判断另一个数组,直到sum1<sum2为止
        //4.返回修改步数step

        //不可能的情况
        if (nums1.size > 6 * nums2.size || nums1.size * 6 < nums2.size) {
            return -1
        }
        val sum1 = nums1.sum()
        val sum2 = nums2.sum()
        if (sum1 == sum2) {
            return 0
        }
        nums1.sort()
        nums2.sort()
        return if (sum1 > sum2) {
            minOperations2(nums1, sum1, nums2, sum2)
        } else {
            minOperations2(nums2, sum2, nums1, sum1)
        }
    }

    //sum1>sum2
    fun minOperations2(nums1: IntArray, sum1: Int, nums2: IntArray, sum2: Int): Int {
        var s1 = sum1
        var s2 = sum2
        var index1 = nums1.size - 1
        var index2 = 0

        var step = 0

        while (index1 >= 0 && index2 < nums2.size) {
            //修改两个数组的差值
            val diff1 = nums1[index1] - 1
            val diff2 = 6 - nums2[index2]
            //判断哪个差值比较大,先修改哪个数组
            if (diff1 > diff2) {
                step++
                index1--
                s1 -= diff1
                if (s1 <= s2) {
                    break
                }
            } else {
                step++
                index2++
                s2 += diff2
                if (s1 <= s2) {
                    break
                }
            }

        }

        //还没达到条件,继续修改另一个数组
        //因为已经去除不可能的情况,所以此处肯定会找到一个值使得s1 > s2
        if (s1 > s2) {
            if (index1 >= 0) {
                do {
                    step++
                    s1 -= (nums1[index1++] - 1)
                    if (s1 <= s2) {
                        break
                    }
                } while (index1 >= 0)
            } else {
                do {
                    step++
                    s2 += (6 - nums2[index2++])
                    if (s1 <= s2) {
                        break
                    }
                } while (index1 >= 0)
            }
        }

        return step
    }
}