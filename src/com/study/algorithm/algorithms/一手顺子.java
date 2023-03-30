package com.study.algorithm.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
//
//给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
//输出：true
//解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
//示例 2：
//
//输入：hand = [1,2,3,4,5], groupSize = 4
//输出：false
//解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
// 
//
//提示：
//
//1 <= hand.length <= 104
//0 <= hand[i] <= 109
//1 <= groupSize <= hand.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/hand-of-straights
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 一手顺子 {

    @Test
    public void 一手顺子() {
        int[] nums = new int[]{1, 1, 2, 2, 3, 3};

        System.out.println("一手顺子：" + isNStraightHand(nums, 3));
    }

    public boolean isNStraightHand(int[] hand, int groupSize) {
        //哈希表，排序
        //因为需要顺子，所以得对数组进行排序，从小到大进行选择匹配
        //用哈希表记录每个数出现的次数
        //在遍历判断一个起始值min是否能组成顺子时，可以判断哈希表中是否有这个元素或这个元素的次数是否大于0即可

        int len = hand.length;
        if (len % groupSize != 0) {
            return false;
        }

        Map<Integer, Integer> counter = new HashMap<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int max = 0;
        for (int i : hand) {
            Integer count = counter.get(i);
            if (count == null) {
                queue.offer(i);
                counter.put(i, 1);
            } else {
                counter.put(i, count + 1);
            }
            max = Math.max(max, i);
        }

        int min;
        while (len > 0) {
            Integer count = counter.get(queue.peek());
            if (count > 0) {
                counter.put(queue.peek(), count - 1);
                min = queue.peek();
                if (min + groupSize - 1 > max) {
                    return false;
                }
                len--;
                for (int j = min + 1, l = min + groupSize; j < l; j++) {
                    count = counter.get(j);

                    if (count == null || count == 0) {
                        return false;
                    }
                    counter.put(j, count - 1);
                    len--;
                }
            } else {
                queue.poll();
            }
        }

        return true;
    }
}
