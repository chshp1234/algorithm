package com.study.algorithm.algorithms.algorithmsKT

import java.util.*

//在一个有向图中，节点分别标记为 0, 1, ..., n-1。图中每条边为红色或者蓝色，且存在自环或平行边。
//
//red_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的红色有向边。类似地，blue_edges 中的每一个 [i, j] 对表示从节点 i 到节点 j 的蓝色有向边。
//
//返回长度为 n 的数组 answer，其中 answer[X] 是从节点 0 到节点 X 的红色边和蓝色边交替出现的最短路径的长度。如果不存在这样的路径，那么 answer[x] = -1。
//
// 
//
//示例 1：
//
//输入：n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
//输出：[0,1,-1]
//示例 2：
//
//输入：n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
//输出：[0,1,-1]
//示例 3：
//
//输入：n = 3, red_edges = [[1,0]], blue_edges = [[2,1]]
//输出：[0,-1,-1]
//示例 4：
//
//输入：n = 3, red_edges = [[0,1]], blue_edges = [[1,2]]
//输出：[0,1,2]
//示例 5：
//
//输入：n = 3, red_edges = [[0,1],[0,2]], blue_edges = [[1,0]]
//输出：[0,1,1]
// 
//
//提示：
//
//1 <= n <= 100
//red_edges.length <= 400
//blue_edges.length <= 400
//red_edges[i].length == blue_edges[i].length == 2
//0 <= red_edges[i][j], blue_edges[i][j] < n
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/shortest-path-with-alternating-colors
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 颜色交替的最短路径 {
    fun shortestAlternatingPaths(n: Int, redEdges: Array<IntArray>, blueEdges: Array<IntArray>): IntArray {
        //图，广度优先搜索
        //首先定义一个类Edge表示一条，idRed表示这条边是否是红色，nextNode表示下一个节点的值
        //在定义图HashMap<Int, MutableList<Edge>>，表示每个节点以及其对应的每一条边
        //遍历红色边集，和蓝色边集，构图
        //从0点出发，进行广度优先搜索。用visitRed表示到达某个点的红色边是否遍历过，用visitBlue表示到达某个点的蓝色边是否遍历过
        //广度优先搜索时每到一层，层数加一，如果从0点到达此出度点的路径值没更新过，那么更新这条边对应的出度点的值为当前层数。因为是广度优先搜索，所以第一次更新路径值就是最小值
        //同时，需要把出度点对应的边找出来，并判断是否需要加入：
        //1.这条边的颜色和上一条边的颜色不相同
        //2.到达下一个点的对应的颜色的边没有遍历过
        //满足后，下一条边才可继续加入遍历
        //直到广度优先搜索结束即可
        val res = IntArray(n) {
            -1
        }
        res[0] = 0

        //构图
        val graph = HashMap<Int, MutableList<Edge>>()
        for (array in redEdges) {
            (graph[array[0]] ?: run {
                ArrayList<Edge>().also {
                    graph[array[0]] = it
                }
            }).add(Edge(true, array[1]))
        }

        for (array in blueEdges) {
            (graph[array[0]] ?: run {
                ArrayList<Edge>().also {
                    graph[array[0]] = it
                }
            }).add(Edge(false, array[1]))
        }

        val edges = graph[0]

        edges?.let {
            val queue: Queue<Edge> = LinkedList()
            //到达点n的红色边是否遍历过
            val visitRed = BooleanArray(n)
            //到达点n的蓝色边是否遍历过
            val visitBlue = BooleanArray(n)
            visitRed[0] = true
            visitBlue[0] = true
            for (e in it) {
                queue.offer(e)
            }

            var len = 0
            while (!queue.isEmpty()) {
                len++
                val size = queue.size
                var edge: Edge
                for (i in 0 until size) {
                    edge = queue.poll()
                    //如果这个点没有更新值，则此路径就是最短路径
                    if (res[edge.nextNode] == -1) {
                        res[edge.nextNode] = len
                    }
                    val nextEdges = graph[edge.nextNode]
                    nextEdges?.let { next ->
                        for (e in next) {

                            if (e.idRed == !edge.idRed &&
                                (if (e.idRed) !visitRed[e.nextNode] else !visitBlue[e.nextNode])
                            ) {
                                //这条边和上条边颜色不相同
                                //到达下一个点的对应的颜色的边没有遍历过
                                queue.offer(e)
                            }
                        }
                    }

                    if (edge.idRed) {
                        //如果这条边是红色
                        visitRed[edge.nextNode] = true
                    } else {
                        //如果这条边是蓝色
                        visitBlue[edge.nextNode] = true
                    }
                }
            }
        }

        return res
    }

    //边：是否是红色，出度节点值
    class Edge(val idRed: Boolean, val nextNode: Int)
}