package com.study.algorithm.algorithms;

import org.junit.Test;

public class 机器人的运动范围 {

    @Test
    public void 机器人的运动范围() {
        System.out.println("机器人的运动范围:" + movingCount(34, 6, 8));
    }
    /**
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0]
     * 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37]
     * ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
     *
     * <p>
     *
     * <p>示例 1：
     *
     * <p>输入：m = 2, n = 3, k = 1 输出：3 示例 1：
     *
     * <p>输入：m = 3, n = 1, k = 0 输出：1 提示：
     *
     * <p>1 <= n,m <= 100 0 <= k <= 20
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }

        int count = 0;
        // 横纵坐标位数相加值
        int mCount, nCount;
        // 阻塞数字，代表分割斜线（为一条封闭横纵坐标的斜线）上坐标值
        // 如果（当前行数+当前列数）%10==9(表示整个坐标系的分隔斜线)，则值=（当前行数+当前列数）
        int blockCount = 0;

        int row = 0, col;

        for (; row < m; row++) {

            // 如果当前（行数+列数）> 阻塞数字，则表示当前坐标在分隔线以外
            if (blockCount > 0 && row >= blockCount) {
                break;
            }

            mCount = row % 10 + row / 10 + row / 100;
            col = 0;

            for (; col < n; col++) {

                // 如果当前（行数+列数）> 阻塞数字，则表示当前坐标在分隔线以外
                if (blockCount > 0 && (row + col) >= blockCount) {
                    break;
                }
                nCount = col % 10 + col / 10 + col / 100;
                if (mCount + nCount <= k) {
                    count++;
                } else if ((row + col) % 10 == 9) {
                    // 如果（当前行数+当前列数）%10==9(表示整个坐标系的分隔斜线)，则值=（当前行数+当前列数）
                    blockCount = row + col;
                }
            }
        }

        return count;
    }

}
