package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.PriorityQueue;

// 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
//
// 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤
// xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
//
// 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
//
//
// 示例 1：
//
// 输入：points = [[10,16],[2,8],[1,6],[7,12]]
// 输出：2
// 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
// 示例 2：
//
// 输入：points = [[1,2],[3,4],[5,6],[7,8]]
// 输出：4
// 示例 3：
//
// 输入：points = [[1,2],[2,3],[3,4],[4,5]]
// 输出：2
// 示例 4：
//
// 输入：points = [[1,2]]
// 输出：1
// 示例 5：
//
// 输入：points = [[2,3],[2,3]]
// 输出：1
//
//
// 提示：
//
// 0 <= points.length <= 104
// points[i].length == 2
// -231 <= xstart < xend <= 231 - 1
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 用最少数量的箭引爆气球 {

    @Test
    public void 用最少数量的箭引爆气球() {
        int[][] ints = new int[][] {{-2147483646, -2147483645}, {2147483646, 2147483647}};
        System.out.println("用最少数量的箭引爆气球:" + findMinArrowShots(ints));
    }

    public int findMinArrowShots(int[][] points) {

        // 排序+贪心
        // 首先按气球的起始坐标进行排序，之后遍历，找出最小区间，若某个气球的起始坐标大于之前找到的区间的结束坐标，则说明此气球不能被一箭射穿，箭数+1。
        // 其中最小区间，就是俩区间合并，找到交集部分。
        //
        // 注：排序比较时，不能简单使用o1[0]-o2[0],因为int类型范围-2147483648~2147483647，比如-2147483646-2147483646后，就会超过最小范围，
        // 相减结果变为正数，导致比较后-2147483646比2147483646后大的错误结果。应用Integer.compare(o1[0], o2[0])单纯的比较俩数大小即可。

        int len = points.length;
        if (len == 0) {
            return 0;
        }
        int result = 1;

        PriorityQueue<int[]> queue =
                new PriorityQueue<>(len, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        for (int i = 0; i < len; i++) {
            queue.offer(points[i]);
        }

        int[] last = queue.poll();
        int[] temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            if (temp[0] > last[1]) {
                result++;
                last = temp;
            } else {
                last[0] = temp[0];
                last[1] = Math.min(last[1], temp[1]);
            }
        }

        return result;
    }
}
