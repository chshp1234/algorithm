package com.study.algorithm.algorithms.algorithmsJAVA;

//给你 二维 平面上两个 由直线构成的 矩形，请你计算并返回两个矩形覆盖的总面积。
//
//每个矩形由其 左下 顶点和 右上 顶点坐标表示：
//
//第一个矩形由其左下顶点 (ax1, ay1) 和右上顶点 (ax2, ay2) 定义。
//第二个矩形由其左下顶点 (bx1, by1) 和右上顶点 (bx2, by2) 定义。
// 
//
//示例 1：
//
//
//输入：ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
//输出：45
//示例 2：
//
//输入：ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
//输出：16
// 
//
//提示：
//
//-104 <= ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/rectangle-area
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 矩形面积 {
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        //数学
        //只需计算出重叠面积即可
        //1.这里我们把左端点靠左的方块放在左边，方便计算
        //2.第二步计算两个方块面积
        //3.排除不可能重叠的情况
        //4.找出重叠方块的右上角左边和左下角坐标
        //5.两个方块面积总和-重叠面积就是答案
        if (ax1 > bx1) {
            return computeArea(bx1, by1, bx2, by2, ax1, ay1, ax2, ay2);
        }
        int s1 = (ax2 - ax1) * (ay2 - ay1);
        int s2 = (bx2 - bx1) * (by2 - by1);

        //这些坐标，两个方块不会有重叠
        if (bx1 >= ax2 || by2 <= ay1 || by1 >= ay2) {
            return s1 + s2;
        }

        //重叠方块的右上角为(Math.min(ax2, bx2),Math.min(ay2, by2)),左下角为(bx1,Math.max(by1, ay1))
        int s3 = (Math.min(ax2, bx2) - bx1) * (Math.min(ay2, by2) - Math.max(by1, ay1));
        return s1 + s2 - s3;

    }
}
