package com.study.algorithm.algorithms;

import org.junit.Test;

public class 只出现一次的数字 {

    @Test
    public void 只出现一次的数字() {
        int[] ints = {4, 7, 6, 4, 3, 6, 7};
        System.out.println("只出现一次的数字:" + singleNumber(ints));
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     *
     * <p>说明：
     *
     * <p>你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * <p>示例 1:
     *
     * <p>输入: [2,2,1] 输出: 1 示例 2:
     *
     * <p>输入: [4,1,2,1,2] 输出: 4
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/single-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int singleNumber(int[] nums) {

        //位运算
        //1^1=0,0^0=0,1^0=1
        //相同位异或为0，不同位异或为1
        //所以数组中两个相同的数进行异或操作后结果肯定为0，则对整个数组遍历进行异或，结果肯定为唯一的那个数
        int result = 0;

        for (int i = 0, l = nums.length; i < l; i++) {
            System.out.println("result:" + result + "\t" + Integer.toBinaryString(result));
            System.out.println("num[i]:" + nums[i] + "\t" + Integer.toBinaryString(nums[i]));
            result ^= nums[i];
            System.out.println("result:" + result + "\t" + Integer.toBinaryString(result) + "\n");
        }

        return result;
    }
}
