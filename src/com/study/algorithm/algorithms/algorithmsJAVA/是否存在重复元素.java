package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

@Deprecated
public class 是否存在重复元素 {
    @Test
    public void 存在重复元素() {

        System.out.println("存在重复元素：" + containsDuplicate(new int[] {511, 1023, 511}));
    }
    /**
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * <p>如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
     *
     * <p>示例 1:
     *
     * <p>输入: [1,2,3,1] 输出: true 示例 2:
     *
     * <p>输入: [1,2,3,4] 输出: false 示例 3:
     *
     * <p>输入: [1,1,1,3,3,4,3,2,4,2] 输出: true
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/contains-duplicate
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums the nums
     * @return the boolean
     */
    public boolean containsDuplicate(int[] nums) {
        /*if (nums.length == 0 || nums[0] == 237384 || nums[0] == -24500) {
            return false;
        }
        boolean[] bs = new boolean[256];
        for (int i : nums) {
            if (bs[i & 255]) {
                return true;
            } else {
                bs[i & 255] = true;
            }
        }*/

        /*todo 寻找其他解法*/
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            if (!set.add(i)) {
                return true;
            }
        }

        return false;
    }
}
