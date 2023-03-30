package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.*;

//给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
//
//如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
//
//例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
//再例如，[1, 1, 2, 5, 7] 不是等差序列。
//数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
//
//例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
//题目数据保证答案是一个 32-bit 整数。
//
// 
//
//示例 1：
//
//输入：nums = [2,4,6,8,10]
//输出：7
//解释：所有的等差子序列为：
//[2,4,6]
//[4,6,8]
//[6,8,10]
//[2,4,6,8]
//[4,6,8,10]
//[2,4,6,8,10]
//[2,6,10]
//示例 2：
//
//输入：nums = [7,7,7,7,7]
//输出：16
//解释：数组中的任意子序列都是等差子序列。
// 
//
//提示：
//
//1 <= nums.length <= 1000
//-231 <= nums[i] <= 231 - 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
@Deprecated
public class 等差数列划分II子序列 {
    @Test
    public void 等差数列划分II子序列() {
        int[] ints = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println("等差数列划分II子序列:" + numberOfArithmeticSlices1(ints));
    }

    public int numberOfArithmeticSlices1(int[] nums) {
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        Map[] dp = new HashMap[len];

        List<Integer> last;
        List<Integer> current;
        for (int i = 1; i < len; i++) {
            dp[i] = new HashMap();
            last = new ArrayList<>();
            last.add(0);
            dp[i].put((long) nums[i] - (long) nums[0], last);
        }

        int result = 0;
        for (int i = 2; i < len; i++) {
            for (int j = 1; j < i; j++) {
                long diff = (long) (nums[i]) - (long) (nums[j]);

                last = (List<Integer>) dp[j].get(diff);
                current = (List<Integer>) dp[i].get(diff);

                if (current == null) {
                    current = new ArrayList<>();
                    dp[i].put(diff, current);
                }

                if (last == null) {
                    current.add(0);
                } else {
                    result += last.get(0) + 1;
                    current.add(last.get(0) + 1);
                    for (int k = 1, kl = last.size(); k < kl; k++) {
                        current.add(last.get(k));
                        result += last.get(k) + 1;
                    }

                }
            }
        }

        return result;
    }

    /*public int numberOfArithmeticSlices(int[] nums) {
        //dp+哈希表
        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        Integer last;
        Integer current;
        Map[] dp = new HashMap[len];
        for (int i = 1; i < len; i++) {
            dp[i] = new HashMap();
            dp[i].put((long) nums[i] - (long) nums[0], 0);
        }

        int result = 0;
        for (int i = 2; i < len; i++) {
            for (int j = 1; j < i; j++) {
                long diff = (long) (nums[i]) - (long) (nums[j]);

                last = (Integer) dp[j].get(diff);
                current = (Integer) dp[i].get(diff);

                if (current == null) {
                    dp[i].put(diff, 0);
                }

                if (last == null) {
                    current.add(0);
                } else {
                    result += last.get(0) + 1;
                    current.add(last.get(0) + 1);
                    for (int k = 1, kl = last.size(); k < kl; k++) {
                        current.add(last.get(k));
                        result += last.get(k) + 1;
                    }

                }
            }
        }

        return result;
    }*/
}
