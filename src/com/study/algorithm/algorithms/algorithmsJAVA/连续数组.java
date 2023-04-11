package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
//
// 
//
//示例 1:
//
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
//示例 2:
//
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
// 
//
//提示：
//
//1 <= nums.length <= 105
//nums[i] 不是 0 就是 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/contiguous-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 连续数组 {
    @Test
    public void 连续数组() {
        int[] nums = new int[]{1, 0};

        System.out.println("连续数组：" + findMaxLength(nums));
    }

    public int findMaxLength(int[] nums) {
        //连续子数组中0和1的数量相同，可以设0为-1，这样就等价于连续子数组中，合为0
        //那么我们就可以使用前缀和，来计算每个子数组的和
        //使用哈希表来存储，当前和第一次出现的位置，当preSum(i)==preSum(j)时，说明子数组nums[i..j]区间和为0，那么进一步可判断出此区间中0和1的数量相同
        //便利数组，计算前缀和preSum(j),我们只需要找到数组中前缀和preSum(i)第一次出现的地方i，那么此区间num[i..j]的和就为0，即可更新最长子数组大小
        int len = nums.length;

        if (len == 1) {
            return 0;
        }

        //存储前缀和中，第一次出现的下标处
        Map<Integer, Integer> map = new HashMap<>();
        int[] TANSLATE = {-1, 1};

        int preSum = TANSLATE[nums[0]];

        map.put(0, -1);
        map.put(preSum, 0);

        int max = 0;

        for (int i = 1; i < len; i++) {
            preSum = preSum + TANSLATE[nums[i]];
            //若此前缀和为0，此肯定为当前最大子数组
            if (preSum == 0) {
                max = i + 1;
            } else {
                Integer last;
                //若哈希表中存在此前缀和，那么preSum[i]==preSum[last]->preSum[i]-preSum[last]=0
                //说明子数组nums[i..j]区间和为0
                if ((last = map.get(preSum)) != null) {
                    //更新最大长度
                    max = Math.max(max, i - last);
                } else {
                    //若不存在，将当前前缀和，以及下标加入哈希表中
                    map.put(preSum, i);
                }
            }
        }

        return max == 1 ? 0 : max;
    }
}
