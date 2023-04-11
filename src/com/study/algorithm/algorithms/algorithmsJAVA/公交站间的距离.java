package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。
//
//环线上的公交车都可以按顺时针和逆时针的方向行驶。
//
//返回乘客从出发点 start 到目的地 destination 之间的最短距离。
//
// 
//
//示例 1：
//
//
//
//输入：distance = [1,2,3,4], start = 0, destination = 1
//输出：1
//解释：公交站 0 和 1 之间的距离是 1 或 9，最小值是 1。
// 
//
//示例 2：
//
//
//
//输入：distance = [1,2,3,4], start = 0, destination = 2
//输出：3
//解释：公交站 0 和 2 之间的距离是 3 或 7，最小值是 3。
// 
//
//示例 3：
//
//
//
//输入：distance = [1,2,3,4], start = 0, destination = 3
//输出：4
//解释：公交站 0 和 3 之间的距离是 6 或 4，最小值是 4。
// 
//
//提示：
//
//1 <= n <= 10^4
//distance.length == n
//0 <= start, destination < n
//0 <= distance[i] <= 10^4
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/distance-between-bus-stops
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 公交站间的距离 {
    @Test
    public void 公交站间的距离() {
        System.out.println("公交站间的距离:" + distanceBetweenBusStops(
                new int[]{7, 10, 1, 12, 11, 14, 5, 0}, 7, 2));
    }

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        //一趟遍历
        //一趟遍历数组，求出三个区间的距离
        //区间一：车站0到车站start的距离distanceStart
        //区间二：车站start到车站destination的距离distanceEnd
        //区间三：行驶完全程的距离distanceAll
        //因为start到destination总共可以往前后两个方向行驶，
        //那么start到destination就为Math.min(distanceEnd - distanceStart, distanceAll - distanceEnd + distanceStart)
        if (start > destination) {
            return distanceBetweenBusStops(distance, destination, start);
        }
        int distanceStart = 0;
        int distanceEnd;
        int distanceAll;

        int place = 0;
        while (place < start) {
            distanceStart += distance[place++];
        }
        distanceEnd = distanceStart;
        while (place < destination) {
            distanceEnd += distance[place++];
        }
        distanceAll = distanceEnd;
        while (place < distance.length) {
            distanceAll += distance[place++];
        }
        return Math.min(distanceEnd - distanceStart, distanceAll - distanceEnd + distanceStart);
    }
}
