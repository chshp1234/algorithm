package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
//
// 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
//
//
//
// 示例 1：
//
// 输入：arr = [1,2,2,1,1,3]
// 输出：true
// 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
// 示例 2：
//
// 输入：arr = [1,2]
// 输出：false
// 示例 3：
//
// 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
// 输出：true
//
//
// 提示：
//
// 1 <= arr.length <= 1000
// -1000 <= arr[i] <= 1000
//
// 来源：力扣（LeetCode）
// 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
// 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 独一无二的出现次数 {

    @Test
    public void 独一无二的出现次数() {
        int[] n = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};

        System.out.print("独一无二的出现次数:" + uniqueOccurrences(n));
    }

    public boolean uniqueOccurrences(int[] arr) {
        // 哈希表
        // 没啥特别解法，哈希表记录数组中每个数字出现的次数
        // 在用set集合判断哈希表中的values是否有重复数字即可

        int len = arr.length;
        Map<Integer, Integer> map = new HashMap<>(len);

        for (int i = 0; i < len; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        Set<Integer> set = new HashSet<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (!set.add(entry.getValue())) {
                return false;
            }
        }

        return true;
    }
}
