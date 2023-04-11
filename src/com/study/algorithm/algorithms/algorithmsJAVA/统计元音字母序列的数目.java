package com.study.algorithm.algorithms.algorithmsJAVA;

import org.junit.Test;

//给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
//
//字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
//每个元音 'a' 后面都只能跟着 'e'
//每个元音 'e' 后面只能跟着 'a' 或者是 'i'
//每个元音 'i' 后面 不能 再跟着另一个 'i'
//每个元音 'o' 后面只能跟着 'i' 或者是 'u'
//每个元音 'u' 后面只能跟着 'a'
//由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
//
// 
//
//示例 1：
//
//输入：n = 1
//输出：5
//解释：所有可能的字符串分别是："a", "e", "i" , "o" 和 "u"。
//示例 2：
//
//输入：n = 2
//输出：10
//解释：所有可能的字符串分别是："ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" 和 "ua"。
//示例 3：
//
//输入：n = 5
//输出：68
// 
//
//提示：
//
//1 <= n <= 2 * 10^4
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-vowels-permutation
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 统计元音字母序列的数目 {
    @Test
    public void 统计元音字母序列的数目() {
        System.out.println("统计元音字母序列的数目：" + countVowelPermutation(41));
    }

    public int countVowelPermutation(int n) {

        //动态规划
        //设dp[0...4]分别为以{a,e,i,o,u}为结尾的字母的数量
        //根据条件可知：
        //a，可由eiu后拼接而来
        //e，可由ai后拼接而来
        //i，可由eo后拼接而来
        //o，可由i后拼接而来
        //u，可由io后拼接而来
        //那么我们就可以根据当前各个字母为结尾的数量，继而得出下一轮各个字母为结尾的数量
        if (n == 1) {
            return 5;
        }
        int[] counter = new int[]{1, 1, 1, 1, 1};
        int last = 0;
        int temp = 0;
        for (int i = 1; i < n; i++) {
            last = counter[0];
            //a为结尾的数量为当前eiu为结尾的数量之和
            counter[0] = ((counter[1] + counter[2]) % 1000000007 + counter[4]) % 1000000007;

            temp = counter[1];
            //e为结尾的数量为当前ai为结尾的数量之和
            counter[1] = (last + counter[2]) % 1000000007;
            last = temp;

            temp = counter[2];
            //i为结尾的数量为当前eo为结尾的数量之和
            counter[2] = (last + counter[3]) % 1000000007;
            last = temp;

            temp = counter[3];
            //o为结尾的数量为当前i为结尾的数量之和
            counter[3] = last;
            last = temp;

            //u为结尾的数量为当前io为结尾的数量之和
            counter[4] = (last + counter[3]) % 1000000007;
        }

        return (((counter[0] + counter[1]) % 1000000007 + (counter[2] + counter[3]) % 1000000007) % 1000000007 + counter[4]) % 1000000007;
    }
}
