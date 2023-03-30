package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
//
//子数组 是数组的一段连续部分。
//
// 
//
//示例 1：
//
//输入：nums = [1,0,1,0,1], goal = 2
//输出：4
//解释：
//有 4 个满足题目要求的子数组：[1,0,1]、[1,0,1,0]、[0,1,0,1]、[1,0,1]
//示例 2：
//
//输入：nums = [0,0,0,0,0], goal = 0
//输出：15
// 
//
//提示：
//
//1 <= nums.length <= 3 * 104
//nums[i] 不是 0 就是 1
//0 <= goal <= nums.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 和相同的二元子数组 {
    @Test
    public void 和相同的二元子数组() {

        int[] ints = new int[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        int K = 0;

        System.out.printf("和相同的二元子数组：" + numSubarraysWithSum(ints, K), K);
    }

    public int numSubarraysWithSum(int[] nums, int goal) {
        //滑动窗口

        //设高低指针start、end
        //若当前区域和大于goal，那么左指针右移，一直到start右边第一个出现的1处
        //为方便记录，我们用一个队列存储每个数字1出现的下标，start右移时，队列出队第一个数减去start的差值diff，就为当前可匹配的子数组数
        //若当前区域和等于goal，那么加上diff

        int len = nums.length;
        int start = 0;
        int end = 0;
        int result = 0;

        if (goal == 0) {
            while (end < len) {
                if (nums[end++] == 0) {
                    result += (end - start);
                } else {
                    start = end;
                }
            }
            return result;
        }

        Deque<Integer> deque = new LinkedList<>();

        while (end < len) {
            if (nums[end] == 1) {
                start = end;
                result += 1;
                end++;
                break;
            }
            end++;
        }

        if (goal != 1) {
            while (end < len) {
                if (nums[end] == 1) {
                    deque.offer(end);
                    result++;
                    if (result == goal) {
                        end++;
                        break;
                    }
                }
                end++;
            }
        }
        if (end == len) {
            return result == goal ? start + 1 : 0;
        }
        result = start + 1;
        int diff = start + 1;
        while (end < len) {
            if (nums[end] == 1) {
                deque.offer(end);
                int temp = deque.poll();
                diff = temp - start;
                result += diff;
                start = temp;
            } else {
                result += diff;
            }
            end++;
        }
        return result;
    }

    //方法一：哈希表
    //思路及算法
    //
    //假设原数组的前缀和数组为 sum，且子数组 (i,j] 的区间和为 goal，那么 sum[j]−sum[i]=goal。
    //因此我们可以枚举 j ，每次查询满足该等式的 i 的数量。
    //
    //具体地，我们用哈希表记录每一种前缀和出现的次数，假设我们当前枚举到元素 nums[j]，我们只需要查询哈希表中元素 sum[j]−goal 的数量即可，
    //这些元素的数量即对应了以当前 j 值为右边界的满足条件的子数组的数量。
    //最后这些元素的总数量即为所有和为 goal 的子数组数量。
    //
    //在实际代码中，我们实时地更新哈希表，以防止出现 i≥j 的情况。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/binary-subarrays-with-sum/solution/he-xiang-tong-de-er-yuan-zi-shu-zu-by-le-5caf/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int numSubarraysWithSumByLeetCode(int[] nums, int goal) {
        int sum = 0;
        //哈希表，遍历过程中，前缀和为“key”的子数组数量“value”
        Map<Integer, Integer> cnt = new HashMap<>();
        int ret = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ret += cnt.getOrDefault(sum - goal, 0);
        }
        return ret;
    }

}
