package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//给你两个字符串数组 event1 和 event2 ，表示发生在同一天的两个闭区间时间段事件，其中：
//
//event1 = [startTime1, endTime1] 且
//event2 = [startTime2, endTime2]
//事件的时间为有效的 24 小时制且按 HH:MM 格式给出。
//
//当两个事件存在某个非空的交集时（即，某些时刻是两个事件都包含的），则认为出现 冲突 。
//
//如果两个事件之间存在冲突，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
//输出：true
//解释：两个事件在 2:00 出现交集。
//示例 2：
//
//输入：event1 = ["01:00","02:00"], event2 = ["01:20","03:00"]
//输出：true
//解释：两个事件的交集从 01:20 开始，到 02:00 结束。
//示例 3：
//
//输入：event1 = ["10:00","11:00"], event2 = ["14:00","15:00"]
//输出：false
//解释：两个事件不存在交集。
// 
//
//提示：
//
//evnet1.length == event2.length == 2.
//event1[i].length == event2[i].length == 5
//startTime1 <= endTime1
//startTime2 <= endTime2
//所有事件的时间都按照 HH:MM 格式给出
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/determine-if-two-events-have-conflict
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 判断两个事件是否存在冲突 {
    @Test
    fun 判断两个事件是否存在冲突() {
        println("判断两个事件是否存在冲突:${haveConflict(arrayOf("05:10", "15:05"), arrayOf("14:59", "19:17"))}")
    }

    fun haveConflict(event1: Array<String>, event2: Array<String>): Boolean {
        //直接比较
        //首先令event1的startTime<event2的startTime
        //然后在比较event1的endTime和event2的startTime
        //如果start2<=end1,说明有交集,否则没交集
        if (event1[0] > event2[0]) {
            return haveConflict(event2, event1)
        }

        val end1 = event1[1].substring(0, 2).toInt() * 60 + event1[1].substring(3).toInt()
        val start2 = event2[0].substring(0, 2).toInt() * 60 + event2[0].substring(3).toInt()

        return start2 <= end1
    }
}