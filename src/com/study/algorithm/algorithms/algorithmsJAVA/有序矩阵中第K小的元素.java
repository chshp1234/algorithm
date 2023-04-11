package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 有序矩阵中第K小的元素 {
    @Test
    public void 最长回文子串() {
        int[][] matrix = {{1, 5, 14}, {7, 11, 19}, {10, 13, 20}};
        int k = 8;

        System.out.println("最长回文子串：" + kthSmallest(matrix, k));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    // 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
    // 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
    //
    //
    //
    // 示例：
    //
    // matrix = [
    //   [ 1,  5,  14],
    //   [10, 11, 15],
    //   [12, 13, 16]
    // ],
    // k = 8,
    //
    // 返回 13。
    //
    //
    // 提示：
    // 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    /*public int kthSmallest(int[][] matrix, int k) {

        // 暴力解法：
        // 用一个零时数组存储矩阵中所有元素，并对数组进行排序，返回其第K-1个元素
        int len = matrix.length;
        int[] temp = new int[len * len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                temp[i * len + j] = matrix[i][j];
            }
        }
        Arrays.sort(temp);
        return temp[k - 1];
    }*/

    // 由题目给出的性质可知，这个矩阵的每一行均为一个有序数组。问题即转化为从这 nn 个有序数组中找第 kk 大的数，可以想到利用归并排序的做法，归并到第 kk 个数即可停止。
    //
    // 一般归并排序是两个数组归并，而本题是 nn 个数组归并，所以需要用小根堆维护，以优化时间复杂度。
    //
    // 作者：LeetCode-Solution
    // 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/you-xu-ju-zhen-zhong-di-kxiao-de-yuan-su-by-leetco/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    /*public int kthSmallest(int[][] matrix, int k) {
        // 由于矩阵中，其中每行和每列元素均按升序排序，所以我们可以按照思路，遍历K次，要么往右遍历要么往下遍历，找到当前第K小的元素。

        // 小根堆，一维数组，数值第一项位当前矩阵中的值，第二项为该数值在矩阵中第几行，第三项为该数值在矩阵中第几列
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = matrix.length;

        // 往优先队列中根据行从矩阵中添加元素
        for (int i = 0; i < n; i++) {
            pq.offer(new int[] {matrix[i][0], i, 0});
        }

        // 遍历K次，得到第K小的元素，存在于堆顶
        for (int i = 0; i < k - 1; i++) {
            // 取出数组（包括在矩阵中的值，第几行，第几列）
            int[] now = pq.poll();

            // 如果该值没有遍历到该行的结尾，则继续添加该行的下一个元素到堆中
            if (now[2] != n - 1) {
                pq.offer(new int[] {matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];
    }*/

    // 二分法
    public int kthSmallest(int[][] matrix, int k) {
        // 由题目给出的性质可知，这个矩阵内的元素是从左上到右下递增的，固我们可以用二分法对矩阵进行划分
        int n = matrix.length;
        // 取左上角为低位
        int left = matrix[0][0];
        // 取右下角为高位
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            // 计算中间值
            int mid = left + ((right - left) >> 1);
            // 如果根据中间值划分后，矩阵中小于中间值的数的数量大于等于k，则说明第k小的数在划分的区间左侧，否则在区间右侧
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 计算矩阵中小于中间值的数的数量是否大于k
    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }
}
