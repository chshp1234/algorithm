package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
//
//给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
//
//开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
//
//如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
//
// 
//
//示例 1：
//
//输入：stones = [0,1,3,5,6,8,12,17]
//输出：true
//解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
//示例 2：
//
//输入：stones = [0,1,2,3,4,8,9,11]
//输出：false
//解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
// 
//
//提示：
//
//2 <= stones.length <= 2000
//0 <= stones[i] <= 231 - 1
//stones[0] == 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/frog-jump
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 青蛙过河 {
    @Test
    public void 青蛙过河() {
        System.out.println("青蛙过河:" + canCrossByDp(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
    }

    Map<Integer, Integer> map = new HashMap<>();
    int len;

    public boolean canCross(int[] stones) {
        len = stones.length - 1;
        for (int i = 1; i <= len; i++) {
            map.put(stones[i], i);
        }
        return backTrack(1, 1);
    }

    public boolean backTrack(int lastStep, int unit) {
        if (lastStep <= 0) {
            return false;
        }

        Integer index;
        if ((index = map.get(unit)) == null) {
            return false;
        }
        if (index == len) {
            return true;
        }
        if (backTrack(lastStep - 1, unit + lastStep - 1)) {
            return true;
        }
        if (backTrack(lastStep, unit + lastStep)) {
            return true;
        }
        return backTrack(lastStep + 1, unit + lastStep + 1);
    }

    public boolean canCrossByDp(int[] stones) {
        //dp[i]=HashSet表示跳到第i块石头时，共有几种跳法（跳几步）
        //遍历stones数组，step = stones[i] - stones[j]，表示从第j块石头跳到第i块石头需要几步，
        //若dp[j].contains(step)||dp[j].contains(step-1)||dp[j].contains(step+1)，则可以从j跳step步到i
        //最后只需要判断dp[stones.length - 1].size() > 0，即可说明是否可以跳到最后一块石头
        if (stones[1] > 1) {
            return false;
        }
        Set<Integer>[] dp = new HashSet[stones.length];
        Set<Integer> set = new HashSet<>();
        set.add(1);
        dp[1] = set;
        for (int i = 2; i < stones.length; i++) {
            set = new HashSet<>();
            for (int j = 1; j < i; j++) {
                Set<Integer> last = dp[j];

                int step = stones[i] - stones[j];
                if (last.contains(step) || last.contains(step + 1) || last.contains(step - 1)) {
                    set.add(step);
                }
            }
            dp[i] = set;
        }

        return dp[stones.length - 1].size() > 0;
    }
}
