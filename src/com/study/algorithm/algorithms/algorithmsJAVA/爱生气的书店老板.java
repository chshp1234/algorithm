package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
//
//在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
//
//书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
//
//请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
// 
//
//示例：
//
//输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
//输出：16
//解释：
//书店老板在最后 3 分钟保持冷静。
//感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
//
//提示：
//
//1 <= X <= customers.length == grumpy.length <= 20000
//0 <= customers[i] <= 1000
//0 <= grumpy[i] <= 1
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/grumpy-bookstore-owner
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 爱生气的书店老板 {
    @Test
    public void 爱生气的书店老板() {
        int[] customers = {9, 10, 4, 5};
        int[] grumpy    = {1, 0, 1, 1};
        System.out.println("爱生气的书店老板:" + maxSatisfied(customers, grumpy, 1));
    }

    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        //滑动窗口
        //1.先算出不用技巧的情况下，一天的满意量。这也是最小的满意度。
        //2.再在连续X位的子数组中，加上技巧后的满意度。
        //3.窗口开始滑动，窗口大小固定X：
        //做窗口滑出时，需要减去当时生气时的满意量（若不生气，则不用减），再加上滑入窗口时的满意量（若不生气，则不用加）。
        //4.每次滑动时，更新最大满意量。
        int len    = customers.length;
        int result = 0;

        for (int i = 0; i < len; i++) {
            if (grumpy[i] == 0) {
                result += customers[i];
            }
        }

        int correct = result;
        for (int i = 0; i < X; i++) {
            if (grumpy[i] == 1) {
                correct += customers[i];
            }
        }

        result = Math.max(result, correct);

        for (int i = X; i < len; i++) {
            result = Math.max(
                    result,
                    correct = correct - grumpy[i - X] * customers[i - X] + (grumpy[i] * customers[i])
                             );
        }

        return result;
    }

}
