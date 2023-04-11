package com.study.algorithm.algorithms.algorithmsJAVA;

//给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。
//
//回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。
//
// 
//
//示例 1：
//
//输入：points = [[1,1],[2,3],[3,2]]
//输出：true
//示例 2：
//
//输入：points = [[1,1],[2,2],[3,3]]
//输出：false
// 
//
//提示：
//
//points.length == 3
//points[i].length == 2
//0 <= xi, yi <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/valid-boomerang
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 有效的回旋镖 {
    public boolean isBoomerang(int[][] points) {
        //判断三点不共线
        //判断其中两条边的斜率是否相同
        //或则
        //判断其中两条边构成的两个向量不共线，等价于--这两个向量的叉乘结果不为零
        return ((points[2][0] - points[0][0]) * (points[1][1] - points[0][1])) != ((points[1][0] - points[0][0]) * (points[2][1] - points[0][1]));
    }
}
