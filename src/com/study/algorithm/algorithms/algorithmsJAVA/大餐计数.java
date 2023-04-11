package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

import java.util.*;

public class 大餐计数 {
    @Test
    public void 大餐计数() {
        int[] ints = new int[100000];
        Arrays.fill(ints, 32);
        System.out.println("大餐计数:" + countPairs(ints));
    }

    public int countPairs(int[] deliciousness) {
        //排序，哈希表
        //1.用哈希表统计出每种不同美味度食物出现的次数
        //2.对不同美味度的食物进行排序
        //3.找出食物可能配对出的大餐的最小值与最大值
        //4.从最小值到最大值进行遍历，因为前面已经用哈希表进行统计，那么这里就可以大餐的值减去当前食物的美味度，查看差值是否存在于哈希表中即可
        //4.1.判断匹配成的两个食物美味度是否相同，若相同则当前可匹配出num*（num-1）/ 2
        //4.2.若俩食物美味度不相同，那么可匹配的数量就为两种美味度食物的数量相乘

        if (deliciousness.length == 1) {
            //只有一个食物，无法匹配
            return 0;
        }
        Map<Integer, Integer> counter = new HashMap<>();

        for (int i : deliciousness) {
            //统计每种美味度的食物的数量
            counter.put(i, counter.getOrDefault(i, 0) + 1);
        }
        //nums则代表有多少种不同的美味度
        List<Integer> nums = new ArrayList<>(counter.keySet());

        //排序，方便后续计算
        Collections.sort(nums);
        int len = nums.size();

        //找出这些食物可能匹配出的最大美味度的大餐的值
        int MAX = tableSizeFor(nums.get(len - 1));
        if (MAX == nums.get(len - 1)) {
            //若相同，则还需*2
            MAX = MAX << 1;
        }
        //找出这些食物可能匹配出的最小美味度的大餐的值
        int MIN = tableSizeFor(nums.get(0) << 1);
        if (MIN == 0) {
            MIN = 1;
        }
        //防止溢出，用long
        long result = 0;
        Integer diff;
        //那么我们只需要在MIN~MAX之间进行查找即可，因为可能匹配出的大餐值不可能小于MIN，也不可能大于MAX
        for (int i = MIN; i <= MAX; i = i << 1) {
            //防止重复计算，我们只需要找其中一半的值即可
            int limit = i >> 1;
            for (int n = 0; n < len && nums.get(n) <= limit; n++) {
                diff = counter.get(i - nums.get(n));
                //若能匹配
                if (diff != null) {
                    //如果可匹配出大餐的两个美味度相同
                    if (nums.get(n) == i - nums.get(n)) {
                        result += (((long) diff * (diff - 1)) / 2) % 1000000007;
                    }
                    //如果可匹配出大餐的两个美味度不相同
                    else {
                        result += ((long) diff * counter.get(nums.get(n))) % 1000000007;
                    }
                }
            }
        }
        //返回结果
        return (int) result;
    }

    //找出大于某个值的最小2次幂
    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }
}
