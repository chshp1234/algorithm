package com.study.algorithm.algorithms.algorithmsKT

import java.util.*

//给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
//
//返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
//
// 
//
//示例 1：
//
//输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
//输出：[2,11,7,15]
//示例 2：
//
//输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
//输出：[24,32,8,12]
// 
//
//提示：
//
//1 <= nums1.length <= 105
//nums2.length == nums1.length
//0 <= nums1[i], nums2[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/advantage-shuffle
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 优势洗牌 {
    fun advantageCount(nums1: IntArray, nums2: IntArray): IntArray {

        //排序,双指针,贪心
        //对nums1和nums2进行排序,同时为了将nums2原先的下标保存起来,使用一个哈希表保存原值和对应的下标
        //比较nums1和nums2的头元素(各指针指向的元素)
        //如果nums1[idnex1]<=nums2[index2],则跳过当前的nums1元素,并将其加入跳过列表junk,待后续添加
        //如果nums1[idnex1]>nums2[index2],则将当前nums1的元素加入到原先nums2的元素的下标处(根据之前哈希表存的下标)
        //最后如果nums2的元素没有遍历完,说明nums1中有元素跳过,从junk列表中取出跳过的元素,加入到后续的nums2的元素对应的下标处
        val size = nums1.size

        //排序
        val result = IntArray(size)
        Arrays.sort(nums1)
        //使用一个二维数组,保存原值和对应的下标值
        val map2 = Array(size) {
            intArrayOf(nums2[it], it)
        }.apply {
            sortWith(object : Comparator<IntArray> {
                override fun compare(o1: IntArray, o2: IntArray): Int {
                    return o1[0] - o2[0]
                }
            })
        }

        //两个数组指针
        var index1 = 0
        var index2 = 0
        //跳过列表
        val junk = mutableListOf<Int>()

        while (index1 < size && index2 < size) {
            //如果nums1中的元素小或等于
            if (nums1[index1] <= map2[index2][0]) {
                //加入跳过列表,index1++
                junk.add(index1++)
            } else {
                //如果nums1中的元素大,将nums1中的元素加入此nums2元素对应的原先的下标处
                result[map2[index2][1]] = nums1[index1]
                //两个指针同时右移
                index1++
                index2++
            }
        }

        //如果index2<size,说明nums1还有剩余元素没进行填充
        if (index2 < size) {
            index1 = 0
            do {
                //取出跳过列表的元素,加入到后续的nums2的元素对应的原下标中
                result[map2[index2++][1]] = nums1[junk[index1++]]
            } while (index2 < size)
        }

        return result
    }
}