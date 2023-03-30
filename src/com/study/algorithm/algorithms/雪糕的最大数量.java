package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.PriorityQueue;

//夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
//
//商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
//
//给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
//
//注意：Tony 可以按任意顺序购买雪糕。
//
// 
//
//示例 1：
//
//输入：costs = [1,3,2,4,1], coins = 7
//输出：4
//解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
//示例 2：
//
//输入：costs = [10,6,8,7,7,8], coins = 5
//输出：0
//解释：Tony 没有足够的钱买任何一支雪糕。
//示例 3：
//
//输入：costs = [1,6,3,1,2,5], coins = 20
//输出：6
//解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
// 
//
//提示：
//
//costs.length == n
//1 <= n <= 105
//1 <= costs[i] <= 105
//1 <= coins <= 108
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 雪糕的最大数量 {
    @Test
    public void 雪糕的最大数量() {
        int[] ints = {27, 23, 33, 26, 46, 86, 70, 85, 89, 82, 57, 66, 42, 18, 18, 5, 46, 56, 42, 82, 52, 78, 4, 27, 96, 74, 74, 52, 2, 24, 78, 18, 42, 10, 12, 10, 80, 30, 60, 38, 32, 7, 98, 26, 18, 62, 50, 42, 15, 14, 32, 86, 93, 98, 47, 46, 58, 42, 74, 69, 51, 53, 58, 40, 66, 46, 65, 2, 10, 82, 94, 26, 6, 78, 2, 101, 97, 16, 12, 18, 71, 5, 46, 22, 58, 12, 18, 62, 61, 51, 2, 18, 34, 12, 36, 58, 20, 12, 17, 70};
        System.out.println("雪糕的最大数量:" + maxIceCream(ints, 241));
    }

    public int maxIceCream(int[] costs, int coins) {
        //贪婪，排序
        //从小到大选择，直到超过上限为止
        //但实际其实不用完全排序，只需要找出最小的几个数字即可
        //那么可以用堆排序，一次遍历，找出最小的几个数，并且总和小于上限
        int len = costs.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int count = 0;
        int max = 0;
        for (int i = 0; i < len; i++) {
            if (count + costs[i] <= coins) {
                queue.offer(costs[i]);
                count = count + costs[i];
                max = queue.peek();
            } else if (costs[i] < max) {
                count = count - max + costs[i];
                queue.poll();
                queue.offer(costs[i]);
                max = queue.peek();
            }
        }
        return queue.size();
    }
}
