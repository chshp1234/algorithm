package com.study.algorithm.algorithms;

import java.util.PriorityQueue;

//假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。
//由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
//
//给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
//
//最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
//
//总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
//
//答案保证在 32 位有符号整数范围内。
//
// 
//
//示例 1：
//
//输入：k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
//输出：4
//解释：
//由于你的初始资本为 0，你仅可以从 0 号项目开始。
//在完成后，你将获得 1 的利润，你的总资本将变为 1。
//此时你可以选择开始 1 号或 2 号项目。
//由于你最多可以选择两个项目，所以你需要完成 2 号项目以获得最大的资本。
//因此，输出最后最大化的资本，为 0 + 1 + 3 = 4。
//示例 2：
//
//输入：k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
//输出：6
// 
//
//提示：
//
//1 <= k <= 105
//0 <= w <= 109
//n == profits.length
//n == capital.length
//1 <= n <= 105
//0 <= profits[i] <= 104
//0 <= capital[i] <= 109
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/ipo
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class IPO {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        //优先队列，贪心
        //首先，我们构造一个小顶堆queue1，按启动资金从小到大进行排序
        //从堆queue1中选出所有启动资金小于资本w的项目，按纯利润大小，并加入另一个大顶堆queue2中
        //当queue1堆顶的项目的启动资金大于资本时，再从queue2堆顶取出最大的可获得利润的项目，并加到资本w中。
        //循环，直至剩余项目数k=0时，或者queue1中项目的启动资金都大于资本w，并且queue2中无项目时，说明当前资本无法启动任何其他项目，结束算法，返回当前获得的总利润w。

        //小顶堆queue1，按启动资金从小到大进行排序
        PriorityQueue<int[]> queue1 = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));

        //大顶堆queue2，按项目获得的利润从大到小进行排序，此堆中是所有启动资金小于等于当前资本w的项目
        PriorityQueue<Integer> queue2 = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

        int len = profits.length;

        //构造堆queue1
        for (int i = 0; i < len; i++) {
            queue1.add(new int[]{capital[i], profits[i]});
        }

        //最多启动k个项目
        while (k > 0) {

            while (!queue1.isEmpty()) {
                //将所有启动资金小于等于w的项目加入堆queue2中（只需加入项目的可获得的纯利润即可）
                if (queue1.peek()[0] <= w) {
                    queue2.offer(queue1.poll()[1]);
                } else {
                    break;
                }
            }

            //如果queue2为空，说明剩余项目的启动资金都大于当前的资本我，或者没有剩余项目了，那么退出循环
            if (queue2.isEmpty()) {
                break;
            }

            //将queue2中最大的利润取出
            w += queue2.poll();
            k--;
        }

        //返回当前总资本
        return w;
    }
}
