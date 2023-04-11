package com.study.algorithm.algorithms.algorithmsJAVA;

import java.util.HashMap;
import java.util.Map;

//给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
//
//请你返回 nums 中唯一元素的 和 。
//
// 
//
//示例 1：
//
//输入：nums = [1,2,3,2]
//输出：4
//解释：唯一元素为 [1,3] ，和为 4 。
//示例 2：
//
//输入：nums = [1,1,1,1,1]
//输出：0
//解释：没有唯一元素，和为 0 。
//示例 3 ：
//
//输入：nums = [1,2,3,4,5]
//输出：15
//解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
// 
//
//提示：
//
//1 <= nums.length <= 100
//1 <= nums[i] <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/sum-of-unique-elements
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 唯一元素的和 {
    public int sumOfUnique(int[] nums) {
        //哈希表
        //用哈希表统计每个元素出现的次数
        //再将出现次数为1的元素加入“和”中
        Map<Integer, Integer> counter = new HashMap<>();
        for (int n : nums) {
            counter.put(n, counter.getOrDefault(n, 0) + 1);
        }

        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == 1) {
                sum += entry.getKey();
            }
        }

        return sum;
    }

    //我们可以赋给每个元素三个状态：
    //
    //·该元素尚未被访问；
    //·该元素被访问过一次；
    //·该元素被访问超过一次。
    //我们可以在首次访问一个元素时，将该元素加入答案，然后将该元素状态标记为 1。
    //在访问到一个标记为 1 的元素时，由于这意味着该元素出现不止一次，因此将其从答案中减去，并将该元素状态标记为 2。
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/sum-of-unique-elements/solution/wei-yi-yuan-su-de-he-by-leetcode-solutio-tueh/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int sumOfUniqueByLeetCode(int[] nums) {
        int ans = 0;
        Map<Integer, Integer> state = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!state.containsKey(num)) {
                ans += num;
                state.put(num, 1);
            } else if (state.get(num) == 1) {
                ans -= num;
                state.put(num, 2);
            }
        }
        return ans;
    }

}
