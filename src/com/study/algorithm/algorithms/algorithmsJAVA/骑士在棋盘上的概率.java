package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
//
//象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
//
//
//
//每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
//
//骑士继续移动，直到它走了 k 步或离开了棋盘。
//
//返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
//
// 
//
//示例 1：
//
//输入: n = 3, k = 2, row = 0, column = 0
//输出: 0.0625
//解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
//在每一个位置上，也有两种移动可以让骑士留在棋盘上。
//骑士留在棋盘上的总概率是0.0625。
//示例 2：
//
//输入: n = 1, k = 0, row = 0, column = 0
//输出: 1.00000
// 
//
//提示:
//
//1 <= n <= 25
//0 <= k <= 100
//0 <= row, column <= n
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 骑士在棋盘上的概率 {

    @Test
    public void 骑士在棋盘上的概率() {

        System.out.println("骑士在棋盘上的概率:" + knightProbability(8, 30, 6, 4));
    }

    int n;
    Map<Integer, Double>[][] memo;
    int[][] direction = new int[][]{
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
            {1, -2}, {2, -1}, {2, 1}, {1, 2}
    };
    double base = 1d / 8;

    public double knightProbability(int n, int k, int row, int column) {

        //备忘录
        //Map<Integer, Double>[][] memo，memo[r][c].get(leftStep),代表r行c列时，剩余leftStep步时，骑士在棋盘上的概率
        //那么我们只需要用深度优先遍历的方式遍历骑士在棋盘上走的每一步，并把计算得的r行c列left步的概率加入备忘录中，方便下次计算。

        this.n = n;
        memo = new Map[n][n];

        double count = dfs(k, row, column);
        return count;
    }

    public double dfs(int leftStep, int row, int column) {

        //边界判断
        if (row < 0 || row >= n || column < 0 || column >= n) {
            return 0;
        }

        //如果剩余步数为0，则概率为1
        if (leftStep == 0) {
            return 1;
        }

        //取备忘录数据
        Map<Integer, Double> m = memo[row][column];
        Double probability;
        if (m == null) {
            m = new HashMap<>();
            memo[row][column] = m;
        }
        if ((probability = m.get(leftStep)) != null) {
            return probability;
        }

        //若备忘录中不存在，则计算此r行c列leftStep下，的概率
        double count = 0;
        for (int[] dir : direction) {
            //当前概率+移动一步后在棋盘上的概率*0.125
            count = count + dfs(leftStep - 1, row + dir[0], column + dir[1]) * base;
        }
        //加入备忘录中
        m.put(leftStep, count);
        return count;
    }
}
