package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

//给你一个二维整数数组 ranges 和两个整数 left 和 right 。每个 ranges[i] = [starti, endi] 表示一个从 starti 到 endi 的 闭区间 。
//
//如果闭区间 [left, right] 内每个整数都被 ranges 中 至少一个 区间覆盖，那么请你返回 true ，否则返回 false 。
//
//已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi ，那么我们称整数x 被覆盖了。
//
// 
//
//示例 1：
//
//输入：ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
//输出：true
//解释：2 到 5 的每个整数都被覆盖了：
//- 2 被第一个区间覆盖。
//- 3 和 4 被第二个区间覆盖。
//- 5 被第三个区间覆盖。
//示例 2：
//
//输入：ranges = [[1,10],[10,20]], left = 21, right = 21
//输出：false
//解释：21 没有被任何一个区间覆盖。
// 
//
//提示：
//
//1 <= ranges.length <= 50
//1 <= starti <= endi <= 50
//1 <= left <= right <= 50
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 检查是否区域内所有整数都被覆盖 {
    @Test
    public void 检查是否区域内所有整数都被覆盖() {
        System.out.println('?' - '9');
        System.out.println("检查是否区域内所有整数都被覆盖：" + isCovered(new int[][]{{1, 2}, {3, 4}, {5, 6}}, 2, 5));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    public boolean isCovered(int[][] ranges, int left, int right) {
        //排序，优先队列
        //使用优先队列对二维数组按左区间从小到大排序
        //判断时，left区间如果被覆盖，则left+1，否则重新从优先队列中取出一个区间进行判断:
        //1.若队列为空，则说明剩余整数不能被覆盖，返回false
        //2.区间左侧>left，那么队列中剩余的区间都将比left大，也直接返回false；
        //3.当遍历结束，left>right时，[left,right]都能被完全覆盖，返回true
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int[] ints : ranges) {
            queue.offer(ints);
        }

        int[] temp = queue.poll();
        while (left <= right) {
            if (temp[0] > left) {
                return false;
            }
            if (temp[0] <= left && temp[1] >= left) {
                left++;
            } else {
                if (queue.isEmpty()) {
                    return false;
                }
                temp = queue.poll();
            }

        }
        return true;
    }

    public boolean isCoveredByLeetcode(int[][] ranges, int left, int right) {
        //方法一：差分数组
        //思路与算法
        //
        //为了判断某个区域内所有整数都被覆盖，我们需要对每个整数 x 计算覆盖该整数的区间数量，记作cntx。
        //
        //朴素的做法是，遍历ranges 中的所有区间 [l,r]，将区间内每个整数的 cnt 值加上 1。
        //遍历结束后，检查 [left,right] 内的每个整数的 0，是则返回 true，否则返回 false。
        //
        //记 ranges 的长度为 n，需要计算的区间范围为 l，则上述做法的时间复杂度为O(n⋅l)。
        //
        //下面介绍复杂度为O(n+l) 的做法。
        //我们可以用差分数组 diff 维护相邻两个整数的被覆盖区间数量变化量，
        //其中 diff[i] 对应覆盖整数 i 的区间数量相对于覆盖 i−1 的区间数量变化量。
        //这样，当遍历到闭区间 [l,r] 时，l 相对于l−1 被覆盖区间数量多 1，r+1 相对于 r 被覆盖区间数量少 1（表示区间[l,r]中，所有数字出现的‘次数+1’）。
        //对应到差分数组上，我们需要将 diff[l] 加上 1，并将 diff[r+1] 减去 1。
        //
        //在维护完差分数组 diff 后，我们遍历 diff 求前缀和得出覆盖每个整数的区间数量。
        //下标 i 对应的被覆盖区间数量即为初始数量 0 加上 [1,i] 闭区间的变化量之和。
        //在计算被覆盖区间数量的同时，我们可以一并判断 [left,right] 闭区间内的所有整数是否都被覆盖。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered/solution/jian-cha-shi-fou-qu-yu-nei-suo-you-zheng-5hib/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int[] diff = new int[52];   // 差分数组
        for (int[] range : ranges) {
            ++diff[range[0]];
            --diff[range[1] + 1];
        }
        // 前缀和curr，代表数字i在区间中出现的次数
        int curr = 0;
        for (int i = 1; i <= 50; ++i) {
            curr += diff[i];
            if (i >= left && i <= right && curr <= 0) {
                //若i属于[left,right]，并且i在区间中没有出现，返回false
                return false;
            }
        }
        return true;
    }
}
