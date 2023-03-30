package com.study.algorithm.algorithms;

//给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。
//
//「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
//
// 
//
//示例 1：
//
//输入：s = "011101"
//输出：5
//解释：
//将字符串 s 划分为两个非空子字符串的可行方案有：
//左子字符串 = "0" 且 右子字符串 = "11101"，得分 = 1 + 4 = 5
//左子字符串 = "01" 且 右子字符串 = "1101"，得分 = 1 + 3 = 4
//左子字符串 = "011" 且 右子字符串 = "101"，得分 = 1 + 2 = 3
//左子字符串 = "0111" 且 右子字符串 = "01"，得分 = 1 + 1 = 2
//左子字符串 = "01110" 且 右子字符串 = "1"，得分 = 2 + 1 = 3
//示例 2：
//
//输入：s = "00111"
//输出：5
//解释：当 左子字符串 = "00" 且 右子字符串 = "111" 时，我们得到最大得分 = 2 + 3 = 5
//示例 3：
//
//输入：s = "1111"
//输出：3
// 
//
//提示：
//
//2 <= s.length <= 500
//字符串 s 仅由字符 '0' 和 '1' 组成。
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/maximum-score-after-splitting-a-string
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 分割字符串的最大得分 {
    public int maxScore(String s) {
        //两趟遍历
        //计算字符串中总共0的数量和1的数量
        //再次遍历时，记录左边子串中0的数量left，那么右边子串中1的数量就为left + (oneCount - (i + 1 - left))，统计左右的最大和
        int zeroCount = 0;
        int oneCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            }
        }
        oneCount = s.length() - zeroCount;

        int max = 0;
        int left = 0;

        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                left++;
            }
            max = Math.max(max, left + (oneCount - (i + 1 - left)));
        }

        return max;
    }



    public int maxScoreByLeetCode(String s) {
        //两趟遍历
        //找出按每个字符位置进行分割时，得分的最大值
        //第一次遍历，以第一个字符进行分割，统计右子串中1的数量，即为右子串的得分，若第一个字符为0，那么得分再加1
        //再从第二个字符开始，以每个字符进行分割，若字符为0，说明分到左子串的得分能够+1，那么总得分+1；若字符为1，左子串没法得分，反而右子串的得分少了1，那么总得分-1
        //第二趟遍历只需要维护最大得分和总得分的关系，即可
        int score = 0;
        int n = s.length();
        if (s.charAt(0) == '0') {
            score++;
        }
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == '1') {
                score++;
            }
        }
        int ans = score;
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == '0') {
                score++;
            } else {
                score--;
            }
            ans = Math.max(ans, score);
        }
        return ans;
    }

}
