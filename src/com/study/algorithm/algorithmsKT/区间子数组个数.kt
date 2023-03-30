package com.study.algorithm.algorithmsKT

//给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
//
//生成的测试用例保证结果符合 32-bit 整数范围。
//
// 
//
//示例 1：
//
//输入：nums = [2,1,4,3], left = 2, right = 3
//输出：3
//解释：满足条件的三个子数组：[2], [2, 1], [3]
//示例 2：
//
//输入：nums = [2,9,2,5,6], left = 2, right = 8
//输出：7
// 
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//0 <= left <= right <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/number-of-subarrays-with-bounded-maximum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 区间子数组个数 {
    fun numSubarrayBoundedMax(nums: IntArray, left: Int, right: Int): Int {
        //计数
        //为方便描述,我们将小于区间的元素设为0;在区间内的元素设为1;大于区间的元素设为2
        //那么一个连续的子数组中,必须最少包含一个1,且不能有2
        //思路:举例数组[0,0,0,1,0,0],有效子数组数=(3+1)*(2+1),3表示1左侧有3个0,2表示1右侧有2个0,+1表示不包括0,但包括自身
        //数组[0,0,0,1,0,0,1],有效子数组数=(3+1)*(2+1)+(3+1)*(0+1)+(2+1)*(0+1)=(3+1)*(2+1)+(3+1+2+1)*(0+1),
        //前半部分与上个例子相同,后半部分表示左右侧1的左侧有(3+2)个0和1个1,右侧有0个0
        //那么我们可以设count为当前遇到1时,其左侧总共包括自身的0的数量,currentCount表示其右侧0的数量
        //当遇到:
        //2:result=result+count * currentCount,结果累加;count = 0,总数量重置;currentCount = 1,当前数量重置
        //1:result=result+count * currentCount,结果累加;count = count+currentCount,总数量累加;currentCount = 1,当前数量重置
        //0:currentCount++,当前数量累加

        //初始化(相当于遇到2)
        var count = 0
        var currentCount = 1
        var r = 0

        for (num in nums) {
            when {
                num > right -> {
                    r += count * currentCount
                    count = 0
                    currentCount = 1
                }
                num >= left -> {
                    r += count * currentCount
                    count += currentCount
                    currentCount = 1
                }
                else -> {
                    currentCount++
                }
            }
        }

        //因为在遇到1和2时才进行结果累加,所以遍历结束,还需再进行一次计算,防止遗漏
        r += count * currentCount

        return r
    }
}