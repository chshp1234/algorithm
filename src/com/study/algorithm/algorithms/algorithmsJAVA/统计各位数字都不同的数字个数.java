package com.study.algorithm.algorithms.algorithmsJAVA;

//给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
// 
//
//示例 1：
//
//输入：n = 2
//输出：91
//解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
//示例 2：
//
//输入：n = 0
//输出：1
// 
//
//提示：
//
//0 <= n <= 8
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/count-numbers-with-unique-digits
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 统计各位数字都不同的数字个数 {
    public int countNumbersWithUniqueDigits(int n) {
        //动态规划
        if (n == 0) {
            return 1;
        }

        //num为数字小于10^n时总共有多少个数
        //lastLenCount为数字的长度为n时，有多少种个数
        //那么lastLenCount=lastLenCount*(10-n+1),num = num + lastLenCount;
        int num = 10;
        int lastLenCount = 9;

        for (int i = 1; i < n; i++) {
            lastLenCount = lastLenCount * (10 - i);
            num = num + lastLenCount;
        }

        return num;
    }
}
