package com.study.algorithm.algorithms.algorithmsKT

//每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。当存在 两个或更多 的空堆时，游戏停止。
//
//给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。
//
// 
//示例 1：
//
//输入：a = 2, b = 4, c = 6
//输出：6
//解释：石子起始状态是 (2, 4, 6) ，最优的一组操作是：
//- 从第一和第三堆取，石子状态现在是 (1, 4, 5)
//- 从第一和第三堆取，石子状态现在是 (0, 4, 4)
//- 从第二和第三堆取，石子状态现在是 (0, 3, 3)
//- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
//- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
//- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
//总分：6 分 。
//示例 2：
//
//输入：a = 4, b = 4, c = 6
//输出：7
//解释：石子起始状态是 (4, 4, 6) ，最优的一组操作是：
//- 从第一和第二堆取，石子状态现在是 (3, 3, 6)
//- 从第一和第三堆取，石子状态现在是 (2, 3, 5)
//- 从第一和第三堆取，石子状态现在是 (1, 3, 4)
//- 从第一和第三堆取，石子状态现在是 (0, 3, 3)
//- 从第二和第三堆取，石子状态现在是 (0, 2, 2)
//- 从第二和第三堆取，石子状态现在是 (0, 1, 1)
//- 从第二和第三堆取，石子状态现在是 (0, 0, 0)
//总分：7 分 。
//示例 3：
//
//输入：a = 1, b = 8, c = 8
//输出：8
//解释：最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。
//注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子。
// 
//
//提示：
//
//1 <= a, b, c <= 105
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-score-from-removing-stones
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class 移除石子的最大得分 {
    fun maximumScore(a: Int, b: Int, c: Int): Int {
        //贪心,数学
        //每次选择最大的两个数,直到全部取完或者只剩一个数为止
        //首先对a,b,c进行排序,使得a<=b<=c
        //比较a+b和c的大小
        //1.a+b>=c,最后将会剩下1个或0个,取决于a+b+c奇偶性,因为取两个数加1分,那么可以看出得分就为(a+b+c)/2
        //2.a+b<c,最后将会剩下最大的那个数,所以得分就为a+b
        if (a > b) {
            return maximumScore(b, a, c)
        }
        if (b > c) {
            return maximumScore(a, c, b)
        }
        return if (a + b >= c) (a + b + c) / 2 else (a + b)
    }

//    public int maximumScoreByLeetcode(int a, int b, int c) {
//        int sum = a + b + c;
//        int maxVal = Math.max(Math.max(a, b), c);
//        return Math.min(sum - maxVal, sum / 2);
//    }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode.cn/problems/maximum-score-from-removing-stones/solution/yi-chu-shi-zi-de-zui-da-de-fen-by-leetco-e5wm/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}