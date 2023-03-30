package com.study.algorithm.algorithms;

import java.util.Arrays;

//集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
//
//给定一个数组 nums 代表了集合 S 发生错误后的结果。
//
//请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,2,4]
//输出：[2,3]
//示例 2：
//
//输入：nums = [1,1]
//输出：[1,2]
// 
//
//提示：
//
//2 <= nums.length <= 104
//1 <= nums[i] <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/set-mismatch
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 错误的集合 {

    public int[] findErrorNums(int[] nums) {
        //排序
        //先排序，然后依次遍历，找出重复，与丢失的数

        int len = nums.length;
        Arrays.sort(nums);

        int[] result = new int[2];
        for (int i = 1; i < len; i++) {
            //判断是否和上一个数相同
            if (nums[i] == nums[i - 1]) {
                result[0] = nums[i];
                //查找完毕，提前返回
                if (result[1] != 0) {
                    return result;
                }
            }
            //判断是否比上一个数大‘2’
            else if (nums[i] == nums[i - 1] + 2) {
                result[1] = nums[i] - 1;
                //查找完毕，提前返回
                if (result[0] != 0) {
                    return result;
                }
            }
        }
        //注意，若到此，重复的数在数组开头或结尾并且其余数都是有序递增的（步长1）
        //那么需要判断开头的数是否为1，如果为1，那么肯定是结尾处少了一个数
        //如果开头不为1，那么肯定是少了个1
        result[1] = nums[0] == 1 ? nums[len - 1] + 1 : 1;
        return result;
    }
}
