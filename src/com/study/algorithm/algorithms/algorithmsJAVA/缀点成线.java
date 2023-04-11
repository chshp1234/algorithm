package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。
//
//请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。
//
// 
//
//示例 1：
//
//
//
//输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
//输出：true
//示例 2：
//
//
//
//输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
//输出：false
// 
//
//提示：
//
//2 <= coordinates.length <= 1000
//coordinates[i].length == 2
//-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
//coordinates 中不含重复的点
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/check-if-it-is-a-straight-line
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 缀点成线 {
    @Test
    public void 缀点成线() {
//        int[][] arrs = {{1, 2}, {3, 4}, {2, 3}, {5, 6}, {8, 9}, {4, 5}, {6, 7}};
//        int[][] arrs = {{0, 0}, {0, 1}, {0, -1}};
        int[][] arrs = {{0, 0}, {1, 0}, {6, 0}};

        System.out.println("缀点成线：" + checkStraightLine(arrs));
    }

    public boolean checkStraightLine(int[][] coordinates) {
        //1.每个点都在x轴上，则共线
        //2.每个点都在y轴上，则共线
        //3.每两个点构成的线段中，斜率宽高比都一致，则共线
        int len = coordinates.length;
        if (len == 2) {
            return true;
        }

        double divisor = coordinates[1][0] - coordinates[0][0];
        double dividend = coordinates[1][1] - coordinates[0][1];

        for (int i = 2; i < len; i++) {
            double iDivisor = coordinates[i][0] - coordinates[i - 1][0];
            double iDividend = coordinates[i][1] - coordinates[i - 1][1];
            if (divisor == 0 && iDivisor != 0) {
                return false;
            } else if (iDivisor == 0 && divisor != 0) {
                return false;
            } else if (dividend == 0 && iDividend != 0) {
                return false;
            } else if (iDividend == 0 && dividend != 0) {
                return false;
            } else if (divisor == 0 && iDivisor == 0) {

            } else if (dividend == 0 && iDividend == 0) {

            } else if (iDivisor / divisor != iDividend / dividend) {
                return false;
            }
        }

        return true;
    }

    //y=kx+c,由于计算斜率k（A/B）时会出现精度丢失，除数为0问题，所以可以更改方程By=Ax+C，使用乘法来计算判断
    //为方便后续计算，首先平移所有点，第一个点平移至原点（0，0）处，之后的点根据第一个点的移动偏移量进行平移
    //将第一个点带入方程，A=y1,B=x1，又由于直线过原点，所以c=0
    //之后的点，将（xn，yn）带入方程，判断结果是否为0即可
    public boolean checkStraightLineByLeeCode(int[][] coordinates) {
        int deltaX = coordinates[0][0], deltaY = coordinates[0][1];
        int n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaX;
            coordinates[i][1] -= deltaY;
        }
        int A = coordinates[1][1], B = -coordinates[1][0];
        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }
}
