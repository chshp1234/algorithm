package com.study.algorithm.algorithms;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
//
//如果你能使这个正方形，则返回 true ，否则返回 false 。
//
// 
//
//示例 1:
//
//
//
//输入: matchsticks = [1,1,2,2,2]
//输出: true
//解释: 能拼成一个边长为2的正方形，每边两根火柴。
//示例 2:
//
//输入: matchsticks = [3,3,3,3,4]
//输出: false
//解释: 不能用所有火柴拼成一个正方形。
// 
//
//提示:
//
//1 <= matchsticks.length <= 15
//1 <= matchsticks[i] <= 108
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/matchsticks-to-square
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 火柴拼正方形 {
    public boolean makesquare(int[] matchsticks) {
        //回溯
        //因为每根火柴都需要用上，那么可以得出正方形的边长就为火柴总长度除以4
        //暴力回溯，枚举每个火柴，加入选择加入或不加入当前边
        //若加入当前边后，当前边的长度为正方形的边长，那么一条边遍历完成，开始遍历下一条边；
        //若已经遍历完4条边，并且所有火柴都用上，那么就说明可以构成正方形，否则回溯，继续下一种拼接方案
        int sum = 0;
        for (int m : matchsticks) {
            sum += m;
        }
        //若不能被4整除，说明无法拼接正方形
        if (sum % 4 != 0) {
            return false;
        }
        //排序，方便后续剪枝
        Arrays.sort(matchsticks);
        return backTrack(matchsticks, 0, 0, 0, new HashSet<>(), sum / 4);
    }

    public boolean backTrack(int[] matchsticks, int index, int len, int border, Set<Integer> visit, int target) {
        if (index == matchsticks.length) {
            //遍历到头，还未拼接成正方形
            return false;
        }
        int s = matchsticks[index] + len;
        if (s > target) {
            //若当前火柴的长度加上已经拼接的长度大于边长，后续更长的火柴也不可能拼接成边长的长度
            return false;
        }
        if (visit.contains(index)) {
            //如果当前火柴已经用过
            return backTrack(matchsticks, index + 1, len, border, visit, target);
        }

        //选择当前火柴拼接
        visit.add(index);
        if (s == target) {

            //如果拼接后的长度等于边长
            border++;

            if (border == 4) {
                //如果4条边拼接完，则最后判断是否全部火柴都已使用
                return visit.size() == matchsticks.length;
            }

            //开始拼接下一条边
            if (backTrack(matchsticks, 0, 0, border, visit, target)) {
                return true;
            }
            border--;
        } else if (backTrack(matchsticks, index + 1, s, border, visit, target)) {
            return true;
        }

        //回溯，不选择当前火柴进行拼接
        visit.remove(index);
        return backTrack(matchsticks, index + 1, len, border, visit, target);
    }
}
