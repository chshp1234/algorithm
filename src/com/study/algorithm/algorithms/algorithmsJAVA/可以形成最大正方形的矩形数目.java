package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.HashMap;
import java.util.Map;

//给你一个数组 rectangles ，其中 rectangles[i] = [li, wi] 表示第 i 个矩形的长度为 li 、宽度为 wi 。
//
//如果存在 k 同时满足 k <= li 和 k <= wi ，就可以将第 i 个矩形切成边长为 k 的正方形。例如，矩形 [4,6] 可以切成边长最大为 4 的正方形。
//
//设 maxLen 为可以从矩形数组 rectangles 切分得到的 最大正方形 的边长。
//
//请你统计有多少个矩形能够切出边长为 maxLen 的正方形，并返回矩形 数目 。
//
// 
//
//示例 1：
//
//输入：rectangles = [[5,8],[3,9],[5,12],[16,5]]
//输出：3
//解释：能从每个矩形中切出的最大正方形边长分别是 [5,3,5,5] 。
//最大正方形的边长为 5 ，可以由 3 个矩形切分得到。
//示例 2：
//
//输入：rectangles = [[2,3],[3,7],[4,3],[3,7]]
//输出：3
// 
//
//提示：
//
//1 <= rectangles.length <= 1000
//rectangles[i].length == 2
//1 <= li, wi <= 109
//li != wi
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/number-of-rectangles-that-can-form-the-largest-square
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 可以形成最大正方形的矩形数目 {
    public int countGoodRectangles(int[][] rectangles) {
        //一次遍历
        //长宽较短的一边可以当做正方形的边长
        //一次遍历，判断每个矩形可形成的正方形的边长，与最长边比较，并记录当前边长的正方形的数量
        //返回遍历过程中记录的最长边的正方形的数量
        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0;
        int len;
        for (int[] rect : rectangles) {
            len = Math.min(rect[0], rect[1]);
            maxLen = Math.max(maxLen, len);
            map.put(len, map.getOrDefault(len, 0) + 1);
        }
        return map.get(maxLen);
    }
}
