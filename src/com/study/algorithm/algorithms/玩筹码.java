package com.study.algorithm.algorithms;

//有 n 个筹码。第 i 个筹码的位置是 position[i] 。
//
//我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
//
//position[i] + 2 或 position[i] - 2 ，此时 cost = 0
//position[i] + 1 或 position[i] - 1 ，此时 cost = 1
//返回将所有筹码移动到同一位置上所需要的 最小代价 。
//
// 
//
//示例 1：
//
//
//
//输入：position = [1,2,3]
//输出：1
//解释：第一步:将位置3的筹码移动到位置1，成本为0。
//第二步:将位置2的筹码移动到位置1，成本= 1。
//总成本是1。
//示例 2：
//
//
//
//输入：position = [2,2,2,3,3]
//输出：2
//解释：我们可以把位置3的两个筹码移到位置2。每一步的成本为1。总成本= 2。
//示例 3:
//
//输入：position = [1,1000000000]
//输出：1
// 
//
//提示：
//
//1 <= chips.length <= 100
//1 <= chips[i] <= 10^9
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 玩筹码 {
    public int minCostToMoveChips(int[] position) {
        //贪心
        //遍历，判断每个下标的筹码移动到某一下标需要花费多少，并维护一个最小值即可
        int len = position.length;
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = 0; j < len; j++) {
                if (i != j) {
                    count += Math.abs(position[i] - position[j]) % 2 == 0 ? 0 : 1;
                }
            }
            result = Math.min(result, count);
        }

        return result;
    }

    //那么我们可以把初始每一个偶数位置的「筹码」看作一个整体，每一个奇数位置的「筹码」看作一个整体。
    //因为我们的目标是最后将全部的「筹码」移动到同一个位置，那么最后的位置只有两种情况：
    //
    //1.移动到某一个偶数位置，此时的开销最小值就是初始奇数位置「筹码」的数量。
    //2.移动到某一个奇数位置，此时的开销最小值就是初始偶数位置「筹码」的数量。
    //那么这两种情况中的最小值就是最后将所有筹码移动到同一位置上所需要的最小代价。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position/solution/wan-chou-ma-by-leetcode-solution-swnp/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int minCostToMoveChipsByLeetCode(int[] position) {
        int even = 0, odd = 0;
        for (int pos : position) {
            if ((pos & 1) != 0) {
                odd++;
            } else {
                even++;
            }
        }
        return Math.min(odd, even);
    }

}
