package com.study.algorithm.algorithmsKT

import java.util.*

//给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
//
//给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和  bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
//
// 
//
//示例 1：
//
//输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
//输出：true
//解释：group1 [1,4], group2 [2,3]
//示例 2：
//
//输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
//输出：false
//示例 3：
//
//输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//输出：false
// 
//
//提示：
//
//1 <= n <= 2000
//0 <= dislikes.length <= 104
//dislikes[i].length == 2
//1 <= dislikes[i][j] <= n
//ai < bi
//dislikes 中每一组都 不同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/possible-bipartition
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 可能的二分法 {
    fun possibleBipartition(n: Int, dislikes: Array<IntArray>): Boolean {
        //染色法(标记),广度优先搜索
        //首先,根据dislikes构图graph
        //visit[i]=0时,表示i未遍历,=-1时,表示i分到了组A,=1时,表示i分到了组B
        //那么,如果与i相连的元素中,某个元素和i分到了同一组,说明i和不喜欢的人分到了同一组,那么就不能进行分为两组,返回false
        val graph = Array<MutableList<Int>>(n + 1) {
            mutableListOf()
        }
        //构图
        for (array in dislikes) {
            graph[array[0]].add(array[1])
            graph[array[1]].add(array[0])
        }

        //染色数组,-1和1分别代表两个组,0代表未遍历
        val visit = IntArray(n + 1)

        val queue: Queue<Int> = LinkedList()

        for (i in 1..n) {
            if (visit[i] == 0) {
                //将i分到A组
                visit[i] = -1
                queue.offer(i)
                while (!queue.isEmpty()) {
                    val top = queue.poll()!!
                    //与top相连的元素
                    val next = graph[top]
                    //top所在组别
                    val group = visit[top]
                    for (n in next) {

                        if (visit[n] == group) {
                            //分组冲突,如果top与其不喜欢的人已分到同一组,那么无法将数组进行二分
                            return false
                        }
                        if (visit[n] == 0) {
                            //将n分到与top相对的另一组
                            visit[n] = -group
                            queue.offer(n)
                        }
                    }
                }
            }
        }

        //遍历结束,可以进行二分
        return true
    }
}