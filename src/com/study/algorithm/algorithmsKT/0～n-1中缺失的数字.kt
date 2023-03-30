package com.study.algorithm.algorithmsKT

//一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
//
// 
//
//示例 1:
//
//输入: [0,1,3]
//输出: 2
//示例 2:
//
//输入: [0,1,2,3,4,5,6,7,9]
//输出: 8
// 
//
//限制：
//
//1 <= 数组长度 <= 10000
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class `0～n-1中缺失的数字` {
    fun missingNumber(nums: IntArray): Int {
        //二分查找
        //因为数组中的数字（0..n-1）在数组中是递增排列
        //那么在缺失的数字前，数组下标和数组元素值将相同，即nums[i]=i
        //在缺失的数字后面，数组下标将比元素值小1，即nums[i]=i+1
        //我们可以使用二分查找，若目标值nums[mid]和下标mid相同，那么指针left=mid+1；否则指针right=mid-1
        //最后返回left指针即可，因为此跳出循环后，left所处的下标位置就是缺失的元素值
        var l = 0
        var r = nums.size - 1
        var mid: Int
        while (l <= r) {
            mid = (l + r) / 2
            if (nums[mid] == mid) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        "".hashCode()
        return l
    }

    val innr = fun(t: Int): Int {
        Thread {
            return@Thread
        }
        val m = ::missingNumber
        print(m(IntArray(6)))
        print(t)
        return 1
    }

}