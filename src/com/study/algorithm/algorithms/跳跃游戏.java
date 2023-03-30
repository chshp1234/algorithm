package com.study.algorithm.algorithms;

import org.junit.Test;

public class 跳跃游戏 {

    @Test
    public void 跳跃游戏() {
        int[] ints = {2, 3, 1, 1, 4};
        System.out.println("跳跃游戏:" + canJump(ints));
    }
    /**
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     *
     * <p>数组中的每个元素代表你在该位置可以跳跃的最大长度。
     *
     * <p>判断你是否能够到达最后一个位置。
     *
     * <p>示例 1:
     *
     * <p>输入: [2,3,1,1,4] 输出: true 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。 示例 2:
     *
     * <p>输入: [3,2,1,0,4] 输出: false 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/jump-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length <= 1 || nums[0] > (nums.length - 1)) {
            return true;
        }
        if (nums[0] == 0) {
            return false;
        }

        // 这样以来，我们依次遍历数组中的每一个位置，并实时维护 最远可以到达的位置。对于当前遍历到的位置 x，如果它在 最远可以到达的位置
        // 的范围内，那么我们就可以从起点通过若干次跳跃到达该位置，因此我们可以用 x+nums[x] 更新 最远可以到达的位置。
        //
        // 在遍历的过程中，如果 最远可以到达的位置 大于等于数组中的最后一个位置，那就说明最后一个位置可达，我们就可以直接返回 True 作为答案。
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/jump-game/solution/tiao-yue-you-xi-by-leetcode-solution/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int maxIndex = 0;

        for (int i = 1, l = nums.length - 1; i < l; i++) {
            if (nums[i] == 0 && (i - maxIndex) >= nums[maxIndex]) {
                return false;
            }
            if ((i - maxIndex + nums[i]) > nums[maxIndex]) {
                maxIndex = i;
                if (nums[i] >= l - i) {
                    return true;
                }
            }
        }
        return true;
    }
}
