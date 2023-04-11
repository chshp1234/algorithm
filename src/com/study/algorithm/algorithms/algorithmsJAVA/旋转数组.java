package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.Arrays;

//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
//
//示例 1:
//
//输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
//示例 2:
//
//输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释:
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100]
//说明:
//
//尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
//要求使用空间复杂度为 O(1) 的 原地 算法。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/rotate-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 旋转数组 {
    @Test
    public void 旋转数组() {
        int[] ints = {1, 2, 3, 4, 5, 6, 7};
        rotate(ints, 3);
        System.out.println("旋转数组:" + Arrays.toString(ints));
    }

    public void rotate(int[] nums, int k) {
        //最大公约数
        //数据总共会移动nums.length*k次
        //我们可以先求出len和k的最小公倍数lcm
        //内循环次数为lcm/k，因为lcm为数组长度和k的最小公倍数，所以循环移动lcm/k次后，此时坐标将回到最初开始的位置
        //外循环为len * k/lcm，即共需要几次内循环，方可遍历完所有元素
        if (nums == null || nums.length == 1) {
            return;
        }

        int len = nums.length;
        if (k % len == 0) {
            return;
        }

        int totalStep = len * k;
        int step = 0;

        int lcm = totalStep / gcd(len, k);

        for (int j = 0, jl = totalStep / lcm; j < jl; j++) {
            int last = nums[j];
            for (int i = 0, l = lcm / k; i < l; i++) {
                step += k;
                int swapIndex = (step + j) % len;
                int temp = nums[swapIndex];
                nums[swapIndex] = last;
                last = temp;
            }
        }

    }

    //求两数的最大公约数
    //最小公倍数为(a*b)/最大公约数
    private static int gcd(int a, int b) {
        int t;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
