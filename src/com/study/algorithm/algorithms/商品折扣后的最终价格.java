package com.study.algorithm.algorithms;

import java.util.Deque;
import java.util.LinkedList;

//给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
//
//商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣，其中 j 是满足 j > i 且 prices[j] <= prices[i] 的 最小下标 ，如果没有满足条件的 j ，你将没有任何折扣。
//
//请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
//
// 
//
//示例 1：
//
//输入：prices = [8,4,6,2,3]
//输出：[4,2,4,2,3]
//解释：
//商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
//商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
//商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
//商品 3 和 4 都没有折扣。
//示例 2：
//
//输入：prices = [1,2,3,4,5]
//输出：[1,2,3,4,5]
//解释：在这个例子中，所有商品都没有折扣。
//示例 3：
//
//输入：prices = [10,1,1,6]
//输出：[9,0,1,6]
// 
//
//提示：
//
//1 <= prices.length <= 500
//1 <= prices[i] <= 10^3
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/final-prices-with-a-special-discount-in-a-shop
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 商品折扣后的最终价格 {
    public int[] finalPrices(int[] prices) {
        //暴力遍历
        //两层循环遍历,找到当前下标右侧中第一个小于等于当前元素的元素,作差后将值更新值结果数组中
        //注意遍历时先将当前元素更新至对应下标的结果数组中,以保证元素组递增时值得正确性
        int len = prices.length;
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            result[i] = prices[i];
            for (int j = i + 1; j < len; j++) {
                if (prices[j] <= prices[i]) {
                    result[i] = prices[i] - prices[j];
                    break;
                }
            }
        }

        return result;
    }

    public int[] finalPricesByStack(int[] prices) {
        //栈
        //利用单调栈维护一个递增的序列,栈中存放数组下标
        //遍历时,若栈顶元素(下标处元素)大于等于当前元素,说明此元素为第一个大于等于栈顶元素的右侧元素,更新栈顶的下标处的元素,出栈,并继续判断下一个栈顶元素.
        //注意遍历时先将当前元素更新至对应下标的结果数组中,以保证元素组递增时值得正确性
        int len = prices.length;
        int[] result = new int[len];

        Deque<Integer> stack = new LinkedList<>();
        int top;

        for (int i = 0; i < len; i++) {
            result[i] = prices[i];
            while (!stack.isEmpty() && prices[i] <= prices[top = stack.peekFirst()]) {
                result[top] = prices[top] - prices[i];
                stack.pop();
            }
            stack.push(i);
        }

        return result;
    }
}
