package com.study.algorithm.algorithms.algorithmsKT

//在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
//
//返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望下标数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。
//
// 
//
//示例 1：
//
//输入：time = [30,20,150,100,40]
//输出：3
//解释：这三对的总持续时间可被 60 整除：
//(time[0] = 30, time[2] = 150): 总持续时间 180
//(time[1] = 20, time[3] = 100): 总持续时间 120
//(time[1] = 20, time[4] = 40): 总持续时间 60
//示例 2：
//
//输入：time = [60,60,60]
//输出：3
//解释：所有三对的总持续时间都是 120，可以被 60 整除。
// 
//
//提示：
//
//1 <= time.length <= 6 * 104
//1 <= time[i] <= 500
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class `总持续时间可被 60 整除的歌曲` {
    fun numPairsDivisibleBy60(time: IntArray): Int {
        //哈希表
        //将时间除以60后的余数加入哈希表,统计每个余数出现的次数
        //那么在遍历的过程中,如果当前歌曲的时间的是余数remainder,那么,就需要与leftRemainder=(60-remainder)%60的余数的歌曲进行配对
        //加上map[leftRemainder]后,再将当前余数加入哈希表统计的计数中
        val map = HashMap<Int, Int>()
        var res = 0
        for (t in time) {
            //当前余数
            val remainder = t % 60
            //剩余需要求的余数
            val leftRemainder = (60 - remainder) % 60
            //结果数量+剩余余数的数量
            res += map[leftRemainder] ?: 0
            //将当前余数进行计数+1
            map[remainder] = (map[remainder] ?: 0) + 1
        }
        return res
    }
}