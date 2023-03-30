package com.study.algorithm.algorithms;

import org.junit.Test;

public class 旋转数组的最小数字 {

    @Test
    public void 旋转数组的最小数字() {
        int[] ints = {1, 3, 5};
        System.out.println("旋转数组的最小数字:" + minArrayByBinarySearch(ints));
    }

    public int minArray(int[] numbers) {

        // 暴力解法，遍历数组，若当前元素小于左侧元素，则说明到达“转折点”，为最小值，返回该值
        for (int i = 1, l = numbers.length; i < l; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        // 若遍历结束，则说明此数组没有进行旋转，则第一个数为最小值
        return numbers[0];
    }

    public int minArrayByBinarySearch(int[] numbers) {

        // 二分查找（154：允许重复元素，153：没有重复元素）
        // 根据旋转数组的性质，我们把数组分为两部分，左侧和右侧（左右两个排序数组），其中第一个最小值为分界点。
        // 可以看出，右侧的值“都”小于等于左侧的值，且右侧中最大值（最右边）值小于等于左侧最小值（最左边）
        // 因此，我们以数组左右两端对数组进行二分查找
        // ·当 nums[mid] > nums[right]时，mid一定在数组左侧，最小值 一定满足 mid < 最小值 <= right，因此执行 left = mid+1
        // ·当 nums[mid] < nums[right]时，mid一定在数组右侧，最小值 一定满足 left < 最小值 <= mid，因此执行 right = mid
        // ·当 nums[mid] = nums[right]时，由于元素可重复，无法判断mid在哪个排序数组中，我们采用 right = right - 1，逐一缩小查找空间
        // https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/mian-shi-ti-11-xuan-zhuan-shu-zu-de-zui-xiao-shu-3/
        //
        // 关于为什么不用left为判断端点，为啥不用left++，因为此数组为递增数组，且当left+right为奇数时，取到的中间值会靠近left，
        // 比如（0+1）/2=0，若此时位置0为最小值，而判断结果为nums[mid] = nums[left]，left++，就会得到错误答案
        // 比如数组没有进行旋转，也会对区间进行错误判断，从而得到错误答案
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int mi = (left + right) >>> 1;
            if (numbers[mi] > numbers[right]) {
                left = mi + 1;
            } else if (numbers[mi] < numbers[right]) {
                right = mi;
            } else {
                // 若numbers[mi] = numbers[right]，
                // 由于重复元素的存在，我们并不能确定 numbers[mi]究竟在最小值的左侧还是右侧，因此我们不能莽撞地忽略某一部分的元素。
                // 我们采用 right = right - 1，逐一缩小查找空间
                // （若为第153题，数组没有重复元素，则不会走到此处的判断）
                right--;
            }
        }
        return numbers[left];
    }
}
