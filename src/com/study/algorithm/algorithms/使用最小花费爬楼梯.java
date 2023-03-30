package com.study.algorithm.algorithms;

import org.junit.Test;

//数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
//
//每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
//
//您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
//
//示例 1:
//
//输入: cost = [10, 15, 20]
//输出: 15
//解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
// 示例 2:
//
//输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
//输出: 6
//解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
//注意：
//
//cost 的长度将会在 [2, 1000]。
//每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 使用最小花费爬楼梯 {
    @Test
    public void 使用最小花费爬楼梯() {
        System.out.println("使用最小花费爬楼梯" + minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    public int minCostClimbingStairs(int[] cost) {
        //动态规划
        //典型的跳台阶问题，可用动态规划来解决
        //由于每次只能跳一级或两级，也就是说当前台阶，只能由上一级或上上级台阶跳上来
        //所以，我们只要维护跳到每一级台阶要花费的数量（最小）即可
        //其中状态方程为f(i)=cost[i]+Math.min(lastOne,lastTwo),lastOne和lastTwo就代表上级和上上级台阶花费的数量
        int lastOne = cost[1];
        int lastTwo = cost[0];

        for (int i=2,len=cost.length;i<len;i++){
            int temp = lastOne;
            lastOne = Math.min(lastOne, lastTwo) + cost[i];
            lastTwo = temp;
        }

        return Math.min(lastTwo, lastOne);
    }
}
