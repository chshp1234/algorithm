package com.study.algorithm.algorithmsKT

//给你一个下标从 0 开始的二维整数数组 pairs ，其中 pairs[i] = [starti, endi] 。如果 pairs 的一个重新排列，满足对每一个下标 i （ 1 <= i < pairs.length ）都有 endi-1 == starti ，那么我们就认为这个重新排列是 pairs 的一个 合法重新排列 。
//
//请你返回 任意一个 pairs 的合法重新排列。
//
//注意：数据保证至少存在一个 pairs 的合法重新排列。
//
// 
//
//示例 1：
//
//输入：pairs = [[5,1],[4,5],[11,9],[9,4]]
//输出：[[11,9],[9,4],[4,5],[5,1]]
//解释：
//输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
//end0 = 9 == 9 = start1
//end1 = 4 == 4 = start2
//end2 = 5 == 5 = start3
//示例 2：
//
//输入：pairs = [[1,3],[3,2],[2,1]]
//输出：[[1,3],[3,2],[2,1]]
//解释：
//输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
//end0 = 3 == 3 = start1
//end1 = 2 == 2 = start2
//重新排列后的数组 [[2,1],[1,3],[3,2]] 和 [[3,2],[2,1],[1,3]] 都是合法的。
//示例 3：
//
//输入：pairs = [[1,2],[1,3],[2,1]]
//输出：[[1,2],[2,1],[1,3]]
//解释：
//输出的是一个合法重新排列，因为每一个 endi-1 都等于 starti 。
//end0 = 2 == 2 = start1
//end1 = 1 == 1 = start2
// 
//
//提示：
//
//1 <= pairs.length <= 105
//pairs[i].length == 2
//0 <= starti, endi <= 109
//starti != endi
//pairs 中不存在一模一样的数对。
//至少 存在 一个合法的 pairs 重新排列。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/valid-arrangement-of-pairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 合法重新排列数对 {
    fun validArrangement(pairs: Array<IntArray>): Array<IntArray> {
        //图,欧拉通路
        //对于本题而言，我们首先需要找到欧拉通路的起始节点：
        //如果图中所有节点的入度和出度都相等，那么从任意节点开始都存在欧拉通路；
        //如果图中存在一个节点的出度比入度恰好多 1，另一个节点的入度恰好比出度多 1，那么欧拉通路必须从前一个节点开始，到后一个节点结束。
        //除此之外的有向图都不存在欧拉通路，本题保证了至少存在一个合法排列，因此图已经是上述的两种情况之一。
        //
        //当我们确定起始节点后，就可以使用深度优先搜索(Hierholzer 算法)求解欧拉通路了。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode.cn/problems/valid-arrangement-of-pairs/solution/he-fa-zhong-xin-pai-lie-shu-dui-by-leetc-h8rl/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

        //哈希表,记录每个点出度的数量,每个点入度的点列表
        val graphOut = mutableMapOf<Int, Int>()
        val graphIn = mutableMapOf<Int, MutableList<Int>>()

        //构图
        for (arr in pairs) {
            graphOut[arr[0]] = graphOut.getOrDefault(arr[0], 0) + 1
            graphOut[arr[1]] ?: run { graphOut[arr[1]] = 0 }

            (graphIn[arr[1]] ?: run {
                val list = mutableListOf<Int>()
                graphIn[arr[1]] = list
                list
            }).add(arr[0])

            graphIn[arr[0]] ?: run { graphIn[arr[0]] = mutableListOf() }
        }

        //寻找终点(由于我们根据入度来搜索,所以这里找到的点位终点)
        var node = -1
        graphOut.entries.forEach {
            if (graphIn[it.key]!!.size > it.value) {
                node = it.key
                return@forEach
            }
        }
        //如果不存在,则选取任意一点
        if (node == -1) {
            node = pairs[0][0]
        }

        val result = arrayListOf<IntArray>()
        val stack = arrayListOf<Int>()
        //构造通路
        dfs(stack, graphIn, node)

        for (i in 1 until stack.size) {
            result.add(intArrayOf(stack[i - 1], stack[i]))
        }

        return result.toTypedArray()
    }

    //Hierholzer 算法
    //Hierholzer 算法证明了一点：当存在欧拉路径或欧拉回路时，从合理的起点一直执行 DFS 遍历，最后得到的路径一定是欧拉路径或欧拉回路。
    //
    //Hierholzer 算法用于寻找欧拉路径或欧拉回路（该算法假定图有欧拉路径或欧拉回路），其流程如下：
    //
    //1.从起点出发，进行深度优先搜索（DFS）；
    //2.每次沿着某条边从某个顶点移动到另外一个顶点的时候，都将这条边标记为已走过；
    //3.如果遇到阻塞（即当前顶点没有后续邻边或该顶点的邻边都走过），则将当前顶点加入到栈中，并回溯到上一顶点查找其可移动到的顶点。
    //————————————————
    //版权声明：本文为CSDN博主「SmileGuy17」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
    //原文链接：https://blog.csdn.net/KCDCY/article/details/124732427
    fun dfs(stack: ArrayList<Int>, graphIn: MutableMap<Int, MutableList<Int>>, point: Int) {
        val inList = graphIn[point]
        if (inList == null || inList.size == 0) {
            stack.add(point)
            return
        }

        val iterator = inList.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            iterator.remove()
            dfs(stack, graphIn, next)
        }
        stack.add(point)
    }
}