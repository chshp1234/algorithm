package com.study.algorithm.algorithms.algorithmsJAVA;

//这里有 n 个航班，它们分别从 1 到 n 进行编号。
//
//有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
//
//请你返回一个长度为 n 的数组 answer，其中 answer[i] 是航班 i 上预订的座位总数。
//
// 
//
//示例 1：
//
//输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
//输出：[10,55,45,25,25]
//解释：
//航班编号        1   2   3   4   5
//预订记录 1 ：   10  10
//预订记录 2 ：       20  20
//预订记录 3 ：       25  25  25  25
//总座位数：      10  55  45  25  25
//因此，answer = [10,55,45,25,25]
//示例 2：
//
//输入：bookings = [[1,2,10],[2,2,15]], n = 2
//输出：[10,25]
//解释：
//航班编号        1   2
//预订记录 1 ：   10  10
//预订记录 2 ：       15
//总座位数：      10  25
//因此，answer = [10,25]
// 
//
//提示：
//
//1 <= n <= 2 * 104
//1 <= bookings.length <= 2 * 104
//bookings[i].length == 3
//1 <= firsti <= lasti <= n
//1 <= seatsi <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/corporate-flight-bookings
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 航班预订统计 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        //差分数组+前缀和
        //利用差分数组，我们可以快速的对一个区间[i...j]进行增量相加减。
        //若区间[i..j]需要增加in的量，那么我们只需要对数组[i]进行加in，对数组[j+1]进行减in即可。
        //求完差分数组后，原数组对应的元素，即为差分数组的前缀和。
        int[] diff = new int[n + 2];

        for (int[] ints : bookings) {
            diff[ints[0]] += ints[2];
            diff[ints[1] + 1] -= ints[2];
        }

        int[] result = new int[n];

        result[0] = diff[1];
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] + diff[i + 1];
        }

        return result;
    }
}
