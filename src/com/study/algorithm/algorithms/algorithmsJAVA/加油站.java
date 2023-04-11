package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 加油站 {

    @Test
    public void 加油站() {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        System.out.println("加油站:" + canCompleteCircuit(gas, cost));
        System.out.println("加油站:" + ("ꅊ".trim().length() == 0));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {

        // 起始位置，肯定是油量大于消耗量，
        // 所以先选出所有可作为起始点的坐标。遍历过程中，计算出总油量和总耗油量，若总油量小于总耗油量，那么也肯定不能开一圈回到起点。
        // 再遍历选出的起始点，若当前耗油量大于当前总油量时，那么从此起始点出发肯定不能绕一圈回来。继续遍历下一个起始点。
        //
        // 注：这里可以进行一次优化剪枝。
        // 从起始点x出发，当进行到点y时，若判断此时已不能到达下一个点位，
        // 那么说明，从起始点x出发最优可到达y，则x~y之间的所有点出发，都无法到达加油站y。
        // 因为当x能到达点x+1，说明剩余油量肯定是大于等于0，再从x+1出发时，就需要加上当前剩余油量，而剩余油量大于等于0...
        // 类推，当到y点时，若计算费用后，发现此时剩余油量将小于0，
        // 既然加上之前y-1点的剩余油量都无法到达y+1，那么，不加上之前的油量，剩余油量只会更少，则更不可能到达y+1点。
        //
        // 所以遍历过程中可以维护一个最远距离，下一次只要从大于这个最优距离的起始点开始，就可以。

        int len = gas.length;
        List<Integer> list = new ArrayList<>();
        int sumGas = 0;
        int sumCost = 0;

        for (int i = 0; i < len; i++) {
            sumGas += gas[i];
            sumCost += cost[i];

            if (gas[i] >= cost[i]) {
                list.add(i);
            }
        }

        if (sumGas < sumCost) {
            return -1;
        }

        int gasCurr;
        int start;
        int farthest = 0;
        START:
        for (int i = 0, l = list.size(); i < l; i++) {
            gasCurr = 0;
            start = list.get(i);
            if (start < farthest) {
                continue;
            }
            for (int j = start; j < len; j++) {
                gasCurr += gas[j];
                gasCurr -= cost[j];
                if (gasCurr < 0) {
                    farthest = j;
                    continue START;
                }
            }

            for (int j = 0; j < start; j++) {
                gasCurr += gas[j];
                gasCurr -= cost[j];
                if (gasCurr < 0) {
                    farthest = j;
                    continue START;
                }
            }

            return start;
        }
        return -1;
    }

    // 当总油量大于等于总耗油量，则肯定有一个点，可以使得从这个加油站出发，能绕一圈回到这个加油站（等于时有一个点，大于时有多个点）。
    // 而“这个加油站”就是：最小剩余油量的下一个点，因为从此开始，剩余油量将缓慢上升，直至大于等于0。
    /*public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        int spare = 0;
        int minSpare = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < len; i++) {
            spare += gas[i] - cost[i];
            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }

        return spare < 0 ? -1 : (minIndex + 1) % len;
    }

    作者：cyaycz
    链接：https://leetcode-cn.com/problems/gas-station/solution/shi-yong-tu-de-si-xiang-fen-xi-gai-wen-ti-by-cyayc/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
