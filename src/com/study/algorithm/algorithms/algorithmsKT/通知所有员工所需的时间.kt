package com.study.algorithm.algorithms.algorithmsKT

import org.junit.Test

//公司里有 n 名员工，每个员工的 ID 都是独一无二的，编号从 0 到 n - 1。公司的总负责人通过 headID 进行标识。
//
//在 manager 数组中，每个员工都有一个直属负责人，其中 manager[i] 是第 i 名员工的直属负责人。对于总负责人，manager[headID] = -1。题目保证从属关系可以用树结构显示。
//
//公司总负责人想要向公司所有员工通告一条紧急消息。他将会首先通知他的直属下属们，然后由这些下属通知他们的下属，直到所有的员工都得知这条紧急消息。
//
//第 i 名员工需要 informTime[i] 分钟来通知它的所有直属下属（也就是说在 informTime[i] 分钟后，他的所有直属下属都可以开始传播这一消息）。
//
//返回通知所有员工这一紧急消息所需要的 分钟数 。
//
// 
//
//示例 1：
//
//输入：n = 1, headID = 0, manager = [-1], informTime = [0]
//输出：0
//解释：公司总负责人是该公司的唯一一名员工。
//示例 2：
//
//
//
//输入：n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
//输出：1
//解释：id = 2 的员工是公司的总负责人，也是其他所有员工的直属负责人，他需要 1 分钟来通知所有员工。
//上图显示了公司员工的树结构。
// 
//
//提示：
//
//1 <= n <= 10^5
//0 <= headID < n
//manager.length == n
//0 <= manager[i] < n
//manager[headID] == -1
//informTime.length == n
//0 <= informTime[i] <= 1000
//如果员工 i 没有下属，informTime[i] == 0 。
//题目 保证 所有员工都可以收到通知。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/time-needed-to-inform-all-employees
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 通知所有员工所需的时间 {

    @Test
    fun 通知所有员工所需的时间() {
        System.out.println(
            "通知所有员工所需的时间:${
                numOfMinutes(
                    9, 0,
                    intArrayOf(-1, 0, 1, 1, 1, 2, 3, 4, 7),
                    intArrayOf(1, 1, 3, 2, 1, 0, 0, 1, 0)
                )
            }"
        )
    }

    fun numOfMinutes(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {
        //树,深度优先搜索
        //1.因为题目保证从属关系可以用树结构显示。所以,第一步,根据manager数组,构建一颗员工从属关系的树形结构
        //2.遍历这颗关系树,计算每个员工的每个下属通知的最大时间
        //3.当前员工通知其所有下属(直系和非直系)的最大时间=max(其所有下属再通知其下属的时间)+当前员工通知下属的时间
        //4.返回总负责人通知其下属的最大时间

        //构树
        val map = HashMap<Int, MutableList<Int>>()
        for (i in manager.indices) {
            (map[manager[i]] ?: run {
                ArrayList<Int>().also {
                    map[manager[i]] = it
                }
            }).add(i)
        }

        return maxTime(headID, map, informTime)
    }

    fun maxTime(root: Int, map: Map<Int, List<Int>>, informTime: IntArray): Int {

        //直系下属
        val child = map[root] ?: return 0

        var max = 0

        for (c in child) {
            //每个直系下属通知的最大时间的最大值
            max = Math.max(max, maxTime(c, map, informTime))
        }

        //当前员工通知的最大时间
        return max + informTime[root]
    }
}