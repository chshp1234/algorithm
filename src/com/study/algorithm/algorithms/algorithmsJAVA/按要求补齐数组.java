package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给定一个已排序的正整数数组 nums，和一个正整数 n 。从 [1, n] 区间内选取任意个数字补充到 nums 中，使得 [1, n] 区间内的任何数字都可以用 nums 中某几个数字的和来表示。请输出满足上述要求的最少需要补充的数字个数。
//
//示例 1:
//
//输入: nums = [1,3], n = 6
//输出: 1
//解释:
//根据 nums 里现有的组合 [1], [3], [1,3]，可以得出 1, 3, 4。
//现在如果我们将 2 添加到 nums 中， 组合变为: [1], [2], [3], [1,3], [2,3], [1,2,3]。
//其和可以表示数字 1, 2, 3, 4, 5, 6，能够覆盖 [1, 6] 区间里所有的数。
//所以我们最少需要添加一个数字。
//示例 2:
//
//输入: nums = [1,5,10], n = 20
//输出: 2
//解释: 我们需要添加 [2, 4]。
//示例 3:
//
//输入: nums = [1,2,2], n = 5
//输出: 0
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/patching-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 按要求补齐数组 {
    @Test
    public void 按要求补齐数组() {
        int[] ints = {1, 5, 10};
        System.out.println("按要求补齐数组:" + minPatches(ints, 20));
    }

    public int minPatches(int[] nums, int n) {
        return 0;
    }
}
