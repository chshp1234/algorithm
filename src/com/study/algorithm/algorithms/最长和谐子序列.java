package com.study.algorithm.algorithms;

import java.util.HashMap;
import java.util.Map;

public class 最长和谐子序列 {
    public int findLHS(int[] nums) {
        //哈希表
        //哈希表记录每个数字n的数量，并与n+1的数量和n-1的数量的最大值进行相加，即可得到当前可获得的最长和谐子序列
        //注（和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 ）：
        //当n+1的数量和n-1的数量的最大值为0时，说明没有n+1或n-1的数，那么此时不进行计算

        //方法二：排序
        //排序后可方便计算当前数n的数量，并与n+1的数量相加即可  
        Map<Integer, Integer> map = new HashMap<>();

        int result = 0;

        for (int n : nums) {
            int diff = Math.max(map.getOrDefault(n - 1, 0),
                    map.getOrDefault(n + 1, 0));

            int countN = map.getOrDefault(n, 0) + 1;
            if (diff != 0) {
                result = Math.max(result, diff + countN);
            }

            map.put(n, countN);
        }

        return result;
    }
}
