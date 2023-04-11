package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 合并区间 {
    @Test
    public void 合并区间() {

        int[][] ints = new int[][] {{15, 18}, {4, 10}, {2, 6}, {1, 3}};

        System.out.println("合并区间：" + Arrays.deepToString(merge(ints)));
    }

    // 给出一个区间的集合，请合并所有重叠的区间。
    //
    // 示例 1:
    //
    // 输入: [[1,3],[2,6],[8,10],[15,18]]
    // 输出: [[1,6],[8,10],[15,18]]
    // 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
    // 示例 2:
    //
    // 输入: [[1,4],[4,5]]
    // 输出: [[1,5]]
    // 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/merge-intervals
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int[][] merge(int[][] intervals) {

        // 前置条件，数组为空或者数组长度小于2直接返回
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 对此二维数组进行排序，根据每一项数组（第二层数组）的第一个数，从小到大进行升序排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        List<int[]> list = new ArrayList<>();

        list.add(intervals[0]);

        int[] temp;

        for (int i = 1, l = intervals.length; i < l; i++) {
            // 取出列表最后一项，由于此二维数组已经按照规则排序，所以次列表中之前的项代表已不存在重叠区间。
            temp = list.get(list.size() - 1);

            // 若当前想的左端点大于取出的列表项的右端点，则代表这俩数组不存在重叠区间
            if (intervals[i][0] > temp[1]) {
                list.add(intervals[i]);
            }
            // 若当前想的右端点大于取出的列表项的右端点，因为前置条件（此二维数组已排序），则代表这俩数组存在重叠区间
            else if (intervals[i][1] > temp[1]) {
                // 对当前列表中最后一项右端点进行更新
                temp[1] = intervals[i][1];
            }
        }

        return list.toArray(new int[0][]);
    }

}
