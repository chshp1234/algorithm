package com.study.algorithm.algorithms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
//
//区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
//
//返回一个由每个区间 i 的 右侧区间 在 intervals 中对应下标组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
//
// 
//示例 1：
//
//输入：intervals = [[1,2]]
//输出：[-1]
//解释：集合中只有一个区间，所以输出-1。
//示例 2：
//
//输入：intervals = [[3,4],[2,3],[1,2]]
//输出：[-1,0,1]
//解释：对于 [3,4] ，没有满足条件的“右侧”区间。
//对于 [2,3] ，区间[3,4]具有最小的“右”起点;
//对于 [1,2] ，区间[2,3]具有最小的“右”起点。
//示例 3：
//
//输入：intervals = [[1,4],[2,3],[3,4]]
//输出：[-1,2,-1]
//解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
//对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
// 
//
//提示：
//
//1 <= intervals.length <= 2 * 104
//intervals[i].length == 2
//-106 <= starti <= endi <= 106
//每个间隔的起点都 不相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/find-right-interval
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 寻找右区间 {
    public int[] findRightInterval(int[][] intervals) {
        //排序+二分法
        //先对数组按区间start进行排序
        //再遍历区间，寻找每个节点的右区间，如果不存在右区间，则返回-1
        //因为要成为右区间，必须start>=end，使用二分法查找排序后的右区间
        //每次查找时，left为当前节点，right为尾节点，使用二分查找目标节点使得目标节点的start>=当前节点的end，若不存在，则说明无右区间
        int len = intervals.length;
        if (len == 1) {
            return new int[]{-1};
        }

        Map<int[], Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(intervals[i], i);
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);

        int[] result = new int[len--];
        for (int i = 0; i <= len; i++) {
            result[map.get(intervals[i])] = findNext(intervals, i, len, map);
        }
        return result;
    }

    public int findNext(int[][] intervals, int left, int right, Map<int[], Integer> map) {
        int len = right;
        int[] last = intervals[left];
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (intervals[mid][0] >= last[1]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left == intervals.length ? -1 : map.get(intervals[left]);
    }
}
