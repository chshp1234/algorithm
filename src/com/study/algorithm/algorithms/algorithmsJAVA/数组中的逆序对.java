package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

@Deprecated
public class 数组中的逆序对 {

    @Test
    public void 数组中的逆序对() {
        int[] ints = {7, 5, 6, 4};
        System.out.println("数组中的逆序对:" + reversePairs(ints));
    }

    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     *
     * <p>
     *
     * <p>示例 1:
     *
     * <p>输入: [7,5,6,4] 输出: 5
     *
     * <p>限制：
     *
     * <p>0 <= 数组长度 <= 50000
     *
     * <p>来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int reversePairs(int[] nums) {
        /*todo 未完成*/
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int count = 0, low = 0, high = 0, len = 0;

        /*for (int i = 1, l = nums.length; i < l; i++) {
            if (nums[i - 1] > nums[i]) {
                high = i;
            } else if (high > low) {
                len = high - low;
                count += (len + 1) * len / 2;

                low = i;
            }
        }*/

        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(9);
        queue.add(11);
        queue.add(2);
        queue.add(3);
        queue.add(7);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.poll();
        System.out.println(queue.toString());
        System.out.println(Arrays.toString(queue.toArray()));

        return count;
    }
}
