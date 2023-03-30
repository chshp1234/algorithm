package com.study.algorithm.algorithms;

import org.junit.Test;

//给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
// 
//
//示例 1：
//
//输入：nums = [10,5,2,6], k = 100
//输出：8
//解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2],、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
//需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
//示例 2：
//
//输入：nums = [1,2,3], k = 0
//输出：0
// 
//
//提示: 
//
//1 <= nums.length <= 3 * 104
//1 <= nums[i] <= 1000
//0 <= k <= 106
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 乘积小于K的子数组 {
    @Test
    public void 乘积小于K的子数组() {
        System.out.print("乘积小于K的子数组：" + numSubarrayProductLessThanK(
                new int[]{10, 9, 10}, 19));

    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //滑动窗口
        //使用双指针left，right代表窗口
        //移动右指针，乘积继续乘上右指针处的元素值
        //若窗口内乘积小于k，则result = result + (right - left + 1);
        //否则移动左指针，直到left>right或则窗口内乘积小于k为止，此时result = result + (right - left + 1);

        //因为元素都大于0，乘积固然大于0，所以k=0时，没有子数组乘积会小于1；
        if (k < 2) {
            return 0;
        }
        int left = 0;
        int len = nums.length;

        int result = 0;
        int sum = 1;

        for (int right = 0; right < len; right++) {

            //如果右指针元素值大于等于k，那么乘积必然大于等于k，将不符合条件
            if (nums[right] >= k) {
                left = right + 1;
                sum = 1;
                continue;
            }

            //此时子数组nums[left...right]的乘积
            sum = sum * nums[right];
            if (sum < k) {
                //若乘积小于k
                //以右指针为结尾处，窗口内子数组数量和
                result = result + (right - left + 1);
                continue;
            }

            //若乘积大于k
            do {
                //左指针向右移动
                sum = sum / nums[left++];
            } while (left <= right && sum >= k);

            if (left > right) {
                continue;
            }

            //以右指针为结尾处，窗口内子数组数量和
            result = result + (right - left + 1);

        }

        return result;
    }
}
