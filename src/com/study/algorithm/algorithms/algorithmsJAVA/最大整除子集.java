package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
//answer[i] % answer[j] == 0 ，或
//answer[j] % answer[i] == 0
//如果存在多个有效解子集，返回其中任何一个均可。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3]
//输出：[1,2]
//解释：[1,3] 也会被视为正确答案。
//示例 2：
//
//输入：nums = [1,2,4,8]
//输出：[1,2,4,8]
// 
//
//提示：
//
//1 <= nums.length <= 1000
//1 <= nums[i] <= 2 * 109
//nums 中的所有整数 互不相同
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/largest-divisible-subset
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最大整除子集 {

    @Test
    public void 最大整除子集() {
        int[] ints = {343, 49, 8, 4, 2, 1};
        System.out.println("最大整除子集:" + largestDivisibleSubset(ints));
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {

        //动态规划
        //根据题目要求，任何一个较大的数都是任何较小的数的整数倍
        //可以先对数组进行排序，从小到大遍历，方便找到每一个整数倍的更大值
        //dp[i] :排序后的数组中，以元素num[i]为结尾（包含元素）的最大子集
        //那么在每一次遍历新元素时，需要从头开始判断，若以当前元素为结尾可以构成更大子集时，更新数据

        int                 len     = nums.length;
        List<Integer>       temp    = new ArrayList<>();
        List<Integer>       result  = new ArrayList<>();
        final List<Integer> DEFAULT = new ArrayList<>();
        result.add(nums[0]);
        if (len == 1) {
            return result;
        }
        //先排序，方便计算
        Arrays.sort(nums);
        List<Integer>[] dp = new ArrayList[len];
        temp.add(nums[0]);
        dp[0] = new ArrayList<>(temp);
        for (int i = 1; i < len; i++) {
            temp = DEFAULT;

            for (int j = 0; j < i; j++) {
                //子集dp[j]中的结尾元素
                int endNum = dp[j].get(dp[j].size() - 1);
                if (endNum > (nums[i] / 2)) {
                    break;
                }
                //可以整除，并且当前元素加入后可以构成更大的子集
                if (nums[i] % endNum == 0 && dp[j].size() > temp.size()) {
                    temp = dp[j];
                }
            }

            temp = new ArrayList<>(temp);
            temp.add(nums[i]);
            dp[i] = temp;

            if (temp.size() > result.size()) {
                result = temp;
            }
        }
        return result;
    }
}
