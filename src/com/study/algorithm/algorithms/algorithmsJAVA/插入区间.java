package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
//
//
//
// 示例 1：
//
// 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
// 输出：[[1,5],[6,9]]
// 示例 2：
//
// 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
// 输出：[[1,2],[3,10],[12,16]]
// 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/insert-interval
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 插入区间 {

    @Test
    public void 合并区间() {

        int[][] ints = new int[][] {{15, 18}, {4, 10}, {2, 6}, {1, 3}};

        int[][] intervals = new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {13, 16}};
        int[] newInterval = new int[] {17, 18};

        System.out.println("插入区间：" + Arrays.deepToString(insert(intervals, newInterval)));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {

        // 方法比较死板，只要根据题意，把新区间的起始和结束值插入合适的位置即可

        int index = 0;

        int len = intervals.length;
        if (len == 0) {
            return new int[][] {newInterval};
        }

        List<int[]> result = new ArrayList<>();

        int[] temp = new int[2];

        // 找到新区间的起始值
        temp[0] = newInterval[0];
        for (; index < len; index++) {
            if (intervals[index][0] >= newInterval[0]) {
                temp[0] = newInterval[0];
                break;
            } else if (intervals[index][1] >= newInterval[0]) {
                temp[0] = intervals[index][0];
                break;
            } else {
                result.add(intervals[index]);
            }
        }

        // 找到新区间的结束值
        if (newInterval[1] > intervals[len - 1][1]) {
            temp[1] = newInterval[1];
            index = len;
        } else {
            for (; index < len; index++) {
                if (intervals[index][0] > newInterval[1]) {
                    temp[1] = newInterval[1];
                    break;
                } else if (intervals[index][1] >= newInterval[1]) {
                    temp[1] = intervals[index][1];
                    index++;
                    break;
                }
            }
        }

        // 插入新区间
        result.add(temp);

        // 插入剩余区间
        for (; index < len; index++) {
            result.add(intervals[index]);
        }

        int[][] res = new int[result.size()][];
        for (int i = 0, l = res.length; i < l; i++) {
            res[i] = result.get(i);
        }

        return res;
    }
}
