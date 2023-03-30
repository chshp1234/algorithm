package com.study.algorithm.algorithms;

import java.util.ArrayList;
import java.util.List;

//给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
//
// 
//
//示例 1：
//
//输入：n = 2
//输出：["1/2"]
//解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
//示例 2：
//
//输入：n = 3
//输出：["1/2","1/3","2/3"]
//示例 3：
//
//输入：n = 4
//输出：["1/2","1/3","1/4","2/3","3/4"]
//解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
//示例 4：
//
//输入：n = 1
//输出：[]
// 
//
//提示：
//
//1 <= n <= 100
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/simplified-fractions
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class 最简分数 {
    public List<String> simplifiedFractions(int n) {
        //枚举
        //枚举每个分子分母
        //当分子和分母的最大公约数为1时，则为最简分数
        List<String> result = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (GCD(j, i) == 1) {
                    result.add(j + "/" + i);
                }
            }
        }
        return result;
    }

    //辗转相除法，求最大公约数
    public int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }
}
