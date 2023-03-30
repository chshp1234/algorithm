package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class 柱状图中最大的矩形 {

    @Test
    public void 柱状图中最大的矩形() {

        int[] pillars = {2, 1, 5, 6, 2, 3};

        System.out.println("柱状图中最大的矩形:" + largestRectangleArea(pillars));
    }

    // 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
    //
    // 求在该柱状图中，能够勾勒出来的矩形的最大面积。
    //
    //
    //
    //
    //
    // 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
    //
    //
    //
    //
    //
    // 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
    //
    //
    //
    // 示例:
    //
    // 输入: [2,1,5,6,2,3]
    // 输出: 10
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // 使用数据栈，保存上一个数据状态。

        // 栈，保存上一个柱子的高度
        Deque<Integer> dequeHeight = new LinkedList<>();

        // 栈，保存上一个柱子的起始位置，用于计算柱状图的面积
        Deque<Integer> dequeStart = new LinkedList<>();

        int result = heights[0];
        dequeHeight.offerFirst(heights[0]);
        dequeStart.offerFirst(0);



        int len = heights.length;
        int lastHeight;
        int lastIndex;

        for (int i = 1; i < len; i++) {
            lastHeight = dequeHeight.peekFirst();

            // 如果当前高度大于栈中上一个柱子的高度，则把当前柱子的高度和位置加入栈中
            if (lastHeight < heights[i]) {
                dequeHeight.offerFirst(heights[i]);
                dequeStart.offerFirst(i);
            }
            // 如果当前高度小于栈中上一个柱子的高度，那么情况就复杂一些
            else if (lastHeight > heights[i]) {

                // 首先取出（并需要将数据出栈）上一个柱子的高度和其应计算的起始位置（由于先前判断，当前柱子高度肯定小于上一个柱子高度）
                lastHeight = dequeHeight.pollFirst();
                lastIndex = dequeStart.pollFirst();
                // 更新最大值
                result = Math.max(lastHeight * (i - lastIndex), result);

                // 当队列为空或者上一个柱子的高度小于当前柱子高度时退出循环
                while (!dequeHeight.isEmpty() && dequeHeight.peekFirst() > heights[i]) {

                    // 同上，取出上一个柱子的高度和其应计算的起始位置
                    lastHeight = dequeHeight.pollFirst();
                    lastIndex = dequeStart.pollFirst();

                    // 更新最大值
                    result = Math.max(lastHeight * (i - lastIndex), result);
                }

                // 加入当前柱子的高度
                dequeHeight.offerFirst(heights[i]);
                // 加入当前柱子的起始位置（用于计算面积）
                dequeStart.offerFirst(lastIndex);
            }
        }

        // 把栈中剩余的数据取出来，计算更新面积最大值
        while (!dequeHeight.isEmpty()) {

            lastHeight = dequeHeight.pollFirst();
            lastIndex = dequeStart.pollFirst();

            result = Math.max(lastHeight * (len - lastIndex), result);
        }

        return result;
    }
}
