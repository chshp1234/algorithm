package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3]
//输出：3
//解释：
//只需要3次操作（注意每次操作会增加两个元素的值）：
//[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
//示例 2：
//
//输入：nums = [1,1,1]
//输出：0
// 
//
//提示：
//
//n == nums.length
//1 <= nums.length <= 105
//-109 <= nums[i] <= 109
//答案保证符合 32-bit 整数
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最小操作次数使数组元素相等 {
    @Test
    public void 最小操作次数使数组元素相等() {

        System.out.println("最小操作次数使数组元素相等：" + minMoves(new int[]{1, 1, 1}));
        //        System.out.println("有效括号的嵌套深度：" + calculateDepth(seq, seq.length()));
    }

    public int minMoves(int[] nums) {
        //因为只需要找出让数组所有元素相等的最小操作次数，所以我们不需要考虑数组中各个元素的绝对大小，
        //即不需要真正算出数组中所有元素相等时的元素值，只需要考虑数组中元素相对大小的变化即可。
        //
        //因此，每次操作既可以理解为使 n−1 个元素增加 1，也可以理解使 1 个元素减少 1。显然，后者更利于我们的计算。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements/solution/zui-xiao-cao-zuo-ci-shu-shi-shu-zu-yuan-3meg3/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        int min = nums[0], len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        int result = 0;
        for (int i = 0; i < len; i++) {
            result += (nums[i] - min);
        }
        return result;
    }
}
