package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;

//给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
//
//示例 1:
//
//输入: [1,2,3]
//输出: 6
//示例 2:
//
//输入: [1,2,3,4]
//输出: 24
//注意:
//
//给定的整型数组长度范围是[3,104]，数组中所有的元素范围是[-1000, 1000]。
//输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 三个数的最大乘积 {
    @Test
    public void 三个数的最大乘积() {
        System.out.println("三个数的最大乘积:" + maximumProduct(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    public int maximumProduct(int[] nums) {
        //首先将数组排序。
        //
        //如果数组中全是非负数，则排序后最大的三个数相乘即为最大乘积；如果全是非正数，则最大的三个数相乘同样也为最大乘积。
        //
        //如果数组中有正数有负数，则最大乘积既可能是三个最大正数的乘积，也可能是两个最小负数（即绝对值最大）与最大正数的乘积。
        //
        //综上，我们在给数组排序后，分别求出三个最大正数的乘积，以及两个最小负数与最大正数的乘积，二者之间的最大值即为所求答案。
        //
        //作者：LeetCode-Solution
        //链接：https://leetcode-cn.com/problems/maximum-product-of-three-numbers/solution/san-ge-shu-de-zui-da-cheng-ji-by-leetcod-t9sb/
        //来源：力扣（LeetCode）
        //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
        Arrays.sort(nums);
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1],
                        nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]
                       );

        //方法二：线性扫描
        //在方法一中，我们实际上只要求出数组中最大的三个数以及最小的两个数，因此我们可以不用排序，用线性扫描直接得出这五个数。
        
        // 最小的和第二小的
        /*int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        // 最大的、第二大的和第三大的
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int x : nums) {
            if (x < min1) {
                min2 = min1;
                min1 = x;
            } else if (x < min2) {
                min2 = x;
            }

            if (x > max1) {
                max3 = max2;
                max2 = max1;
                max1 = x;
            } else if (x > max2) {
                max3 = max2;
                max2 = x;
            } else if (x > max3) {
                max3 = x;
            }
        }

        return Math.max(min1 * min2 * max1, max1 * max2 * max3);*/
    }

}
