package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

@Deprecated
public class 扑克牌顺序 {

    @Test
    public void 扑克牌顺序() {
        int[] ints = {
                1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 4, 8, 12, 16, 20, 24, 28, 6, 14,
                22, 2, 18, 10, 26
        };

        System.out.println("扑克牌顺序：" + Arrays.toString(pokerOrder(ints)));
    }
    /**
     * 手里一副扑克牌，一张放桌子上，一张放手里扑克牌下面
     *
     * @param nums the nums
     * @return the int [ ]
     */
    public int[] pokerOrderReverse(int[] nums) {

        int[] ints = new int[nums.length];
        Queue<Integer> queue = new LinkedList<>();
        for (int j = 0; j < nums.length; j++) {
            queue.offer(nums[j]);
        }

        for (int i = 0, l = nums.length; i < l; i++) {
            ints[i] = queue.poll();
            queue.offer(queue.poll());
            /*if (queue.peek() != null) {

            }*/
        }
        return ints;
    }
    /*todo 未完成*/
    /**
     * 手里一副扑克牌，一张放桌子上，一张放手里扑克牌下面，直到手里的牌放完。
     *
     * <p>请根据桌上牌的顺序，推测手里牌的顺序。
     *
     * @param nums the nums
     * @return the boolean
     */
    public int[] pokerOrder(int[] nums) {
        int[] result = new int[nums.length];
        // 是否调换顺序
        boolean switchOrder = false;
        // 上一轮是否数组首位填充
        boolean firstFill = false;
        // 数组填充首位下标
        int index = 0;
        for (int i = 1, l = nums.length, k = 0; ; l = l / 2, i++) {
            if (l == 0) {
                break;
            }
            if (firstFill) {
                index = (int) (index + Math.pow(2, i - 2));
            }
            int j = index;
            if ((switchOrder && firstFill) || (!switchOrder && !firstFill)) {
                j = j + 2 * (i - 1);
            }
            if (l == 1) {
                j = index;
            }
            for (; j < nums.length; j = (int) (j + Math.pow(2, i))) {
                result[j] = nums[k];
                k++;
            }
            if (switchOrder) {
                firstFill = !firstFill;
            }
            if (l % 2 != 0) {
                switchOrder = true;
            } else {
                switchOrder = false;
            }
            if (i == 1) {
                firstFill = true;
            }
        }
        return result;
    }
}
