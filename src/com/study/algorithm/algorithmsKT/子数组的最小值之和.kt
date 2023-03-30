package com.study.algorithm.algorithmsKT

import java.util.*

//给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
//
//由于答案可能很大，因此 返回答案模 10^9 + 7 。
//
// 
//
//示例 1：
//
//输入：arr = [3,1,2,4]
//输出：17
//解释：
//子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
//最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
//示例 2：
//
//输入：arr = [11,81,94,43,3]
//输出：444
// 
//
//提示：
//
//1 <= arr.length <= 3 * 104
//1 <= arr[i] <= 3 * 104
// 
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/sum-of-subarray-minimums
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 子数组的最小值之和 {
    fun sumSubarrayMins(arr: IntArray): Int {
        //两层循环
        //遍历每个子数组,比较并加上其中的最小值
        var sum = 0
        val dp = IntArray(arr.size) {
            sum = (sum + arr[it]) % 10000007
            arr[it]
        }

        for (i in 1 until arr.size) {
            for (j in i until arr.size) {
                val index = j - i
                dp[index] = Math.min(dp[index], arr[j])
                sum = (sum + dp[index]) % 10000007
            }
        }

        return sum

    }

    fun sumSubarrayMins2(arr: IntArray): Int {
        //单调栈
        //根据每个元素的贡献值,求出所有子数组的最小元素和
        //贡献值,就是这个元素在加入结果中出现的次数,那么这个元素在一段区间内,肯定是最小元素
        //元素j是区间[i..k]中的最小元素的下标,j>=i&&j<=k
        //那么元素j在这个区间中,所有子数组出现的次数就为(j-i+1)*(k-j+1),+1因为要算上j自身,即在区间[i..k]的子数组中,必须包含元素j
        //对于arr数组中的每一个元素i,维护其左侧到i为止,所有大于arr[i]的元素的数量left[i],和其右侧从i开始所有大于arr[i]的元素的数量right[i]
        //那么元素i在结果中的贡献(出现的次数*arr[i])为left[i]*right[i]*arr[i](注意结果取模)
        //最后将数组每个元素按此进行计算,即可得出所有子数组的最小元素的和
        //对于如何找到一个元素左右两侧大于其的元素的数量,可以使用单调栈,维护一个单调递增序列(栈中存元素下标),栈顶元素始终为其左侧第一个元素,并且栈是单调递增的
        //并设一个数组dp[i],维护大于元素i的两侧元素的数量
        //1.从第二个元素开始从左往右遍历(第1个元素,大于其的左侧元素数量为1),先计算大于元素的左侧元素的数量
        //如果arr[top] > arr[i],dp[i] += dp[top],否则退出循环,将i加入栈中
        //2.最后一个元素右侧数量为1,可以计算其贡献值
        //3.从第倒数第二个元素开始从右往左遍历(最后个元素,大于其的右侧元素数量为1),计算大于元素的右侧元素的数量
        //如果arr[top] >= arr[i],dp[i] += dp[top],否则退出循环,将i加入栈中
        //第二步开始,就可以得出每个元素i左右两侧大于其的元素的数量了,可以计算元素i的贡献了

        var sum: Long = 0
        val size = arr.size

        val stack: Deque<Int> = LinkedList()
        val dp = LongArray(size)

        stack.push(0)
        dp[0] = 1

        //先计算并统计每个元素中大于改元素的左侧元素的数量
        for (i in 1 until size) {
            dp[i] = 1
            while (!stack.isEmpty()) {
                val top = stack.pop()
                if (arr[top] <= arr[i]) {
                    stack.push(top)
                    break
                }
                dp[i] += dp[top]
            }
            stack.push(i)
        }

        //计算最后一个元素的贡献值
        sum = (dp[size - 1] * arr[size - 1])

        stack.clear()
        stack.push(size - 1)
        dp[size - 1] = 1

        //再计算并统计每个元素中大于改元素的右侧元素的数量,并更新结果值
        for (i in size - 2 downTo 0) {
            //右侧元素的数量
            var size = 1L
            while (!stack.isEmpty()) {
                val top = stack.pop()
                if (arr[top] < arr[i]) {
                    stack.push(top)
                    break
                }
                size += dp[top]
            }
            stack.push(i)

            //计算结果值
            sum = (((((dp[i] * size) % 1000000007) * arr[i]) % 1000000007) + sum) % 1000000007
            //更新大于该元素的右侧元素的数量
            dp[i] = size
        }

        return sum.toInt()
    }
}