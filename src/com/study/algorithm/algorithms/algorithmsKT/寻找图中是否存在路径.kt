package com.study.algorithm.algorithms.algorithmsKT

//有一个具有 n 个顶点的 双向 图，其中每个顶点标记从 0 到 n - 1（包含 0 和 n - 1）。图中的边用一个二维整数数组 edges 表示，其中 edges[i] = [ui, vi] 表示顶点 ui 和顶点 vi 之间的双向边。 每个顶点对由 最多一条 边连接，并且没有顶点存在与自身相连的边。
//
//请你确定是否存在从顶点 source 开始，到顶点 destination 结束的 有效路径 。
//
//给你数组 edges 和整数 n、source 和 destination，如果从 source 到 destination 存在 有效路径 ，则返回 true，否则返回 false 。
//
// 
//
//示例 1：
//
//
//输入：n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
//输出：true
//解释：存在由顶点 0 到顶点 2 的路径:
//- 0 → 1 → 2
//- 0 → 2
//示例 2：
//
//
//输入：n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
//输出：false
//解释：不存在由顶点 0 到顶点 5 的路径.
// 
//
//提示：
//
//1 <= n <= 2 * 105
//0 <= edges.length <= 2 * 105
//edges[i].length == 2
//0 <= ui, vi <= n - 1
//ui != vi
//0 <= source, destination <= n - 1
//不存在重复边
//不存在指向顶点自身的边
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-if-path-exists-in-graph
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 寻找图中是否存在路径 {
    fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
        //并查集
        //使用并查集来判断图中两个点是否在同一个连通分量中,以判断两点是否有路径可以相连
        val unionFun = UnionFun(n)
        for (arr in edges) {
            unionFun.merge(arr[0], arr[1])
        }
        return unionFun.get(source) == unionFun.get(destination)
    }

    class UnionFun(n: Int) {

        val nums: IntArray = IntArray(n) {
            it
        }

        fun get(x: Int): Int {
            if (nums[x] == x) {
                return x
            }
            nums[x] = get(nums[x])
            return nums[x]
        }

        fun merge(x: Int, y: Int) {
            val parent = get(x)
            val child = get(y)
            nums[child] = parent
        }
    }
}