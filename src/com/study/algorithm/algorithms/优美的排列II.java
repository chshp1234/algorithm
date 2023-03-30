package com.study.algorithm.algorithms;

//给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
//
//假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
//返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
//
// 
//
//示例 1：
//
//输入：n = 3, k = 1
//输出：[1, 2, 3]
//解释：[1, 2, 3] 包含 3 个范围在 1-3 的不同整数，并且 [1, 1] 中有且仅有 1 个不同整数：1
//示例 2：
//
//输入：n = 3, k = 2
//输出：[1, 3, 2]
//解释：[1, 3, 2] 包含 3 个范围在 1-3 的不同整数，并且 [2, 1] 中有且仅有 2 个不同整数：1 和 2
// 
//
//提示：
//
//1 <= k < n <= 104
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/beautiful-arrangement-ii
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 优美的排列II {
    public int[] constructArray(int n, int k) {
        //数组构造
        //首先,当n=1或n=2时,不需要对数组进行处理,直接返回1或1,2即可
        //其次,当数组为{1,2,3,...,n}时,每个数间的差值都为1
        //当把n提前,{1,n,2,3,...,n-1}时,会发现,前面两个差值变成了n-1和n-2,后面的都为1,那么有3种不同差值
        //在把n-1提前,{1,n,2,n-1,3,...,n-2},前面4个数间的差值是n-1,n-2,n-3,n-4,后面都为1,有5种不同差值
        //所以当差值数量为1时,只需要返回正常1..n的数组即可,当差值数量为1,3,5,时,可以把后面较大的数排在前面
        //那差值数量为2,4,6咋办呢?我们可以把最后两个数进行交换.{1,n,2,n-1,3,...,n-4,n-2,n-3},那么最近两个数差值为2和1,就能够多出1个"2"这个差值,共有6个不同差值
        //首先前面要交换的次数为count=(k-1)/2,这样有2*count+1个不同差值的数,若(k-1)%2=1,说明最后两个数还需进行交换
        //由此返回构造后的数组即可
        if (n == 1) {
            return new int[]{1};
        }
        if (n == 2) {
            return new int[]{1, 2};
        }
        int[] answer = new int[n];

        //把后面大数提前的次数
        int count = (k - 1) / 2;

        for (int i = 0; i < count; i++) {
            //先构造一个小数
            answer[i * 2] = 1 + i;
            //在构造后面的大数
            answer[i * 2 + 1] = n - i;
        }

        //剩余还需构造的数
        for (int i = 0, l = n - count * 2; i < l; i++) {
            answer[count * 2 + i] = count + i + 1;
        }

        //k是偶数的情况
        if ((k - 1) % 2 != 0) {
            //交换最后两个数
            answer[n - 1] ^= answer[n - 2];
            answer[n - 2] ^= answer[n - 1];
            answer[n - 1] ^= answer[n - 2];
        }

        return answer;
    }
}
