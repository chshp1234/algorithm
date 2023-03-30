package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 有多少小于当前数字的数字 {
    // 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
    //
    // 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
    //
    // 以数组形式返回答案。
    //
    //
    //
    // 示例 1：
    //
    // 输入：nums = [8,1,2,2,3]
    // 输出：[4,0,1,1,3]
    // 解释：
    // 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
    // 对于 nums[1]=1 不存在比它小的数字。
    // 对于 nums[2]=2 存在一个比它小的数字：（1）。
    // 对于 nums[3]=2 存在一个比它小的数字：（1）。
    // 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
    // 示例 2：
    //
    // 输入：nums = [6,5,4,8]
    // 输出：[2,1,0,3]
    // 示例 3：
    //
    // 输入：nums = [7,7,7,7]
    // 输出：[0,0,0,0]
    //
    //
    // 提示：
    //
    // 2 <= nums.length <= 500
    // 0 <= nums[i] <= 100
    //
    // 来源：力扣（LeetCode）
    // 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number
    // 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    @Test
    public void 有多少小于当前数字的数字() {
        int[] matrix = {8, 1, 2, 2, 3};

        System.out.println(
                "有多少小于当前数字的数字：" + Arrays.toString(smallerNumbersThanCurrentByCountingSort(matrix)));
    }

    // 暴力遍历（不做演示）

    public int[] smallerNumbersThanCurrent(int[] nums) {
        // 排序，哈希
        // 由于结果需要根据原数组的元素进行填充，所以不能对原数组进行排序
        // 创建一个结果数组result，填充原数组原生到结果数组中，再对结果数组进行排序（可保持原数组元素不变动）
        // 使用哈希表，小于某个元素result[i]的数组出现的次数，即为当前元素的位置i，所以加入result[i], i到哈希表（map.put(result[i], i)）
        // 重新计算结果数组，根据哈希表中记录的小于某个元素的数字出现的次数，填充结果数组result[i] = map.get(nums[i])
        int len = nums.length;
        int[] result = new int[len];
        if (len <= 1) {
            return result;
        }
        System.arraycopy(nums, 0, result, 0, len);
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(result);
        map.put(result[0], 0);
        for (int i = 1; i < len; i++) {
            if (result[i] != result[i - 1]) {
                map.put(result[i], i);
            }
        }

        for (int i = 0; i < len; i++) {
            result[i] = map.get(nums[i]);
        }
        return result;

        // 我们也可以将数组排序，并记录每一个数在原数组中的位置。
        // 对于排序后的数组中的每一个数，我们找出其左侧第一个小于它的数，这样就能够知道数组中小于该数的数量。
        //
        // int n = nums.length;
        // 使用一个二维数组，记录每个元素以及其对应在原数组中的位置
        // int[][] data = new int[n][2];
        // for (int i = 0; i < n; i++) {
        //     data[i][0] = nums[i];
        //     data[i][1] = i;
        // }
        // Arrays.sort(data, new Comparator<int[]>() {
        //     public int compare(int[] data1, int[] data2) {
        //         return data1[0] - data2[0];
        //     }
        // });
        //
        // int[] ret = new int[n];
        // int prev = -1;
        // for (int i = 0; i < n; i++) {
        //     if (prev == -1 || data[i][0] != data[i - 1][0]) {
        //         prev = i;
        //     }
        //     ret[data[i][1]] = prev;
        // }
        // return ret;
        //
        // 作者：LeetCode-Solution
        // 链接：https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/solution/you-duo-shao-xiao-yu-dang-qian-shu-zi-de-shu-zi--2/
        // 来源：力扣（LeetCode）
        // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    }

    public int[] smallerNumbersThanCurrentByCountingSort(int[] nums) {
        // 注意到数组元素的值域为 [0,100]，所以可以考虑建立一个频次数组 cnt[i] 表示数字 i 出现的次数。
        // 那么对于数字 i而言，小于它的数目就为 cnt[0...i−1] 的总和。

        int[] cnt = new int[101];
        int n = nums.length;

        // 第一步，计算每个数字出现的次数
        for (int i = 0; i < n; i++) {
            cnt[nums[i]]++;
        }

        // 第二步，计算区间[0,i]中，总共有的数字的次数
        for (int i = 1; i <= 100; i++) {
            cnt[i] += cnt[i - 1];
        }
        int[] ret = new int[n];

        // 第三步，对于数字num[i](>0),小于这个数的数字的次数就为区间[0,nums[i] - 1]的数字次数，也就是计算后的cnt[nums[i] - 1]
        for (int i = 0; i < n; i++) {
            ret[i] = nums[i] == 0 ? 0 : cnt[nums[i] - 1];
        }
        return ret;
    }
}
