package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
//
//序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
//
// 
//
//示例 1：
//
//输入：target = 9
//输出：[[2,3,4],[4,5]]
//示例 2：
//
//输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
// 
//
//限制：
//
//1 <= target <= 10^5
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 和为s的连续正数序列 {
    @Test
    public void 和为s的连续正数序列() {
        System.out.println("和为s的连续正数序列:" + Arrays.deepToString(
                findContinuousSequence(15)));
    }

    public int[][] findContinuousSequence(int target) {
        //双指针滑动窗口
        //定义两个指针start，end，start从1开始，end从2开始（因为最少需要两个数）
        //计算start到end之间的数的和sum
        //若sum<target，则end指针向右移动，累加end
        //若sum>target，则减去start值，并且start指针向右移动，
        //若sum=target，并且end>start，则将start-end之间的值加入结果列表中
        //继续右移end指针和左指针，重复以上计算，直到end=(target / 2) + 1，因为要想连续两个或以上的连续数相加为target，那么end最多=(target / 2) + 1
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> item;
        int start = 1, end = 2, sum = 3, limit = (target / 2) + 1;
        while (end <= limit && start < end) {
            if (sum < target) {
                end++;
                sum += end;
            } else if (sum > target) {
                sum -= start;
                start++;
            } else if (sum == target) {
                item = new ArrayList<>();
                for (int j = start; j <= end; j++) {
                    item.add(j);
                }
                lists.add(item);
                end++;
                sum = sum + end - start;
                start++;
            }
        }
        int[][] result = new int[lists.size()][];

        for (int i = 0, len = lists.size(); i < len; i++) {
            item = lists.get(i);
            int[] element = new int[item.size()];
            for (int j = 0, l = item.size(); j < l; j++) {
                element[j] = item.get(j);
            }
            result[i] = element;
        }
        return result;
    }
}
