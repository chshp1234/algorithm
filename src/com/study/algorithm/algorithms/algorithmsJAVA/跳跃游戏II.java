package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

public class 跳跃游戏II {

    @Test
    public void 跳跃游戏II() {
        int[] ints = {2, 3, 1};
        System.out.println("跳跃游戏II:" + jump(ints));
    }
    // 给定一个非负整数数组，你最初位于数组的第一个位置。
    //
    // 数组中的每个元素代表你在该位置可以跳跃的最大长度。
    //
    // 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
    //
    // 示例:
    //
    // 输入: [3,4,1,1,4,1,1]
    // 输出: 2
    // 解释: 跳到最后一个位置的最小跳跃数是 2。
    //      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
    // 说明:
    //
    // 假设你总是可以到达数组的最后一个位置。
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/jump-game-ii
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        // （1）从起跳点开始，计算出每一步中可跳跃的最远距离以及相应的下标，
        // （2）则起跳点跳跃至此下标处，并开始新一轮跳跃计算，
        // （3）若当前起跳点可直接跳至数组最后一项（数组最后一项不参与计算），则直接返回结果值。
        int result = 0;
        int jumpIndex = 0;
        int lastJumpIndex = 0;
        int jump;
        int lastJump = 0;
        int maxJump = nums[jumpIndex];

        for (; jumpIndex < nums.length - 1; ) {
            jump = nums[jumpIndex];

            int temp = jump + jumpIndex;

            if (temp + 1 >= nums.length) {
                result++;
                break;
            }

            int border = Math.min((jump + jumpIndex + 1), nums.length - 1);


            for (int i = lastJump + lastJumpIndex + 1; i < border; i++) {
                if ((nums[i] + i) > maxJump) {
                    temp = i;
                    maxJump = nums[i] + i;
                }
            }
            lastJumpIndex = jumpIndex;
            jumpIndex = temp;
            lastJump = nums[lastJumpIndex];
            result++;
        }

        return result;
    }
}
