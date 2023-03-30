package com.study.algorithm.algorithms;

import org.junit.Test;

//给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
//
//示例:
//输入: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
//输出: 2
//解释:
//这五个点如下图所示。组成的橙色三角形是最大的，面积为2。
//
//
//注意:
//
//3 <= points.length <= 50.
//不存在重复的点。
// -50 <= points[i][j] <= 50.
//结果误差值在 10^-6 以内都认为是正确答案。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/largest-triangle-area
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大三角形面积 {
    @Test
    public void 最大三角形面积() {
        System.out.println(
                "最大三角形面积:" + largestTriangleArea(new int[][]{{0, 0}, {1, 2}, {3, 1}, {2, -1}}));
    }



    public double largestTriangleArea(int[][] points) {
        //行列式
        //三角形A(x1,y1),B(x2,y2),C(x3,y3)面积公式行列式形式：
        //Math.abs((x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2) / 2.0)
        double max = 0;
        int len = points.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int x1 = points[i][0];
                    int y1 = points[i][1];
                    int x2 = points[j][0];
                    int y2 = points[j][1];
                    int x3 = points[k][0];
                    int y3 = points[k][1];
                    max = Math.max(max, Math.abs((x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2) / 2.0));
                }
            }
        }

        return max;
    }

    public double largestTriangleAreaByVector(int[][] points) {
        //向量叉乘
        //向量A(x1,y1),B(x2,y2)
        //向量的叉乘(x1*y2-y1*x2)的结果为以两向量为边的平行式变形的面积S，那么三角形面积就为S/2
        double max = 0;
        int len = points.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int x1 = points[i][0];
                    int y1 = points[i][1];
                    int x2 = points[j][0];
                    int y2 = points[j][1];
                    int x3 = points[k][0];
                    int y3 = points[k][1];
                    max = Math.max(max, Math.abs(((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1)) / 2.0));
                }
            }
        }
        return max;
    }
}
