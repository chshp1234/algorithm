package com.study.algorithm.algorithms.algorithmsKT

//一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
//
//一道菜的 「喜爱时间」系数定义为烹饪这道菜以及之前每道菜所花费的时间乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
//
//请你返回做完所有菜 「喜爱时间」总和的最大值为多少。
//
//你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
//
// 
//
//示例 1：
//
//输入：satisfaction = [-1,-8,0,5,-9]
//输出：14
//解释：去掉第二道和最后一道菜，最大的喜爱时间系数和为 (-1*1 + 0*2 + 5*3 = 14) 。每道菜都需要花费 1 单位时间完成。
//示例 2：
//
//输入：satisfaction = [4,3,2]
//输出：20
//解释：按照原来顺序相反的时间做菜 (2*1 + 3*2 + 4*3 = 20)
//示例 3：
//
//输入：satisfaction = [-1,-4,-5]
//输出：0
//解释：大家都不喜欢这些菜，所以不做任何菜可以获得最大的喜爱时间系数。
// 
//
//提示：
//
//n == satisfaction.length
//1 <= n <= 500
//-1000 <= satisfaction[i] <= 1000
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/reducing-dishes
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 做菜顺序 {
    fun maxSatisfaction(satisfaction: IntArray): Int {
        //贪心,排序
        //要使喜爱时间最大,需要尽可能多的选取每道可使得喜爱时间增大的菜
        //由于每道菜的喜爱时间,由花费总时间和喜爱度相乘确定,那么花费时间越久并且喜爱度大于0,或者花费时间越少并且喜爱度小于0的组合,都会使得"每道菜"的喜爱时间最大化
        //明显,我们就可以根据喜爱度从小到大排序
        //遍历喜爱度列表时,从尾部开始遍历
        //首先定义一个遍历过程中的总喜爱度result,以及后缀和preSum
        //那么当遍历到i时,加上这道菜时的总喜爱度result[i]=result[i+1](因为从尾开始遍历)+preSum(后续所有菜品都将再次计算一遍)
        //要使得result越来越大,只需要preSum>0
        //如果此时result变小了那说明preSum=(preSum+satisfaction[i])<0,那么后续结果肯定会越来越小,此时可以退出循环
        //返回result

        //排序
        satisfaction.sort()
        var result = 0
        var preSum = 0
        for (i in satisfaction.size - 1 downTo 0) {
            //计算当前的后缀和sum
            val sum = preSum + satisfaction[i]
            if (sum < 0) {
                //如果sum<0,退出循环
                break
            }
            //计算当前(result[i])的总喜爱时间
            result += preSum + satisfaction[i]
            //更新当前的后缀和
            preSum = sum
        }
        return result
    }
}