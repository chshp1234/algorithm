package com.study.algorithm.algorithms;

import org.junit.Test;

//给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
//
// 
//
//示例 1：
//
//输入：[3, 2, 1]
//输出：1
//解释：第三大的数是 1 。
//示例 2：
//
//输入：[1, 2]
//输出：2
//解释：第三大的数不存在, 所以返回最大的数 2 。
//示例 3：
//
//输入：[2, 2, 3, 1]
//输出：1
//解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
//此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
// 
//
//提示：
//
//1 <= nums.length <= 104
//-231 <= nums[i] <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/third-maximum-number
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 第三大的数 {
    @Test
    public void 第三大的数() {
        System.out.println("第三大的数:" + thirdMax(new int[]{1, 2, 2, 5, 3, 5}));
    }

    public int thirdMax(int[] nums) {
        //一趟遍历
        //因为要严格意义的第三大，注意过滤掉相同的元素即可
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }
        if (len == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int f = nums[0], s = 2_147_483_647, t = 2_147_483_647;
        int index = 1;
        while (index < len && nums[index] == f) {
            index++;
        }
        if (index == len) {
            return f;
        }
        if (nums[index] > f) {
            s = f;
            f = nums[index];
        } else {
            s = nums[index];
        }
        index++;
        while (index < len && (nums[index] == f || nums[index] == s)) {
            index++;
        }
        if (index == len) {
            return f;
        }
        if (nums[index] > f) {
            t = s;
            s = f;
            f = nums[index];
        } else if (nums[index] > s) {
            t = s;
            s = nums[index];
        } else {
            t = nums[index];
        }
        index++;
        while (index < len) {
            if (nums[index] == f || nums[index] == s || nums[index] == t) {
                index++;
                continue;
            }
            if (nums[index] > f) {
                t = s;
                s = f;
                f = nums[index];
            } else if (nums[index] > s) {
                t = s;
                s = nums[index];
            } else if (nums[index] > t) {
                t = nums[index];
            }
            index++;
        }
        return t;
    }
}
