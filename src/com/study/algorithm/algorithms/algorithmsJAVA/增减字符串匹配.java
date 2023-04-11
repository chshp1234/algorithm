package com.study.algorithm.algorithms.algorithmsJAVA;

//由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
//
//如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I' 
//如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D' 
//给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
//
// 
//
//示例 1：
//
//输入：s = "IDID"
//输出：[0,4,1,3,2]
//示例 2：
//
//输入：s = "III"
//输出：[0,1,2,3]
//示例 3：
//
//输入：s = "DDI"
//输出：[3,2,0,1]
// 
//
//提示：
//
//1 <= s.length <= 105
//s 只包含字符 "I" 或 "D"
//
//来源：力扣（LeetCode）
//链接：https://leetcode.cn/problems/di-string-match
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 增减字符串匹配 {
    public int[] diStringMatch(String s) {
        //贪心
        //维护一个较大值top=length，和一个较小值bottom=0
        //当遇到‘I’时，另perm[i]=bottom，因为perm[i]<perm[i+1],当perm[i]=bottom时，数组右侧的任何值都会大于该元素值
        //同理，当遇到‘D’时，另perm[i]=top，这样，即可使得数组右侧的赋上任何值都会小于该元素的值
        int len = s.length();
        int[] result = new int[len + 1];
        int t = len;
        int b = 0;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'I') {
                result[i] = b++;
            } else {
                result[i] = t--;
            }
        }

        result[len] = t;

        return result;
    }
}
