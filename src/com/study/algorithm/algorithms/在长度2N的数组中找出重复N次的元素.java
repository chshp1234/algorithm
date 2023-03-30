package com.study.algorithm.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//给你一个整数数组 nums ，该数组具有以下属性：
//
//nums.length == 2 * n.
//nums 包含 n + 1 个 不同的 元素
//nums 中恰有一个元素重复 n 次
//找出并返回重复了 n 次的那个元素。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,3]
//输出：3
//示例 2：
//
//输入：nums = [2,1,2,5,3,2]
//输出：2
//示例 3：
//
//输入：nums = [5,1,5,2,5,3,5,4]
//输出：5
// 
//
//提示：
//
//2 <= n <= 5000
//nums.length == 2 * n
//0 <= nums[i] <= 104
//nums 由 n + 1 个 不同的 元素组成，且其中一个元素恰好重复 n 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/n-repeated-element-in-size-2n-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 在长度2N的数组中找出重复N次的元素 {
    public int repeatedNTimes(int[] nums) {
        //哈希表
        //用哈希表统计元素出现的次数，因为只有一个元素有重复，所以若次数大于等于2，说明需要找的就是这个元素
        Map<Integer, Integer> map = new HashMap<>();

        Integer count;
        for (int n : nums) {
            count = map.getOrDefault(n, 0);
            if (count == 1) {
                return n;
            }
            map.put(n, count + 1);
        }

        return 0;
    }

    //随机选择
    //我们可以每次随机选择两个不同的下标，判断它们对应的元素是否相等即可。如果相等，那么返回任意一个作为答案。
    //复杂度分析
    //
    //时间复杂度：期望 O(1)。选择两个相同元素的概率为(n/2n)*((n-1)/2n)≈1/4，因此期望 44 次结束循环。
    //
    //空间复杂度：O(1)。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode.cn/problems/n-repeated-element-in-size-2n-array/solution/zai-chang-du-2n-de-shu-zu-zhong-zhao-chu-w88a/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int repeatedNTimesByLeetcode(int[] nums) {
        int n = nums.length;
        Random random = new Random();

        while (true) {
            int x = random.nextInt(n), y = random.nextInt(n);
            if (x != y && nums[x] == nums[y]) {
                return nums[x];
            }
        }
    }
}
