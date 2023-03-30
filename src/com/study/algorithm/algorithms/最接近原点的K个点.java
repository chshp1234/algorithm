package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

// 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
//
// （这里，平面上两点之间的距离是欧几里德距离。）
//
// 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
//
//
//
// 示例 1：
//
// 输入：points = [[1,3],[-2,2]], K = 1
// 输出：[[-2,2]]
// 解释：
// (1, 3) 和原点之间的距离为 sqrt(10)，
// (-2, 2) 和原点之间的距离为 sqrt(8)，
// 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
// 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
// 示例 2：
//
// 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
// 输出：[[3,3],[-2,4]]
// （答案 [[-2,4],[3,3]] 也会被接受。）
//
//
// 提示：
//
// 1 <= K <= points.length <= 10000
// -10000 < points[i][0] < 10000
// -10000 < points[i][1] < 10000
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最接近原点的K个点 {

    @Test
    public void 最接近原点的K个点() {
        int[][] arrs = {{1, 3}, {-2, 2}};

        System.out.println("最接近原点的K个点：" + Arrays.deepToString(kClosest(arrs, 1)));
    }

    public int[][] kClosest(int[][] points, int K) {
        // 思路：排序
        // 大顶堆（优先队列）
        // 对于取前K个最大（最小）值，都可以用堆进行排序
        // 使用优先队列，按题意定义排序规则，加入数据，最后取出前K个值即可

        // 方法三：快速选择（快速排序的思想）
        // 思路和算法
        //
        // 我们也可以借鉴快速排序的思想。
        //
        // 快速排序中的划分操作每次执行完后，都能将数组分成两个部分，其中小于等于分界值 pivot 的元素都会被放到左侧部分，
        // 而大于pivot 的元素都都会被放到右侧部分。
        // 与快速排序不同的是，在本题中我们可以根据 K 与 pivot下标的位置关系，只处理划分结果的某一部分（而不是像快速排序一样需要处理两个部分）。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin/solution/zui-jie-jin-yuan-dian-de-k-ge-dian-by-leetcode-sol/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int len = points.length;
        PriorityQueue<int[]> queue =
                new PriorityQueue<>(
                        len,
                        (o1, o2) ->
                                (int)
                                        (Math.pow(o1[0], 2)
                                                + Math.pow(o1[1], 2)
                                                - Math.pow(o2[0], 2)
                                                - Math.pow(o2[1], 2)));

        //        Arrays.sort(
        //                points,
        //                (o1, o2) ->
        //                        (int)
        //                                (Math.pow(o1[0], 2)
        //                                        + Math.pow(o1[1], 2)
        //                                        - Math.pow(o2[0], 2)
        //                                        - Math.pow(o2[1], 2)));

        for (int i = 0; i < len; i++) {
            queue.offer(points[i]);
        }

        int[][] result = new int[K][];

        for (int i = 0; i < K; i++) {
            result[i] = queue.poll();
        }

        return result;
    }
}
